/* Class: Student
 * Purpose: Represents a student with a first name, last name, unique PID, and a Grade object.
 */

package util;

public class Student {
    private String firstName;
    private String lastName;
    private int pid;
    private Grade grade;

    //Student constructor
    public Student(String firstName, String lastName, int pid, Grade grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pid = pid;
        this.grade = grade;
    }

    //converts the score to a letter grade.
    public void setGrade(int score) {
        this.grade = new Grade(score);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getPid() {
        return pid;
    }
    public Grade getGrade() {
        return grade;
    }
}
