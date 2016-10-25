/*
 * Jacob Dickinson
 * Types
 * 8/28/15
 * data set for wild and mutant types
 * holds an array of these object for the mutant function to switch from wild to mutant  
 */
public class Types {
	private double Km = 0; 
	private double Xmax = 0;
	
	public Types(double k, double x){
		Km = k;
		Xmax = x;
	}
	
	public double getKm(){
		return Km;
	}
	
	public double getXmax(){
		return Xmax;
	}
	
	public static Types[] typeSet(){
		Types[] types = new Types[4];
		
		types[0] = new Types(.335, 3.7); //wild type
		types[1] = new Types(.335, 2.59); //mutant 33% of the time
		types[2] = new Types(.66, 3.7); //mutant 33% of the time
		types[3] = new Types(.66, 2.59); //mutant 33% of the time
		
		return types;
	}
}
