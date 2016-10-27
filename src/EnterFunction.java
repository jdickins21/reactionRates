import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * Jacob Dickinson
 * EnterFunction
 * 8/28/15
 * function to add new data points to the data set and represented on the graph   
 */

public class EnterFunction {

	/*
	 * enter function implemented in master view for enter button
	 * adds points to graph and displays current velocity in text area
	 */
	public static void enter(JTextField enzyme, JTextField substrate, JTextArea velocity, double lastEnz, double lastSub, Types currType, GraphView graph){
		double first = 0, second = 0, vel;
		  
		  try{
			  first = Double.parseDouble(enzyme.getText());
			  second = Double.parseDouble(substrate.getText());
		  }
		  catch(NullPointerException ex){
			  enzyme.setText(Double.toString(lastEnz));
			  substrate.setText(Double.toString(lastSub));
			  return;
		  }catch(NumberFormatException ex){
			  enzyme.setText(Double.toString(lastEnz));
			  substrate.setText(Double.toString(lastSub));
			  return;
		  }
		  
		  lastEnz = first;
		  lastSub = second;
		  
		  vel = Vrate.Vfunc(currType.getXmax(), currType.getKm(), first, second);
		  
		  GraphModel.addPoint(second, vel, graph.getSeries());
		  
		  velocity.setText(Double.toString((double)Math.round(vel * 1000) / 1000));
	}
}
