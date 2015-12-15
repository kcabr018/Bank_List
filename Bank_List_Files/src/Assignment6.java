import javax.swing.*;
import java.util.*;

public class Assignment6 extends JApplet {
   private int APPLET_WIDTH = 500, APPLET_HEIGHT = 200;
   private JTabbedPane tPane;
   private CreatePanel createPanel;
   private SelectPanel selectPanel;
   private Vector<String> bankList;

   //The method init initializes the Applet with a Pane with two tabs
   public void init() {
	   //list of banks to be used in both panel
	   bankList = new Vector<String>();

	   //select panel uses the list of banks
	   selectPanel = new SelectPanel(bankList);
	   //bank list is passed to CreatePanel constructor
	   createPanel = new CreatePanel(bankList, selectPanel);

	   //create a tabbed pane with two tabs
	   tPane = new JTabbedPane();
	   tPane.addTab("Add Bank's Name", createPanel);
	   tPane.addTab("Select Bank", selectPanel);

	   getContentPane().add(tPane);
	   setSize (APPLET_WIDTH, APPLET_HEIGHT); //set Applet size
   }
}