/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupgenerator;

/**
 *
 * @author 23781271
 */

import java.util.ArrayList; // import the ArrayList class
import javax.swing.JOptionPane;

/**
 * This class is in charge of generating the groups, taking the students from 
 * the studentManager class. At creation it creates an instance of the
 * StudentManager class
 * It stores the groups generated, the remainder students and 
 * the only StudentManager Class instance
 * @author 23781271
 */
public class GroupGenerator {

    /**
     */
    public static studentManager students;
    private ArrayList<Group> groups;
    private ArrayList<Student> remainderStudents;
    
    /**
     * The constructor instantiates the groups and remainder students ArrayList, 
     * and also creates an instance of the StudentManager Class 
     */
    public GroupGenerator() {
        students = new studentManager();
        groups = new ArrayList<>();
        remainderStudents = new ArrayList<>();
    }

    /**
     * Generates the groups using all the students stored in the StudentManager Class instance
     * @param size defines the group size, the number of total students will be divided 
     * by the size to define how many groups there will be
     * @return false if it could not generate the groups, 
     * true if it generated the groups
     */
    public boolean generateGroups(int size){
        groups.clear();
        ArrayList<Student> designReq = students.getDesignReq();
        ArrayList<Student> docMaintain = students.getImplementTest();
        ArrayList<Student> implementTest = students.getDocMaintain();
        
        int numberOfStudents = designReq.size() + docMaintain.size() + implementTest.size();
        int numberOfGroups = numberOfStudents/size;
        if (numberOfGroups < 1){ //if numberOfGroups is less than 1 a group or groups cannot be created
            return false;
        }
        else{
            int remainder = numberOfStudents%size;
            int designReqCounter = designReq.size() - 1;
            int docMaintainCounter = docMaintain.size() - 1;
            int implementTestCounter = implementTest.size() - 1;
            
            for (int i = 0; i < numberOfGroups; i++){
                ArrayList<Student> groupStudents = new ArrayList<>();
                int groupSpace = size;
                
                while(groupSpace>0){    //Loop will run as long as theres space in the group (dependable on group size defined by the user
                    switch(groupSpace){
                        case 1:
                            if(designReqCounter > -1){
                                if(implementTestCounter < 0){
                                    if(docMaintainCounter < 0){ //This means only designReq list has items
                                        groupStudents.add(designReq.get(designReqCounter));
                                        designReqCounter--;
                                    }
                                    else{   //This means both designReq and docMaintain have items, pick at random
                                        double random = Math.random();
                                        if(random < 0.5){
                                            groupStudents.add(designReq.get(designReqCounter));
                                            designReqCounter--;
                                        }
                                        else{
                                            groupStudents.add(docMaintain.get(docMaintainCounter));
                                            docMaintainCounter--;
                                        }
                                    }
                                }
                                else if(docMaintainCounter < 0){ //This means both designReq and implementTest have items, pick at random
                                    double random = Math.random();
                                        if(random < 0.5){
                                            groupStudents.add(designReq.get(designReqCounter));
                                            designReqCounter--;
                                        }
                                        else{
                                            groupStudents.add(implementTest.get(implementTestCounter));
                                            implementTestCounter--;
                                        }
                                }
                                else{   //This means all 3 lists have items, pick at random
                                    double random = Math.random();
                                    if(random < 0.33){
                                        groupStudents.add(designReq.get(designReqCounter));
                                        designReqCounter--;
                                    }
                                    else if(random < 0.66){
                                        groupStudents.add(implementTest.get(implementTestCounter));
                                        implementTestCounter--;
                                    }
                                    else{
                                        groupStudents.add(docMaintain.get(docMaintainCounter));
                                        docMaintainCounter--;
                                    }
                                }
                            }
                            else if(implementTestCounter > -1){
                                if(docMaintainCounter < 0){     //This means that only implementTest has items
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                }
                                else{   //This means both implementTest and docMaintain have items pick at random
                                    double random = Math.random();
                                    if(random < 0.5){
                                        groupStudents.add(implementTest.get(implementTestCounter));
                                        implementTestCounter--;
                                    }
                                    else{
                                        groupStudents.add(docMaintain.get(docMaintainCounter));
                                        docMaintainCounter--;
                                    }
                                }
                            }
                            else{   //If all else fails, it means only docMaintain has items
                                groupStudents.add(docMaintain.get(docMaintainCounter));
                                docMaintainCounter--;
                            }
                            groupSpace--;
                            break;
                            
                        case 2:
                            if(designReqCounter < 0){
                                if(implementTestCounter < 0){ //This means only docMaintain list has items
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;
                                }
                                else if(docMaintainCounter < 0){ //This means only implementTest has items
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                }
                                else{   //take 1 from implementTest other from docMaintain
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;
                                }
                            }
                            else if(implementTestCounter < 0){ 
                                if(docMaintainCounter < 0){ //This means only desinCounter has items
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                }
                                else{ //Take 1 from designReq and other from DocMaintain
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;
                                }
                            }
                            else{  //This means all 3 lists have items, pick at random twice
                                double random = Math.random();
                                    if(random < 0.33){
                                        groupStudents.add(designReq.get(designReqCounter));
                                        designReqCounter--;
                                        double secondRandom = Math.random();
                                        if(secondRandom < 0.5){
                                            groupStudents.add(implementTest.get(implementTestCounter));
                                            implementTestCounter--;
                                        }
                                        else{
                                            groupStudents.add(docMaintain.get(docMaintainCounter));
                                            docMaintainCounter--;
                                        }
                                    }
                                    else if(random < 0.66){
                                        groupStudents.add(implementTest.get(implementTestCounter));
                                        implementTestCounter--;
                                        double secondRandom = Math.random();
                                        if(secondRandom < 0.5){
                                            groupStudents.add(designReq.get(designReqCounter));
                                            designReqCounter--;
                                        }
                                        else{
                                            groupStudents.add(docMaintain.get(docMaintainCounter));
                                            docMaintainCounter--;
                                        }
                                    }
                                    else{
                                        groupStudents.add(docMaintain.get(docMaintainCounter));
                                        docMaintainCounter--;
                                        double secondRandom = Math.random();
                                        if(secondRandom < 0.5){
                                            groupStudents.add(designReq.get(designReqCounter));
                                            designReqCounter--;
                                        }
                                        else{
                                            groupStudents.add(implementTest.get(implementTestCounter));
                                            implementTestCounter--;
                                        }
                                    }
                            }
                            groupSpace -= 2;
                            break;
                            
                        default:
                            if(designReqCounter > -1){
                                if(implementTestCounter > -1){
                                    if(docMaintainCounter > -1){ // this means all three lists have items pick 1 from each
                                        groupStudents.add(designReq.get(designReqCounter));
                                        designReqCounter--;
                                        groupStudents.add(implementTest.get(implementTestCounter));
                                        implementTestCounter--;
                                        groupStudents.add(docMaintain.get(docMaintainCounter));
                                        docMaintainCounter--;
                                    }
                                    else{ //only designReq and implementTest lists have items, pick 1 from each
                                        groupStudents.add(designReq.get(designReqCounter));
                                        designReqCounter--;
                                        groupStudents.add(implementTest.get(implementTestCounter));
                                        implementTestCounter--;

                                    }
                                }
                                else if(docMaintainCounter > -1){ //This means only designReq and docMaintain have items, pick 1 from each 
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;

                                }
                                else{   //only designReq array has items, pick 3 from that list
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                    groupStudents.add(designReq.get(designReqCounter));
                                    designReqCounter--;
                                }
                            }
                            else if(implementTestCounter > -1){
                                if(docMaintainCounter > -1){ //only implementTest and docMaintain lists have items, pick 1 from each and 1 at random from those 2
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                    groupStudents.add(docMaintain.get(docMaintainCounter));
                                    docMaintainCounter--;

                                }
                                else{ //This means only the implementTest list has items, pick 3 from that list
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                    groupStudents.add(implementTest.get(implementTestCounter));
                                    implementTestCounter--;
                                }
                            }
                            else{ //This means only the docMaintain list has items, pick 3 from that list
                                groupStudents.add(docMaintain.get(docMaintainCounter));
                                docMaintainCounter--;
                                groupStudents.add(docMaintain.get(docMaintainCounter));
                                docMaintainCounter--;
                                groupStudents.add(docMaintain.get(docMaintainCounter));
                                docMaintainCounter--;
                            }
                            groupSpace -= 3;
                            break;
                    }
                }
                int groupNumber = i+1;
                String groupName = "group " + groupNumber;
                groups.add(new Group(groupStudents,groupName)) ;
            }
            if(remainder > 0){
                while(designReqCounter >= 0){
                    remainderStudents.add(designReq.get(designReqCounter));
                    designReqCounter--;
                }
                while(implementTestCounter >= 0){
                    remainderStudents.add(implementTest.get(implementTestCounter));
                    implementTestCounter--;
                }
                while(docMaintainCounter >= 0){
                    remainderStudents.add(docMaintain.get(docMaintainCounter));
                    docMaintainCounter--;
                }
                JOptionPane.showMessageDialog(null, "With the current group size, there were some remainder students, please add these manually to the desired groups");
            }
            return true;
        }
    }
    
