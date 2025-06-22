import java.util.ArrayList;
import java.util.List;

public class Spielfeld {
    private final Feld[] spielfeld = new Feld[40]; // Array für alle Felder (Straßen, Ereignis, etc.)
    private final int[] spielerPositionen;  // Array für die Positionen der Spieler
    private int aktuellerSpieler = 0; // Index des aktuellen Spielers
    private final Spieler[] spielerArray;   // Array für die Spieler
    private final List<Spieler> spielerListe; // Liste für die Spieler

    // Konstruktor mit Spieleranzahl und Namen (für neue Spiele)
    public Spielfeld(int spielerAnzahl, String[] spielerNamen) {
        spielerPositionen = new int[spielerAnzahl];
        spielerArray = new Spieler[spielerAnzahl];
        spielerListe = new ArrayList<>();
        for (int i = 0; i < spielerAnzahl; i++) {
            spielerPositionen[i] = 0;
            spielerArray[i] = new Spieler(spielerNamen[i], this);
            spielerArray[i].setPosition(0);
            spielerListe.add(spielerArray[i]);
        }
        initialisiereFelder();
    }

    //Konstruktor für Kompatibilität (z.B. mit bestehender Spieler-Liste)
    public Spielfeld(List<Spieler> spielerListe) {
        this.spielerListe = spielerListe;
        spielerPositionen = new int[spielerListe.size()];
        spielerArray = new Spieler[spielerListe.size()];
        for (int i = 0; i < spielerListe.size(); i++) {
            spielerPositionen[i] = 0;
            spielerListe.get(i).setPosition(0);
            spielerArray[i] = spielerListe.get(i);
        }
        initialisiereFelder();
    }

    // Initialisiert Spielfeld mit allen Feldern/Straßen
    private void initialisiereFelder() {
        spielfeld[0] = new Spezialfeld("Los-Feld", Spezialfeld.Typ.LOS);
        spielfeld[1] = new Strasse("Herrnstraße",400,false );
        spielfeld[2] = new Spezialfeld("Zusatzsteuer", Spezialfeld.Typ.ZUSATZSTEUER);
        spielfeld[3] = new Strasse("Hauptsraße", 350, false);
        spielfeld[4] = new Spezialfeld("Ereignisfeld", Spezialfeld.Typ.EREIGNISFELD);
        spielfeld[5] = new Strasse("BHF", 200, false);
        spielfeld[6] = new Strasse("Stadtwaldstraße", 320, false );
        spielfeld[7] = new Spezialfeld("Gemeinschaftsfeld", Spezialfeld.Typ.GEMEINSCHAFTSFELD);
        spielfeld[8] = new Strasse("Schlesierstraße", 300, false );
        spielfeld[9] = new Strasse("Dr.Theodor-Neubauer-Straße", 300, false );
        spielfeld[10] = new Spezialfeld("Geh in die Schule", Spezialfeld.Typ.GEFÄNGNIS);
        spielfeld[11] = new Strasse("Landshuter Straße", 280, false );
        spielfeld[12] = new Strasse("Kläranlage", 150, false );
        spielfeld[13] = new Strasse("Amperstraße", 260, false );
        spielfeld[14] = new Strasse("Degernpoint", 260, false );
        spielfeld[15] = new Strasse("BHF", 200, false);
        spielfeld[16] = new Strasse("Kirchplatz", 240, false );
        spielfeld[17] = new Strasse("Luitpoldstraße", 220, false );
        spielfeld[18] = new Spezialfeld("Ereignisfeld", Spezialfeld.Typ.EREIGNISFELD);
        spielfeld[19] = new Strasse("Falkenstraße",220, false );
        spielfeld[20] = new Spezialfeld("Free Parking", Spezialfeld.Typ.FREIPARKEN);
        spielfeld[21] = new Strasse("Lessinstraße", 200, false );
        spielfeld[22] = new Strasse("Goethestraße", 180, false );
        spielfeld[23] = new Spezialfeld("Gemeinschaftsfeld", Spezialfeld.Typ.GEMEINSCHAFTSFELD);
        spielfeld[24] = new Strasse("Schillerstraße", 180, false );
        spielfeld[25] = new Strasse("BHF", 200, false);
        spielfeld[26] = new Strasse("Haydnstraße", 160, false );
        spielfeld[27] = new Strasse("Beethovenstraße", 140, false );
        spielfeld[28] = new Strasse("Umspannwerk", 150, false );
        spielfeld[29] = new Strasse("Mozartstraße", 140, false );
        spielfeld[30] = new Spezialfeld("Schule / Elternsprechtag", Spezialfeld.Typ.GEFÄNGNIS);
        spielfeld[31] = new Strasse("Merkurstraße", 120, false );
        spielfeld[32] = new Strasse("Venusstraße", 100, false );
        spielfeld[33] = new Spezialfeld("Ereignisfeld", Spezialfeld.Typ.EREIGNISFELD);
        spielfeld[34] = new Strasse("Sonnenstraße", 100, false );
        spielfeld[35] = new Strasse("BHF", 200, false);
        spielfeld[36] = new Spezialfeld("Einkommensteuer", Spezialfeld.Typ.EINKOMMENSTEUER);
        spielfeld[37] = new Strasse("Birkenweg", 60, false );
        spielfeld[38] = new Spezialfeld("Gemeinschaftsfeld", Spezialfeld.Typ.GEMEINSCHAFTSFELD);
        spielfeld[39] = new Strasse("Eichenweg", 60, false );
    }

