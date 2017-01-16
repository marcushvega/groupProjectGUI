package gui;

import classes.*;
import panels.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*; //For URL and MalformedURLException classes
						 //  For putting images / gif into panel
import java.util.*;
import java.util.List;
import java.applet.*; //For music

/**
 * REMEMBER TO DO TITLE SCREEN
 * @author ThinLlama
 *
 */

public class RacingGUI extends JFrame
{
	private JPanel winnerPanel;		  //Reference winning message
	
	private JFrame fireworksFrame;	  //Reference Fireworks window
	
	private JLabel winnerLabel;		  //Reference Winner message
	private JLabel winnerLabel2;		  //Reference part 2 of Winner message
	
	private Player player1;				  //Reference player 1
	private Player player2;				  //Reference player 2
	
	private PlayerPanel playerPanel1;  //Reference the Player 1 panel
	private PlayerPanel playerPanel2;  //Reference the Player 2 panel
	private BetPanel betPanel1;		  //Reference panel for betting
	private BetPanel betPanel2;		  //Reference panel for betting
	
	private JPanel northPanel;			  //Reference what will be the NORTH panel
	private JPanel westPanel;			  //Reference what will be the WEST panel
	private JPanel centerPanel;		  //Reference what will be the CENTER panel
	private JPanel eastPanel;			  //Reference what will be the EAST panel
	private JPanel southPanel;			  //Reference what will be the SOUTH panel
	private JPanel buttonPanel;		  //Reference a panel of button
	
	private JButton raceButton;		  //Reference the RACE! button
	private JButton exitButton;		  //Reference the EXIT button
	private JButton newGameButton;	  //Reference the New Game button
	private JButton acceptWagerButton; //Reference the Accept Wager button
	private JButton clFireworksButton; //Reference button to close fireworks window
	
	private ArrayList<JButton> allButtons;	//Reference array to hold all buttons
	
	private JTextField wagerTxt;		  //Reference Text Field for wager
	
	private boolean theFight = false;  //Flags true if the players should fight		
	
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
		
		//Build the layout 
		buildLayout();
		
		//THIS MUST BE THE LAST PANEL TO BE BUILT
		//Build button panel
		buildButton();
		
