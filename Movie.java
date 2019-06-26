// Marcus Sullins

public class Movie extends Rental {

    public enum MovieType{BLURAY,DVD}
    private MovieType mType = MovieType.DVD;

    public Movie()
    {
        setMovieType(null);
    }

    public Movie(String title, String rentalCode, double price, MovieType type)
    {
        super(title,rentalCode,price);
        setMovieType(type);
    }

    public void setMovieType(MovieType type)
    {
        this.mType = type;
    }

    public MovieType getMovieType()
    {
        return mType;
    }

    @Override
    public double calculateCharge()
    {
        double charge = 0;
        charge = getPrice();

        if(mType == MovieType.BLURAY)
        {
            return charge + 1.00;
        }

        else{ return charge;}
    }
    @Override
    public double calculateBill()
    {
        return calculateCharge();
    }

    public String toString()
    {
        return super.toString() + " Movie Type " + mType;
    }

}
