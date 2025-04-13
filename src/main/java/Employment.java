import java.time.LocalDate;

public class Employment {
    private LocalDate employed;
    private LocalDate fired;
    private double salary;
    private int howManyHoursShift;
    private Mechanic mechanic;
    private CarRepairShop shop;

    public Employment(LocalDate employed, double salary, int hours, Mechanic mechanic, CarRepairShop shop) {
        this.employed = employed;
        this.salary = salary;
        this.howManyHoursShift = hours;
        this.setMechanic(mechanic);
        this.setShop(shop);
    }

    public void setMechanic(Mechanic mechanic) {
        if (this.mechanic != null){
            this.mechanic.removeEmployment(this);
        }
        this.mechanic = mechanic;
        if (mechanic != null) mechanic.addEmployment(this);
    }

    public void setShop(CarRepairShop shop) {
        if (this.shop != null) this.shop.removeEmployment(this);
        this.shop = shop;
        if (shop != null) shop.setEmployment(this);
    }

    public int getHowManyHoursShift() {
        return howManyHoursShift;
    }

    public void setHowManyHoursShift(int howManyHoursShift) {
        this.howManyHoursShift = howManyHoursShift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getFired() {
        return fired;
    }

    public void setFired(LocalDate fired) {
        this.fired = fired;
    }

    public void terminate(LocalDate firedDate) {
        this.fired = firedDate;
        if (this.mechanic != null) this.mechanic.removeEmployment(this);
        if (this.shop != null) this.shop.removeEmployment(this);
    }

    public LocalDate getEmployed() {
        return employed;
    }

    public void setEmployed(LocalDate employed) {
        this.employed = employed;
    }

    public void removeMechanic() {
        this.mechanic = null;
    }

    public void removeShop() {
        this.shop = null;
    }
}
