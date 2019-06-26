// Marcus Sullins

import java.util.ArrayList;

public class Customer extends MavBox{
    private String name;
    private String creditCardNumber;
    private ArrayList<Rental> rentalList = new ArrayList<Rental>();
    private int id;

    public Customer()
    {
        setName("");
        setCreditCardNumber("");
        setID(0);
        
    }

    public Customer(String name, int id, String creditCardNumber)
    {
        setName(name);
        setCreditCardNumber(creditCardNumber);
        setID(id);
        
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setID(int id)
    {
        this.id = id;
    }

    public void setCreditCardNumber(String creditCardNumber) 
    {
        this.creditCardNumber = creditCardNumber;
    }

    public void addRental(Rental rental)
    {
        rentalList.add(rental);
    }

    public String getName() 
    {
        return name;
    }

    public int getID()
    {
        return id;
    }

    public String getCreditCardNumber() 
    {
        return creditCardNumber;
    }

    public ArrayList<Rental> getRental() 
    {
        return rentalList;
    }

    public String toString() 
    {
        return "\nName " + name + " Credit Card Number " + creditCardNumber + " ID: " + id + "\nRental List " + rentalList +"\n";
    }
}
