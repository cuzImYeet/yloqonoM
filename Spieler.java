public class Spieler
{
    int Kapital;
    Strasse[] gekaufteStrassen; // statt String[]
    boolean gefängnis;
    Spielfeld Spi; // Annahme: Spielfeld ist eine Klasse, die das Spielfeld repräsentiert

    public Spieler(Spielfeld brett)
    {
        Spi = brett; // Initialisiere das Spielfeld
        Kapital = 1500; // Startkapital
        gefängnis = false; // Spieler ist nicht im Gefängnis
        gekaufteStrassen = new Strasse[28];

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

    public void aneignen(Strasse strasse)
    {
        for (int i = 0; i < gekaufteStrassen.length; i++) 
        {
            if (gekaufteStrassen[i] == null) 
            {
                gekaufteStrassen[i] = strasse;
                strasse.setBesitzer(this);
                System.out.println("Straße erfolgreich gekauft: " + strasse.getName());
                break;
            }
        }
    }

    public void würfeln()
    {
        Spi.würfeln();
        System.out.println("Der Spieler hat gewürfelt.");
    }

    public void insGefängnis()
    {
        gefängnis = true; // Spieler ist im Gefängnis
        System.out.println("Der Spieler ist im Gefängnis.");
    }
}