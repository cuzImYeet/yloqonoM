//Autor Diyar Karatas, Co-Autor Florian Grabichler
import java.awt.*;
import javax.swing.*;

public class Oberflaeche
{
    private JFrame infoFenster;
    private JLabel infoLabel;
    private SpielManager spielManager;

    private void zeigeInfoFenster(Spieler[] spielerListe) {
        if (infoFenster == null) {
            infoFenster = new JFrame("Spieler-Infos");
            infoLabel = new JLabel();
            infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            infoFenster.add(new JScrollPane(infoLabel));
            infoFenster.setSize(400, 400);
            infoFenster.setLocation(1050, 100);
            infoFenster.setVisible(true);
        }
        aktualisiereInfoFenster(spielerListe);
    }

    private void aktualisiereInfoFenster(Spieler[] spielerListe) {
        StringBuilder sb = new StringBuilder("<html>");
        for (Spieler s : spielerListe) {
            sb.append("<b>").append(s.getName()).append("</b><br>");
            sb.append("Kapital: ").append(s.getKapital()).append("<br>");
            sb.append("Straßen: ");
            if (s.getStrassen().length == 0) {
                sb.append("keine");
            } else {
                for (Strasse str : s.getStrassen()) {
                    if (str != null) {
                        sb.append(str.getName()).append(", ");
                    }
                }
                sb.setLength(sb.length() - 2); // letztes Komma entfernen
            }
            sb.append("<br><br>");
        }
        sb.append("<b>Freiparken-Jackpot: ").append(spielManager.getFreiParkenJackpot()).append("€</b><br>");
        sb.append("</html>");
        infoLabel.setText(sb.toString());
    }

    private JButton wuerfelnButton;
    private JButton hausKaufenButton;
    private JButton strasseKaufenButton;
    private JButton zugBeendenButton;
    private JButton spielBeendenButton;
    private java.util.Map<Integer, JPanel> feldPanels = new java.util.HashMap<>();
    private int spielerAnzahl = 2; // Standardwert
    private String[] spielerNamen;
    private JLabel aktuellerSpielerLabel;

    public Oberflaeche() 
    {
        // Spieleranzahl abfragen
        String eingabe = JOptionPane.showInputDialog(
                null,
                "Mit wie vielen Personen möchtest du spielen? (2-4)",
                "Spieleranzahl wählen",
                JOptionPane.QUESTION_MESSAGE
            );
        try {
            int anzahl = Integer.parseInt(eingabe);
            if (anzahl >= 2 && anzahl <= 4) {
                spielerAnzahl = anzahl;
            } else {
                JOptionPane.showMessageDialog(null, "Bitte gib eine Zahl zwischen 2 und 4 ein. Es werden 2 Spieler verwendet.");
            }
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Ungültige Eingabe. Es werden 2 Spieler verwendet.");
        }

        spielerNamen = new String[spielerAnzahl];
        for (int i = 0; i < spielerAnzahl; i++) {
            String name = JOptionPane.showInputDialog(
                    null,
                    "Name für Spieler " + (i + 1) + ":",
                    "Spielername eingeben",
                    JOptionPane.QUESTION_MESSAGE
                );
            if (name == null || name.trim().isEmpty()) {
                name = "Spieler " + (i + 1);
            }
            spielerNamen[i] = name;
        }

        //Startoberfläche
        JFrame fenster1 = new JFrame("Startbildschirm");
        fenster1.setBounds(300, 300, 500, 400);

        //Schriftzug
        JPanel hauptPanel = new JPanel(new BorderLayout());

        //Erzeugt den Namen des Spiels im Hauptfenster
        JLabel label1 = new JLabel("yloqonoM", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 70));
        hauptPanel.add(label1, BorderLayout.NORTH);

        //Knöpfe
        JPanel zentralesPanel = new JPanel(new GridBagLayout());

