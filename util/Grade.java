/* Class: Grade
 * Purpose: Represents a student's grade score and its letter grade.
 */

package util;

public class Grade {
    private int score;
    private String letterGrade;

    public static String scoreToLetter(int score){
        //grade breakdown according to course syllabus
        if (score >= 95) return "A";
        else if (score >= 90) return "A-";
        else if (score >= 87) return "B+";
        else if (score >= 83) return "B";
        else if (score >= 80) return "B-";
        else if (score >= 77) return "C+";
        else if (score >= 70) return "C";
        else if (score >= 60) return "D";
        else return "F"; // 59 or less
    }

    //Constructor to create a Grade object.
    public Grade(int score) {
        this.score = score;
        this.letterGrade = scoreToLetter(score);
    }

    public int getScore() {
        return score;
    }
    public String getLetterGrade() {
        return letterGrade;
    }
}
