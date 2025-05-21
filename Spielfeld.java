// ...existing code...
private Feld[] spielfeld = new Feld[40]; // Array für alle Felder (Straßen, Ereignis, etc.)

public Spielfeld(int spielerAnzahl){
    spielerPositionen = new int[spielerAnzahl];
    for (int i = 0; i < spielerPositionen.length; i++) {
        spielerPositionen[i] = 0;
    }
    // Initialisiere das Spielfeld mit Feldern/Straßen
    spielfeld[0] = new LosFeld();
    spielfeld[1] = new Strasse("Badstraße", ...);
    // ... weitere Felder ...
}

public Feld getFeldVonSpieler(int spielerIndex) {
    int pos = getSpielerPosition(spielerIndex);
    return spielfeld[pos];
}

public Strasse getStrasseVonSpieler(int spielerIndex) {
    Feld feld = getFeldVonSpieler(spielerIndex);
    if (feld instanceof Strasse) {
        return (Strasse) feld;
    }
    return null;
}

public int[] getAlleSpielerPositionen() {
    return spielerPositionen.clone();
}
