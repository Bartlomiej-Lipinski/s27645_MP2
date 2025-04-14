import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mechanic {
    private String specialization;
    private String name;
    private String surname;
    private Set<Employment> employments = new HashSet<>();

    public Mechanic(String specialization, String name, String surname) {
        this.specialization = specialization;
        this.name = name;
        this.surname = surname;
    }

    public Set<Employment> getEmployments() {
        return Collections.unmodifiableSet(employments);
    }


    public void addEmployment(Employment employment) {
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
        checkForNullValue(specialization,"Specialization cannot be null");
        checkStringForEmptyAndBlank(specialization,"Specialization cannot be empty or blank");
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkForNullValue(name,"Name cannot be null");
        checkStringForEmptyAndBlank(name,"Name cannot be empty or blank");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        checkForNullValue(surname,"Surname cannot be null");
        checkStringForEmptyAndBlank(surname,"Surname cannot be empty or blank");
        this.surname = surname;
    }
    private void checkStringForEmptyAndBlank(String string, String message) {
        if (string.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        if (string.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
    private void checkForNullValue(String string, String message) {
        if (string == null) {
            throw new IllegalArgumentException(message);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////overrides
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Mechanic mechanic = (Mechanic) o;
        return Objects.equals(specialization, mechanic.specialization) && Objects.equals(name, mechanic.name) && Objects.equals(surname, mechanic.surname) && Objects.equals(employments, mechanic.employments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialization, name, surname, employments);
    }

    @Override
    public String toString() {
        return "Mechanic{" +
                "specialization='" + specialization + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", employments=" + employments +
                '}';
    }
}
