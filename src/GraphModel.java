import org.jfree.data.xy.XYSeries;
/*
 * Jacob Dickinson
 * GraphModel
 * 8/28/15
 * functions to add point to data set 
 */

public class GraphModel {
	private static  double a = 0;
	private static double b = 0;
	
	public static void addPoint(double c, double e, XYSeries series){
		function(c, e);
		series.add(a, b);
	}
	
	private static void function(double first, double second){
		
		a = first;
		b = second;
	}
}
