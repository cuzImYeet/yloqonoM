public class Spezialfeld {
    private String name;
    private Typ typ;

    public enum Typ {
        GEFÄNGNIS,
        FREIPARKEN,
        GEMEINSCHAFTSFELD,
        EREIGNISFELD,
        LOS
        // weitere Typen nach Bedarf
    }

    public Spezialfeld(String name, Typ typ) {
        this.name = name;
        this.typ = typ;
    }

    public void aktionAusführen(Spieler spieler) {
        // Hier je nach Typ das Verhalten implementieren
        switch (typ) {
            case GEFÄNGNIS:
                // Spieler ins Gefängnis schicken
                break;
            case FREIPARKEN:
                // Nichts tun oder Geld geben
                break;
            case GEMEINSCHAFTSFELD:
                // Gemeinschaftskarte ziehen
                break;
            case EREIGNISFELD:
                // Ereigniskarte ziehen
                break;
            case LOS:
                // Geld auszahlen
                break;
        }
    }

    public String getName() {
        return name;
    }

    public Typ getTyp() {
        return typ;
    }
}
