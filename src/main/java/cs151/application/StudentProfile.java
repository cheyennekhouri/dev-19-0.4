package cs151.application;

public class StudentProfile  implements Comparable<StudentProfile> {

    private String name;

    public StudentProfile(){
        this.name = "";
    }

    public StudentProfile(String name){
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //compare names to sort
    @Override
    public int compareTo(StudentProfile other){
        return this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public String toString(){
        return "Name: " + this.name;
    }
}
