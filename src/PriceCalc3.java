
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PriceCalc3 extends JFrame implements ActionListener, ItemListener
{
 
  JLabel makeL = new JLabel ("Make :");
  JLabel priceL = new JLabel ("Orig Price:");
  
  String [] cars = {"None", "Audi", "BMW", "Merc"};
  double [] price = {0, 450000, 300000, 560000};
  
  JComboBox makeC = new JComboBox(cars);
  JTextField priceT = new JTextField("0", 5);
  Font f1=new Font("Times New Roman", Font.BOLD, 16);
      
  JCheckBox cd = new JCheckBox ("CD player R800", false);
  JCheckBox mp3 = new JCheckBox ("MP3 player R1000", false);
  JCheckBox met = new JCheckBox ("Metallic Paint R1500",false);
  
  ImageIcon img1 = new ImageIcon("calc.gif");
  ImageIcon img2 = new ImageIcon("res.gif");
  
  JButton calc = new JButton("Calc", img1);
  JButton reset = new JButton("Reset",img2);
   	  
  JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
  double origp, extrasp,newp = 0;
  String driveside = "";
  
  ButtonGroup checkbg = new ButtonGroup();
  ButtonGroup radiobg = new ButtonGroup();
  
  JTextArea disp = new JTextArea(5,12);
  
  JScrollPane scroll = new JScrollPane(disp,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
                                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
  
  JToolBar bar = new JToolBar ();
  
  
  JRadioButton rhd = new JRadioButton("Right Hand Drive",true);
  JRadioButton lhd = new JRadioButton("Left Hand Drive",false);
  
  public PriceCalc3()
  {
    super("Price");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    pane.add(bar);
           
    pane.add(makeL);	
    pane.add(makeC);
              
    pane.add(priceL);	
    pane.add(priceT);	
  
    pane.add(cd);
    pane.add(mp3);	
    pane.add(met);	
    
    checkbg.add(cd);
    checkbg.add(mp3);
    
   
    bar.add(calc);  
    bar.add(reset);
     
    calc.setFont(f1);
    pane.add(rhd);
    pane.add(lhd);
    
    radiobg.add(rhd);
    radiobg.add(lhd);
        
    pane.add(scroll);
   
    makeC.addActionListener(this);
    calc.addActionListener(this);
	reset.addActionListener(this);
	
	cd.addItemListener(this);
	mp3.addItemListener(this);
    met.addItemListener(this);
    
    rhd.addItemListener(this);
    lhd.addItemListener(this);
      
    setContentPane(pane);     
  }

   
    public static void main(String[] args)
    {
      PriceCalc3 aFrame = new PriceCalc3();
      aFrame.setSize(500,570);
      aFrame.setVisible(true);
   //   aFrame.setResizable(false);
    }

    public void actionPerformed(ActionEvent e)
	{
	  Object source = e.getSource();
      	  
	  if (source == makeC)
	   {
	     int pos = makeC.getSelectedIndex();
	     priceT.setText(""+ price[pos]);
	   }
	  
	  else if (source == calc)
	   {
   	     origp = Double.parseDouble(priceT.getText());
	     
	     newp = origp + extrasp;

	     disp.append("Make: " + makeC.getSelectedItem()+ "\n");
	     disp.append("Drive Side: " + driveside+ "\n");
	     disp.append("Original Price R: " + priceT.getText()+ "\n");
	     disp.append("Extras Price R: " + extrasp+ "\n");
	     disp.append("Total Price R: " + newp+ "\n\n");
	   } 
	    
	  else if (source == reset)
	   {	
	  	 priceT.setText("0");
		 met.setSelected(false);
		 disp.setText("");
		 makeC.requestFocus();
	   } 
    }

  public void itemStateChanged(ItemEvent e)
	{
	  Object item = e.getItem();
      int state = e.getStateChange();
   	  
   	  if (item == cd)
	    if(state == ItemEvent.SELECTED)
	 	   extrasp = extrasp + 800;
	    else
     	   extrasp = extrasp - 800;
     
   	  else if (item == mp3)
	    if(state == ItemEvent.SELECTED)
	 	   extrasp = extrasp + 1000;
	    else
     	   extrasp = extrasp - 1000;
     	   
      if (item == met)
	    if(state == ItemEvent.SELECTED)
	 	  	extrasp = extrasp + 1500;
	    else
	      	extrasp = extrasp - 1500;
	      	
	  if (item == rhd)
	    driveside = "Right Hand Drive";
	  else
	    driveside = "Left Hand Drive";
	       	
   }
}
