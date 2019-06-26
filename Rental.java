// Marcus Sullins

abstract class Rental extends Customer implements Bill{
    private String title;
    private String rentalCode;
    private double price;

    public Rental() {
        setTitle("");
        setRentalCode("");
        setPrice(0);
    }

    public Rental(String title, String rentalCode, double price) {
        setTitle(title);
        setRentalCode(rentalCode);
        setPrice(price);
    }


    public double getPrice() {
        return price;
    }

    public String getRentalCode() {
        return rentalCode;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRentalCode(String rentalCode) {
        this.rentalCode = rentalCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString()
    {
        return "Title " + title + " Rental Code " + rentalCode + " Price " + price;
    }

    public double calculateBill()
    {
        return calculateCharge();
    }

    
    public abstract double calculateCharge();
}