    /**
     * Adds a student to a specific group
     * @param groupIndex used to get the specific group from the groups ArrayList
     * @param studentListIndex used to get the specific student 
     * from the remainderStudents ArrayList
     */
    public void addStudentToGroup(int groupIndex, int studentListIndex){
        groups.get(groupIndex).addStudent(remainderStudents.get(studentListIndex));
        remainderStudents.remove(studentListIndex);
    }

    /**
     * Removes a student from the groups ArrayList and 
     * stores it in the remainderStudents ArrayList instead
     * @param groupIndex used to get the specific group from the groups ArrayList
     * @param studentIndex used to get the specific student 
     * from the group students ArrayList
     */
    public void removeStudentFromGroup(int groupIndex,int studentIndex){
        remainderStudents.add(groups.get(groupIndex).getStudent(studentIndex));
        groups.get(groupIndex).removeStudent(studentIndex);
    }
    
    /**
     * Returns a group from the groups ArrayList
     * @param index defines which specific group to return 
     * from the groups ArrayList
     * @return a specific group based on index
     */
    public Group getGroup(int index){
        return groups.get(index);
    }

    /**
     * Returns the remainderStudents ArrayList
     * @return The remainderStudents ArrayList
     */
    public ArrayList<Student> getRemainderStudents(){
        return remainderStudents;
    }

    /**
     * Returns all the group names as String[] in order to be displayed in a list
     * @return Returns the group names as String[]
     */
    public String[] getGroupNames(){
        String[] groupNames = new String[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            groupNames[i] = groups.get(i).getName();
        }
        return groupNames;
    }

    /**
     * Returns all the remainder Students information to be displayed in a list
     * @return returns a String[]
     */
    public String[] getRemainderStudentsNames(){
        String[] studentNames = new String[remainderStudents.size()];
        for (int i = 0; i < remainderStudents.size(); i++) {
            studentNames[i] = remainderStudents.get(i).getFirstName() + " " + remainderStudents.get(i).getLastName() + ", Age: "+ remainderStudents.get(i).getAge() 
                    + ", Skill: " + remainderStudents.get(i).getSkill() + ", Skill Rating: " +  remainderStudents.get(i).getSkillRating();
        }
        return studentNames;
    }
}
