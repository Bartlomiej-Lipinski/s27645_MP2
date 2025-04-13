import java.util.Objects;
import java.util.regex.Pattern;

public class TowTruck {
    private String type;
    private int amountOfCars;
    private String carType;
    private String registrationNumber;
    private CarRepairShop belongsTo;

    public TowTruck(String type, int amountOfCars, String carType, String registrationNumber) {
        this.type = type;
        this.amountOfCars = amountOfCars;
        this.carType = carType;
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setBelongsTo(CarRepairShop belongsTo) {
        this.belongsTo = belongsTo;
    }
    public void setRegistrationNumber(String registrationNumber) {
        checkForNullValue(registrationNumber, "Registration number is null");
        checkStringForEmptyAndBlank(registrationNumber, "Registration number is empty or contains only spaces");
        Pattern pattern = Pattern.compile("^[A-Z]{2}\\d{5}$");
        if (!pattern.matcher(registrationNumber).matches()) {
            throw new IllegalArgumentException("Registration number is invalid");
        }
        this.registrationNumber = registrationNumber;
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
                ", amountOfCars=" + amountOfCars +
                ", carType='" + carType + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", belongsTo=" + belongsTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TowTruck towTruck = (TowTruck) o;
        return amountOfCars == towTruck.amountOfCars && Objects.equals(type, towTruck.type) && Objects.equals(carType, towTruck.carType) && Objects.equals(registrationNumber, towTruck.registrationNumber) && Objects.equals(belongsTo, towTruck.belongsTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, amountOfCars, carType, registrationNumber, belongsTo);
    }
}
