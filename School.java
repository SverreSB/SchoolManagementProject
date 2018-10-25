/*
* Title School.java
* Abstract Class having all function to the sample run. Having the option to register, unregister, read data and print out any information asked for by the sample runs
* Author: Sverre Broen
* Date 09/28/2018
 */

package cst338.project.project1.schooldemo;

import java.io.*;
import java.util.Map;

public class School {
    private String schoolName;

    public School(String schoolName) {
        this.schoolName = schoolName;
    }

    public void readData(String fileName){
        //Amount is used to store the amount of instructors, courses and students in a list
        int amount = 0;
        //Counter is used for seperate between instructors, courses and students.
        int counter = 0;

        File file = new File(fileName);
        //Try-catch for reading file, handles IOE and file not found.
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))){

            String line = "";

            //While-loop going through wile until the next line is empty
            while((line = fileReader.readLine()) != null){

                //Checks if there is only integers on a line in the txt file and stores the number as amount.
                if(line.matches("\\d")) {
                    amount = Integer.parseInt(line);

                    //For-loop if if-statements to conclude if the line contains instructor, course or student.
                    for(int i=0; i<amount; i++){

                        //new filereader and a splitline so we can store the split up elements in array through making an object of a class.
                        String newLine = fileReader.readLine();
                        String[] splitLine = newLine.split(",");

                        if(counter == 0){
                            //Looking through instructor keys and checks if employee number exist in hash map
                            if(Instructor.instructors.containsKey(Integer.parseInt(splitLine[0]))){
                                System.out.println("Instructor reading failed - Employee number " + splitLine[0] + " already used");
                            }

                            else {
                                //Stores each split as an element passed through instructors constructor and stored as an object in array
                                Instructor tempInstructor = new Instructor(Integer.parseInt(splitLine[0]), splitLine[1], splitLine[2], splitLine[3]);
                            }
                        }
                        else if(counter == 1){
                            if(Course.courses.containsKey(Integer.parseInt(splitLine[0]))){
                                System.out.println("Course reading failed - Course number " + splitLine[0] + " already used");
                            }

                            else {
                                //Stores each split as an element passed through course constructor and stored as an object in hash map
                                Course tempCourse = new Course(Integer.parseInt(splitLine[0]), splitLine[1], Integer.parseInt(splitLine[2]), splitLine[3]);
                            }
                        }

                        else{
                            //Looking through hash map key to see if studentID is already used
                            if(Student.students.containsKey(Integer.parseInt(splitLine[0]))){
                                System.out.println("Student info reading failed - Student ID " + splitLine[0] + " already used");
                            }

                            else {
                                //Stores each split as an element passed through student constructor and stored as an object in hash map
                                Student tempStudent = new Student(Integer.parseInt(splitLine[0]), splitLine[1]);
                            }
                        }
                    }
                    counter++;
                }
            }

            System.out.println("Done.");

        }catch (FileNotFoundException fnf){
            System.out.println("File not found");
        }catch (IOException ioe){
            System.out.println("Exception error");
        }
    }

    public void schoolInfo(){

        System.out.println("School name: " + schoolName);

        System.out.println("Instructor Information");
        for(Map.Entry<Integer, Instructor> instructorEntry : Instructor.instructors.entrySet()){
            System.out.println("    " + instructorEntry.getValue().getInstructorName());
        }

        System.out.println("Course information");
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            System.out.println("    " + courseEntry.getValue().getCourseNumber() + " " + courseEntry.getValue().getCourseTitle());
        }

        System.out.println("Student information");
        for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
            System.out.println("    " + studentEntry.getValue().getStudentName());
        }

    }
    // Method for finding instructors based on their email. 1. For each-loop iterating through hash map.
    // 2. If - map has an email that equals the parameter then it will print out instructor information using a toString and return true.
    // 3. If for-each is done iterating without finding email in map it will automatically  print out error message and return fals.
    public boolean searchByEmail(String email){

        for(Map.Entry<Integer, Instructor> instructorEntry : Instructor.instructors.entrySet()){

            if(instructorEntry.getValue().getEmail().equals(email)){
                System.out.println("Search key: " + email);
                System.out.println("   Employee Number: " + instructorEntry.getValue().getInstructorID());
                System.out.println("   Name: " + instructorEntry.getValue().getInstructorName());
                System.out.println("   Phone: " + instructorEntry.getValue().getPhoneNumber());

                return true;
            }
        }

        System.out.println("Search key: " + email);
        System.out.println("    No employee with email " + email);

        return false;
    }

    //Method for adding instructor. If instructorID exists in hash map, error message will appear and method returns false.
    //An object of Instructor will be created and added through the constructor in class Instructor and return false if instructorID doesn't exist in has map.
    public boolean addInstructor(int instructorID, String instructorName, String email, String phoneNumber){

        if(Instructor.instructors.containsKey(instructorID)){
            System.out.println("Instructor addition failed - Employee ID " + instructorID + " already used");

            return false;
        }

        Instructor tempInstructor = new Instructor(instructorID, instructorName, email, phoneNumber);

        return true;
    }

    //Method for adding course. If coursenumber exists in hash map, then the method returns false and prints out message that adding course failed
    //If it doesn't return false, then an object og Course will be created and put in hash map through Course constructor
    public boolean addCourse(int courseNumber, String courseTitle, int capacity, String location){

        if(Course.courses.containsKey(courseNumber)){
            System.out.println("Course addition failed - Course number " + courseNumber + " already used");

            return false;
        }


        Course tempCourse = new Course(courseNumber, courseTitle, capacity, location);

        return true;
    }

    //Method for aasigning instructor to a course.
    public void assignInstructor(int courseNumber, int instructorID){
        Course tempCourse;

        if(!Instructor.instructors.containsKey(instructorID)){
            System.out.println("Instructor " + instructorID + " does not exist");
        }

        else if(!Course.courses.containsKey(courseNumber)){
            System.out.println("Course " + courseNumber + " does not exist");
        }

        else{
            for(Map.Entry<Integer, Instructor> instructorEntry : Instructor.instructors.entrySet()){
                if(instructorEntry.getKey() == instructorID){
                    for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
                        if(courseEntry.getKey() == courseNumber){
                            courseEntry.getValue().setInstructor(instructorEntry.getValue().getInstructorName());
                        }
                    }
                }
            }
        }
    }

    //Method for registering students to a class. Iterates through course hashmap to find the course and then going through student map to find student and then check arraylist with students in the class if the student is in the class already. Also checks if class is full.
    public void register(int courseNumber, int studentID){

        if(!Course.courses.containsKey(courseNumber)){
            System.out.println("Course" + courseNumber + "does not exist");
            return;
        }
        if(!Student.students.containsKey(studentID)){
            System.out.println("Student " + studentID + " does not exist.");
            return;
        }

        Course courseList;
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            if(courseEntry.getKey() == courseNumber){
                courseList = courseEntry.getValue();

                for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
                    if(studentEntry.getKey() == studentID){
                        Student s = studentEntry.getValue();

                        if(courseList.getCapacity() <= courseList.studentList.size()){
                            System.out.println("Registration failed - Class is full.");

                        }
                        else if(courseList.studentList.contains(s)){
                            System.out.println("Student: " + studentEntry.getKey() + " - " + studentEntry.getValue().getStudentName() + " is already enrolled in class");
                        }
                        else{
                            courseList.studentList.add(s);
                            courseEntry.getValue().enroll();
                        }
                    }
                }
            }

        }
    }

    public void putScore(int courseNumber, int studentID, double score){

        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            if(courseEntry.getKey() == courseNumber) {
                for (Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()) {
                    Student s = studentEntry.getValue();
                    if (courseEntry.getValue().studentList.contains(s) && studentEntry.getKey() == studentID) {
                        s = studentEntry.getValue();
                        courseEntry.getValue().scoreMap.put(s, score);
                        return;
                    }
                }
            }
        }

        for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
            if(studentEntry.getKey().equals(studentID)){
                System.out.println("Student " + studentID  + " (" + studentEntry.getValue().getStudentName() + ") " + "is not enrolled in " + courseNumber);
            }
        }



    }

    public void unRegister(int courseNumber, int studentID){
        Course courseList = new Course();
        Student stud;

        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            if(courseEntry.getKey() == courseNumber){
                courseList = courseEntry.getValue();
                for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
                    stud = studentEntry.getValue();
                    if(courseEntry.getValue().studentList.contains(stud) && studentEntry.getKey() == studentID){
                        courseEntry.getValue().scoreMap.remove(stud);
                        courseList.studentList.remove(stud);
                        courseEntry.getValue().unregister();
                    }
                }
            }
        }
    }

    public void courseInfo(){
        System.out.println("Number of courses: " + Course.courses.size());
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            System.out.println("    " + courseEntry.getValue().getCourseNumber() + ": " + courseEntry.getValue().getEnrolled() + " enrolled");

        }

    }

    public void courseInfo(int courseNumber){
        if(!Course.courses.containsKey(courseNumber)){
            System.out.println("This is not a course");
            return;
        }

        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
            if(courseEntry.getKey() == courseNumber){
                Course c = courseEntry.getValue();
                c.calculateAverage(courseNumber);
                System.out.println("Course number: " + c.getCourseNumber() + "\n" + "Instructor: " + c.getInstructor() + "\n" +
                "Course title: " + c.getCourseTitle() + "\n" + "Room: " + c.getLocation() + "\n" +
                "Total enrolled: " + c.getEnrolled() + "\n" + "Course average: " + c.getAverage());
            }
        }
    }




    public void deleteCourse(int courseNumber){
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()) {
            if (courseEntry.getValue().getEnrolled() <= 0 && courseEntry.getKey() == courseNumber) {
                Course.courses.remove(courseNumber);
                return;
            }
        }
        System.out.println("Course deletion failed - Enrolled student(s) in the class");
    }

    public void addStudent(int studentID, String name){
        if(Student.students.containsKey(studentID)){
            System.out.println("Student number: " + studentID + " is already in use");
        }
        else{
            Student tempStud = new Student(studentID, name);
        }
    }

    public Course getCourse(int courseNumber){
        for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()) {
            if (courseEntry.getKey() == courseNumber) {
                return courseEntry.getValue();
            }
        }
        return null;
    }

    //I didn't know if the parameter was supposed to be a course number or instructor id, so since 205 is a course, I chose to use courseNumber, even though I feel instructorID would have been more logical.
    public Instructor getInstructor(int courseNumber){
        for(Map.Entry<Integer, Instructor> instructorEntry : Instructor.instructors.entrySet()){
            for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
                if(courseEntry.getValue().getInstructor() == instructorEntry.getValue().getInstructorName()){
                    return instructorEntry.getValue();
                }
            }
        }

        return null;
    }

    //Function returning student after we've been given a student id
    public Student getStudent(int studentID){

        for(Map.Entry<Integer, Student> studentEntry : Student.students.entrySet()){
            if(studentEntry.getKey() == studentID){
                return studentEntry.getValue();
            }
        }
        return null;
    }

    //Removing student from courses, studentlist and score map.
    public void graduateStudent(int studentID){
        boolean isRemoved = false;
        if(Student.students.containsKey(studentID)){
            for(Map.Entry<Integer, Student> studentEntry: Student.students.entrySet()){
                if(studentEntry.getKey() == studentID){
                    for(Map.Entry<Integer, Course> courseEntry : Course.courses.entrySet()){
                        Student s = studentEntry.getValue();
                        if(courseEntry.getValue().studentList.contains(s)){
                            courseEntry.getValue().unregister();
                            courseEntry.getValue().scoreMap.remove(s);
                            Course courseList = courseEntry.getValue();
                            courseList.studentList.remove(s);
                            isRemoved = true;

                        }
                    }
                }
            }
        }
        if(isRemoved){
            Student.students.remove(studentID);
        }
    }


}
