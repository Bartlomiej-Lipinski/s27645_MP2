import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Owner {
    private String name;
    private String surname;
    private Set<CarRepairShop> owns = new HashSet<>();

    public Owner(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void addShop(CarRepairShop shop) {
        if (owns.add(shop)){
            shop.addOwner(this);
        }
    }

    public void removeShop(CarRepairShop shop) {
        if (owns.remove(shop)){
            shop.removeOwner(this);
        }
    }

    public Set<CarRepairShop> getOwns() {
        return Collections.unmodifiableSet(owns);
    }
    public void removeTowTruck(String registrationNumber) {
        for (CarRepairShop shop : owns) {
            shop.removeTowTruck(registrationNumber);
        }
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
    public CarRepairShop getCarRepairShop(String place) {
        for (CarRepairShop shop : owns) {
            if (shop.getPlace().equals(place)) {
                return shop;
            }
        }
        return null;
    }
/// ///////////getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkForNullValue(name, "Name cannot be null");
        checkStringForEmptyAndBlank(name, "Name cannot be empty or blank");
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        checkForNullValue(surname," Surname cannot be null");
        checkStringForEmptyAndBlank(surname, "Surname cannot be empty or blank");
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", owns=" + owns +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(name, owner.name) && Objects.equals(surname, owner.surname) && Objects.equals(owns, owner.owns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, owns);
    }
}
