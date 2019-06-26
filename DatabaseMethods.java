//Marcus Sullins

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DatabaseMethods
{
   private Connection connection = null;
   private String DATABASE_URL = "jdbc:derby:rentals;create=true";
   private MavBox mb;

   public void setMavBox(MavBox mavbox)
   {
      this.mb = mavbox;
   }

   public void connectDatabase()
    {
         // connect to the database
         try
         {
            connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
               
            importCustomers();
            importMovies();
            importGames();
            importRentals();
            connection.close();
         } // AutoCloseable objects' close methods are called now 
         catch (SQLException sqlException)                                
         {                                                                  
            sqlException.printStackTrace();
         }
         catch (Exception except)
         {
             except.printStackTrace();
         }  
    }

   //************************************************//
   //          INSERT DATA INTO DATABASE
   //************************************************//

    public void insertCustomer(String name, int id, String creditCard)
    {
      try
      { 
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO Customers (customerName,customerID,creditCardNumber) VALUES(?,?,?)");
         statement.setString(1, name);
         statement.setInt(2, id);
         statement.setString(3, creditCard);
         statement.executeUpdate();
         connection.commit();
         connection.close();
      }

      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }

    }

    public void insertRental(String name, String rentalCode)
    {
      try
      {
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO Rentals (customerName,rentalCode) VALUES(?,?)");
         statement.setString(1, name);
         statement.setString(2, rentalCode);
         statement.executeUpdate();
         connection.commit();
         connection.close();
      }

      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }

    }

   //************************************************//
   //          WRITE DATA FROM DATABASE
   //************************************************//

   public void writeCustomerTable()
   {
      final String SELECT_QUERY = 
        "SELECT * FROM Customers";
      try
      { 
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
       
      
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Customers Table of customers :%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // display query results
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         } 

         connection.close();
      }
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }  
   }

   public void writeRentalTable()
   {
      final String SELECT_QUERY = 
        "SELECT * FROM Rentals";
      try
      { 
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
       
      
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Rentals Table of rentals :%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // display query results
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         } 

         connection.close();
      } 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }  
   }

   public void writeMoviesTable()
   {
      final String SELECT_QUERY = 
        "SELECT * FROM Movies";
      try
      { 
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
       
      
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Movies Table of Movies :%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // display query results
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         } 

         connection.close();
      }
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }  
   }

   public void writeGamesTable()
   {
      final String SELECT_QUERY = 
        "SELECT * FROM Games";
      try
      { 
         connection = DriverManager.getConnection(
               DATABASE_URL, "admin", "admin");
         Statement statement = connection.createStatement(); 
         ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
       
      
         // get ResultSet's meta data
         ResultSetMetaData metaData = resultSet.getMetaData();
         int numberOfColumns = metaData.getColumnCount();     
         
         System.out.printf("Games Table of games :%n%n");

         // display the names of the columns in the ResultSet
         for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
         System.out.println();
         
         // display query results
         while (resultSet.next()) 
         {
            for (int i = 1; i <= numberOfColumns; i++)
               System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
         }
         connection.close(); 
      } 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }  
   }


   //************************************************//
   //          CHECK IF RENTAL AVAILABLE
   //************************************************//


   public boolean isAvailable(String rentalCode, String type)
   {
      final String MOVIE_QUERY = 
      "SELECT * FROM Movies";
      final String GAME_QUERY = 
      "SELECT * FROM Games";

      boolean available = false;
   try
   {
      connection = DriverManager.getConnection(
            DATABASE_URL, "admin", "admin");
      Statement statement = connection.createStatement(); 
       
       
     
      if (type == "Movie")
      {
         ResultSet movieSet = statement.executeQuery(MOVIE_QUERY);
         while(movieSet.next()) 
         {
            String RentalCode = movieSet.getString("rentalCode");
            boolean isAvailable = movieSet.getBoolean("isAvailable");
            if(RentalCode.equals(rentalCode))
            {
               if(isAvailable == true)
               {
                  return available = true;
               }
            }
         }
      }
      else if (type == "Game")
      {
         ResultSet gameSet = statement.executeQuery(GAME_QUERY); 
         while(gameSet.next()) 
         {
            String RentalCode = gameSet.getString("rentalCode");
            boolean isAvailable = gameSet.getBoolean("isAvailable");
            if(RentalCode.equals(rentalCode))
            {
               if(isAvailable == true)
               {
                  return available = true;
               }
            }
         }
      }

      connection.close();
      
   }
   
   catch (SQLException sqlException)                                
   {                                                                  
      sqlException.printStackTrace();
   }  

   return available;
   }

   //************************************************//
   //            MAKE RENTAL UNAVAILABLE
   //************************************************//

   public void makeUnavailable(String type, String rentalCode)
   {
      try
      {
            connection = DriverManager.getConnection(
                  DATABASE_URL, "admin", "admin");

            
            if (type.equals("Movie"))
            {
               
               PreparedStatement Movie_Query = connection.prepareStatement
               ("UPDATE Movies SET isAvailable = ? WHERE rentalCode = ?");

               Movie_Query.setBoolean(1, false);
               Movie_Query.setString(2, rentalCode);
               Movie_Query.executeUpdate();
               connection.commit();
               connection.close();
            }
            else if (type.equals("Game"))
            {
               
               PreparedStatement Game_Query = connection.prepareStatement
               ("UPDATE Games SET isAvailable = ? WHERE rentalCode = ?");

               Game_Query.setBoolean(1, false);
               Game_Query.setString(2, rentalCode);
               Game_Query.executeUpdate();
               connection.commit();
               connection.close();
            }

         
         
      }

      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }

   } 
   


   //************************************************//
   //          IMPORT DATA FROM DATABASE
   //************************************************//

   public void importCustomers()
   {
      try
      {
         connection = DriverManager.getConnection(
            DATABASE_URL, "admin", "admin");
            
         PreparedStatement statement=connection.prepareStatement("select * from Customers");  
         ResultSet customerSet=statement.executeQuery();  
         while(customerSet.next())
         {
            String name =   customerSet.getString("customerName");
            int ID = customerSet.getInt("customerID");
            String creditCard = customerSet.getString("creditCardNumber");
            Customer customer = new Customer(name,ID,creditCard);
            mb.addCustomer(customer);
            
         }
         connection.close();  
      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
      catch (Exception except)
      {
          except.printStackTrace();
      }
   }

   public void importRentals()
   {
      try
      {
         connection = DriverManager.getConnection(
            DATABASE_URL, "admin", "admin");
         
         PreparedStatement statement=connection.prepareStatement("select * from Rentals");  
         ResultSet rentalSet=statement.executeQuery();  
         while(rentalSet.next())
         {
            String name =   rentalSet.getString("customerName");
            String rentalCode =   rentalSet.getString("rentalCode");

            mb.purchaseRental(name, rentalCode);
         }

         connection.close();

      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
      catch (Exception except)
      {
          except.printStackTrace();
      }
   }

   public void importMovies()
   {
      try
      {
         connection = DriverManager.getConnection(
            DATABASE_URL, "admin", "admin");
            
         PreparedStatement statement=connection.prepareStatement("select * from Movies");  
         ResultSet movieSet=statement.executeQuery();  
         while(movieSet.next())
         {
            String title =   movieSet.getString("title");
            String rentalCode =   movieSet.getString("rentalCode");
            double price =   movieSet.getDouble("price");
            String rentalType =   movieSet.getString("rentalType");

            Movie movie = new Movie(title,rentalCode,price,Movie.MovieType.valueOf(rentalType));
            mb.addMovie(movie);
         }

         connection.close();
      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
      catch (Exception except)
      {
          except.printStackTrace();
      }
   }

   public void importGames()
   {
      try
      {
         connection = DriverManager.getConnection(
            DATABASE_URL, "admin", "admin");
            
         PreparedStatement statement=connection.prepareStatement("select * from Games");  
         ResultSet gameSet=statement.executeQuery();  
         while(gameSet.next())
         {
            String title =   gameSet.getString("title");
            String rentalCode =   gameSet.getString("rentalCode");
            double price =   gameSet.getDouble("price");
            String rentalType =   gameSet.getString("rentalType");

            Game game = new Game(title,rentalCode,price,Game.GameType.valueOf(rentalType));
            mb.addGame(game);
         }

         connection.close();
      } // AutoCloseable objects' close methods are called now 
      catch (SQLException sqlException)                                
      {                                                                  
         sqlException.printStackTrace();
      }
      catch (Exception except)
      {
          except.printStackTrace();
      }
   }

} 