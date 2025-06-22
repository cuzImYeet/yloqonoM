
public class Strasse extends Feld {
    int Kaufpreis;
    int Häuser;
    int Miete;
    boolean gekauft;
    int Hauspreis;
    Spieler besitzer;

    public Strasse(String na, int Ka, boolean ge) {
        super(na);
        Kaufpreis = Ka;
        Häuser = 0;
        Miete = (int)(Kaufpreis * 0.1);
        gekauft = ge;
        Hauspreis = (int)(Kaufpreis * 0.5);
    }

    public void kaufeHaus(Spieler spieler) {
        if (this.getBesitzer() != spieler) return; // Keine Meldung!
        if (this.Häuser >= 5) return;
        if (spieler.getKapital() < this.Hauspreis) return;
        this.Häuser++;
    }

    public void KaufeStrasse() {
        gekauft = true;
    }

    public int MieteNeu() {
        switch (Häuser) {
            case 0 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 1);
            case 1 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 3);
            case 2 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 5);
            case 3 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 10);
            case 4 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 15);
            case 5 -> Miete = (int) Math.ceil(Kaufpreis * 0.1 * 20);
            default -> System.out.println("Maximale Anzahl an Häusern erreicht!");
        }
        return Miete;} // Ende der Methode

    //NEU: Getter für Name
    @Override
    public String getName() {
        return name;
    }

    //NEU: Bestitzer setzen 
    public void setBesitzer(Spieler spieler) {
        this.besitzer = spieler;
    }

    //NEU: Besitzer abfragen
    public Spieler getBesitzer() {
        return besitzer;
    }

    @Override
    public void betreteFeld(Spieler spieler) {
        if (!gekauft) {
            System.out.println("Die Straße kann gekauft werden.");
        } else {
            if (besitzer != null && !besitzer.equals(spieler)) {
                System.out.println("Die Straße gehört " + besitzer.getName() + ". " + spieler.getName() + " zahlt Miete: " + Miete);
                spieler.bezahlen(Miete);
                besitzer.bezahlen(-Miete);
            } else if (besitzer != null && besitzer.equals(spieler)) {
                System.out.println("Willkommen auf deiner eigenen Straße, " + spieler.getName() + "!");
            } 
        }
    }

    public int getMiete() {
        // Stell sicher, dass Miete aktuell
        MieteNeu();
        return this.Miete;
    }
}
