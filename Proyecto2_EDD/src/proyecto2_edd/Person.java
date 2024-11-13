/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 *
 * @author yilup
 */
public class Person {
    private String fullName;
    private String numeral;
    private String mote;
    private String title;
    private String fatherName;
    private String[] childrenNames; 
    private String notes;
    private String fate;

    public Person(String fullName, String numeral, String mote, String title, String fatherName, String[] childrenNames, String notes, String fate) {
        this.fullName = fullName;
        this.numeral = numeral;
        this.mote = mote;
        this.title = title;
        this.fatherName = fatherName;
        this.childrenNames = childrenNames != null ? childrenNames : new String[0];
        this.notes = notes;
        this.fate = fate;
    }
    
    public String getFullName() { return fullName; }
    public String getMote() { return mote; }
    public String[] getChildrenNames() { return childrenNames; }
    public String getFatherName() { return fatherName; }
    public String getNotes() { return notes; }
    public String getFate() { return fate; }

}

