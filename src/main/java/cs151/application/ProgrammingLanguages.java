package cs151.application;

import java.util.Comparator;

public class ProgrammingLanguages implements Comparable<ProgrammingLanguages> {
   // private String fullName;
    private String programmingLanguage;
    // constructor
    public ProgrammingLanguages(){
        //this.fullName = "";
        this.programmingLanguage = "";
    }
    public ProgrammingLanguages(/*String fullName,*/String programmingLanguage){
       // setFullName(fullName);
        setProgrammingLanguage(programmingLanguage);
    }
   /* public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }*/
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
    //compare languages to sort
    @Override
    public int compareTo(ProgrammingLanguages other){
        return this.programmingLanguage.compareToIgnoreCase(other.programmingLanguage);
    }

    @Override
    public String toString(){
        return /*"Name: " + this.fullName + */" Programming Language: " + this.programmingLanguage;
    }
}