
public class Grades {

	private double grade;
	private double total;
	private double weight;
	private String assignmentName;
	
	public Grades(double grade, double total, double weight, String assignmentName) {
		this.grade = grade;
		this.total = total;
		this.weight = weight;
		this.assignmentName = assignmentName;
	}
	
	public Grades(double grade, double total, String assignmentName) {
		this.weight = 0;
		
		this.grade = grade;
		this.total = total;
		this.assignmentName = assignmentName;
	}
	
	public Grades(double grade, double total) {
		this.weight = 0;
		this.assignmentName = "NA";
		
		this.grade = grade;
		this.total = total;
		
	}

	public double getGrade() {
		return grade;
	}

	public double getTotal() {
		return total;
	}

	public double getWeight() {
		return weight;
	}

	public String getAssignmentName() {
		return assignmentName;
	}
	
	
	
}
