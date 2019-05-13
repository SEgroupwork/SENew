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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList; // import the ArrayList class

/**
 * This class manages all the students in three different ArrayLists depending
 * on what skill they were assigned at creation. Students are loaded every time
 * the program starts and an instance of this class is created
 * @author 23781271
 */
public class studentManager {
    private ArrayList<Student> designReq;
    private ArrayList<Student> implementTest;
    private ArrayList<Student> docMaintain;
    
    /**
     * The constructor loads all the students into the respective ArrayLists
     * using the respective methods
     */
    public studentManager(){
        designReq = loadStudents("designReqStudents.dat");
        implementTest = loadStudents("implementTestStudents.dat");
        docMaintain = loadStudents("docMaintainStudents.dat");
    }

    /**
     * returns the designReq ArrayList
     * @return The arrayList designReq of type Student
     */
    public ArrayList<Student> getDesignReq(){
        return designReq;
    }

    /**
     * returns the implementTest ArrayList
     * @return The arrayList implementTest of type Student
     */
    public ArrayList<Student> getImplementTest(){
        return implementTest;
    }

    /**
     * returns the docMaintain ArrayList
     * @return The arrayList docMaintain of type Student
     */
    public ArrayList<Student> getDocMaintain(){
        return docMaintain;
    }
    
    /**
     * Saves all the students ArrayLists into the respective .dat files
     * should be called every time a student is created
     */
    public void saveStudents() {
        try {
            ObjectOutputStream studentsFile = new ObjectOutputStream(new FileOutputStream("designReqStudents.dat"));
            for (int i = 0; i < designReq.size(); i++) {
                studentsFile.writeObject(designReq.get(i));
            }
            studentsFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream studentsFile = new ObjectOutputStream(new FileOutputStream("implementTestStudents.dat"));
            for (int i = 0; i < implementTest.size(); i++) {
                studentsFile.writeObject(implementTest.get(i));
            }
            studentsFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream studentsFile = new ObjectOutputStream(new FileOutputStream("docMaintainStudents.dat"));
            for (int i = 0; i < docMaintain.size(); i++) {
                studentsFile.writeObject(docMaintain.get(i));
            }
            studentsFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the students previously saved on the 
     * ArrayLiss every time the program 
     * (every time studentManager is instantiated)
     * @param path Takes the path as parameter to access different files depending on which ArrayList is being loaded into
     * @return returns the ArrayList
     */
    public ArrayList<Student> loadStudents(String path) {
        ArrayList<Student> students = new ArrayList<>();
        ObjectInputStream studentsFile = null;    //declaring it outside so file can be closed in the finally statement
        try {
            studentsFile = new ObjectInputStream(new FileInputStream(path));
            /**
             * Tries to add a student to the students ArrayList and it will keep
             * returning true when added, once it fails, loop breaks and EOFException is caught
             */
            while (students.add((Student) studentsFile.readObject()));

        } catch (Exception e) {
            if (e instanceof java.io.EOFException) { //this will always be thrown when there are no more objects to be added to the array
                System.out.println("All objects have been added");
            } else {
                e.printStackTrace();
            }
        } finally {  //Do not forget to close the ObjectInputStream after all objects have been read
            try {
                if (studentsFile != null) {
                    studentsFile.close();
                    System.out.println("File has been closed");
                }
            } catch (IOException e) {
                System.err.println("There was an error closing the file");
            }
        }
        return students;
    }


    /**
     * Returns all the student's first name and last name from the designReq ArrayList
     * @return returns a String[] to be displayed in a list
     */
    public String[] getDesignReqNames(){
        String[] designReqNames = new String[designReq.size()];
        for (int i = 0; i < designReq.size(); i++) {
            designReqNames[i] = designReq.get(i).getFirstName() + " " + designReq.get(i).getLastName();
        }
        return designReqNames;
    }

    /**
     * Returns all the student's first name and last name from the implementTest ArrayList
     * @return returns a String[] to be displayed in a list
     */
    public String[] getImplementTestNames(){
        String[] implementTestNames = new String[implementTest.size()];
        for (int i = 0; i < implementTest.size(); i++) {
            implementTestNames[i] = implementTest.get(i).getFirstName() + " " + implementTest.get(i).getLastName();
        }
        return implementTestNames;
    }

    /**
     * Returns all the student's first name and last name from the docMaintain ArrayList
     * @return returns a String[] to be displayed in a list
     */
    public String[] getDocMaintainNames(){
        String[] docMaintainNames = new String[docMaintain.size()];
        for (int i = 0; i < docMaintain.size(); i++) {
            docMaintainNames[i] = docMaintain.get(i).getFirstName() + " " + docMaintain.get(i).getLastName();
        }
        return docMaintainNames;
    }
    
    /**
     * Adds a student that was just created to the respective skill ArrayList
     * and executes the saveStudents() method to save it into the local file
     * @param student student to be  added
     * @param skill depending on the skill number, it will add the student
     * to a different ArrayList
     */
    public void addStudent(Student student, int skill){
        switch(skill){
            case 1:
                designReq.add(student);
                break;
            case 2:
                implementTest.add(student);
                break;
            case 3:
                docMaintain.add(student);
                break;
        }
        saveStudents();
    }

    /**
     * Removes a student from one of the skill ArrayLists, user will select
     * it from the displayed list, and the list selected index will be fed as a parameter
     * @param index tells the position of the student in the ArrayList
     * @param skill tells which ArrayList to remove the student from
     */
    public void removeStudent(int index,int skill){
        switch(skill){
            case 1:
                designReq.remove(index);
                break;
            case 2:
                implementTest.remove(index);
                break;
            case 3:
                docMaintain.remove(index);
                break;
        }
        saveStudents();
    }
}
