// Marcus Sullins

public class Game extends Rental{
    
    public enum GameType{XBOX,PLAYSTATION,NINTENDO}
    private GameType gType = GameType.XBOX;

    public Game()
    {
        setGameType(null);
    }

    public Game(String title, String rentalCode, double price, Game.GameType type)
    {
        super(title,rentalCode,price);
        setGameType(type);
    }

    public void setGameType(GameType type)
    {
        this.gType = type;
    }

    public GameType getGameType()
    {
        return gType;
    }

    public String toString()
    {
        return super.toString() + " Game Type " + gType;
    }

    @Override
    public double calculateCharge()
    {
        double charge = 0;
        charge = getPrice();

        if(gType == GameType.PLAYSTATION)
        {
            return charge + 2.50;
        }

        else{ return charge;}
    }
    @Override
    public double calculateBill()
    {
        return calculateCharge();
    }

}
