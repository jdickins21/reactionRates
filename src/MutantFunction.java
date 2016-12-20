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
	public static Types wildType(ActionEvent event, JTextField enzIn, JTextField strtIn, JTextArea velocity, GraphView graph, Types currType, Types[] types){
		
		System.out.println("wild");
		enzIn.setEditable(true);
		strtIn.setText("0");
		velocity.setText("0");
		graph.getSeries().clear();
		currType = types[0];
		return currType;
	}
	
	public static Types mutate1(ActionEvent event, JTextField enzIn, JTextField strtIn, JTextArea velocity, GraphView graph, Types currType, Types[] types){
		
		JCheckBox cb0 = (JCheckBox) event.getSource();
        if(cb0.isSelected()){
			enzIn.setText(".056");
			enzIn.setEditable(false);
			strtIn.setText("0");
			velocity.setText("0");
			graph.getSeries().clear();
			currType = types[1];
		}
		else{
			//System.out.println("OFF");
			currType = wildType(event, enzIn, strtIn, velocity, graph, currType, types);
		}
        //System.out.println("km: " + currType.getKm() + ", " + "xMax: " + currType.getXmax());
		return currType;
	}
	
	public static Types mutate2(ActionEvent event, JTextField enzIn, JTextField strtIn, JTextArea velocity, GraphView graph, Types currType, Types[] types){
		
		JCheckBox cb0 = (JCheckBox) event.getSource();
        if(cb0.isSelected()){
			enzIn.setText(".056");
			enzIn.setEditable(false);
			strtIn.setText("0");
			velocity.setText("0");
			graph.getSeries().clear();
			currType = types[2];
		}
		else{
			//System.out.println("OFF");
			currType = wildType(event, enzIn, strtIn, velocity, graph, currType, types);
		}
        //System.out.println("km: " + currType.getKm() + ", " + "xMax: " + currType.getXmax());
		return currType;
	}
	
	public static Types mutate3(ActionEvent event, JTextField enzIn, JTextField strtIn, JTextArea velocity, GraphView graph, Types currType, Types[] types){
		
		JCheckBox cb0 = (JCheckBox) event.getSource();
        if(cb0.isSelected()){
			enzIn.setText(".056");
			enzIn.setEditable(false);
			strtIn.setText("0");
			velocity.setText("0");
			graph.getSeries().clear();
			currType = types[3];
		}
		else{
			//System.out.println("OFF");
			currType = wildType(event, enzIn, strtIn, velocity, graph, currType, types);
		}
        //System.out.println("km: " + currType.getKm() + ", " + "xMax: " + currType.getXmax());
		return currType;
	}
}
