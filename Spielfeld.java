public class Spielfeld {
    private int[] spielfeld = new int[40]; // 40 Felder für das Spielfeld
    private int[] spielerPositionen;  // Array für die Positionen der Spieler
    
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

}
