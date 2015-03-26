//Official Name: Jeremy Dennen
//E-mail: jcdennen@syr.edu
//Final Project: Battleship
//Compiler: drJava on PC or mac
//Date: Dec. 2, 2013

/* Class that creates location of both ships on 2D array
 * -assigns boolean value to ship coordinates,
 * -contains two Constructors: 1st ship and 2nd ship(uses 1st ship coordinates to not assign same values)
 * -contains getters and setters for: coordinate values, # of times user clicks a button, 
 * checking/declaring a hit/miss, & values regarding whether a ship is sunk or not
 * 
 */
import java.util.Random;

public class BSShips
{
  Random rand = new Random();

  private boolean[][] battlefield = new boolean[5][5];
  private int x1,y1,x2,y2,i,z;
  private int numHits, missiles;
  private boolean shipIsSunk;

//constructor for first battleship
  public BSShips ()
  {
    numHits = 0;
    shipIsSunk = false;
    missiles =0;
//assigns 1st random coordinate of ship
    x1 = rand.nextInt(5);
    y1 = rand.nextInt(5);
    battlefield[x1][y1] = true; 
/*AR*/      
//assigns 2nd random set of coordinates for ship
    do {
      x2 = rand.nextInt(5);
      y2 = rand.nextInt(5);
      if(x2==x1 && (y2==(y1+1) || y2==(y1-1)))
      {
        battlefield[x2][y2] = true;
/*AR*/  
        i=0;
      }
      else if (y2==y1 && (x2==(x1+1) || x2==(x1-1)))
      {
        battlefield[x2][y2] = true;
/*AR*/  
        i=0;
      }
      else
        i=1;
    }while(i!=0);
//do-while keeps generating a 2nd set of coordinates until 
//it makes the ship either vertical or horizontal on gameboard
  }
  
//Constructor for 2nd ship  
  public BSShips (int a1,int b1,int a2, int b2)
  {
    numHits = 0;
    shipIsSunk = false;
    missiles = 0;
//assigns 1st random set of coordinates for second ship - a coordinate 
//that has not already been used by the first ship
    do{
      x1 = rand.nextInt(5);
      y1 = rand.nextInt(5);
      if(!(x1==a1 && y1==b1)&&!(x1==a2 && y1==b2))
      {
        battlefield[x1][y1] = true;
/*AR*/  
        z=0;
      }
      else 
        z=1;
    }while(z!=0); 
//assigns 2nd random set of coordinates for ship second
    do {
      x2 = rand.nextInt(5);
      y2 = rand.nextInt(5);
      i=1;
      if(!(x2==a1 && y2==b1) && !(x2==a2 && y2==b2))
      {
        if(x2==x1 && (y2==(y1+1) || y2==(y1-1)))
        {
          battlefield[x2][y2] = true;
/*AR*/  
          i=0;
        }
        else if (y2==y1 && (x2==(x1+1) || x2==(x1-1)))
        {
          battlefield[x2][y2] = true;
/*AR*/  
          i=0;
        }
        else
          i=1;
      }
    }while(i!=0);//do-while keeps generating a 2nd set of coordinates until 
                 //it makes the ship either vertical or horizontal on gameboard
  }
  
  public String getCoordinates() //returns String of all coordinates of  ship
  {
    return x1+","+y1+" and "+x2+","+y2;
  }
  public int getx1() //gets specific coordinate of a ship (generally only ship 1)
  {
    return x1;
  }
  public int getx2() //gets specific coordinate of a ship (generally only ship 1)
  {
    return x2;
  }
  public int gety1() //gets specific coordinate of a ship (generally only ship 1)
  {
    return y1;
  }
  public int gety2() //gets specific coordinate of a ship (generally only ship 1)
  {
    return y2;
  }
  public boolean getBool(int r, int c) //checks if the button pressed is a hit or miss
  {
    return battlefield[r][c];
  }
  public void setBool (int r, int c) //sets a spot already hit as a miss if clicked again
  {
    battlefield[r][c] = false;
  }
  public void hit(int r, int c) //increases the # of hits a ship has taken
  {
    numHits++;
  } 
  public boolean sunk() //checks to see if a ship has been hit twice-if so declares it sunk
  {
    if (numHits==2)
      shipIsSunk=true;
    return shipIsSunk;
  }
  public void missileFired() //increases the # of missles fired
  {
    missiles++;
  }
  public int missileCounter() //gets # of missles fired
  {
    return missiles;
  }
}