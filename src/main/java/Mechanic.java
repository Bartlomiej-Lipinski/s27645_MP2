import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mechanic {
    private String specialization;
    private LocalDate birthday;
    private String name;
    private String surname;
    private Set<Employment> employments = new HashSet<>();

    public Mechanic(String specialization, LocalDate birthday, String name, String surname) {
        this.specialization = specialization;
        this.birthday = birthday;
        this.name = name;
        this.surname = surname;
    }

    public Set<Employment> getEmployments() {
        return Collections.unmodifiableSet(employments);
    }

    void addEmployment(Employment employment) {
        if (employments.add(employment)) {
            employment.setMechanic(this);
        }
    }

    void removeEmployment(Employment employment) {
        if (employments.remove(employment)) {
            employment.removeMechanic();
        }
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////overrides
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(specialization, mechanic.specialization) && Objects.equals(birthday, mechanic.birthday) && Objects.equals(name, mechanic.name) && Objects.equals(surname, mechanic.surname) && Objects.equals(employments, mechanic.employments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialization, birthday, name, surname, employments);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "specialization='" + specialization + '\'' +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", employments=" + employments +
                '}';
    }
}
