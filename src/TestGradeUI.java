import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//add weight feature
//clean up code


public class TestGradeUI extends JPanel implements ListSelectionListener{

  
	JList list;
	DefaultListModel model;
  
	static ArrayList<Grades> grades = new ArrayList<>();
	
	private JTextField grade;
	private JTextField total;
	private JTextField assignmentName;
	private JTextField weight;
	
	private static JLabel finalTotal;
	private static JLabel textGradeWeight;
	
	private JLabel textGrade;
	private JLabel textTotal;
	private JLabel textAssignment;
	private JLabel textWGrade;
	
	private JButton addButton;
	private JButton removeButton;
	private JButton edit;
	private JButton gradeWeight;
	
	private Handler handler;
  
public TestGradeUI() {
	
	//Setting Layout, added spaces to borders
	setLayout(new BorderLayout());
	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   
	//Set model, created list
    model = new DefaultListModel();
    list = new JList(model);
    
    //Labels show final values
    finalTotal = new JLabel("Final Grade Percentage: 0.0%");
    textGradeWeight = new JLabel("Weight %: 0.0%");
    
    //Labels identify inputs 
    textGrade = new JLabel("Grade Recieved:");
    textTotal = new JLabel("Total Grade:");
    textAssignment = new JLabel("Assign. Name:");
    textWGrade = new JLabel("Weight Update:");
    
    //Initialize handler with addListener to track: grade, total, assignmentName, weight
    handler = new Handler();
    
    //Creates inputs
    grade = new JTextField(5);
    total = new JTextField(5);
    assignmentName = new JTextField(5);
    weight = new JTextField(5);
    
    //Adds actionListeners to the components
    grade.addActionListener(handler);
    total.addActionListener(handler);
    assignmentName.addActionListener(handler);
    weight.addActionListener(handler);
    
    //Sets up ListSelectionListener
    list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    list.addListSelectionListener(this);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(5);
    
    
    JScrollPane pane = new JScrollPane(list);
    
    
    
    addButton = new JButton("Add Grade");
    removeButton = new JButton("Remove Grade");
    edit = new JButton("Edit Grade");
    gradeWeight = new JButton("Update Weight");
    
    edit.addActionListener(handler);
    addButton.addActionListener(handler);
    removeButton.addActionListener(new Remove());
    
    removeButton.setEnabled(false);
    edit.setEnabled(false);

    pane.setPreferredSize(new Dimension(220, 20));
    grade.setPreferredSize(new Dimension(110, 10));
    total.setPreferredSize(new Dimension(110, 10));
    removeButton.setPreferredSize(new Dimension(120, 10));
    addButton.setPreferredSize(new Dimension(120, 10));
    edit.setPreferredSize(new Dimension(120, 10));

    GridLayout layout = new GridLayout(1,0);
    GridLayout layout2 = new GridLayout(0, 1);
    GridLayout layout3 = new GridLayout(1, 0);
    
    layout.setVgap(20);
    layout2.setVgap(10);
    layout3.setHgap(20);
    

    JPanel right = new JPanel(layout2);
    
    JPanel top = new JPanel(layout);
    JPanel middle = new JPanel(layout);
    JPanel bottom = new JPanel(layout);

    
    //pane addButton removeButton grade total finalTotal edit
    
    top.add(addButton);
    top.add(edit);
    top.add(removeButton);
    top.add(gradeWeight);
    
    middle.add(textGrade);
    middle.add(grade);
    middle.add(textTotal);
    middle.add(total);
    middle.add(textAssignment);
    middle.add(assignmentName);
    middle.add(textWGrade);
    middle.add(weight);
    
    bottom.add(finalTotal);
    bottom.add(textGradeWeight);
    
    right.add(top);
    right.add(middle);
    right.add(bottom);
    
    add(pane, BorderLayout.LINE_START);
    add(right, BorderLayout.LINE_END);
  }
  
  public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (list.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            removeButton.setEnabled(false);
	            edit.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            removeButton.setEnabled(true);
	            edit.setEnabled(true);
	            int index = list.getSelectedIndex();
	    		grade.setText("" + grades.get(index).getGrade());
	    		total.setText("" + grades.get(index).getTotal());
	    		assignmentName.setText(grades.get(index).getAssignmentName());
	        }
	    }
	}

  private class Remove implements ActionListener{

	  public void actionPerformed(ActionEvent e) {
		  int index = list.getSelectedIndex();
          model.remove(index);
          grades.remove(index);
          int size = model.getSize();
         
          updateFinalTotal();
          
         
          if (size == 0) { //Nobody's left, disable firing.
              removeButton.setEnabled(false);

          } else { //Select an index.
              if (index == model.getSize()) {
                  //removed item in last position
                  index--;
              }

              list.setSelectedIndex(index);
              list.ensureIndexIsVisible(index);
          }
          
	}
  }
  
  
  private class Handler implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		String temp = "";
		
		if(e.getSource() == edit) {
			if(grade.getText().isEmpty() || total.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill Empty Box");
			}else if(!assignmentName.getText().isEmpty()){
				temp = grade.getText() + " / " + total.getText() + " : " + assignmentName.getText();
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				int index = list.getSelectedIndex();
				grades.remove(index);
				grades.add(index, new Grades(temp1, temp2, assignmentName.getText()));
				
				model.removeElementAt(index);
				model.add(index, temp);
				
				
				list.setSelectedIndex(index);
				updateFinalTotal();
			}else {
				temp = grade.getText() + " / " + total.getText() + " : NA";
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				int index = list.getSelectedIndex();
				grades.remove(index);
				grades.add(index, new Grades(temp1, temp2));
				
				model.removeElementAt(index);
				model.add(index, temp);
				
				
				list.setSelectedIndex(index);
				updateFinalTotal();
			
			}
		}
		
		if(e.getSource() == grade || e.getSource() == total || e.getSource() == addButton || e.getSource() == assignmentName) {
			if(grade.getText().isEmpty() || total.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill Empty Box");
			}else if(!assignmentName.getText().isEmpty()){
				temp = grade.getText() + " / " + total.getText() + " : " + assignmentName.getText();
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				
				grades.add(new Grades(temp1, temp2, assignmentName.getText()));
				model.addElement(temp);	
				updateFinalTotal();
				grade.setText("");
				total.setText("");
				assignmentName.setText("");
			}else {
				temp = grade.getText() + " / " + total.getText() + " : NA";
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				
				grades.add(new Grades(temp1, temp2));
				model.addElement(temp);	
				updateFinalTotal();
				grade.setText("");
				total.setText("");
				assignmentName.setText("");
			}
			
		}
		
		
	}
	  
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("List Model Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new TestGradeUI());
    frame.setSize(1020, 220);
    frame.setVisible(true);
    frame.setResizable(false);
  }


  public static double calcGrades() {
	  double grade = 0;
	  double total = 0;
	  for(int i = 0; i < grades.size(); i++) {
		  grade += grades.get(i).getGrade();
		  total += grades.get(i).getTotal();
	  }
	
	  
	  return (grade / total) * 100;
  }

  private void updateFinalTotal() {
	  finalTotal.setText("Final Grade Percentage: " + Math.round(calcGrades()*100.0)/100.0 + "%");
  }

}