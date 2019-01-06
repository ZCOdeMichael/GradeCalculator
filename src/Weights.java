import java.util.ArrayList;

public class Weights {

	static double total = 100;
	
	private double weight;
	private ArrayList<Grades> grades;
	
	public Weights(double weight, ArrayList<Grades> grades) {
		this.weight = weight;
		this.grades = grades;
	}

	public static double getTotal() {
		return total;
	}

	public double getWeight() {
		return weight;
	}

	public ArrayList<Grades> getGrades() {
		return grades;
	}
	
	public double calcWeighted() {
		double grade = 0;
		double total = 0;
		
		for(Grades temp : grades) {
			grade += temp.getGrade();
			total += temp.getTotal();
		}
		
		double finalTotal = grade/total;
		
		return finalTotal * (weight/100.0);
	}
}
