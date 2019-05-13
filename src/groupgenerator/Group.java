/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupgenerator;
import java.util.ArrayList; // import the ArrayList class
/**
 * Class that will be used to create the group instances
 * This class has two attributes, an ArrayList to store the students, and a 
 * String to store the name of the group, just for display purposes
 * @author 23781271
 */
public class Group {
    private ArrayList<Student> students;
    private String name;

    /**
     * When the group is created both the name and students will be attributed
     * @param studentList feeds the ArrayList to the students variable
     * @param Name feeds the String that will be the group name
     */
    public Group(ArrayList<Student> studentList, String Name){
        students = studentList;
        name = Name;
    }

    /**
     * Returns the name of the group as String
     * @return this Group's name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the students of the group as ArrayList
     * @return this Group's students ArrayList
     */
    public ArrayList<Student> getStudents(){
        return students;
    }

    /**
     * Returns the student's information such as name, age and skill to be
     * displayed in a list
     * @return this Student's info as String[]
     */
    public String[] getStudentsInfo(){
        String[] studentInfo = new String[students.size()];
        for(int i = 0; i < students.size();i++){
            Student temp = students.get(i);
            studentInfo[i] = temp.getFirstName() + " " + temp.getLastName() + 
                    ", Age: " + temp.getAge() + ", Skill: " + temp.getSkill() + ", Skill Rating: " + temp.getSkillRating();
        }
        return studentInfo;
    }

    /**
     * Adds a student to the students ArrayList
     * @param student Student to be added
     */
    public void addStudent(Student student){
        students.add(student);
    }

    /**
     * Removes a student from the students ArrayList 
     * @param index defines the position of the student to be removed
     */
    public void removeStudent(int index){
        students.remove(index);
    }

    /**
     * Returns a student from the students ArrayList
     * @param index defines the position of the student to be returned
     * @return Returns the Student
     */
    public Student getStudent(int index){
        return students.get(index);
    }
}
