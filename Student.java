/*
 * Title Instructor.java
 * Abstract Student class having all the variables for students. Having hash map to store all the students also have method to print out classes students is enrolled in and to find the scores in the classes
 * Author: Sverre Broen
 * Date 09/28/2018
 */


package cst338.project.project1.schooldemo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class Student {
    private int studentId;
    private String studentName;


    public static HashMap<Integer, Student> students = new HashMap<Integer, Student>();

    public Student() {
    }

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;

        students.put(studentId, this);
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
    //String method called in to string to get courses a student is enrolled in
    public String enrolled(int studentId){
        String enrollment = "";
        boolean studentFound = false;
        Course score;



        for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
            Student s = studentEntry.getValue();
            for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
                if(courseEntry.getValue().studentList.contains(s)){
                    enrollment += "\n   " + courseEntry.getValue().getCourseNumber() + ": " + studentScores(studentId);
                }
            }
        }
        return enrollment;
    }

    //String method to get students scores. Called in method enrolled.
    public String studentScores(int studentId){
        Student stud = new Student();
        boolean studentFound = false;
        String stringScore = "";
        for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
            if(studentEntry.getKey().equals(studentId)){
                stud = studentEntry.getValue();
                studentFound = true;
            }
        }

        if(studentFound){

            for(Map.Entry<Integer, Course> courseEntry: Course.courses.entrySet()){
                for(Map.Entry<Student, Double> scoreEntry : courseEntry.getValue().scoreMap.entrySet()){
                    if(scoreEntry.getKey() == stud && courseEntry.getValue().studentList.contains(stud)){
                        stringScore = String.valueOf(scoreEntry.getValue());
                    }
                }
            }
        }
        return stringScore;
    }


    @Override
    public String toString() {

        return "Student number: " + this.studentId + " \n" + "Name: " + studentName + "\nCourses enrolled: " + enrolled(this.studentId);
    }
}

