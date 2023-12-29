package facebook.src;

import java.time.LocalDate;

/**
 * The Person class represents an individual in the Facebook application.
 */
public abstract class Person {
    /** The unique identifier of the person. */
    public int id;

    /** The name of the person. */
    public String name;

    /** The gender of the person. */
    public String gender;

    /** The birthdate of the person. */
    public LocalDate Date;

    /** The email address of the person. */
    public String Email;

    /**
     * Constructs a new Person with the specified attributes.
     *
     * @param id     The unique identifier of the person.
     * @param name   The name of the person.
     * @param email  The email address of the person.
     * @param gender The gender of the person.
     * @param date   The birthdate of the person.
     */
    public Person(int id, String name, String email, String gender, LocalDate date) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.Date = date;
        this.Email = email;
    }

    /**
     * Gets the gender of the person.
     *
     * @return The gender of the person.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender The new gender of the person.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the birthdate of the person.
     *
     * @return The birthdate of the person.
     */
    public LocalDate getDate() {
        return Date;
    }

    /**
     * Sets the birthdate of the person.
     *
     * @param date The new birthdate of the person.
     */
    public void setDate(LocalDate date) {
        this.Date = date;
    }

    /**
     * Gets the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The new name of the person.
     */
    public void setName(String name) {
        this.name = name;
    }
}
