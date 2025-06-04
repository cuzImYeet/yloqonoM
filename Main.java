import java.util.Scanner;
public class Main 
{
    Spielfeld brett;
    

public static void main(String[] args)   
{
    Scanner scanner = new Scanner(System.in);
    System.out.print("Wie viele Spieler? ");
    int anzahl = scanner.nextInt();
    Spielfeld brett = new Spielfeld(anzahl);
}
}
