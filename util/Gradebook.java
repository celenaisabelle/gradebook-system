/* Class: Gradebook
 * Purpose: Stores a collection of students and their grades.
 *          Computes stats (min, max, average, median),
 *          modify student grades, retrieve student info, and print student info.
 */
package util;

import java.sql.Array;
import java.util.*;

public class Gradebook {
	public Gradebook(){
		listOfStudents = new ArrayList<Student>();
	}
    private ArrayList<Student> listOfStudents;
	public void addStudent(Student student){
		listOfStudents.add(student);
	}

	//Checks if the gradebook is empty
	public boolean isEmpty() {
		return listOfStudents.isEmpty();
	}

	//Finds minimum score across all students
	public int calculateMinScore() {
		return listOfStudents.stream().mapToInt(s -> s.getGrade().getScore()).min().orElse(0);
	}

	//Finds minimum letter across all students
	public String calculateMinLetter() {
		if (listOfStudents.isEmpty()) return "N/A";
		return listOfStudents.stream()
				.min(Comparator.comparingInt(s -> s.getGrade().getScore())) // Get student with min score
				.map(s -> s.getGrade().getLetterGrade()) // Extract letter grade
				.orElse("N/A");
	}

	//Finds maximum letter across all students
	public int calculateMaxScore() {
		if (listOfStudents.isEmpty()) return 0;  // Return 0 instead of "N/A"
		return listOfStudents.stream()
				.max(Comparator.comparingInt(s -> s.getGrade().getScore())) // Find student with min score
				.map(s -> s.getGrade().getScore()) // Extracts score, not letter grade
				.orElse(0);  // Return 0 if no students exist
	}

	//Finds maximum letter across all students
	public String calculateMaxLetter() {
		if (listOfStudents.isEmpty()) return "N/A";
		return listOfStudents.stream()
				.max(Comparator.comparingInt(s -> s.getGrade().getScore())) // Get student with highest score
				.map(s -> s.getGrade().getLetterGrade()) // Extract letter grade of highest score
				.orElse("N/A");
	}

	//calculates the average score
    public double calculateAvg() {
		double sum = 0;
		for(Student s: listOfStudents)
			sum += s.getGrade().getScore();
		return sum / listOfStudents.size();
    }

	//calculates the average letter grade
	public String calculateAverageLetter() {
		int avgScore = (int) Math.round(calculateAvg());
		return Grade.scoreToLetter(avgScore);
	}

	//calculates the median score between all students
    public float calculateMedian() {
		int i = 0, n = listOfStudents.size();
		int[] scores = new int[n];
		for(Student s: listOfStudents)
			scores[i++] = s.getGrade().getScore();
		Arrays.sort(scores);
		if (n % 2 == 0)
			return (scores[n / 2] + scores[n / 2 - 1]) / 2.0f;
		else
			return scores[n / 2];
    }

	//calculates the median letter grade  between all students
	public String calculateMedianLetter() {
		int medianScore = (int) calculateMedian();
		return Grade.scoreToLetter(medianScore);
	}

	//function to allow the user to modify a student's score given user input (PID, score)
	public void changeGrade(int pid, int newScore) {
		for (Student s : listOfStudents) {
			if (s.getPid() == pid) {
				s.setGrade(newScore);
				return;
			}
		}
		System.out.println("Student not found.");
	}

	//return student's name assigned to a PID
	public String getStudentName(int pid) {
		return listOfStudents.stream()
				.filter(s -> s.getPid() == pid)
				.map(s -> s.getFirstName() + " " + s.getLastName())
				.findFirst().orElse("Student not found.");
	}

	//return student's letter grade assigned to a PID
	public String getLetterGrade(int pid) {
		return listOfStudents.stream()
				.filter(s -> s.getPid() == pid)
				.map(s -> s.getGrade().getLetterGrade())
				.findFirst().orElse("Student not found.");
	}

	//prints out all student information stored in order of input
	public void printAllStudents(boolean showLetters) {
		if (listOfStudents.isEmpty()) {
			System.out.println("No students available.");
			return;
		}

		// Adjusted column widths
		int firstNameWidth = 12; // Reduced to avoid extra padding
		int lastNameWidth = 12;  // Slightly smaller to fix Skywalker issue
		int pidWidth = 10;
		int gradeWidth = 5;

		// Print Header row
		System.out.printf("%-" + firstNameWidth + "s %-"
						+ lastNameWidth + "s %-"
						+ pidWidth + "s %-"
						+ gradeWidth + "s\n",
				"First Name", "Last Name", "PID", showLetters ? "Letter Grade" : "Score");

		// Print each student's data
		for (Student s : listOfStudents) {
			System.out.printf("%-" + firstNameWidth + "s %-"
							+ lastNameWidth + "s %-"
							+ pidWidth + "d %-"
							+ gradeWidth + "s\n",
					s.getFirstName(),
					s.getLastName(),
					s.getPid(),
					showLetters ? s.getGrade().getLetterGrade() : s.getGrade().getScore());
		}
	}
}
