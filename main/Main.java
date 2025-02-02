/* Program: Gradebook Program
 * Description: This program allows users to enter student data (first name, last name, PID, grade),
 *              and then process commands to retrieve stats such as min, max, average, median scores,
 *              and letter grades. Users can also change student grades and retrieve student details.
 */

package main;
import util.*;
import java.util.*;

public class Main {

     //Validates that the first name starts with an uppercase letter followed by lowercase letters.
    private static boolean checkFirstName(String given){
        return given.matches("[A-Z][a-zA-Z]*");
    }
    //Validates that the last name starts with an uppercase letter, allows letters and periods.
    private static boolean checkLastName(String given){
        return given.matches("[A-Z][a-zA-Z.]*");
    }
    //Validates that the PID is exactly 7 digits and does not start with zero.
    private static boolean checkID(String given){
        return given.matches("[1-9][0-9]{6}");
    }
    //Validates that the score is between 0 and 100.
    private static boolean checkScore(String given){
        return given.matches("\\d{1,3}") && Integer.parseInt(given) >= 0 && Integer.parseInt(given) <= 100;
    }
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Gradebook gradebook = new Gradebook();

        //Welcome message and asking for input
        System.out.println("Welcome to the grade book!");
        System.out.println("Please enter the information of the first student using the following format: ");
        System.out.println("\"firstName lastName PID grade\"");
        System.out.println("Please Enter when you are done.");


        // Input handling loop
        while(true){
            System.out.print("> ");  // Prompt user
            String line = keyboard.nextLine().trim();  // Read input and remove leading/trailing spaces

            if (line.equalsIgnoreCase("DONE")) break; // Stop when "DONE" is entered

            String[] tokens = line.split(" ");
            if (tokens.length != 4) { // Ensure input is exactly 4 parts
                System.out.println("TRY AGAIN: Incorrect format. Please enter: firstName lastName PID grade");
                continue;
            }

            // Validate input
            if (!checkFirstName(tokens[0])) {
                System.out.println("TRY AGAIN: Invalid first name.");
                continue;
            }
            if (!checkLastName(tokens[1])) {
                System.out.println("TRY AGAIN: Invalid last name.");
                continue;
            }
            if (!checkID(tokens[2])) {
                System.out.println("TRY AGAIN: Invalid PID.");
                continue;
            }
            if (!checkScore(tokens[3])) {
                System.out.println("TRY AGAIN: Invalid score.");
                continue;
            }

            // Create and add student to the gradebook
            Student student = new Student(tokens[0],
                    tokens[1],
                    Integer.parseInt(tokens[2]),
                    new Grade(Integer.parseInt(tokens[3])));
            gradebook.addStudent(student);

            // Ask for next information of next student
            System.out.println("Please enter the information of the next student using the same format.");
            System.out.println("If there is no more students, please enter the keyword \"DONE\"");
            System.out.println("Press Enter when you are done.");
        }

        System.out.println("Please enter a new command");

        //command handling phase
        while(true){
            String command = keyboard.nextLine();
            if(command.equals("quit"))
                break;
            if(command.equals("min score")){
                System.out.println("Min score: " + gradebook.calculateMinScore());
            }
            else if(command.equals("min letter")){
                System.out.println("Min letter: " + gradebook.calculateMinLetter());
            }
            else if(command.equals("max score")){
                System.out.println("Max score: " + gradebook.calculateMaxScore());
            }
            else if(command.equals("max letter")){
                System.out.println("Max letter: " + gradebook.calculateMaxLetter());
            }
            else if(command.equals("average score")){
                System.out.println("Average score: " + gradebook.calculateAvg());
            }
            else if(command.equals("average letter")){
                System.out.println("Average letter: " + gradebook.calculateAverageLetter());
            }
            else if(command.equals("median score")){
                System.out.println("Median score: " + gradebook.calculateMedian());
            }
            else if(command.equals("median letter")){
                System.out.println("Median letter: " + gradebook.calculateMedianLetter());
            }
            else if(command.equals("tab scores")){
                gradebook.printAllStudents(false);
            }
            else if(command.equals("tab letters")){
                gradebook.printAllStudents(true);
            }
            else if(command.startsWith("change")){  //Command format: change [PID] [newScore]
                String[] parts = command.split(" ");
                if (parts.length != 3 || !checkID(parts[1]) || !checkScore(parts[2])) {
                    System.out.println("Invalid command format.");
                } else {
                    gradebook.changeGrade(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                }
            }
            else if(command.startsWith("name")){ // Command format: name [PID]
                String[] parts = command.split(" ");
                if (parts.length != 2 || !checkID(parts[1])) {
                    System.out.println("Invalid command format.");
                } else {
                    System.out.println(gradebook.getStudentName(Integer.parseInt(parts[1])));
                }
            }
            else if(command.startsWith("letter")){ // Command format: letter [PID]
                String[] parts = command.split(" ");
                if (parts.length != 2 || !checkID(parts[1])) {
                    System.out.println("Invalid command format.");
                } else {
                    System.out.println(gradebook.getLetterGrade(Integer.parseInt(parts[1])));
                }
            }
            else{
                //print an error message "Illegal command, try again"...
                System.out.println("Illegal command, try again");
            }

            System.out.println("Please enter a new command");
        }
    }

}
