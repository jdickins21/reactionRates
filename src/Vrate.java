/*
 * Jacob Dickinson
 * Vrate
 * 8/28/15
 * function for points on the graph 
 */
public class Vrate {
	
	/*
	 * returns points for the graph
	 */
	public static double Vfunc(double vMax, double kM, double e, double s){
		double result = 0, stepOne, stepTwo;
		stepOne = vMax * (e / .056);
		stepTwo = s / (s + kM);
		result = stepOne * stepTwo;
		//System.out.print("vMax " + vMax + " " + "kM " + kM + " " + "S " + s + "\n" + "resule " + result + "\n");
		return result;
	}

}
