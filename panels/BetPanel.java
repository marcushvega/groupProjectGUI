package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;		//More border things	
import java.util.*;			//For ArrayList

public class BetPanel extends JPanel
{
	private JLabel errorLabel;		 	//References JLabel for error message
	private JLabel filler;				//Reference JLabel used for spacing
	private JLabel betWages;			//Reference JLabel used for betting
	
//	private JButton betButton;			//Reference JButton for Bet button
	private JButton acceptButton;		//Reference JButton for accepting wager
	private JButton rejectButton;		//Reference JButton for rejecting wager
	private JButton bet10;
	private JButton bet50;
	private JButton bet100;
	private JButton lifeSavingsButton;	//Reference JButton for betting away
														//  your life savings
	
	private JPanel buttPanel;				//Reference panel for bet buttons
	private JPanel acceptWagerPanel;		//Reference panel for accept/reject buttons
	
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
		filler = new JLabel();
		betWages = new JLabel("Place your wager");
		
		//Create border
		setBorder(BorderFactory.createTitledBorder("Place your wager"));
		
		//Create button
//		betButton = new JButton("Bet");
		bet10 = new JButton("Bet $10");
		bet50 = new JButton("Bet $50");
		bet100 = new JButton("Bet $100");
		lifeSavingsButton = new JButton("Bet Away Your Life Savings?");
		acceptButton = new JButton("Accept");
		rejectButton = new JButton("Reject");
		
		//Create betting text field
		betText = new JTextField();
		
		//Create bet button panel
		buttPanel = new JPanel(new GridLayout(1, 3));
		acceptWagerPanel = new JPanel(new GridLayout(1, 2));
		
		//Add buttons to buttPanel
		buttPanel.add(bet10);
		buttPanel.add(bet50);
		buttPanel.add(bet100);
		
		//Add buttons to acceptWagerPanel
		acceptWagerPanel.add(acceptButton);
		acceptWagerPanel.add(rejectButton);
		
		//Create Layout
		// 3 rows 1 column
		setLayout(new GridLayout(3, 1));
		
		//Add components to panel
//		add(errorLabel);
//		add(playerNumLabel);
		add(filler);
		add(buttPanel);
		add(acceptWagerPanel);
//		add(lifeSavingsButton);
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
	
	/**
	 * @return the betText
	 */
	public JTextField getBetText()
	{
		return betText;
	}

	/**
	 * @param betText the betText to set
	 */
	public void setBetText(JTextField betText)
	{
		this.betText = betText;
	}

	/**
	 * Within an ArrayList, put all the buttons declared in BetPanel
	 * @return
	 */
	public ArrayList<JButton> getBetButtons()
	{
		ArrayList<JButton> allButtons = new ArrayList<JButton>(
			Arrays.asList(bet10, bet50, bet100, lifeSavingsButton));
		
		return allButtons;
	}

	/**
	 * @return the acceptButton
	 */
	public JButton getAcceptButton()
	{
		return acceptButton;
	}

	/**
	 * @return the rejectButton
	 */
	public JButton getRejectButton()
	{
		return rejectButton;
	}
	
	
}

