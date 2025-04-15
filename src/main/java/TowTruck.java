import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class TowTruck {
    private static List<String> registrationNumbers = new ArrayList<>(); // zapewnienei że nr rejestracyjny jest unikalny
    private String type;
    private final String registrationNumber;
    private CarRepairShop belongsTo;

    public TowTruck(String type, String registrationNumber, CarRepairShop belongsTo) {
        this.belongsTo = belongsTo;
        belongsTo.addTowTruck(this);
        this.type = type;
        this.registrationNumber = checkRegistrationNumber(registrationNumber);
        registrationNumbers.add(registrationNumber);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setBelongsTo(CarRepairShop belongsTo) {
        this.belongsTo = belongsTo;
    }
    public CarRepairShop getBelongsTo() {
        return belongsTo;
    }

    private String checkRegistrationNumber(String registrationNumber) {
        checkForNullValue(registrationNumber, "Registration number is null");
        checkStringForEmptyAndBlank(registrationNumber, "Registration number is empty or contains only spaces");
        if (registrationNumbers.contains(registrationNumber)){
            throw new IllegalArgumentException("Registration number already exists");
        }
        Pattern pattern = Pattern.compile("^[A-Z]{2}\\d{5}$");
        if (!pattern.matcher(registrationNumber).matches()) {
            throw new IllegalArgumentException("Registration number is invalid");
        }
        return registrationNumber;
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
/////////////////////////////////////////// overrides
    @Override
    public String toString() {
        return "TowTruck{" +
                "type='" + type + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", belongsTo=" + belongsTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TowTruck towTruck = (TowTruck) o;
        return  Objects.equals(type, towTruck.type) && Objects.equals(registrationNumber, towTruck.registrationNumber) && Objects.equals(belongsTo, towTruck.belongsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, registrationNumber, belongsTo);
    }
}
