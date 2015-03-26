//Official Name: Jeremy Dennen
//E-mail: jcdennen@syr.edu
//Final Project: Battleship
//Compiler: drJava on PC or mac
//Date: Dec. 2, 2013

/* -Contains main
 * -welcomes user (asks if he/she wants to play), 
 * -assigns user name, 
 * -asks for instructions & loads file, 
 * -& prompts user to play game via game board
 * -gives user the option to quit program
 * -calls BattleshipButtonPanel & BattleshipPanel
 */

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class BSApp
{
  public static void main (String[] args) throws IOException
  {
    String title, name, instructionsAsk, importFile, instructionsLine, instructionsDoc;
    String[] welcomeOptions = {"Yes","No, thanks"};
    String[] options = {"Yes", "No", "Quit"};
    int answer;
    boolean panel = true;
    Scanner fileScan;
    importFile = "instructions.txt";
    instructionsLine = "";
    instructionsDoc = "";
    title="Battleship";
    
//Welcomes user
/*GUI1*/ answer = JOptionPane.showOptionDialog(null, "Welcome to Battleship! \nWould you like to play?",
                                          title, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                          null,  welcomeOptions,  welcomeOptions[0]);
    
    if (answer==JOptionPane.YES_OPTION)
    {
//Assigns name   
      name = JOptionPane.showInputDialog("Please enter your name:");
      
//Asks user for instructions OptionDB
      instructionsAsk = "Would you like to go over the Instructions before playing, "+name+"?";
      answer = JOptionPane.showOptionDialog(null, instructionsAsk,
                                            title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                            null,  options,  options[0]);
//User wants instructions      
      if (answer == JOptionPane.YES_OPTION)
      {
/* I/O */
        fileScan = new Scanner (new File(importFile));
        while (fileScan.hasNext())
        { 
          instructionsLine=fileScan.nextLine();
          instructionsLine+=" \n";
          instructionsDoc+=instructionsLine;
        }
        JOptionPane.showMessageDialog(null,instructionsDoc,"Instructions", JOptionPane.PLAIN_MESSAGE); 
//JFrame that calls the gameboard    
        JFrame gameboard = new JFrame ("Battleship");
        gameboard.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        BattleshipButtonPanel bbp = new BattleshipButtonPanel();
        BattleshipPanel bsp = new BattleshipPanel();
        JPanel primary = new JPanel();
        primary.setBackground(Color.blue);
        primary.add(bsp);
        primary.add(bbp);
        gameboard.getContentPane().add(primary);
        gameboard.pack();
        gameboard.setVisible(true);
        gameboard.setSize(1050,750);
        
      }
//User doesn't want instructions    
      else if (answer == JOptionPane.NO_OPTION)
      {
        JOptionPane.showMessageDialog(null,"Let's play, "+name+"!", "Ready?",JOptionPane.PLAIN_MESSAGE);
//JFrame that calls the gameboard          
        JFrame gameboard = new JFrame ("Battleship");
        gameboard.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        BattleshipButtonPanel bbp = new BattleshipButtonPanel();
        BattleshipPanel bsp = new BattleshipPanel();
        JPanel primary = new JPanel();
        primary.setBackground(Color.blue);
        primary.add(bsp);
        primary.add(bbp);
        gameboard.getContentPane().add(primary);
        gameboard.pack();
        gameboard.setVisible(true);
        gameboard.setSize(1050,750);
      }
//user wants to quit program
      else
        JOptionPane.showMessageDialog(null, "Goodbye!", title, JOptionPane.PLAIN_MESSAGE);
    }    
    else
      JOptionPane.showMessageDialog(null, "Goodbye!", title, JOptionPane.PLAIN_MESSAGE);
  }
}