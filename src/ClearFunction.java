import javax.swing.JTextArea;
import javax.swing.JTextField;
/*
 * Jacob Dickinson
 * ClearFunction
 * 8/28/15
 * function to restart dataset   
 */

public class ClearFunction {
	/*
	 * called in MsterView in the clear button
	 * the function clears out all of the data points and returns the graph back to its original view
	 */
	public static void clear(GraphView graph, JTextField enzIn, JTextField strtIn, JTextArea velocity){
		graph.restoreAutoBounds();
		enzIn.setText(".056");
		strtIn.setText("0");
		velocity.setText("0");
		graph.getSeries().clear();
	}
}