        JPanel start = new JPanel();
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));

        JButton knopf1 = new JButton("Starten");
        knopf1.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf2 = new JButton("Optionen");
        knopf2.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf3 = new JButton("Beenden");
        knopf3.setFont(new Font("Arial", Font.BOLD, 50));

        knopf3.addActionListener
        (
            evt2 -> System.exit(0)
        );

        start.add(knopf1);
        start.add(Box.createVerticalStrut(20));
        start.add(knopf2);
        start.add(Box.createVerticalStrut(20));
        start.add(knopf3);

        zentralesPanel.add(start, new GridBagConstraints());

        hauptPanel.add(zentralesPanel, BorderLayout.CENTER);

        fenster1.setContentPane(hauptPanel);
        fenster1.setVisible(true);

        //Optionen

        JFrame fenster2 = new JFrame("Optionen");
        fenster2.setBounds(300, 300, 850, 150);

        // JPanel hauptPanelOptionen = new JPanel(new BorderLayout());

        JPanel nebenPanel1 = new JPanel(new BorderLayout());

        JPanel optionen = new JPanel();
        optionen.setLayout(new BoxLayout(optionen, BoxLayout.X_AXIS));
        nebenPanel1.add(optionen, BorderLayout.NORTH);

        JButton knopf4 = new JButton("Steuerung");
        knopf4.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf5 = new JButton("Spielregeln");
        knopf5.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf6 = new JButton("Zurück");
        knopf6.setFont(new Font("Arial", Font.BOLD, 50));

        knopf2.addActionListener(e -> fenster2.setVisible(true));
        knopf6.addActionListener(e -> fenster2.setVisible(false));
        optionen.add(knopf4);
        optionen.add(Box.createVerticalStrut(10));
        optionen.add(knopf5);
        optionen.add(Box.createVerticalStrut(10));
        optionen.add(knopf6);

        fenster2.setContentPane(nebenPanel1);
        fenster2.setVisible(false);

        //Spielregeln
        JFrame fenster4 = new JFrame("Spielregeln");
        fenster4.setBounds(300, 300, 500, 700);

        JButton knopf7 = new JButton("Zurück");
        knopf7.setFont(new Font("Arial", Font.BOLD, 50));
        JLabel Regeln = new JLabel("<html> 1.Spielrichtung <br> Das Spiel verläuft im Uhrzeigersinn mit Rückwärts verlaufenden Feldern. <br> <br> 2.Spielstart <br> Jeder Spieler startet auf dem Startfeld ('Los') <br> <br> 3.Spielzug <br> Spieler würfeln zu Beginn ihres Zuges. <br> Die Spielfigur wird entsprechend der Augenzahl im Uhrzeigersinn bewegt. <br> Das betretene Feld wird ausgeführt (z.B. Grundstück kaufen, Miete zahlen, Ereignis ziehen).<br> <br> Eigentum & Miete<br> Freie Grundstücke können gekauft werden.<br> Bei Betreten eines fremden Grundstücks muss Miete gezahlt werden.<br> Zahlungsunfähigkeit kann zum Spielausscheiden führen.<br> <br> 4.Sonderfelder<br> Schule (Gefängnis):<br> Spieler landen durch Ereignisse oder Felder in der Schule.<br> Dort müssen sie drei Runde aussetzen oder einen Pasch (zweimal die gleiche Zahl) würfeln um im nächsten Zug weiterzuspielen.<br> <br> Ereignisfelder: Spieler ziehen eine zufällige Ereigniskarte mit positiven oder negativen Effekten.<br> <br> 'Gehe zur Schule': Spieler werden direkt in die Schule versetzt. <br> <br> Ziel des Spiels<br> Ziel ist es, möglichst viel Geld und Besitz anzuhäufen.<br> Das Spiel endet wenn nur noch ein Spieler zahlungsfähig ist.<br> <br> 6.Zusätzliche Regeln<br> Es gibt nur Häuser aber keine Hotels.<br> Beim Passieren von 'Los' erhält der Spieler 200€.<br> </html>");

        knopf5.addActionListener(e -> fenster4.setVisible(true));
        knopf7.addActionListener(e -> fenster4.setVisible(false));

        Container zeichenflaeche1 = fenster4.getContentPane();
        //fenster4.setBounds(300,300,500,200);

        zeichenflaeche1.add(Regeln, "North");
        zeichenflaeche1.add(knopf7, "South");

        // Start-Knopf ActionListener
        knopf1.addActionListener(e -> {
                    fenster1.setVisible(false); // Startfenster ausblenden

                    Spielfeld Feld = new Spielfeld(spielerAnzahl, spielerNamen);
                    spielManager = new SpielManager(Feld, java.util.Arrays.asList(Feld.getSpielerArray()));

                    JFrame fenster3 = new JFrame("Spielfeld");
                    fenster3.setBounds(200, 100, 800, 800);
                    fenster3.setMinimumSize(new Dimension(1200, 700));
                    JPanel spielPanel = new JPanel();
                    int size = 11;
                    JPanel gridPanel = new JPanel(new GridLayout(size, size));

                    JPanel zentrum = new JPanel();
                    zentrum.setOpaque(false);
                    zentrum.setLayout(new GridBagLayout());
                    JLabel zentrenLabel = new JLabel("yloqonoM");
                    zentrenLabel.setFont(new Font("Arial", Font.BOLD, 80));
                    zentrenLabel.setForeground(Color.BLACK);
                    zentrum.add(zentrenLabel);

                    // OverlayLayout für zentrum und gridPanel
                    spielPanel.setLayout(new OverlayLayout(spielPanel));
                    spielPanel.add(zentrum);
                    spielPanel.add(gridPanel);

                    for (int y = 0; y < size; y++) {
                        for (int x = 0; x < size; x++) {
                            JPanel feld = new JPanel();
                            feld.setPreferredSize(new Dimension(60, 60));
                            feld.setLayout(new BorderLayout());

                            boolean istRand = (y == 0 || y == size - 1 || x == 0 || x == size - 1);

                            if (istRand) {
                                int index = getSpielfeldIndex(x, y, size);
                                if (index >= 0 && index < 40) {
                                    Feld spielfeldFeld = Feld.getFeld(index);
                                    String name = spielfeldFeld.getName();
                                    name = name.replace(" / ", "<br>/ ");
                                    if (name.length() > 14 && !name.contains("<br>")) {
                                        name = name.replaceFirst(" ", "<br>");
                                    }
                                    JLabel beschriftung = new JLabel("<html><center>" + name + "</center></html>");
                                    beschriftung.setFont(new Font("Arial", Font.PLAIN, 9));
                                    beschriftung.setHorizontalAlignment(SwingConstants.CENTER);
                                    feld.add(beschriftung, BorderLayout.CENTER);
                                    feldPanels.put(index, feld);

                                    // HIER: Farbe setzen!
                                    feld.setBackground(getFeldFarbe(spielfeldFeld, index));
                                } else {
                                    feld.setBackground(Color.GRAY);
                                }
                            } else {
                                feld.setBackground(Color.WHITE);
                            }
                            gridPanel.add(feld);
                        }
                    }

                    // Beispiel-Button für Aktionen
                    wuerfelnButton = new JButton("Würfeln");
                    wuerfelnButton.setFont(new Font("Arial", Font.BOLD, 30));
                    hausKaufenButton = new JButton("Haus kaufen");
                    hausKaufenButton.setFont(new Font("Arial", Font.BOLD, 30));
                    strasseKaufenButton = new JButton("Straße kaufen");
                    strasseKaufenButton.setFont(new Font("Arial", Font.BOLD, 30));
                    zugBeendenButton = new JButton("Zug beenden");
                    zugBeendenButton.setFont(new Font("Arial", Font.BOLD, 30));
                    spielBeendenButton = new JButton("Spiel beenden");
                    spielBeendenButton.setFont(new Font("Arial", Font.BOLD, 30));

                    // ActionListener für die Buttons
                    wuerfelnButton.addActionListener(
                        e2 -> {
                                spielManager.fuehreSpielzugAus(fenster3);
                                aktuellerSpielerLabel.setText("Aktueller Spieler: " + spielerNamen[spielManager.getAktuellerSpielerIndex()]);
                                zeigeInfoFenster(Feld.getSpielerArray());
                                aktualisiereSpielerAnzeige(Feld);
                                wuerfelnButton.setEnabled(false); // Nach dem Würfeln deaktivieren
                        }
                    );

                    hausKaufenButton.addActionListener(
                        evt -> {
                                int aktuellerSpieler = Feld.getAktuellerSpieler();
                                int pos = Feld.getSpielerPosition(aktuellerSpieler);
                                Feld aktuellesFeld = Feld.getFeld(pos);

                                if (aktuellesFeld instanceof Strasse strasse) {
                                    Spieler spieler = Feld.getSpielerArray()[aktuellerSpieler];
                                    strasse.kaufeHaus(spieler);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hier kann kein Haus gebaut werden!");
                                }
                        }
                    );

                    strasseKaufenButton.addActionListener(
                        e1 -> {
                                int aktuellerSpieler = Feld.getAktuellerSpieler();
                                int pos = Feld.getSpielerPosition(aktuellerSpieler);
                                Feld aktuellesFeld = Feld.getFeld(pos);

                                if (aktuellesFeld instanceof Strasse strasse) {
                                    Spieler spieler = Feld.getSpielerArray()[aktuellerSpieler];
                                    if (!strasse.gekauft) {
                                        if (spieler.getKapital() >= strasse.Kaufpreis) {
                                            strasse.gekauft = true;
                                            strasse.setBesitzer(spieler);
                                            spieler.bezahlen(strasse.Kaufpreis);
                                            JOptionPane.showMessageDialog(null, "Straße gekauft!");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Nicht genug Kapital, um die Straße zu kaufen!");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Diese Straße ist bereits gekauft!");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Hier kann keine Straße gekauft werden!");
                                }
                        }
                    );
                    zugBeendenButton.addActionListener(
                        evt -> {
                                // Spielerwechsel: nächster nicht-bankrotter Spieler
                                do {
                                    int next = (spielManager.getAktuellerSpielerIndex() + 1) % spielManager.getSpielerListe().size();
                                    spielManager.setAktuellerSpielerIndex(next);
                                } while (spielManager.getSpielerListe().get(spielManager.getAktuellerSpielerIndex()).isBankrott());
                                // Anzeige aktualisieren
                                aktuellerSpielerLabel.setText("Aktueller Spieler: " + spielerNamen[spielManager.getAktuellerSpielerIndex()]);
                                zeigeInfoFenster(Feld.getSpielerArray());
                                aktualisiereSpielerAnzeige(Feld);
                                wuerfelnButton.setEnabled(true); // Für den nächsten Spieler wieder aktivieren

                                // Spielende prüfen
                                long aktiveSpieler = spielManager.getSpielerListe().stream().filter(s -> !s.isBankrott()).count();
                                if (aktiveSpieler == 1) {
                                    Spieler gewinner = spielManager.getSpielerListe().stream().filter(s -> !s.isBankrott()).findFirst().orElse(null);
                                    JOptionPane.showMessageDialog(fenster3, "Spiel beendet! Gewinner: " + (gewinner != null ? gewinner.getName() : "Unbekannt"));
                                    System.exit(0);
                                }
                        }
                    );

                    spielBeendenButton.addActionListener(
                        evt3 -> System.exit(0)
                    );

                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

                    JPanel buttonRow = new JPanel();
                    buttonRow.setLayout(new BoxLayout(buttonRow, BoxLayout.X_AXIS));
                    buttonRow.add(Box.createHorizontalGlue());
                    buttonRow.add(wuerfelnButton);
                    buttonRow.add(Box.createHorizontalStrut(15));
                    buttonRow.add(hausKaufenButton);
                    buttonRow.add(Box.createHorizontalStrut(15));
                    buttonRow.add(strasseKaufenButton);
                    buttonRow.add(Box.createHorizontalStrut(15));
                    buttonRow.add(zugBeendenButton);
                    buttonRow.add(Box.createHorizontalStrut(15));
                    buttonRow.add(spielBeendenButton);
                    buttonRow.add(Box.createHorizontalGlue());
                    buttonPanel.add(Box.createVerticalStrut(15));
                    buttonPanel.add(buttonRow);

                    JPanel spielfeldOverlay = new JPanel();
                    spielfeldOverlay.setLayout(new OverlayLayout(spielfeldOverlay));
                    spielfeldOverlay.add(zentrum);
                    spielfeldOverlay.add(gridPanel);

                    JPanel hauptPanel3 = new JPanel(new BorderLayout());
                    aktuellerSpielerLabel = new JLabel("Aktueller Spieler: " + spielerNamen[0], SwingConstants.CENTER);
                    aktuellerSpielerLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    hauptPanel3.add(aktuellerSpielerLabel, BorderLayout.NORTH);
                    hauptPanel3.add(spielfeldOverlay, BorderLayout.CENTER);
                    hauptPanel3.add(buttonPanel, BorderLayout.SOUTH);

                    fenster3.setContentPane(hauptPanel3);
                    fenster3.setVisible(true);
                    zeigeInfoFenster(Feld.getSpielerArray());
                    aktualisiereSpielerAnzeige(Feld); // <-- HIER!
            });

    }

    private int getSpielfeldIndex(int x, int y, int size) {
        if (x == 0 && y == 0) return 0;
        if (x == size - 1 && y == 0) return 10;
        if (x == size - 1 && y == size - 1) return 20;
        if (x == 0 && y == size - 1) return 30;
        if (y == 0 && x > 0 && x < size - 1) return x;
        if (x == size - 1 && y > 0 && y < size - 1) return 10 + y;
        if (y == size - 1 && x > 0 && x < size - 1) return 30 - x;
        if (x == 0 && y > 0 && y < size - 1) return 30 + (size - 1 - y);
        return -1;
    }

    private static final Color[] STRASSEN_FARBEN = {
            null, new Color(30, 59, 138), null, new Color(30, 59, 138), null, Color.BLACK,
            new Color(25, 142, 14), null, new Color(25, 142, 14), new Color(25, 142, 14), null,
            new Color(255, 255, 25), new Color(102, 102, 111), new Color(255, 255, 25), new Color(255, 255, 25), Color.BLACK,
            new Color(255, 42, 40), new Color(255, 42, 40), null, new Color(255, 42, 40), null,
            new Color(255, 165, 0), new Color(255, 165, 0), null, new Color(255, 165, 0), Color.BLACK,
            new Color(255, 94, 245), new Color(255, 94, 245), new Color(102, 102, 111), new Color(255, 94, 245), null,
            new Color(16, 183, 255), new Color(16, 183, 255), null, new Color(16, 183, 255), Color.BLACK,
            null, new Color(138, 80, 54), null, new Color(138, 80, 54)
        };

    private Color getFeldFarbe(Feld feld, int index) {
        if (feld instanceof Strasse) {
            if (index < STRASSEN_FARBEN.length && STRASSEN_FARBEN[index] != null) {
                return STRASSEN_FARBEN[index];
            }
            return new Color(255, 255, 200);
        } else if (feld instanceof Spezialfeld sf) {
            return switch (sf.getTyp()) {
                case LOS, GEFÄNGNIS, FREIPARKEN, GEMEINSCHAFTSFELD, EREIGNISFELD, ZUSATZSTEUER, EINKOMMENSTEUER -> Color.WHITE;
                default -> Color.WHITE;
            };
        }
        return Color.LIGHT_GRAY;
    }

    /**
     * Zeigt die Spieler auf dem Spielfeld an.
     * Diese Methode sollte nach jedem Zug aufgerufen werden!
     */
    private void aktualisiereSpielerAnzeige(Spielfeld Feld) {
        // Zuerst alle alten Spieler-Labels entfernen
        for (JPanel panel : feldPanels.values()) {
            // Entferne alle Labels, die als Spieler-Label markiert sind
            Component[] comps = panel.getComponents();
            for (Component c : comps) {
                if (c instanceof JLabel label && "spieler".equals(label.getName())) {
                    panel.remove(label);
                }
            }
            panel.revalidate();
            panel.repaint();
        }

        // Jetzt für jeden Spieler das Label auf das richtige Feld setzen
        Spieler[] spielerArray = Feld.getSpielerArray();
        Color[] spielerFarben = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA}; // bis zu 4 Spieler
        for (int i = 0; i < spielerArray.length; i++) {
            int pos = spielerArray[i].getPosition();
            JPanel feldPanel = feldPanels.get(pos);
            if (feldPanel != null) {
                JLabel spielerLabel = new OutlinedLabel("▲", 36, spielerFarben[i % spielerFarben.length], Color.BLACK);
                feldPanel.add(spielerLabel, BorderLayout.SOUTH);
                feldPanel.revalidate();
                feldPanel.repaint();
            }
        }
    }

    public static void main(String[] args) {
        new Oberflaeche();
    }
}
class OutlinedLabel extends JLabel {
    private final Color outlineColor;
    private final Color fillColor;
    public OutlinedLabel(String text, int fontSize, Color fillColor, Color outlineColor) {
        super(text, SwingConstants.CENTER);
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
        setFont(new Font("Arial", Font.BOLD, fontSize));
        setOpaque(false);
        setName("spieler");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(getFont());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;

        // Outline (schwarz, dicker)
        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(4f));
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    g2.drawString(text, x + dx, y + dy);
                }
            }
        }

        // Füllung (Spielerfarbe)
        g2.setColor(fillColor);
        g2.drawString(text, x, y);

        g2.dispose();
    }
}