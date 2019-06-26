// Marcus Sullins

import java.util.ArrayList;
public class MavBox {

    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private ArrayList<Movie> movieList = new ArrayList<Movie>();
    private ArrayList<Game> gameList = new ArrayList<Game>();

    public MavBox ()
    {

    }

    public void addCustomer(Customer customer)
    {
        this.customerList.add(customer);
    }

    public void addMovie(Movie movie)
    {
        movieList.add(movie);
    }

    public void addGame(Game game)
    {
        gameList.add(game);
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public Boolean customerExists(String name)
    {
        for(Customer customer : customerList)
        { 
            
            if(customer.getName().equals(name))
            {   
                return true;
            }
        }
        return false;
    }

    public void purchaseRental(String name, String code) throws CustomerDoesNotExistException
    {   
            if(customerExists(name) == true)
            {   
                
                for(Movie movie : movieList)
                {
                    
                    if(movie.getRentalCode().equals(code))
                    {
                        for(Customer customer : customerList)
                        {
                            if (customer.getName().equals(name))
                            {
                                customer.addRental(new Movie(movie.getTitle(),movie.getRentalCode(),movie.getPrice(),movie.getMovieType()));
                                break;
                            } 
                        }
                        
                    }   
                }
            
                for(Game game : gameList)
                {
                    if(game.getRentalCode().equals(code))
                    {
                        for(Customer customer : customerList)
                        {
                            if (customer.getName().equals(name))
                            {
                                customer.addRental(new Game(game.getTitle(),game.getRentalCode(),game.getPrice(),game.getGameType()));
                                break;
                            }
                        }

                    }
                }
                
            }
            else{throw new CustomerDoesNotExistException("Cusomer: "+ name + " Does Not Exist");}
            
    }


    public String toString() {
        return "Customers " + customerList +
                "\n\n\nMovies " + movieList +
                "\n\n\nGames " + gameList;
    }
}
