public class Spielfeld {
    private Strasse[] spielfeld = new Strasse[40]; // Array für alle Felder (Straßen, Ereignis, etc.)
    private int[] spielerPositionen;  // Array für die Positionen der Spieler
    private int aktuellerSpieler = 0; // Index des aktuellen Spielers
    
    public Spielfeld(int spielerAnzahl){
        spielerPositionen = new int[spielerAnzahl];
        for (int i = 0; i < spielerPositionen.length; i++) {
            spielerPositionen[i] = 0; // Alle Spieler starten auf dem Feld 0
        }
        // Initialisiere das Spielfeld mit Feldern/Straßen
        spielfeld[0] = new Strasse("Los-Feld", -2000, false); //Los-Feld
        spielfeld[1] = new Strasse("Herrnstraße",400,false );
        spielfeld[2] = new Strasse("Special 1", 0, false); //SPECIAL-Feld
        spielfeld[3] = new Strasse("Hauptsraße", 350, false);
        spielfeld[4] = new Strasse("Special 2", 0, false); //SPECIAL-Feld
        spielfeld[5] = new Strasse("BHF", 200, false);    // Bahnhof-Feld
        spielfeld[6] = new Strasse("Stadtwaldstraße", 320, false );
        spielfeld[7] = new Strasse("Special 3",0, false); //SPECIAL-Feld
        spielfeld[8] = new Strasse("Schlesierstraße", 300, false );
        spielfeld[9] = new Strasse("Dr.Theodor-Neubauer-Straße", 300, false );
        spielfeld[10] = new Strasse("Geh Gefängnis", 0, false); // Gefängnis-Feld
        spielfeld[11] = new Strasse("Landshuter Straße", 280, false );
        spielfeld[12] = new Strasse("Kläranlage", 150, false );
        spielfeld[13] = new Strasse("Amperstraße", 260, false );
        spielfeld[14] = new Strasse("Degernpoint", 260, false );
        spielfeld[15] = new Strasse("BHF", 200, false); // Bahnhof-Feld
        spielfeld[16] = new Strasse("Kirchplatz", 240, false );
        spielfeld[17] = new Strasse("Luitpoldstraße", 220, false );
        spielfeld[18] = new Strasse("Special 4", 0, false); //SPECIAL-Feld
        spielfeld[19] = new Strasse("Falkenstraße",220, false );
        spielfeld[20] = new Strasse("Free Parking", 0, false); // Freies Parken-Feld
        spielfeld[21] = new Strasse("Lessinstraße", 200, false );
        spielfeld[22] = new Strasse("Goethestraße", 180, false );
        spielfeld[23] = new Strasse("Special 5", 0, false); //SPECIAL-Feld
        spielfeld[24] = new Strasse("Schillerstraße", 180, false );
        spielfeld[25] = new Strasse("BHF", 200, false); // Bahnhof-Feld
        spielfeld[26] = new Strasse("Haydnstraße", 160, false );
        spielfeld[27] = new Strasse("Beethovenstraße", 140, false );
        spielfeld[28] = new Strasse("Umspannwerk", 150, false );
        spielfeld[29] = new Strasse("Mozartstraße", 140, false );
        spielfeld[30] = new Strasse("Schule / Elternsprechtag", 0, false); // Gefängnis-Feld
        spielfeld[31] = new Strasse("Merkurstraße", 120, false );
        spielfeld[32] = new Strasse("Venusstraße", 100, false );
        spielfeld[33] = new Strasse("Special 6", 0, false);   //SPECIAL-Feld
        spielfeld[34] = new Strasse("Sonnenstraße", 100, false );
        spielfeld[35] = new Strasse("BHF", 200, false); // Bahnhof-Feld
        spielfeld[36] = new Strasse("Special 7", 0, false); //SPECIAL-Feld
        spielfeld[37] = new Strasse("Birkenweg", 60, false );
        spielfeld[38] = new Strasse("Special 8", 0, false); //SPECIAL-Feld
        spielfeld[39] = new Strasse("Eichenweg", 60, false ); 
        
    }
    
    public Strasse getFeldVonSpieler(int spielerIndex) {
        int pos = getSpielerPosition(spielerIndex);
        return spielfeld[pos];
    }

    public Strasse getStrasseVonSpieler(int spielerIndex) {
        Strasse feld = getFeldVonSpieler(spielerIndex);
        if (feld instanceof Strasse) {
            return (Strasse) feld;
        }
        return null;
    }

    // Gibt das Feld-Objekt mit dem angegebenen Namen zurück (oder null, falls nicht gefunden)
    public Strasse getFeld(String name) {
        for (Strasse feld : spielfeld) {
            if (feld != null && feld.getName().equals(name)) {
                return feld;
            }
        }
        return null;
    }

    // Setzt ein Feld an gegebener Position (z.B. für Spezialfelder)
    public void setFeld(int index, Strasse wert) {
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
    }
    
    /**
     * Bewegt den Spieler um die angegebene Anzahl Felder vorwärts.
     * Gibt true zurück, wenn der Spieler über "Los" gegangen ist.
     */
    public boolean bewegeSpieler(int spielerIndex, int anzahlFelder) {
        if (spielerIndex < 0 || spielerIndex >= spielerPositionen.length) {
            throw new IllegalArgumentException("Ungültiger Spieler-Index: " + spielerIndex);
        }
        int altePosition = spielerPositionen[spielerIndex];
        int neuePosition = (altePosition + anzahlFelder) % spielfeld.length;
        spielerPositionen[spielerIndex] = neuePosition;
        // Überrundung erkannt, wenn neue Position kleiner als alte Position
        return neuePosition < altePosition;
    }
     
    public int würfeln() {
        // Simuliert einen Würfelwurf mit zwei Würfeln (2-12)
        int wurf = (int)(Math.random() * 6 + 1) + (int)(Math.random() * 6 + 1);
        bewegeSpieler(aktuellerSpieler, wurf);
        return wurf;
    }
    

    
    //Gibt den Index des aktuellen Spielers zurück.
    public int getAktuellerSpieler() {
        return aktuellerSpieler;
    }

    public int[] getAlleSpielerPositionen() {
        return spielerPositionen.clone();
    }
}