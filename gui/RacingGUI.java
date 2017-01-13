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
import java.applet.*; //For music

public class RacingGUI extends JFrame
{
	private JFrame fireworksFrame;	  //Reference Fireworks window
	
	private JLabel winnerLabel;		  //Reference Winner message
	
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
		
//		raceButton.addActionListener(new buttonListener());
//		exitButton.addActionListener(new buttonListener());
//		newGameButton.addActionListener(new buttonListener());

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
		
		//Create the acceptWager button
		// Also create blank filler panels
		acceptWagerButton = new JButton("Accept Wager");
		JLabel filler1 = new JLabel(" ");
//		JPanel filler2 = new JPanel();
		
		//Create wager text field
		wagerTxt = new JTextField("0.00");
		
		//Add wager button, wager text field, and fillers 
		//   to CENTER panel
		centerPanel.add(wagerTxt);
		centerPanel.add(filler1);
		centerPanel.add(acceptWagerButton);
		
		//SOUTH PANEL add is in the buildButtonPanel() method
		
	}
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//RACE!! button is clicked
			if (e.getSource() == raceButton)
			{
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
				
				//Start playing music
//				playMusic();
				
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
			}
			//Close button on fireworks screen is clicked
			else if (e.getSource() == clFireworksButton)
			{
				//Remove window from view
				fireworksFrame.setVisible(false);
				
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
		}
	}
	
	public void theFireworks()
	{
		//Add GIF to the an object
		ImageIcon icon = new ImageIcon("fireworks.gif");
		JLabel fireworks = new JLabel(new ImageIcon("fireworks.gif"), JLabel.CENTER);
		JLabel label1 = new JLabel();
		fireworks.setIcon(icon);
		
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
		fireworksFrame.add(fireworks, BorderLayout.CENTER);
		fireworksFrame.add(clFireworksButton, BorderLayout.SOUTH);
		fireworksFrame.add(winnerLabel, BorderLayout.NORTH);
		
	   fireworksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   fireworksFrame.pack();
	   fireworksFrame.setLocationRelativeTo(null);
	   fireworksFrame.setVisible(true);
//		
//	   frame.setIconImage(new ImageIcon(RacingGUI.class.getResource("/fireworks.gif")).getImage());
	
	}
	
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
	 * Initialize allButtons ArrayList to 
	 * contain ALL THE BUTTONS!!!*
	 * 
	 *  *All the buttons that exist in the initial window
	 */
	public void arrayListTheButtons()
	{
		allButtons = new ArrayList<JButton>(
				Arrays.asList(raceButton, exitButton, newGameButton, 
						acceptWagerButton));	
		
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
	public void playMusic()
	{
		//Gets the resource from folder
		URL url = RacingGUI.class.getResource("Never-Gonna-Give-You-Up.wav");
		AudioClip clip = Applet.newAudioClip(url);
		
		//Plays the clip
		clip.play();
	}
	
	/**
	 * Stops the music
	 */
	public void stopMusic()
	{
		//Gets the resource from folder
		URL url = RacingGUI.class.getResource("Never-Gonna-Give-You-Up.wav");
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
		
		//Winner is calculated until someone DOES win
		if (p1Time > p2Time)
			winner = player1.getName();
		else if (p2Time > p1Time)
			winner = player2.getName();
		else 
			calcWinner();
		
		//Create new font for the label
		Font font = new Font("Arial", Font.BOLD, 36);
		
		winnerLabel = new JLabel("The winner is " + winner, SwingConstants.CENTER);
		
		//Set font for winnerLabel
		winnerLabel.setFont(font);
	}
	
	public static void main(String[] args)
	{
		RacingGUI race = new RacingGUI();
		
		System.out.println(race.getSize());
	}

	
}
