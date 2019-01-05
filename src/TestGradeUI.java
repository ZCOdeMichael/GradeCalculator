
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

public class TestGradeUI extends JPanel implements ListSelectionListener{

  
	JList list;
	DefaultListModel model;
  
	static ArrayList<Grades> grades = new ArrayList<>();
	
	private JTextField grade;
	private JTextField total;
	private JTextField assignmentName;
	  
	private JLabel finalTotal;
	private JLabel textGrade;
	private JLabel textTotal;
	private JLabel textAssignment;
	
	
	private JButton addButton;
	private JButton removeButton;
	private JButton edit;
	  
	private Handler handler;
  
public TestGradeUI() {
	
	
	setLayout(new BorderLayout());
	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   
    model = new DefaultListModel();
    list = new JList(model);
    
    finalTotal = new JLabel("HA YOU SUC!");
    textGrade = new JLabel("Actual Grade:");
    textTotal = new JLabel("Total Grade:");
    textAssignment = new JLabel("Assign. Name:");
    
    handler = new Handler();
    grade = new JTextField(5);
    grade.addActionListener(handler);
    total = new JTextField(5);
    total.addActionListener(handler);
    assignmentName = new JTextField(5);
    assignmentName.addActionListener(handler);
    
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(this);
    list.setSelectedIndex(0);
    list.setVisibleRowCount(5);
    
    
    JScrollPane pane = new JScrollPane(list);
    
    
    
    addButton = new JButton("Add Grade");
    removeButton = new JButton("Remove Grade");
    edit = new JButton("Edit Grade");
    
    
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
    
    layout.setVgap(20);
    layout2.setVgap(10);
    
    JPanel left = new JPanel(new GridLayout(0,1));
    JPanel right = new JPanel(layout2);
    
    JPanel top = new JPanel(layout);
    JPanel bottom = new JPanel(layout);
    JPanel bottomPart2 = new JPanel(layout);
    
    
    //pane addButton removeButton grade total finalTotal edit
    
    top.add(addButton);
    top.add(edit);
    top.add(removeButton);
    
    bottom.add(textGrade);
    bottom.add(grade);
    bottom.add(textTotal);
    bottom.add(total);
    bottom.add(textAssignment);
    bottom.add(assignmentName);
    
    bottomPart2.add(finalTotal);
    
    right.add(top);
    right.add(bottom);
    right.add(bottomPart2);
    
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
          System.out.println(index);
          int size = model.getSize();
          finalTotal.setText("" + calcGrades(grades));
          
          /* After remove still selects last element
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
          */
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
				model.removeElementAt(index);
				model.add(index, temp);
				
				grades.remove(index);
				grades.add(index, new Grades(temp1, temp2, assignmentName.getText()));
			}else {
				temp = grade.getText() + " / " + total.getText() + " : NA";
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				int index = list.getSelectedIndex();
				
				model.removeElementAt(index);
				model.add(index, temp);
				
				grades.remove(index);
				grades.add(index, new Grades(temp1, temp2));
			
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
				finalTotal.setText("Final Grade Percentage: " + Math.round(calcGrades(grades)*100.0)/100.0 + "%");
				grade.setText("");
				total.setText("");
				assignmentName.setText("");
			}else {
				temp = grade.getText() + " / " + total.getText() + " : NA";
				Double temp1 = (double) Double.parseDouble(grade.getText());
				Double temp2 = (double) Double.parseDouble(total.getText());
				
				grades.add(new Grades(temp1, temp2));
				model.addElement(temp);	
				finalTotal.setText("Final Grade Percentage: " + Math.round(calcGrades(grades)*100.0)/100.0 + "%");
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
    frame.setSize(800, 200);
    frame.setVisible(true);
    frame.setResizable(false);
  }


  public static double calcGrades(ArrayList<Grades> grades) {
	  double grade = 0;
	  double total = 0;
	  for(int i = 0; i < grades.size(); i++) {
		  grade += grades.get(i).getGrade();
		  total += grades.get(i).getTotal();
	  }
	  
	  
	  return (grade / total) * 100;
  }



}