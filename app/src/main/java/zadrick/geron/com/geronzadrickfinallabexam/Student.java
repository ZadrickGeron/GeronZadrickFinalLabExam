package zadrick.geron.com.geronzadrickfinallabexam;

public class Student {

    String firstname;
    String lastname;
    String average;


    public Student(){



    }

    public Student(String firstname, String lastname, String average) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.average = average;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
