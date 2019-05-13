/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupgenerator;

/**
 * Class that will be used to create the student instances
 * @author 23781271
 */
public class Student implements java.io.Serializable{
    private String firstName;
    private String lastName;
    private int age;
    private String skill;
    private int skillRating;

    /**
     * When the student is created first and last name, age, skill and 
     * skill rating will be already defined using the respective parameters
     * @param first defines the firstName attribute of the student
     * @param last defines the lasttName attribute of the student
     * @param Age defines the age attribute of the student
     * @param Skill defines the skill attribute of the student
     * @param SkillRating defines the skillRating attribute of the student
     */
    public Student(String first, String last,int Age,String Skill,int SkillRating){
        firstName = first;
        lastName = last;
        age = Age;
        skill = Skill;
        skillRating = SkillRating;
    }

    /**
     * Returns the first name of the student
     * @return This student's first name
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Returns the last name of the student
     * @return This student's last name
     */
    public String getLastName(){
        return lastName;
    }

    /**
     * returns the age of the student
     * @return This student's age
     */
    public int getAge(){
        return age;
    }

    /**
     * returns the skill of the student
     * @return This student's skill
     */
    public String getSkill(){
        return skill;
    }

    /**
     * returns the skill rating of the student
     * @return This student's skillRating
     */
    public int getSkillRating(){
        return skillRating;
    }
}
