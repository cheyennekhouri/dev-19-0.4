package cs151.application;
import java.util.ArrayList;
import java.util.List;
public class StudentProfile  implements Comparable<StudentProfile> {

    private String name;
    private String major;
    private List<String> languages;

    public StudentProfile(){
        this.name = "";
        this.major = "";
        this.languages = new ArrayList<>();
    }
    public StudentProfile(String name, String major, List<String> languages){
        setName(name);
        setMajor(major);
        setLanguages(languages);
    }

    public StudentProfile(String name){
        setName(name);
    }
    // Name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    // Major
    public void setMajor(String major) {
        this.major = major;
    }
    public String getMajor() {
        return major;
    }
    // Languages
    public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
    public String getLanguagesString(){
        return String.join(" | ", languages);
    }
    //compare names to sort
    @Override
    public int compareTo(StudentProfile other){
        return this.name.compareToIgnoreCase(other.name);
    }
    @Override
    public String toString(){
        return name + "," + major + "," + String.join("|", languages);
    }
}
