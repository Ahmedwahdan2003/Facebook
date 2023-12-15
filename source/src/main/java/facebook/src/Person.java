package facebook.src;

import java.time.LocalDate;

public class Person {
    private String name;
    private String gender;
    private LocalDate Date;

    public Person(String name, String gender, LocalDate date) {
        this.name = name;
        this.gender = gender;
        Date = date;
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
