public abstract class Feld {
    protected String name;
    public Feld(String name) { this.name = name; }

    public String getName() { return name; }

    public abstract void betreteFeld(Spieler spieler);
}