//Official Name: Jeremy Dennen
//E-mail: jcdennen@syr.edu
//Final Project: Battleship
//Compiler: drJava on PC or mac
//Date: Dec. 2, 2013

/* Panel that contains drawing element for Title of game board
 * uses drawString
 */
import javax.swing. *; 
import java.awt.*;

public class BattleshipPanel extends JPanel 
{
  public BattleshipPanel()
  {
    setBackground(Color.blue);
    setPreferredSize(new Dimension(600,100));
    setFont(new Font("Arial", Font.BOLD, 56));
  }
/*DR*/  
  public void paintComponent(Graphics page)
  {
    super.paintComponent (page);
    
    page.setColor(Color.orange);
    page.drawString("Battleship", 160, 80);
  }
}