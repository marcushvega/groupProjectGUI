package panels;

import javax.swing.*;		//Needed for swing components
import java.awt.*;			//Needed for layout manager(s)
import java.util.*;			//Needed for ArrayList class

/**
 * MultiLabelPanel creates panels with 
 * GridLayout(X rows, 1 column)
 *  so you don't have to 
 */

public class MultiLabelPanel extends JPanel
{
	private ArrayList<JLabel> labels;		//Reference the labels to hold
	private JPanel panelOut;					//Reference the returned panel	
	private Font font = new Font("Arial", Font.BOLD, 36);	//Reference type of font of message
	
	/**
	 * Constructor
	 * Default font is Arial bold
	 * @param messages The messages to go into the panel
	 */
	public MultiLabelPanel(String... messages)
	{
		//Create ArrayList
		labels = new ArrayList<JLabel>();
		
		//Create new labels per message
		for(String item: messages)
			labels.add(new JLabel(item, SwingConstants.CENTER));
		
		//Set font to Arial bold
		for(JLabel item: labels)
			item.setFont(font);
			
		//Create JPanel
		panelOut = new JPanel();
		//Create grid based on number of messages
		panelOut.setLayout(new GridLayout(labels.size(), 1)); 
		
		//Add labels to JPanel panelOut
		for(JLabel item: labels)
		{
			panelOut.add(item);
		}
	}
	
	/**
	 * Constructor
	 * @param messages The messages to go into the panel
	 * @param font The font of ALL messages
	 */
	public MultiLabelPanel(Font customFont, String... messages)
	{
		//Create new labels per message
		for(String item: messages)
			labels.add(new JLabel(item, SwingConstants.CENTER));
			
		//Set font to customFont
		for(JLabel item: labels)
			item.setFont(font);
		
		//Create JPanel
		panelOut = new JPanel();
		//Create grid based on number of messages
		panelOut.setLayout(new GridLayout(labels.size(), 1)); 
		
		//Add labels to JPanel panelOut
		for(JLabel item: labels)
		{
			panelOut.add(item);
		}
	}
	
	/**
	 * getPanel method
	 * @return The multi-row panel
	 */
	public JPanel getPanel()
	{
		return panelOut;
	}
}
