/*
 * Title Course.java
 * Abstract Course class having all the variables for course. Having hash map to store all the courses also have studentlist and score hash map to store students and scores.
 * Author: Sverre Broen
 * Date 09/28/2018
 */

package cst338.project.project1.schooldemo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private int courseNumber;
    private String courseTitle;
    private int capacity;
    private String location;
    private String instructor;
    private int enrolled;
    private double average;
    public ArrayList<Student> studentList = new ArrayList<Student>();
    public HashMap<Student, Double> scoreMap = new HashMap<Student, Double>();

    public static HashMap<Integer, Course> courses = new HashMap<Integer, Course>();

    public Course(){

    }

    public Course(int courseNumber, String courseTitle, int capacity, String location, String instructor) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.location = location;
        this.instructor = instructor;

        courses.put(courseNumber, this);
    }

    public Course(int courseNumber, String courseTitle, int capacity, String location) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.capacity = capacity;
        this.location = location;
        this.instructor = "UNKOWN";

        courses.put(courseNumber, this);
    }


    public void calculateAverage(int courseNumber){
        double score = 0;
        int i = 0;
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            if(courseEntry.getKey() == courseNumber){
                for(Map.Entry<Student, Double> scoreEntry : scoreMap.entrySet()){

                    score += scoreEntry.getValue();
                    i++;
                }
            }
        }


        this.average = score/this.scoreMap.size();
    }

    public void updateLocation(String newLocation){
        this.location = newLocation;
    }



    public void unregister(){
        this.enrolled--;
    }
    public void enroll(){
        this.enrolled ++;
    }




    @Override
    public String toString() {
        return "Course number: " + courseNumber + "\n" +
                "Instructor: " + instructor + "\n" +
                "Course title: " + courseNumber + " - " + courseTitle + "\n" +
                "Room: " + location + "\n" +
                "Total enrolled: " + enrolled + "\n" +
                "Course average: " + average + "\n";
    }


    //The never ending list of getters and setters. Stop here.....


    public int getCourseNumber() {
        return courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCapacity() {
        return capacity;
    }


    public String getLocation() {
        return location;
    }


    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public double getAverage() {
        return average;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }
}




