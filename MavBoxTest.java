//Marcus Sullins
import javax.swing.*;


public class MavBoxTest{
    static MavBox mb = new MavBox();
    static DatabaseMethods db = new DatabaseMethods();
    static MavRentalGUI gui = new MavRentalGUI();
    public static void main(String[] args)
    {
        db.setMavBox(mb);
        gui.setMavBox(mb);
        db.connectDatabase();
        
        
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(550,550);
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }
}
