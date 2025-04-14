import java.util.*;

public class CarRepairShop  {
    private String place;
    private Set<Employment> employments = new HashSet<>();
    private Map<String, TowTruck> belongsTo = new HashMap<>();
    private Set<Owner> isOwned = new HashSet<>();
    private final List<WorkPlace> workPlace = new ArrayList<>();

    public CarRepairShop(String place) {
        this.place = place;
    }

    public void addWorkPlace(WorkPlace workPlace) {
        if (this.workPlace.contains(workPlace)) {
            throw new IllegalArgumentException("Workplace already exists");
        }
        if (workPlace == null) {
            throw new IllegalArgumentException("Workplace cannot be null");
        }
        this.workPlace.add(workPlace);
    }
    public void setEmployment(Employment e) {
        if (employments.add(e)){
            e.setShop(this);
        }
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        checkForNullValue(place, "Place is null");
        checkStringForEmptyAndBlank(place, "Place is empty or contains only spaces");
        this.place = place;
    }

    public void removeEmployment(Employment e) {
        if (employments.remove(e)) e.removeShop();
    }

    public void addTowTruck(TowTruck truck) {
        if (!belongsTo.containsKey(truck.getRegistrationNumber())){
            belongsTo.put(truck.getRegistrationNumber(), truck);
            truck.setBelongsTo(this);
        }
    }
    public TowTruck findTowTruck(String regNum) throws NullPointerException{
        if (!belongsTo.containsKey(regNum)) {
            throw new NullPointerException("Tow truck with this registration number does not exist");
        }
        return belongsTo.get(regNum);
    }

    public void removeTowTruck(String registrationNumber) {
        TowTruck removed = belongsTo.remove(registrationNumber);
        if (removed != null){
            removed.setBelongsTo(null);
        }
    }

    public TowTruck getTowTruck(String regNum) {
        return belongsTo.get(regNum);
    }

    public void addOwner(Owner owner) {
        if (isOwned.add(owner)) {
            owner.addShop(this);
        }
    }

    public void removeOwner(Owner owner) {
        if (isOwned.remove(owner)) {
            owner.removeShop(this);
        }
    }
    public Owner getOwner(String name, String surname) {
        for (Owner owner : isOwned) {
            if (owner.getName().equals(name) && owner.getSurname().equals(surname)) {
                return owner;
            }
        }
        return null;
    }
    public void getTowTruck(){
        for (Map.Entry<String, TowTruck> entry : belongsTo.entrySet()) {
            System.out.println("Registration number: " + entry.getKey() + ", Tow truck: " + entry.getValue());
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
////////////////////////////////////////////////////////////////////////////////////overrides
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarRepairShop that = (CarRepairShop) o;
        return Objects.equals(place, that.place)
                && Objects.equals(employments, that.employments)
                && Objects.equals(belongsTo, that.belongsTo)
                && Objects.equals(isOwned, that.isOwned)
                && Objects.equals(workPlace, that.workPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, employments, belongsTo, isOwned, workPlace);
    }

    @Override
    public String toString() {
        return "CarRepairShop{" +
                "place='" + place + '\'' +
                ", employments=" + employments +
                ", towTrucks=" + belongsTo +
                ", owners=" + isOwned +
                ", workPlace=" + workPlace +
                '}';
    }
}
