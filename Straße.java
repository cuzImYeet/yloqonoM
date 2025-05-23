public class Strasse {
    String name;
    int Kaufpreis;
    int Häuser;
    int Miete;
    boolean gekauft;
    int Hauspreis;

    public Strasse(String na, int Ka, boolean ge) {
        name = na;
        Kaufpreis = Ka;
        Häuser = 0;
        Miete = (int)(Kaufpreis * 0.1);
        gekauft = ge;
        Hauspreis = (int)(Kaufpreis * 0.5); // z.B. 50 % des Kaufpreises
    }

    public void KaufeHaus() {
        if (Häuser <= 5 && gekauft) {
            Häuser++;
            MieteNeu();
        } else if (!gekauft) {
            System.out.println("Strasse nicht gekauft!");
        } else {
            System.out.println("Maximale Häuserzahl erreicht!");
        }
    }

    public void KaufeStrasse() {
        gekauft = true;
    }

   public int MieteNeu() {
    if (Häuser == 0) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 1);
    } else if (Häuser == 1) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 3);
    } else if (Häuser == 2) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 5);
    } else if (Häuser == 3) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 10);
    } else if (Häuser == 4) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 15);
    } else if (Häuser == 5) {
        Miete = (int) Math.ceil(Kaufpreis * 0.1 * 20);
    } else {
        System.out.println("Maximale Anzahl an Häusern erreicht!");
    }
    return Miete;} // Ende der Methode

}
