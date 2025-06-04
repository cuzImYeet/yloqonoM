import java.util.List;

public class SpielManager {
    private Spielfeld spielfeld;
    private List<Spieler> spielerListe;
    private int aktuellerSpielerIndex = 0;

    public SpielManager(Spielfeld spielfeld, List<Spieler> spielerListe) {
        this.spielfeld = spielfeld;
        this.spielerListe = spielerListe;
    }

    // Versucht, eine Straße zu kaufen
    public void versucheStrasseZuKaufen(Spieler spieler, Strasse strasse) {
        if (strasse.getBesitzer() == null && spieler.Kapital >= strasse.Kaufpreis) {
            spieler.bezahlen(strasse.Kaufpreis);
            spieler.aneignen(strasse);
            strasse.setBesitzer(spieler);
            System.out.println(spieler.getName() + " hat " + strasse.getName() + " gekauft!");
        } else if (strasse.getBesitzer() != null) {
            System.out.println("Diese Straße gehört bereits " + strasse.getBesitzer().getName() + "!");
        } else {
            System.out.println("Nicht genug Kapital für den Kauf!");
        }
    }

    // Führt einen Spielzug für den aktuellen Spieler aus
    public void fuehreSpielzugAus() {
        Spieler spieler = spielerListe.get(aktuellerSpielerIndex);
        int wurf = spielfeld.würfeln();
        System.out.println(spieler.getName() + " würfelt eine " + wurf + ".");
        boolean ueberLos = spielfeld.bewegeSpieler(aktuellerSpielerIndex, wurf);
        if (ueberLos) {
            spieler.Kapital += 200; // Beispiel: 200€ für Überqueren von Los
            System.out.println(spieler.getName() + " erhält 200€ für das Überqueren von LOS!");
        }
        Strasse feld = spielfeld.getFeldVonSpieler(aktuellerSpielerIndex);
        if (feld != null && feld.Kaufpreis > 0) {
            if (feld.getBesitzer() == null) {
                versucheStrasseZuKaufen(spieler, feld);
            } else if (feld.getBesitzer() != spieler) {
                int miete = feld.Miete;
                if (spieler.bezahlen(miete) != -1) {
                    feld.getBesitzer().Kapital += miete;
                    System.out.println(spieler.getName() + " zahlt " + miete + "€ Miete an " + feld.getBesitzer().getName());
                } else {
                    System.out.println(spieler.getName() + " kann die Miete nicht zahlen!");
                }
            }
        }
        aktuellerSpielerIndex = (aktuellerSpielerIndex + 1) % spielerListe.size();
    }


    // NEU: alles bis nächste //
    public Spieler getAktuellerSpieler() {
        return spielerListe.get(aktuellerSpielerIndex);
    }
        public void kaufeHaus(Spieler spieler, Strasse strasse) {
        // Prüfen, ob Spieler Besitzer ist
        if (strasse.getBesitzer() != spieler) {
            System.out.println("Du besitzt diese Straße nicht!");
            return;
        }
        // Prüfen, ob noch Häuser gebaut werden können
        if (strasse.Häuser >= 5) {
            System.out.println("Maximale Häuserzahl erreicht!");
            return;
        }
        // Prüfen, ob genug Kapital vorhanden ist
        if (spieler.Kapital < strasse.Hauspreis) {
            System.out.println("Nicht genug Kapital für ein Haus!");
            return;
        }
        // Haus kaufen
        spieler.bezahlen(strasse.Hauspreis);
        strasse.KaufeHaus();
        System.out.println(spieler.getName() + " hat ein Haus auf " + strasse.getName() + " gebaut!");
    }

// bis hier
}