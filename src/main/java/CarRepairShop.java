import java.util.*;

public class CarRepairShop  {
    private String place;
    private Set<Employment> employments = new HashSet<>();
    private Map<String, TowTruck> belongsTo = new HashMap<>();
    private Set<Owner> isOwned = new HashSet<>();
    private final List<WorkPlace> workPlaces = new ArrayList<>();

    public CarRepairShop(String place, Owner owner, TowTruck towTruck, WorkPlace workPlace) {
        setPlace(place);
        addOwner(owner);
        addTowTruck(towTruck);
        addWorkPlace(workPlace);
    }

    public void addEmployment(Employment employment) {
        if (employment == null) {
            throw new IllegalArgumentException("Employment cannot be null");
        }
        employments.add(employment);
    }

    public void removeEmployment(Employment employment) {
        employments.remove(employment);
    }

    public void removeCarRepairShop() {
        Set<Employment> temp = new HashSet<>(employments);
        for (Employment employment : temp) {
            employment.removeEmployment();
        }
        for (TowTruck towTruck : belongsTo.values()) {
            towTruck.setBelongsTo(null);
        }
        for (Owner owner : isOwned) {
            owner.removeShop(this);
        }
        for (WorkPlace wp : new ArrayList<>(workPlaces)) {
            wp.removeShop();
        }
    }

    public void addWorkPlace(WorkPlace workPlace) {
        if (workPlace == null) {
            throw new IllegalArgumentException("Workplace cannot be null");
        }
        if (this.workPlaces.contains(workPlace)) {
            throw new IllegalArgumentException("Workplace already exists");
        }
        this.workPlaces.add(workPlace);
    }

    public void removeWorkPlace(WorkPlace workPlace) {
        if (this.workPlaces.remove(workPlace)) {
            workPlace.removeShop();
        }
    }

    public void addTowTruck(TowTruck truck) {
        if (truck == null) {
            throw new IllegalArgumentException("TowTruck cannot be null");
        }
        if (!belongsTo.containsKey(truck.getRegistrationNumber())){
            belongsTo.put(truck.getRegistrationNumber(), truck);
            truck.setBelongsTo(this);
        }else {
            throw new IllegalArgumentException("TowTruck with this registration number already exists");
        }
    }

    public void removeTowTruck(String registrationNumber) {
        TowTruck removed = belongsTo.remove(registrationNumber);
        if (removed != null){
            removed.setBelongsTo(null);
        }
    }

    public void addOwner(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }
        if (isOwned.contains(owner)){
            throw new IllegalArgumentException("Owner already exists");
        }
        if (isOwned.add(owner)) {
            owner.addShop(this);
        }
    }

    public void removeOwner(Owner owner) {
        if (isOwned.remove(owner)) {
            owner.removeShop(this);
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

    private void checkStringForEmptyAndBlank(String string, String message) {
        if (string.isEmpty() || string.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private void checkForNullValue(String string, String message) {
        if (string == null) {
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarRepairShop that = (CarRepairShop) o;
        return Objects.equals(place, that.place)
                && Objects.equals(employments, that.employments)
                && Objects.equals(belongsTo, that.belongsTo)
                && Objects.equals(isOwned, that.isOwned)
                && Objects.equals(workPlaces, that.workPlaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, employments, belongsTo, isOwned, workPlaces);
    }

    @Override
    public String toString() {
        return "CarRepairShop{" +
                "place='" + place + '\'' +
                ", employments=" + employments +
                ", towTrucks=" + belongsTo +
                ", owners=" + isOwned +
                ", workPlaces=" + workPlaces +
                '}';
    }
}

