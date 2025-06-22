public class Spieler
{
    private int Kapital;
    private final Strasse[] gekaufteStrassen; // Array für gekaufte Straßen
    private boolean gefängnis;
    private final Spielfeld Spi; // Referenz auf das Spielfeld
    private final String name; // Name des Spielers
    private int position;
    private int gefaengnisRunden = 0;
    private boolean bankrott = false;

    public Spieler(String name, Spielfeld brett)
    {
        this.name = name;
        this.Spi = brett;
        this.Kapital = 1500;
        this.gefängnis = false;
        this.gekaufteStrassen = new Strasse[28];
        this.position = 0;
    }

    public int bezahlen(int betrag)
    {
        if (Kapital >= betrag) {
            Kapital -= betrag;
            return Kapital;
        } else {
            System.out.println("Nicht genug Kapital!");
            return -1;
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

    public void insGefängnis() {
        gefängnis = true;
        gefaengnisRunden = 0;
        int index = getIndexImSpielfeld();
        this.position = 30;
        if (index != -1) {
            Spi.setSpielerPosition(index, 30);
        }
        System.out.println("Der Spieler ist im Gefängnis.");
    }

    public String getName()
    {
        return name;
    }

    public int getKapital()
    {
        return Kapital;
    }

    public void zahlen(int betrag)
    {
        Kapital -= betrag;
    }

    public void erhalten(int betrag)
    {
        Kapital += betrag;
    }

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public Strasse[] getStrassen()
    {
        return gekaufteStrassen;
    }

    public boolean isImGefängnis()
    {
        return gefängnis;
    }

    public int getGefaengnisRunden() {
        return gefaengnisRunden;
    }

    public void erhoeheGefaengnisRunden() {
        gefaengnisRunden++;
        if (gefaengnisRunden >= 3) {
            verlasseGefaengnis();
        }
    }

    public void verlasseGefaengnis() {
        gefängnis = false;
        gefaengnisRunden = 0;
        System.out.println(name + " verlässt das Gefängnis!");
    }

    public boolean isBankrott() {
        return bankrott;
    }

    public void setBankrott(boolean bankrott) {
        this.bankrott = bankrott;
    }

    public int getIndexImSpielfeld() {
        Spieler[] arr = Spi.getSpielerArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == this) {
                return i;
            }
        }
        return -1; // nicht gefunden
    }
}