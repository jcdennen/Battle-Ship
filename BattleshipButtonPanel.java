//Official Name: Jeremy Dennen
//E-mail: jcdennen@syr.edu
//Final Project: Battleship
//Compiler: drJava on PC or mac
//Date: Dec. 2, 2013

/* -Implements BSShips class
 * -constructs 2D array of buttons
 * -constructs 2D array of Strings to be used as button labels
 * -constructs two ships
 * -constructs two checkboxes
 * -Uses a layout manager for 2D array of buttons
 * -uses two Listeners: ButtonListener & CheckListener
 * -ButtonListener checks to see if button clicked is a hit/miss, 
 * sinks a ship, and/or prompts a win or loss
 * -CheckListener determines if button text changes to red or white
 * when ButtonListener senses a hit/miss
 * -exits upon win or loss
 *  */
import java.awt.*; 
import java.awt.event.*; 
import javax.swing. *;  

public class BattleshipButtonPanel extends JPanel
{
  private JButton[][] gridButton;
  private String[][] coordinates={
    {"A1","B1","C1","D1","E1"},//1st row
    {"A2","B2","C2","D2","E2"},//2nd row
    {"A3","B3","C3","D3","E3"},//3rd row
    {"A4","B4","C4","D4","E4"},//4th row
    {"A5","B5","C5","D5","E5"} }; //5th row
/*CL*/  
  private BSShips ship1 = new BSShips();
  private BSShips ship2 = new BSShips(ship1.getx1(), ship1.gety1(), ship1.getx2(), ship1.gety2());
//variables for CheckBox Panel  
  private JCheckBox hits, misses;
  private boolean showHit, showMiss;
  
  public BattleshipButtonPanel()
  {
    gridButton = new JButton[5][5];
//creates buttons
/*GUI2*/
/*AR*/
    for (int r=0;r<5; r++)
    {
      for (int c=0; c<5; c++)
      {
        gridButton[r][c]=new JButton(coordinates[r][c]);
        gridButton[r][c].setBackground(Color.white);
        gridButton[r][c].setPreferredSize(new Dimension(100,100));
        gridButton[r][c].addActionListener(new ButtonListener());
        add(gridButton[r][c]);
      }
    }
/*LM*/    
    setLayout (new GridLayout(6,5));
/*GUI3*/
    hits = new JCheckBox("Show hits     ");
    hits.addItemListener(new CheckListener());
    misses = new JCheckBox("Show misses");
    misses.addItemListener(new CheckListener());
    
    add(hits);
    add(misses);
    
    setBackground (Color.cyan);
  }
  
/*LI*/  
//--------------------------------------------
//Listener for check boxes
//--------------------------------------------
  public class CheckListener implements ItemListener
  {
    public void itemStateChanged (ItemEvent event)
    {
      showHit = false;
      showMiss = false;
      if (hits.isSelected())
        showHit = true;//boolean to change color to red
      
      if (misses.isSelected())
        showMiss = true;//boolean to change color to white
    }//end itemStateChanged
  }//end CheckListener
  
/*LI*/  
//---------------------------------------------------
//ButtonListener
//---------------------------------------------------
  private class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      
      for(int r=0; r<5; r++)
        for (int c=0; c<5; c++)
      {
        if(event.getSource() == gridButton[r][c])
        {
          ship1.missileFired();
          ship2.missileFired();
//if the button clicked was a hit on ship 1
          if (ship1.getBool(r,c)) 
          {
            JOptionPane.showMessageDialog(null, "That's a hit!", "HIT!", JOptionPane.PLAIN_MESSAGE);
            ship1.hit(r,c);
            ship1.setBool(r,c);
            if (showHit)
              gridButton[r][c].setForeground(Color.red);
//if hit is the second hit on ship 1 (ship is sunk)
            if(ship1.sunk())
            {
              JOptionPane.showMessageDialog(null,"You sunk a battleship!", "SUNK", JOptionPane.PLAIN_MESSAGE);
              
              if(ship1.sunk()&&ship2.sunk()) //both ships are sunk --> user win & close
              {
                JOptionPane.showMessageDialog(null,"Congratulations!!! You Won!", "WIN", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
            }
          }
//if the button clicked was a hit on ship 2
          else if (ship2.getBool(r,c))
          {
            JOptionPane.showMessageDialog(null, "That's a hit!", "HIT!", JOptionPane.PLAIN_MESSAGE);
            ship2.hit(r,c);
            ship2.setBool(r,c);
            if (showHit)
              gridButton[r][c].setForeground(Color.red);
//if hit is the second hit on ship 2 (ship is sunk)              
            if(ship2.sunk())
            {
              JOptionPane.showMessageDialog(null,"You sunk a battleship!", "SUNK", JOptionPane.PLAIN_MESSAGE);
              
              if(ship1.sunk()&&ship2.sunk())//both ships are sunk --> user win & close
              {
                JOptionPane.showMessageDialog(null,"Congratulations!!! You Won!", "WIN", JOptionPane.PLAIN_MESSAGE);
                System.exit(0);
              }
            }
          }
//if the button clicked was a miss
          else
          {
            JOptionPane.showMessageDialog(null, "You missed...", "MISS!", JOptionPane.PLAIN_MESSAGE);
            if (showMiss)
              gridButton[r][c].setForeground(Color.white);
          
            if (ship1.missileCounter()==15||ship2.missileCounter()==15)//if either missleCounter reaches 15, user automatically loses
            {
              JOptionPane.showMessageDialog(null,"Looks like you ran out of missles... \nYou Lose!", "YOU LOST", JOptionPane.PLAIN_MESSAGE);
              System.exit(0);//loss & close
            }
          }
        }

      }
    }// end actionPerformed
  }//end ButtonListener
  
}