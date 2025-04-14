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
        if (this.mechanic != null) {
            this.mechanic.removeEmployment(this);
        }
        mechanic.addEmployment(this);
        this.mechanic = mechanic;
        mechanic.addEmployment(this);
    }

    public void setShop(CarRepairShop shop) {
        if (this.shop != null){
            this.shop.removeEmployment(this);
        }
        shop.setEmployment(this);
        this.shop = shop;
        shop.setEmployment(this);
    }

    public int getHowManyHoursShift() {
        return howManyHoursShift;
    }

    public void setHowManyHoursShift(int howManyHoursShift) {
        if (howManyHoursShift < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        if (howManyHoursShift > 24) {
            throw new IllegalArgumentException("Hours cannot be more than 24");
        }
        this.howManyHoursShift = howManyHoursShift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        this.salary = salary;
    }

    public LocalDate getFired() {
        return fired;
    }

    public void setFired(LocalDate fired) {
        if (fired != null && fired.isBefore(employed)) {
            throw new IllegalArgumentException("Fired date cannot be before employed date");
        }
        if (this.fired != null) {
            throw new IllegalArgumentException("Already fired");
        }
        if (fired != null && fired.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fired date cannot be in the future");
        }
        this.fired = fired;
    }

    public LocalDate getEmployed() {
        return employed;
    }

    public void setEmployed(LocalDate employed) {
        if (employed == null) {
            throw new IllegalArgumentException("Employed date cannot be null");
        }
        if (employed.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Employed date cannot be in the future");
        }
        if (this.employed != null) {
            throw new IllegalArgumentException("Already employed");
        }
        this.employed = employed;
    }

    public void removeMechanic() {
        this.mechanic = null;
    }

    public void removeShop() {
        this.shop = null;
    }
}
