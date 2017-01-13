package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import classes.*;
import java.io.IOException;		//Needed for input/output
import java.net.URL;					//To take image from the Internet
import javax.imageio.ImageIO;		//Needed to "read" image into object
import javax.swing.border.*;		//More border things	

public class PlayerPanel extends JPanel
{
	private JPanel txtPanel;		//Reference the text field panel
	private JPanel btnPanel;		//Reference the button panel
	private JLabel nameLabel;		//Reference name label
	private JTextField nameText;	//Reference name TextField
	private JLabel carLabel;		//Reference car label
	private JLabel cashLabel;		//Reference for cash label
	private JTextField cashText;  //Reference for cash TextField
	
	private JLabel newLine;			//Reference for adding another label line		
	private JLabel blankLabel;		//Reference for a blank label
											//Used for padding
	
	//Declare references to the radio buttons
	private ButtonGroup bg;			//Reference a group of radio buttons
	private JRadioButton car1;		//Reference car1
	private JRadioButton car2;		//Reference car2
	private JRadioButton car3;		//Reference car3
	private JRadioButton car4;		//Reference car4
	private JRadioButton car5;		//Reference car5
	
	/**
	 * Constructor
	 */
	public PlayerPanel(String playerNumber)
	{
		//Set size of window
		//Size
		
		//Set Layout Manager
		// 2 columns, 1 row
		setLayout(new GridLayout(1, 2));
		
		//Create the radio buttons
		car1 = new JRadioButton("Pontiac Fiero");
		car2 = new JRadioButton("Suzuki Samurai");
		car3 = new JRadioButton("Delorean DMC 12");
		car4 = new JRadioButton("Glenfrome Facet");
		car5 = new JRadioButton("Yugo 45");
		newLine = new JLabel("(Can't go above 87)");
		
		//Set RIGHT_ALIGNMENT for object newline
		newLine.setAlignmentX(RIGHT_ALIGNMENT);
		
		//Group the radio buttons
		bg = new ButtonGroup();
		bg.add(car1);
		bg.add(car2);
		bg.add(car3);
		bg.add(car4);
		bg.add(car5);
		
		//Set border around the panel
		setBorder(BorderFactory.createTitledBorder("Player " + playerNumber));
		
		//Create the button panel
		btnPanel = new JPanel();
		
		//Set Layout Manager for btnPanel
		//  GridLayout with 5 Rows, 1 column
		btnPanel.setLayout(new GridLayout(6, 1));
		
		//Add the radio buttons to the button panel
		btnPanel.add(car1);
		btnPanel.add(car2);
		btnPanel.add(car3);
		btnPanel.add(newLine);
		btnPanel.add(car4);
		btnPanel.add(car5);
		
		//Create the labels
		nameLabel = new JLabel("Enter Name of Player " + playerNumber + ":");
		cashLabel = new JLabel("Amount of cash: " );
		carLabel = new JLabel("Choose a car: ");
		blankLabel = new JLabel("");
		
		//Create the text fields
		nameText = new JTextField();
		cashText = new JTextField("$100.00");
		
		//Set Text Color to a dark green
		cashText.setDisabledTextColor(Color.decode("#228b22"));
//		cashText.setDisabledTextColor(new Color(0x002c00));
		cashText.setPreferredSize(new Dimension());
		
		//Make the cash field uneditable
		cashText.setEnabled(false);
		
		//Create text panel & set layout
		txtPanel = new JPanel();
		
		//Create inner panels
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));
		topPanel.add(nameLabel);
		topPanel.add(nameText);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));
		bottomPanel.add(cashLabel);
		bottomPanel.add(cashText);
		
		//3 rows, 1 column
		txtPanel.setLayout(new GridLayout(3, 1));
		
		//Add text fields and labels to txtPanel
//		txtPanel.add(nameLabel);
//		txtPanel.add(nameText);
		txtPanel.add(topPanel);
		txtPanel.add(blankLabel);
		txtPanel.add(bottomPanel);
//		txtPanel.add(cashLabel);
//		txtPanel.add(cashText);

		//Add the custom panels to the outer Grid Layout
		add(btnPanel);
		add(txtPanel);
	}
	
	public Player getCarChoice()
	{
		//Initialize Car object
		Car playerCar = new Car("", "");
		
		//Create Player object
		Player player = new Player();
		
		//Select car
		if(car1.isSelected())
		{
			playerCar = new Car("Pontiac", "Fiero");
		}
		else if (car2.isSelected())
		{
			playerCar = new Car("Suzuki", "Samurai");
		}
		else if (car3.isSelected())
		{
			playerCar = new Car("DeLorean", "DMC 12");
		}
		else if (car4.isSelected())
		{
			playerCar = new Car("Glenfrome", "Facet");
		}
		else if (car5.isSelected())
		{
			playerCar = new Car("Yugo", "45");
		}
		
		//Set vehicle
		player.setVehicle(playerCar);
		
		//Return player object
		return player;
	}
	
	/**
	 * Enables or Disables the radio buttons
	 * 
	 * @param trueOrFalse - Set True to enable radio buttons
	 * 						 - Set False to disable radio buttons
	 * 
	 * Unused
	 */
	public void editRadioButtons(boolean trueOrFalse)
	{	
		car1.setEnabled(trueOrFalse);
		car2.setEnabled(trueOrFalse);
		car3.setEnabled(trueOrFalse);
		car4.setEnabled(trueOrFalse);
		car5.setEnabled(trueOrFalse);
		
//		btnPanel.setEnabled(trueOrFalse);
	}
	
	public void editNameText(boolean trueOrFalse)
	{
		//Disable editing of nameText
		nameText.setEnabled(trueOrFalse);
		
		//Set Text Color to black
		nameText.setDisabledTextColor(Color.decode("#228b22"));
	}

	/**
	 * @return the nameText
	 */
	public JTextField getNameText()
	{
		return nameText;
	}

	/**
	 * @param nameText the nameText to set
	 */
	public void setNameText(JTextField nameText)
	{
		this.nameText = nameText;
	}

	/**
	 * @param cashText the cashText to set
	 */
	public void setCashText(JTextField cashText)
	{
		this.cashText = cashText;
	}
	
	

}
