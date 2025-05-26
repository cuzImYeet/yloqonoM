public class Spieler
{
    int Kapital;
    String [] gekaufteStraßen;
    boolean gefängnis;

    public Spieler()
    {
        Kapital = 1500; // Startkapital
        gefängnis = false; // Spieler ist nicht im Gefängnis
        gekaufteStraßen = new String[28];

    }

    public int bezahlen(int betrag)
    {
        if (Kapital >= betrag) {
            Kapital -= betrag; // Betrag abziehen
            return Kapital; // aktuelles Kapital zurückgeben
        } else {
            System.out.println("Nicht genug Kapital!");
            return -1; // Fehlercode, wenn nicht genug Kapital vorhanden ist
        }
    }

    public void aneignen(String straße)
    {
        for (int i = 0; i < gekaufteStraßen.length; i++) 
        {
        if (gekaufteStraßen[i] == null) 
        {
            gekaufteStraßen[i] = straße;
            System.out.println("Straße erfolgreich gekauft: " + straße);
        }
        }
    }

    public void würfeln()
    {
        Spielfeld.würfeln();
        System.out.println("Der Spieler hat gewürfelt.");
    }
}