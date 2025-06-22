import javax.swing.JOptionPane;

public class Spezialfeld extends Feld {
    public enum Typ {
        GEFÄNGNIS,
        FREIPARKEN,
        GEMEINSCHAFTSFELD,
        EREIGNISFELD,
        LOS,
        ZUSATZSTEUER,
        EINKOMMENSTEUER
    }
    private final Typ typ;

    public Spezialfeld(String name, Typ typ) {
        super(name);
        this.typ = typ;
    }

    @Override
    public void betreteFeld(Spieler spieler) {
        switch (typ) {
            case GEFÄNGNIS -> spieler.insGefängnis();
            case FREIPARKEN -> JOptionPane.showMessageDialog(null, "Frei Parken! Du kriegst den Jackpot!");
            case GEMEINSCHAFTSFELD -> {
                    JOptionPane.showMessageDialog(null, "Gemeinschaftsfeld! Ziehe eine Gemeinschaftskarte. (Es steht nichts drauf, denn wir hatten keine Zeit, die zu programmieren :|)");
                    // Hier könntest du eine Karte ziehen und anzeigen
                }
            case EREIGNISFELD -> {
                    JOptionPane.showMessageDialog(null, "Ereignisfeld! Ziehe eine Ereigniskarte. (Es steht nichts drauf, denn wir hatten keine Zeit, die zu programmieren :|)");
                    // Hier könntest du eine Karte ziehen und anzeigen
                }
            case LOS -> JOptionPane.showMessageDialog(null, "Du bist auf LOS!");
            case ZUSATZSTEUER -> {
                    JOptionPane.showMessageDialog(null, "Zusatzsteuer! Zahle 200€.");
                    if (spieler.bezahlen(200) == -1) {
                        JOptionPane.showMessageDialog(null, "Nicht genug Kapital, um die Zusatzsteuer zu zahlen!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Zusatzsteuer erfolgreich bezahlt.");
                    }
                }
            case EINKOMMENSTEUER -> {
                    JOptionPane.showMessageDialog(null, "Einkommensteuer! Zahle 100€.");
                    if (spieler.bezahlen(100) == -1) {
                        JOptionPane.showMessageDialog(null, "Nicht genug Kapital, um die Einkommensteuer zu zahlen!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Einkommensteuer erfolgreich bezahlt.");
                    }
                }
            default -> {
                    // nothing to do
                }
        }
    }

    public Typ getTyp() {
        return typ;
    }
}