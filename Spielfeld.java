import java.util.Random;

public class Spielfeld {
    private int[] spielfeld = new int[40]; // 40 Felder für das Spielfeld
    private int[] spielerPositionen;  // Array für die Positionen der Spieler
    private int aktuellerSpieler = 0; // Index des aktuellen Spielers
    private Random random = new Random(); // Zufallszahlengenerator für Würfeln

    public Spielfeld(int spielerAnzahl){
        spielerPositionen = new int[spielerAnzahl];
        for (int i = 0; i < spielerPositionen.length; i++) {
            spielerPositionen[i] = 0; // Alle Spieler starten auf dem Feld 0
        }
    }
    
    public int getFeld(int index) {
        if (index < 0 || index >= spielfeld.length) {
            throw new IllegalArgumentException("Ungültiger Feld-Index: " + index);
        }
        return spielfeld[index];
    }
    
    public void setFeld(int index, int wert) {
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
        // Simuliert einen Würfelwurf mit zwei Würfeln (je 1-6)
        int wurf = random.nextInt(6) + 1 + random.nextInt(6) + 1;
        bewegeSpieler(aktuellerSpieler, wurf);
        return wurf;
    }

    // Gibt den Index des aktuellen Spielers zurück.
    public int getAktuellerSpieler() {
        return aktuellerSpieler;
    }
    
    // Wechselt zum nächsten Spieler
    public void naechsterSpieler() {
        aktuellerSpieler = (aktuellerSpieler + 1) % spielerPositionen.length;
    }
}