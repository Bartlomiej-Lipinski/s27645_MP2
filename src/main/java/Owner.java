import java.util.Collections;
import java.util.HashSet;
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
        if (owns.add(shop)) shop.addOwner(this);
    }

    public void removeShop(CarRepairShop shop) {
        if (owns.remove(shop)) shop.removeOwner(this);
    }

    public Set<CarRepairShop> getOwns() {
        Set<CarRepairShop> reference = Collections.unmodifiableSet(owns);
        return reference;
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
}
