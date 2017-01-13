package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;		//More border things	
import java.util.*;			//For ArrayList

public class BetPanel extends JPanel
{
	private JLabel errorLabel;		 	//References JLabel for error message
	private JLabel playerNumLabel;  	//Reference JLabel for Player Number
	
//	private JButton betButton;				//Reference JButton for Bet button
	private JButton bet10;
	private JButton bet50;
	private JButton bet100;
	private JButton lifeSavingsButton;	//Reference JButton for betting away
														//  your life savings
	
	private JPanel buttPanel;
	
	private JTextField betText;		//Reference text field for betting
	
	private String errorString = null;	//Initialize errorString

	/**
	 * Constructor
	 * @param playerNumber Sets various texts
	 */
	public BetPanel(String playerNumber)
	{
		//Create labels
		errorLabel = new JLabel(errorString);
		
		//Create border
		setBorder(BorderFactory.createTitledBorder("Player" + playerNumber));
		playerNumLabel = new JLabel("Player " + playerNumber);
		
		//Create button
//		betButton = new JButton("Bet");
		bet10 = new JButton("Bet $10");
		bet50 = new JButton("Bet $50");
		bet100 = new JButton("Bet $100");
		lifeSavingsButton = new JButton("Bet Away Your Life Savings?");
		
		//Create betting text field
		betText = new JTextField();
		
		//Create bet button panel
		buttPanel = new JPanel(new GridLayout(1, 3));
		
		//Add buttons to buttPanel
		buttPanel.add(bet10);
		buttPanel.add(bet50);
		buttPanel.add(bet100);
		
		//Create Layout
		// 3 rows 1 column
		setLayout(new GridLayout(3, 1));
		
		//Add components to panel
//		add(errorLabel);
//		add(playerNumLabel);
		add(buttPanel);
//		add(betButton);
		add(lifeSavingsButton);
	}

	/**
	 * @return the bet10
	 */
	public JButton getBet10()
	{
		return bet10;
	}

	/**
	 * @return the bet50
	 */
	public JButton getBet50()
	{
		return bet50;
	}

	/**
	 * @return the bet100
	 */
	public JButton getBet100()
	{
		return bet100;
	}
	
	public ArrayList<JButton> getBetButtons()
	{
		ArrayList<JButton> allButtons = new ArrayList<JButton>(
			Arrays.asList(bet10, bet50, bet100, lifeSavingsButton));
		
		return allButtons;
	}
}
