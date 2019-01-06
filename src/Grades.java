
public class Grades {

	private double grade;
	private double total;
	private String assignmentName;

	public Grades(double grade, double total, String assignmentName) {
		this.grade = grade;
		this.total = total;
		this.assignmentName = assignmentName;
	}
	
	public Grades(double grade, double total) {
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

	public String getAssignmentName() {
		return assignmentName;
	}
	
	
	
}
