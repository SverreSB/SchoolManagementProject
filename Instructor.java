/*
 * Title Instructor.java
 * Abstract Instructor class having all the variables for instructor. Having hash map to store all the instructors also have method to find courses the instructor has.
 * Author: Sverre Broen
 * Date 09/28/2018
 */

package cst338.project.project1.schooldemo;


import java.util.HashMap;
import java.util.Map;

public class Instructor {
    private int instructorID;
    private String instructorName;
    private String email;
    private String phoneNumber;

    public static HashMap<Integer, Instructor> instructors = new HashMap<Integer, Instructor>();

    public Instructor() {
    }

    public Instructor(int instructorID, String instructorName, String email, String phoneNumber) {
        this.instructorID = instructorID;
        this.instructorName = instructorName;
        this.email = email;
        this.phoneNumber = phoneNumber;

        instructors.put(instructorID, this);
    }

    public int getInstructorID() {
        return instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    //Method to return courses a instructor is teaching
    public String coursesTeaching(int instructorID){
        String instructorClasses = "";
        for(Map.Entry<Integer, Instructor> instructorEntry : Instructor.instructors.entrySet()){
            if(instructorEntry.getKey() == instructorID){
                Instructor i = instructorEntry.getValue();
                for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
                    if(i.getInstructorName() == courseEntry.getValue().getInstructor()){
                        instructorClasses += "\n   " + courseEntry.getValue().getCourseNumber() + ": " + courseEntry.getValue().getEnrolled() + " enrolled";
                    }
                }
            }

        }

        return instructorClasses;
    }



    @Override
    public String toString() {
        return "Employee number: " + this.instructorID + "\n" +
                "Name: " + this.instructorName + "\n" +
                coursesTeaching(this.instructorID);
    }
}
