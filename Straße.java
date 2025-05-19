public class Straße
{
    String name; // Name der Straße
    int Kaufpreis; // Kaufpreis der Straße
    int Häuser; // Anzahl der Häuser auf der Straße
    int Miete; // aktuelle Miete der Straße
    boolean gekauft; // true, wenn Straße gekauft wurde

    public Straße (String na, int Ka, int Mi, boolean ge)
    {
        name=na;
        Kaufpreis = Ka;
        Häuser =0;
        Miete = Mi;
        gekauft =ge;
    }

public int KaufeHaus () // Methode zum Kauf eines Hauses
{
    if (Häuser<=4)
    {Häuser = Häuser+1;}
    else
    {System.out.println("Maximale Häuserzahl erreicht!");}
    
    return Häuser;
}


public void KaufeStraße() // Kaufe die Straße
{
    gekauft = true;
}

public int MieteNeu() // Methode zur Berechnung der Miete
{
   
    
    if (Häuser == 0) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.1, Häuser));
    } else if (Häuser == 1) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.2, Häuser));
    } else if (Häuser == 2) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.3, Häuser));
    } else if (Häuser == 3) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.4, Häuser));
    } else if (Häuser == 4) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.5, Häuser));
    } else if (Häuser == 5) {
        Miete = (int) Math.ceil(Miete * Math.pow(1.6, Häuser));
    } else {
        System.out.println("Maximale Anzahl an Häusern erreicht!");
    }
    return Miete;
}
    
// Berecchnung neu
// int berechneMiete(int kaufpreis)
// {retrun kaufpreis * 0.1;}
}