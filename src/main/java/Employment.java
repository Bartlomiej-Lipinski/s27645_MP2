import java.time.LocalDate;
import java.util.Objects;

public class Employment {
    private final LocalDate employed;
    private LocalDate fired;
    private double salary;
    private int howManyHoursShift;
    private final Mechanic mechanic;
    private final CarRepairShop shop;

    public Employment(LocalDate employed, double salary, int hours, Mechanic mechanic, CarRepairShop shop) {
        if (employed == null || mechanic == null || shop == null) {
            throw new IllegalArgumentException("Żaden z parametrów (data, mechanik, warsztat) nie może być null.");
        }
        if (salary < 0) {
            throw new IllegalArgumentException("Wynagrodzenie nie może być ujemne.");
        }
        if (hours < 0 || hours > 24) {
            throw new IllegalArgumentException("Liczba godzin zmiany musi być w przedziale 0-24.");
        }
        this.employed = employed;
        this.salary = salary;
        this.howManyHoursShift = hours;
        this.mechanic = mechanic;
        this.shop = shop;

        mechanic.addEmployment(this);
        shop.addEmployment(this);
    }

    public LocalDate getEmployed() {
        return employed;
    }

    public LocalDate getFired() {
        return fired;
    }

    public double getSalary() {
        return salary;
    }

    public int getHowManyHoursShift() {
        return howManyHoursShift;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public CarRepairShop getShop() {
        return shop;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Wynagrodzenie nie może być ujemne.");
        }
        this.salary = salary;
    }

    public void setHowManyHoursShift(int hours) {
        if (hours < 0 || hours > 24) {
            throw new IllegalArgumentException("Liczba godzin zmiany musi być w przedziale 0-24.");
        }
        this.howManyHoursShift = hours;
    }

    public void setFired(LocalDate fired) {
        if (fired != null && fired.isBefore(employed)) {
            throw new IllegalArgumentException("Data zwolnienia nie może być wcześniejsza niż data zatrudnienia.");
        }
        if (this.fired != null) {
            throw new IllegalArgumentException("Obecnie istnieje ustalona data zwolnienia.");
        }
        if (fired != null && fired.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data zwolnienia nie może być w przyszłości.");
        }
        this.fired = fired;
    }

    public void removeEmployment() {
        mechanic.removeEmployment(this);
        shop.removeEmployment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Employment that = (Employment) o;
        return Double.compare(salary, that.salary) == 0 && howManyHoursShift == that.howManyHoursShift && Objects.equals(employed, that.employed) && Objects.equals(fired, that.fired) && Objects.equals(mechanic, that.mechanic) && Objects.equals(shop, that.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employed, fired, salary, howManyHoursShift, mechanic, shop);
    }

    @Override
    public String toString() {
        return "Employment{" +
                "employed=" + employed +
                ", fired=" + fired +
                ", salary=" + salary +
                ", howManyHoursShift=" + howManyHoursShift +
                ", mechanic=" + mechanic +
                ", shop=" + shop +
                '}';
    }
}
