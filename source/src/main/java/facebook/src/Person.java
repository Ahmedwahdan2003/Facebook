package facebook.src;

import java.time.LocalDate;

public class Person {
    public String name;
    public String gender;
    public LocalDate Date;
    public String Email;

    public int id;

    public Person(int id,String name,String email, String gender, LocalDate date) {
        this.name = name;
        this.gender = gender;
        Date = date;
        this.id=id;
        this.Email=email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
