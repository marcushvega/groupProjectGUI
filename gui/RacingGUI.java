package gui;

import classes.*;
import panels.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RacingGUI extends JFrame
{
	private PlayerPanel playerPanel1;  //Reference the Player 1 panel
	private PlayerPanel playerPanel2;  //Reference the Player 2 panel
	
	private JButton raceButton;		  //Reference the RACE! button
	private JButton exitButton;		  //Reference the EXIT button
	private JButton newGameButton;	  //Reference the New Game button
	
	private JPanel buttonPanel;		  //Reference a panel of button
	
	/**
	 * Constructor
	 */
	public RacingGUI()
	{
		//Set size of window
//		setSize(800, 182);
		
		//Set title
		setTitle("Racing Game");
		
		//Specify what to do when the close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Create a Layout Manager
		setLayout(new BorderLayout());
		
		//Create custom panels
		playerPanel1 = new PlayerPanel("1");
		playerPanel2 = new PlayerPanel("2");
		
		//Build button panel
//		buildButton();
		
		//Add the components to the content pane
		add(playerPanel1, BorderLayout.WEST);
		add(playerPanel2, BorderLayout.EAST);
		
		//Pack the contents
		pack();
		
		//Display the window
		setVisible(true);

	}
	
	public static void main(String[] args)
	{
		RacingGUI race = new RacingGUI();
		
		System.out.println(race.getSize());
	}

}
