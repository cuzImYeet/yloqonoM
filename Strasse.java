public class Strasse {
    String name;
    int Kaufpreis;
    int Häuser;
    int Miete;
    boolean gekauft;
    int Hauspreis;
    boolean istSpezialfeld;
    String spezialTyp;

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
        } else {
            System.out.println("Maximale Häuserzahl erreicht!");
        }
    }

    public void KaufeStrasse() {
        if (!gekauft) {
            System.out.println("Die Straße kann gekauft werden.");
            gekauft = true;
        } else {
            System.out.println("Die Straße gehört bereits jemandem.");
        }
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

    public void betreteFeld(Spieler spieler) {
    if (istSpezialfeld) {
        if (spezialTyp != null) {
            switch (spezialTyp.toUpperCase()) {
                case "GEFÄNGNIS":
                    spieler.insGefängnis();
                    break;
                case "FREIPARKEN":
                    System.out.println("Frei Parken! Keine Aktion.");
                    break;
                case "GEMEINSCHAFTSFELD":
                    System.out.println("Gemeinschaftsfeld! Ziehe eine Gemeinschaftskarte.");
                    // Hier könntest du eine Methode zum Ziehen einer Gemeinschaftskarte aufrufen
                    break;
                case "EREIGNISFELD":
                    System.out.println("Ereignisfeld! Ziehe eine Ereigniskarte.");
                    // Hier könntest du eine Methode zum Ziehen einer Ereigniskarte aufrufen
                    break;
                case "LOS":
                    spieler.Kapital += 200;
                    System.out.println("Du erhältst 200€ für das Überqueren von LOS!");
                    break;
                default:
                    System.out.println("Unbekanntes Spezialfeld: " + spezialTyp);
            }
        } else {
            System.out.println("Spezialfeld ohne Typ!");
        }
    } else {
        if (!gekauft) {
            System.out.println("Die Straße kann gekauft werden.");
            // Kauflogik hier einbauen
        } else {
            System.out.println("Die Straße gehört jemandem. Miete zahlen!");
            // Mietlogik hier einbauen
        }
    }
}

    
}
