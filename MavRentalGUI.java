//Marcus Sullins

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class MavRentalGUI extends JFrame
{
    private JDesktopPane desktop;
    DatabaseMethods db = new DatabaseMethods();
    MavBox mb;

    public void setMavBox(MavBox mavbox)
    {
       this.mb = mavbox;
    }

    //************************************************//
    //                 MavRentalGUI
   //************************************************//

    public MavRentalGUI()
    {
        super("MavBox");
        desktop = new JDesktopPane();

        JMenuBar bar = new JMenuBar();

        JMenu addMenu = new JMenu(" Add");
        JMenuItem addCustomer = new JMenuItem(" Add Customer");
        JMenuItem addRental = new JMenuItem(" Add Rental");
        JMenu exitMenu = new JMenu(" Exit");
        JMenuItem calculateFees = new JMenuItem(" Calculate Fees");
        JMenuItem writeDatabase = new JMenuItem(" Write Database");
        JMenuItem close = new JMenuItem(" Close");

        addMenu.add(addCustomer);
        addMenu.add(addRental);
        exitMenu.add(calculateFees);
        exitMenu.add(writeDatabase);
        exitMenu.add(close);

        bar.add(addMenu);
        bar.add(exitMenu);

        add(desktop);
        setJMenuBar(bar);

        // ADD CUSTOMER LISTENER

        addCustomer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JInternalFrame frame = new JInternalFrame("Add Customer",
                true,true,true,true);
                customerPanel cp = new customerPanel();
                frame.add(cp);
                frame.pack();
                desktop.add(frame);
                frame.setVisible(true);
            }
        });

        // ADD RENTAL LISTENER

        addRental.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                JInternalFrame frame = new JInternalFrame("Add Rental",
                true,true,true,true);
                rentalPanel cp = new rentalPanel();
                frame.add(cp);
                frame.pack();
                desktop.add(frame);
                frame.setVisible(true);
            }
        });

        // CLOSE BUTTON LISTENER

        close.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                System.exit(0);
            }
        });

        // WRITE DATABASE BUTTON LISTENER

        writeDatabase.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                db.writeCustomerTable();
                System.out.println("\n");
                db.writeRentalTable();
                System.out.println("\n");
                db.writeMoviesTable();
                System.out.println("\n");
                db.writeGamesTable();
                System.out.println("\n");
            }
        });

        // CALCULATE FEES BUTTON LISTENER

        calculateFees.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                ArrayList<Customer> cList = mb.getCustomerList();
                String totals = "";
                for(Customer customer : cList)
                {
                    double a = 0;
                    for(Rental rental : customer.getRental())
                    {
                        a = a + rental.calculateCharge();
                        
                        
                    }
                    totals += customer.getName() +" "+"$"+ String.format("%.2f", a) +"\n";
                }
                JOptionPane.showMessageDialog(null, totals,"Customer Totals", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    //************************************************//
    //              Customer Panel
   //************************************************//

    class customerPanel extends JPanel
    {
        private JLabel nameLabel;
        private JLabel idLabel;
        private JLabel creditCardLabel;
        private JLabel submitLabel;

        private JTextField nameField;
        private JTextField idField;
        private JTextField creditCardField;
        private JButton submitButton;

        public customerPanel()
        {
            setLayout(new GridLayout(4,2));
            
            nameLabel = new JLabel(" Enter Name");
            idLabel = new JLabel(" Enter ID");
            creditCardLabel = new JLabel(" Enter Credit Card Number");
            submitLabel = new JLabel(" Click Here To Submit");

            nameField = new JTextField(20);
            idField = new JTextField(20);
            creditCardField = new JTextField(18);
            submitButton = new JButton(" Submit");

            Handler myHandler = new Handler();

            submitButton.addActionListener(myHandler);

            add(nameLabel);
            add(nameField);

            add(idLabel);
            add(idField);

            add(creditCardLabel);
            add(creditCardField);

            add(submitLabel);
            add(submitButton);
        }

        class Handler implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(valid())
                {
                    
                    Customer customer = new Customer(nameField.getText(),Integer.parseInt(idField.getText()),creditCardField.getText());
                    mb.addCustomer(customer);
                    String name = nameField.getText();
                    int id = Integer.parseInt(idField.getText());
                    String creditCard = creditCardField.getText();

                    db.insertCustomer(name, id, creditCard);
                    clearAll();
                }

            }

            //VALIDATE DATA ENTERED FOR CUSTOMER PANEL
            
            public boolean valid()
            {
                boolean validData = true;

                if(nameField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! No Customer Name Was Found. Please Enter A Customer Name.", "No Name Found", JOptionPane.ERROR_MESSAGE);
                    nameField.requestFocus();
                    validData = false;
                }
                else if(idField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! No Customer ID Was Found. Please Enter A Customer ID.", "No ID Found", JOptionPane.ERROR_MESSAGE);
                    idField.requestFocus();
                    validData = false;
                }
                else if(creditCardField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! No Customer Credit Card Was Found. Please Enter A Credit Card Number.", "No Credit Card Found", JOptionPane.ERROR_MESSAGE);
                    creditCardField.requestFocus();
                    validData = false;
                }
                if(creditCardField.getText().isEmpty() == false)
                {
                    String creditCard = creditCardField.getText();

                    if(creditCard.charAt(0) == '4' || creditCard.charAt(0) == '5' ||creditCard.charAt(0) == '6')
                    {
                        
                        if(creditCard.length() == 19)
                        {
                            
                            for(int i=0; i < creditCard.length(); i++)
                            {
                                char j = creditCard.charAt(i);

                                if( i == 4 ||i == 9 ||i == 14)
                                {
                                    i++;
                                }

                                else if(j<'0' || j>'9' || j>='a' && j<='z' || j>='A' && j<='Z')
                                {
                                    JOptionPane.showMessageDialog(null, "Error! Credit Card Is Not Valid. Card May Only Contain Numbers 0-9 In The Format xxxx-xxxx-xxxx-xxxx.", "Credit Card Not Valid", JOptionPane.ERROR_MESSAGE);
                                    creditCardField.setText("");
                                    creditCardField.requestFocus();
                                    validData = false;
                                    break;
                                }
                            }

                            if(creditCard.charAt(4) != '-' || creditCard.charAt(9) != '-' || creditCard.charAt(14) != '-')
                            {
                                JOptionPane.showMessageDialog(null, "Error! Credit Card Is Not Valid. Please Enter A Card In The Format xxxx-xxxx-xxxx-xxxx.", "Credit Card Not Valid", JOptionPane.ERROR_MESSAGE);
                                creditCardField.setText("");
                                creditCardField.requestFocus();
                                validData = false;  
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Error! Credit Card Is Not Valid. Please Enter A Card In The Format xxxx-xxxx-xxxx-xxxx.", "Credit Card Not Valid", JOptionPane.ERROR_MESSAGE);
                            creditCardField.setText("");
                            creditCardField.requestFocus();
                            validData = false;
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Error! Credit Card Is Not Valid. Card Must Begin With A 4, 5, or 6.", "Credit Card Not Valid", JOptionPane.ERROR_MESSAGE);
                        creditCardField.setText("");
                        creditCardField.requestFocus();
                        validData = false;
                    }
                }
                if(nameField.getText().isEmpty() == false)
                {
                    String name = nameField.getText();
                    ArrayList<Customer> cList = mb.getCustomerList();
                   for(Customer customer : cList)
                   {
                       if(customer.getName().equals(name))
                       {
                            JOptionPane.showMessageDialog(null, "Error! Customer Already Exists", "Customer Already Exists", JOptionPane.ERROR_MESSAGE);
                            nameField.setText("");
                            nameField.requestFocus();
                            validData = false;  
                            
                       }
                   }
                }
                
                if(!idField.getText().isEmpty())
                {
                    try{Integer.parseInt(idField.getText());}
                    catch(NumberFormatException nfe)
                    {
                        JOptionPane.showMessageDialog(null, "Error! ID Is Not Valid. Please Enter An Integer ID.", "ID Not Valid", JOptionPane.ERROR_MESSAGE);
                        idField.setText("");
                        idField.requestFocus();
                        validData = false;
                    }
                }


                return validData;
            }

            public void clearAll()
            {
                nameField.setText("");
                idField.setText("");
                creditCardField.setText("");
                nameField.requestFocus();
            }
        }
    }


    //************************************************//
    //              Rental Panel
   //************************************************//

    class rentalPanel extends JPanel
    {
        private JLabel nameLabel;
        private JLabel typeLabel;
        private JLabel rentalCodeLabel;
        private JLabel submitLabel;

        private JTextField nameField;
        private JComboBox<String> typeField;
        private JTextField rentalCodeField;
        private JButton submitButton;

        private String[] rtype ={"","Movie","Game"};

        public rentalPanel()
        {
            setLayout(new GridLayout(4,2));
            
            nameLabel = new JLabel(" Enter Name");
            typeLabel = new JLabel(" Select Rental Type");
            rentalCodeLabel = new JLabel(" Enter Rental Code");
            submitLabel = new JLabel(" Click Here To Submit");

            nameField = new JTextField(20);
            typeField = new JComboBox<>(rtype);
            rentalCodeField = new JTextField(20);
            submitButton = new JButton(" Submit");

            Handler myHandler = new Handler();

            submitButton.addActionListener(myHandler);

            add(nameLabel);
            add(nameField);

            add(typeLabel);
            add(typeField);

            add(rentalCodeLabel);
            add(rentalCodeField);

            add(submitLabel);
            add(submitButton);
        }

        class Handler implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(valid())
                {
                    try
                    {
                        String name = nameField.getText();
                        String rentalCode = rentalCodeField.getText();
                        String type = (String) typeField.getSelectedItem();
    
                        mb.purchaseRental(name, rentalCode);
                        db.makeUnavailable(type,rentalCode);
                        db.insertRental(name, rentalCode);
                        clearAll();
                    }
                    catch(CustomerDoesNotExistException dne)
                    {
                        System.out.println("Customer Does Not Exist");
                    }
                    

                }

            }

            //VALIDATE DATA ENTERED FOR RENTAL PANEL

            public boolean valid()
            {
                boolean validData = true;

                if(nameField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! No Customer Name Was Entered. Please Enter A Customer Name.", "No Name Found", JOptionPane.ERROR_MESSAGE);
                    nameField.requestFocus();
                    validData = false;
                }
                else if(typeField.getSelectedItem().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Error! No Rental Type Was Selected. Please Select A Rental Type.", "No Rental Type Selected", JOptionPane.ERROR_MESSAGE);
                    typeField.requestFocus();
                    validData = false;
                }
                else if(rentalCodeField.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Error! No Rental Code Was Entered. Please Enter A Rental Code.", "No Rental Code", JOptionPane.ERROR_MESSAGE);
                    rentalCodeField.requestFocus();
                    validData = false;
                }
                else if (!nameField.getText().isEmpty())
                {
                    String name = nameField.getText();
                    
                    if(mb.customerExists(name) == false)
                    {
                        JOptionPane.showMessageDialog(null, "Error! No Customer Was Found By This Name", "No Customer Found", JOptionPane.ERROR_MESSAGE);
                        nameField.requestFocus();
                        nameField.setText("");
                        validData = false;  
                    }
                }
                if (db.isAvailable(rentalCodeField.getText(), (String)typeField.getSelectedItem()) == false)
                {
                        JOptionPane.showMessageDialog(null, "Error! This Rental Is Not Available", "Rental Not Available", JOptionPane.ERROR_MESSAGE);
                        validData = false;
                        rentalCodeField.setText("");
                        rentalCodeField.requestFocus();
                }
                return validData;
            }

            //CLEAR ALL RENTAL PANEL FIELDS

            public void clearAll()
            {
                nameField.setText("");
                rentalCodeField.setText("");
                typeField.setSelectedItem("");
                nameField.requestFocus();
            }
        }
    }
}