		//Add the components to the content pane
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.EAST);
		
		//Pack the contents
		pack();
		
		//Start playing music
		playMusic("astley.wav");
		
		//Display the window
		setVisible(true);

	}
	
	/**
	 * Build button panel
	 */
	public void buildButton()
	{		
		buttonPanel = new JPanel();
		
		raceButton = new JButton("RACE!!");
		exitButton = new JButton("Exit (If You Are Scared)");
		newGameButton = new JButton("New Race");

		//Add buttonListeners to all buttons
		//Put all the buttons into an ArrayList<JButton>
		arrayListTheButtons();
		
		buttonPanel.add(raceButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(newGameButton);
		
		//Put buttonPanel in southPanel
		southPanel = new JPanel();
		southPanel.add(buttonPanel);
	}
	
	/**
	 * Builds the panels that need to go into the
	 *   NORTH, WEST, CENTER, EAST, and SOUTH panels
	 */
	public void buildLayout()
	{
		//Create custom panels
		playerPanel1 = new PlayerPanel("1");
		playerPanel2 = new PlayerPanel("2");
		
		//Create the NORTH panel
		//  Contains 1 Rows, 2 Columns
		northPanel = new JPanel(new GridLayout(1, 2));
		
		//Create the WEST panel and betPanel1
		westPanel = new JPanel();
		betPanel1 = new BetPanel("1");
		
		//add custom panels to WEST panel
		westPanel.add(betPanel1);
		
		//Create the EAST panel and betPanel2
		eastPanel = new JPanel();
		betPanel2 = new BetPanel("2");
		
		//add custom panels to EAST panel
		eastPanel.add(betPanel2);
		
		//add custom panels to NORTH panel
		northPanel.add(playerPanel1);
		northPanel.add(playerPanel2);
		
		//Create Center panel
		// with 3 Rows and 1 Column
		centerPanel = new JPanel(new GridLayout(3, 1));
		
//		//Create the acceptWager button
		// Also create blank filler panels
//		acceptWagerButton = new JButton("Accept Wager");
		JLabel filler1 = new JLabel(" ");
//		JPanel filler2 = new JPanel();
		
		//Create wager text field
		wagerTxt = new JTextField("0.00",7);
		wagerTxt.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Add wager button, wager text field, and fillers 
		//   to CENTER panel
		centerPanel.add(wagerTxt);
		centerPanel.add(filler1);
//		centerPanel.add(acceptWagerButton);
		
		//SOUTH PANEL add is in the buildButtonPanel() method
		
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//RACE!! button is clicked
			if (e.getSource() == raceButton)
			{	
				//Stop current music
				stopMusic("astley.wav");
				
				playerPanel1.editNameText(false);
				playerPanel2.editNameText(false);
				
				//Create Player objects
				player1 = new Player();
				player2 = new Player();
				
				//Set player vehicles
				player1 = playerPanel1.getCarChoice();
				player2 = playerPanel2.getCarChoice();
				
				//Set player names
				player1.setName(playerPanel1.getNameText());
				player2.setName(playerPanel2.getNameText());
				
				theFireworks();

			}
			//EXIT button is clicked
			else if (e.getSource() == exitButton)
			{
				//Closes the application
				System.exit(0);
			}
			//New Game button is clicked
			else if (e.getSource() == newGameButton)
			{
				//Players, once again, are allowed to change their name
				playerPanel1.editNameText(true);
				playerPanel2.editNameText(true);
				
				//Car selection is reset
				playerPanel1.getBtnGroup().clearSelection();
				playerPanel2.getBtnGroup().clearSelection();
				
				//Starts music again
				playMusic("astley.wav");
			}
			//Close button on fireworks screen is clicked
			else if (e.getSource() == clFireworksButton)
			{
				//Remove window from view
				fireworksFrame.setVisible(false);
				
				//Stop the fireworks sound
				stopMusic("fireworks.wav");
				
				//Dispose window from memory
				fireworksFrame.dispose();
			}
			//Either "Bet $10" button is clicked
			else if (e.getSource() == betPanel1.getBet10()
					|| e.getSource() == betPanel2.getBet10())
			{
				//Add $10.00 to the wager
				addToWager(10);
			}
			//Either "Bet $50" button is clicked
			else if (e.getSource() == betPanel1.getBet50() 
					|| e.getSource() == betPanel2.getBet50())
			{
				//Add $50.00 to the wager
				addToWager(50);
			}
			//Either "Bet $100" button is clicked
			else if (e.getSource() == betPanel1.getBet100()
					|| e.getSource() == betPanel2.getBet100())
			{
				//Add $100.00 to the wager
				addToWager(100);
			}
			else if (e.getSource() == betPanel1.getRejectButton()
					|| e.getSource() == betPanel2.getRejectButton())
			{
				//Clear the bet
				clearWager();
			}
		}
	}
	
	public void theFireworks()
	{	
		//Create new window
		fireworksFrame = new JFrame();

		//Set layout
		setLayout(new BorderLayout());
		
		//Create button
		clFireworksButton = new JButton("Close");
		clFireworksButton.addActionListener(new ButtonListener());
		
		//Calculate the winner of the race
		calcWinner();
		
		//Add components to frame
		//Center Panel is set during calcWinner()
		fireworksFrame.add(clFireworksButton, BorderLayout.SOUTH);
		fireworksFrame.add(winnerPanel, BorderLayout.NORTH);
		
	   fireworksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   fireworksFrame.pack();
	   fireworksFrame.setLocationRelativeTo(null);
	   fireworksFrame.setVisible(true);
//		
//	   frame.setIconImage(new ImageIcon(RacingGUI.class.getResource("/fireworks.gif")).getImage());
	
	}
	
	/**
	 * Adds the bet to the wager
	 * @param money
	 */
	public void addToWager(double money)
	{
		double wager; 
//		String delim = "$";
//		String[] amount;
		
		//Get current wager & add 10
		wager = Double.parseDouble(wagerTxt.getText()) 
				+ money;
		
		//Set new Wager
		wagerTxt.setText("" + wager);
	}
	
	/**
	 * Clears wager
	 */
	public void clearWager()
	{
		wagerTxt.setText("0.00");
	}
	
	/**
	 * Initialize allButtons ArrayList to 
	 * contain ALL THE BUTTONS!!!*
	 * 
	 *  *All the buttons that exist in the initial window
	 */
	public void arrayListTheButtons()
	{
		allButtons = new ArrayList<JButton>(
				Arrays.asList(raceButton, exitButton, newGameButton));	
		
		//Add more buttons
		addMoreButtons();
		
		enhFor(allButtons);
	}
	
	/**
	 * Add buttons from the other panel(s)
	 *   into the allButtons ArrayList<JButton>
	 */
	public void addMoreButtons()
	{
		for(JButton item: betPanel1.getBetButtons())
			allButtons.add(item);
		
		for(JButton item: betPanel2.getBetButtons())
			allButtons.add(item);
	}
	
	/**
	 * Add an ActionListener to ALL THE BUTTONS!!!
	 * @param buttons The ArrayList containing the buttons
	 */
	public void enhFor(ArrayList<JButton> buttons)
	{
		for(JButton item: buttons)
			item.addActionListener(new ButtonListener());
	}
	
	/**
	 * Starts playing music
	 */
	public void playMusic(String sound)
	{
		//Gets the resource from folder
		URL url = RacingGUI.class.getResource(sound);
		AudioClip clip = Applet.newAudioClip(url);
		
		//Plays the clip
		clip.play();
	}
	
	/**
	 * Stops the music.
	 */
	public void stopMusic(String sounds)
	{
		//Gets the resource from folder
		URL url = RacingGUI.class.getResource(sounds);
		AudioClip clip = Applet.newAudioClip(url);
			
			//Stops the clip
			clip.stop();
		
	}
	
	/**
	 * Calculates the winner
	 * 
	 * @return The winner of the race
	 */
	public void calcWinner()
	{
		//Vars to hold 1/4 mile times of vehicles
		double p1Time = player1.getVehicle().getTime();
		double p2Time = player2.getVehicle().getTime();
		
		//To hold name of the winner
		String winner = null;
		//To hold the unfortunate event that occurred
		String message = "le null";	//Kept getting error when var set to null
		String message2 = null;
		String message3 = null;
		
		//Winner is calculated until someone DOES win
		if (p1Time > p2Time)
			winner = player1.getName();
		else if (p2Time > p1Time)
			winner = player2.getName();
		else if (p2Time == p1Time)
			message = "tie";
		
		//A series of unfortunate events should someone race without a car
		if (p1Time == -99 && p2Time == -99)
		{
			//Set messages
			message = "Both players have rejected cars and have";
			message2 = "decided to duke it out the old-fashioned way:";
			message3 = "With their fists";
			
			//Create panel full of messages
			winnerPanel = 
					new MultiLabelPanel(
							message, message2, message3).getPanel();

			//Set image to cartoons fighting
			setIcon("fight.gif");

		}
		else if (p1Time == -99)
		{
			//Play fireworks sounds
			playMusic("fireworks.wav");
			
			message = player1.getName() + " decided to race without a car.";
			
			if (player1.getName().contains("man"))
				message2 = player1.getName() + " miraculously won!";
			else
				message2 = player1.getName() + " lost, obviously.";
			
			//Create panel with message
			winnerPanel = 
					new MultiLabelPanel(message).getPanel();
			
			//Create panel full of messages
			winnerPanel = 
					new MultiLabelPanel(
							message, message2).getPanel();
			
			//Set image to a fireworks gif
			setIcon("fireworks.gif");
	
		}
		else if (p2Time == -99)
		{
			message = player2.getName() + " was tragically attacked by a yeti";
			message2 = "after attempting to race without a car";
			
			//Create panel full of messages
			winnerPanel = 
					new MultiLabelPanel(
							message, message2).getPanel();
			
			//Set image to a yeti
			setIcon("yeti.jpg");
		}
		//If both players chose cars
		else if (message.equalsIgnoreCase("Tie"))
		{
			message = "There was a tie.";
		}
		else
		{
			message = "The winner is " + winner;
			
			//Create panel with message
			winnerPanel = 
					new MultiLabelPanel(message).getPanel();
			
			//Set icon to fireworks gif
			setIcon("fireworks.gif");
		}
		
		//Remove the wager amount from the loser's money
		//Add the wager amount to the winner's money
		//Set the TextFields as well
		if (player1.getName() == winner)
		{
			//Set Player objects' cash fields
			player1.setCash(Double.parseDouble(wagerTxt.getText()));
			player2.setCash(-1 * Double.parseDouble(wagerTxt.getText()));
		}
		else 
		{
			//Set Player objects' cash fields
			player2.setCash(Double.parseDouble(wagerTxt.getText()));
			player1.setCash(-1 * Double.parseDouble(wagerTxt.getText()));
		}
		
		//Set TextField objects' text
		playerPanel1.setCashText("" + player1.getCash());
		playerPanel2.setCashText("" + player2.getCash());
	}
	
	/**
	 * Sets the icon at the end screen
	 *   --The end screen is called fireworksFrame
	 * @param outcome The outcome of the choices
	 */
	public void setIcon(String outcome)
	{
		ImageIcon fightIcon = new ImageIcon(outcome);
		JLabel endRace = new JLabel(new ImageIcon(outcome), JLabel.CENTER);
		endRace.setIcon(fightIcon);
		fireworksFrame.add(endRace, BorderLayout.CENTER);
	}
	
	public static void main(String[] args)
	{
		RacingGUI race = new RacingGUI();
		
		System.out.println(race.getSize());
	}

	
}
