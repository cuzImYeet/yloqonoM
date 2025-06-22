import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SpielManager {
    private final Spielfeld spielfeld;
    private final List<Spieler> spielerListe;

    private int aktuellerSpielerIndex = 0;
    private int freiParkenJackpot = 0; // NEU: Jackpot für "Frei Parken"-Feld

    public SpielManager(Spielfeld spielfeld, List<Spieler> spielerListe) {
        this.spielfeld = spielfeld;
        this.spielerListe = spielerListe;

    }
    // Versucht, eine Straße zu kaufen
    public void versucheStrasseZuKaufen(Spieler spieler, Strasse strasse) {
        if (strasse.getBesitzer() == null && spieler.getKapital() >= strasse.Kaufpreis) {
            spieler.bezahlen(strasse.Kaufpreis);
            spieler.aneignen(strasse);
            strasse.setBesitzer(spieler);
            System.out.println(spieler.getName() + " hat " + strasse.getName() + " gekauft!");
        } else if (strasse.getBesitzer() != null) {
            System.out.println("Diese Straße gehört bereits " + strasse.getBesitzer().getName() + "!");
        } else {
            System.out.println("Nicht genug getKapital() für den Kauf!");
        }
    }

    // Führt Spielzug für den aktuellen Spieler aus
    public void fuehreSpielzugAus(JFrame parentFrame) {
        Spieler spieler = spielerListe.get(aktuellerSpielerIndex);

        // Gefängnis-Logik
        if (spieler.isImGefängnis()) {
            int wurf1 = (int)(Math.random() * 6 + 1);
            int wurf2 = (int)(Math.random() * 6 + 1);
            JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " würfelt im Gefängnis: " + wurf1 + " und " + wurf2);

            if (wurf1 == wurf2) {
                spieler.verlasseGefaengnis();
                JOptionPane.showMessageDialog(parentFrame, "Pasch! " + spieler.getName() + " darf das Gefängnis verlassen und zieht " + (wurf1 + wurf2) + " Felder.");
                // Spieler darf sofort ziehen
                int wurf = wurf1 + wurf2;
                boolean ueberLos = spielfeld.bewegeSpieler(aktuellerSpielerIndex, wurf);
                JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " zieht " + wurf + " Felder.");
                if (ueberLos) {
                    spieler.erhalten(200);
                    JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " erhält 200€ für das Überqueren von LOS!");
                }
                Feld feld = spielfeld.getFeldVonSpieler(aktuellerSpielerIndex);
                bearbeiteFeld(parentFrame, spieler, feld);
            } else {
                spieler.erhoeheGefaengnisRunden();
                if (!spieler.isImGefängnis()) {
                    JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " hat 3 Runden abgesessen und ist wieder frei und zieht jetzt.");
                    // Spieler darf jetzt mit neuem Wurf ziehen
                    int wurf = (int)(Math.random() * 6 + 1) + (int)(Math.random() * 6 + 1);
                    boolean ueberLos = spielfeld.bewegeSpieler(aktuellerSpielerIndex, wurf);
                    JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " zieht " + wurf + " Felder.");
                    if (ueberLos) {
                        spieler.erhalten(200);
                        JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " erhält 200€ für das Überqueren von LOS!");
                    }
                    Feld feld = spielfeld.getFeldVonSpieler(aktuellerSpielerIndex);
                    bearbeiteFeld(parentFrame, spieler, feld);
                    return;
                } else {
                    JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " muss aussetzen (" + spieler.getGefaengnisRunden() + "/3).");
                    return;
                }
            }
        }

        // Normale Zuglogik (wie bisher)
        int wurf = spielfeld.würfeln();
        boolean ueberLos = spielfeld.bewegeSpieler(aktuellerSpielerIndex, wurf);
        JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " würfelt eine " + wurf + ".");

        if (ueberLos) {
            spieler.erhalten(200);
            JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " erhält 200€ für das Überqueren von LOS!");
        }

        Feld feld = spielfeld.getFeldVonSpieler(aktuellerSpielerIndex);
        bearbeiteFeld(parentFrame, spieler, feld);

        long aktiveSpieler = spielerListe.stream().filter(s -> !s.isBankrott()).count();
        if (aktiveSpieler == 1) {
            Spieler gewinner = spielerListe.stream().filter(s -> !s.isBankrott()).findFirst().orElse(null);
            JOptionPane.showMessageDialog(parentFrame, "Spiel beendet! Gewinner: " + (gewinner != null ? gewinner.getName() : "Unbekannt"));
            System.exit(0);
        }
    }

    // Hilfsmethode für Feld-Logik
    private void bearbeiteFeld(JFrame parentFrame, Spieler spieler, Feld feld) {
        switch (feld) {
            case Strasse strasse -> {
                if (strasse.getBesitzer() == null) {
                    // Straße kaufen anbieten
                    Object[] options = {"Straße kaufen", "Zug beenden"};
                    int wahl = JOptionPane.showOptionDialog(
                            parentFrame,
                            spieler.getName() + ", möchtest du " + strasse.getName() + " für " + strasse.Kaufpreis + "€ kaufen?",
                            "Aktion wählen",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                        );
                    if (wahl == 0 && spieler.getKapital() >= strasse.Kaufpreis) {
                        spieler.bezahlen(strasse.Kaufpreis);
                        spieler.aneignen(strasse);
                        strasse.setBesitzer(spieler);
                        JOptionPane.showMessageDialog(parentFrame, "Du hast die Straße gekauft!\nKapital: " + spieler.getKapital() + "€");
                    } else if (wahl == 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Nicht genug Kapital!");
                    }
                } else if (strasse.getBesitzer() == spieler) {
                    // Haus kaufen anbieten
                    Object[] options = {"Haus kaufen", "Zug beenden"};
                    int wahl = JOptionPane.showOptionDialog(
                            parentFrame,
                            "Du besitzt diese Straße. Möchtest du ein Haus kaufen für " + strasse.Hauspreis + "€?",
                            "Aktion wählen",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]
                        );
                    if (wahl == 0 && spieler.getKapital() >= strasse.Hauspreis && strasse.Häuser < 5) {
                        if (spieler.bezahlen(strasse.Hauspreis) != -1) {
                            strasse.kaufeHaus(spieler);
                            JOptionPane.showMessageDialog(parentFrame, "Du hast ein Haus gebaut!\nKapital: " + spieler.getKapital() + "€");
                        }
                    } else if (wahl == 0) {
                        JOptionPane.showMessageDialog(parentFrame, "Nicht genug Kapital oder maximale Häuserzahl erreicht!");
                    }
                } else {
                    // Miete zahlen
                    int miete = strasse.getMiete();
                    if (spieler.bezahlen(miete) != -1) {
                        strasse.getBesitzer().erhalten(miete);
                        JOptionPane.showMessageDialog(parentFrame,
                            "Diese Straße gehört " + strasse.getBesitzer().getName() + ".\nDu musst " + miete + "€ Miete zahlen!\n" +
                            "Dein Kapital: " + spieler.getKapital() + "€");
                    } else {
                        // Spieler kann nicht zahlen → bankrott
                        JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " ist bankrott und scheidet aus!");
                        spieler.setBankrott(true);
                        // Alle Straßen freigeben
                        for (Strasse s : spieler.getStrassen()) {
                            if (s != null && s.getBesitzer() == spieler) {
                                s.setBesitzer(null);
                                s.gekauft = false;
                                s.Häuser = 0;
                            }
                        }
                    }
                }
            }
            case Spezialfeld spezialfeld -> {
                switch (spezialfeld.getTyp()) {
                    case EINKOMMENSTEUER, ZUSATZSTEUER -> {
                        int steuer = 100; // oder ein anderer Betrag
                        if (spieler.bezahlen(steuer) != -1) {
                            freiParkenJackpot += steuer;
                            JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " zahlt " + steuer + "€ Steuer. Jackpot auf Freiparken: " + freiParkenJackpot + "€");
                        } else {
                            // Spieler kann nicht zahlen → bankrott
                            JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " ist bankrott und scheidet aus!");
                            spieler.setBankrott(true);
                            // Straßen freigeben usw.
                        }
                    }
                    case FREIPARKEN -> {
                        if (freiParkenJackpot > 0) {
                            spieler.erhalten(freiParkenJackpot);
                            JOptionPane.showMessageDialog(parentFrame, spieler.getName() + " erhält " + freiParkenJackpot + "€ vom Freiparken-Jackpot!");
                            freiParkenJackpot = 0;
                        } else {
                            JOptionPane.showMessageDialog(parentFrame, "Freiparken: Kein Jackpot vorhanden.");
                        }
                    }
                    default -> feld.betreteFeld(spieler); // andere Spezialfelder wie gehabt
                }
            }
            default -> feld.betreteFeld(spieler);
        }
    }

    // NEU: alles bis nächste //
    public Spieler getAktuellerSpieler() {
        return spielerListe.get(aktuellerSpielerIndex);
    }

    public void kaufeHaus(Spieler spieler, Strasse strasse) {
        // Prüfen ob Spieler Besitzer ist
        if (strasse.getBesitzer() != spieler) {
            System.out.println("Du besitzt diese Straße nicht!");
            return;
        }
        // Prüfen ob Häuser gebaut werden können
        if (strasse.Häuser >= 5) {
            System.out.println("Maximale Häuserzahl erreicht!");
            return;
        }
        // Prüfen ob genug Kapital vorhanden
        if (spieler.getKapital() < strasse.Hauspreis) {
            System.out.println("Nicht genug getKapital() für ein Haus!");
            return;
        }
        // Haus kaufen
        if (spieler.bezahlen(strasse.Hauspreis) != -1) {
            strasse.kaufeHaus(spieler);
            System.out.println(spieler.getName() + " hat ein Haus auf " + strasse.getName() + " gebaut!");
        } else {
            System.out.println("Zahlung fehlgeschlagen! Kein Haus gebaut.");
        }
    }

    // bis hier
    public List<Spieler> getSpielerListe() {
        return spielerListe;
    }

    public int getAktuellerSpielerIndex() {
        return aktuellerSpielerIndex;
    }

    public int[] getSpielerPositionen() {
        return spielfeld.getAlleSpielerPositionen();
    }

    public void setAktuellerSpielerIndex(int index) {
        this.aktuellerSpielerIndex = index;
    }

    public int getFreiParkenJackpot() {
        return freiParkenJackpot;
    }
}