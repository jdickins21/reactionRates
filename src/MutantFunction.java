import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * Jacob Dickinson
 * MutantFunction
 * 8/28/15
 * function to select a mutant subset changing variables of the equation for the graph  
 */

public class MutantFunction {
	/*
	 * function called in masterview in mutant checkbox
	 * changes the Vmax and kM
	 * clears substrate and velocity box as well as the graph
	 * locks enzyme when on
	 */
	public static Types mutate(ActionEvent event, JTextField enzIn, JTextField strtIn, JTextArea velocity, GraphView graph, Types currType, Types[] types){
		Random rand = new Random();
		
		JCheckBox cb = (JCheckBox) event.getSource();
        if(cb.isSelected()){
			enzIn.setText(".056");
			enzIn.setEditable(false);
			strtIn.setText("0");
			velocity.setText("0");
			graph.getSeries().clear();
			int mute = rand.nextInt(3));
			currType = types[mute];
        	//System.out.println("ON\n" + mute + " " + currType.getXmax() + " " + currType.getKm());
		}
		else{
			//System.out.println("OFF");
			enzIn.setEditable(true);
			strtIn.setText("0");
			velocity.setText("0");
			currType = types[0];
		}
		return currType;
	}
}