    public Feld getFeldVonSpieler(int spielerIndex) {
        int pos = getSpielerPosition(spielerIndex);
        return spielfeld[pos];
    }

    public Strasse getStrasseVonSpieler(int spielerIndex) {
        Feld feld = getFeldVonSpieler(spielerIndex);
        if (feld instanceof Strasse strasse) {
            return strasse;
        }
        return null;
    }

    public Strasse getFeld(String name) {
        for (Feld feld : spielfeld) {
            if (feld instanceof Strasse && feld.getName().equals(name)) {
                return (Strasse) feld;
            }
        }
        return null;
    }

    public void setFeld(int index, Feld wert) {
        if (index < 0 || index >= spielfeld.length) {
            throw new IllegalArgumentException("Ungültiger Feld-Index: " + index);
        }
        spielfeld[index] = wert;
    }

    public int getSpielerPosition(int spielerIndex) {
        if (spielerIndex < 0 || spielerIndex >= spielerPositionen.length) {
            throw new IllegalArgumentException("Ungültiger Spieler-Index: " + spielerIndex);
        }
        return spielerPositionen[spielerIndex];
    }

    public void setSpielerPosition(int spielerIndex, int position) {
        if (spielerIndex < 0 || spielerIndex >= spielerPositionen.length) {
            throw new IllegalArgumentException("Ungültiger Spieler-Index: " + spielerIndex);
        }
        if (position < 0 || position >= spielfeld.length) {
            throw new IllegalArgumentException("Ungültige Spielfeld-Position: " + position);
        }
        spielerPositionen[spielerIndex] = position;
        spielerArray[spielerIndex].setPosition(position); // Synchronisiere Spieler-Objekt
    }

    /**
     * Bewegt den Spieler um angegebene Anzahl Felder vorwärts
     * Gibt true, wenn Spieler über "Los" gegangen
     */
    public boolean bewegeSpieler(int spielerIndex, int anzahlFelder) {
        if (spielerIndex < 0 || spielerIndex >= spielerPositionen.length) {
            throw new IllegalArgumentException("Ungültiger Spieler-Index: " + spielerIndex);
        }
        int altePosition = spielerPositionen[spielerIndex];
        int neuePosition = (altePosition + anzahlFelder) % spielfeld.length;
        spielerPositionen[spielerIndex] = neuePosition;
        spielerArray[spielerIndex].setPosition(neuePosition); // Synchronisiere Spieler-Objekt
        return neuePosition < altePosition;
    }

    public int würfeln() {
        // Simuliert Würfelwurf mit zwei Würfeln (2-12)
        return (int)(Math.random() * 6 + 1) + (int)(Math.random() * 6 + 1);
    }

    public void setAktuellerSpieler(int index) {
        this.aktuellerSpieler = index;}

    public int getAktuellerSpieler() {
        return aktuellerSpieler;
    }

    public int[] getAlleSpielerPositionen() {
        return spielerPositionen.clone();
    }

    public Feld getFeld(int index) {
        return spielfeld[index];
    }

    public Spieler[] getSpielerArray() {
        return spielerArray;
    }

    public List<Spieler> getSpielerListe() {
        return spielerListe;
    }
}