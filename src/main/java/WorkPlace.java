import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkPlace  {
    private List<String> tools;
    private CarRepairShop shop;

    private WorkPlace(CarRepairShop shop) {
        this.shop = shop;
        shop.addWorkPlace(this);
        this.tools = new ArrayList<>();
    }

    public static WorkPlace workPlace(CarRepairShop shop, List<String> tools) throws NullPointerException{
        if (shop == null) {
            throw new NullPointerException("Warsztat nie może być null");
        }
        WorkPlace workPlace = new WorkPlace(shop);
        shop.addWorkPlace(workPlace);
        workPlace.setTools(tools);
        return workPlace;
    }
    public void removeShop() {
        if (shop != null) {
            shop.removeWorkPlace(this);
            shop = null;
        }
    }

    public CarRepairShop getShop() {
        return shop;
    }
    public List<String> getTools() {
        return tools;
    }
    public void setTools(List<String> tools) {
        if (tools == null) {
            throw new IllegalArgumentException("Narzędzia nie mogą być null");
        }
        this.tools = tools;
    }
    public void addTool(String tool) {
        checkForNullValue(tool, "Narzędzie nie może być null");
        checkStringForEmptyAndBlank(tool, "Narzędzie nie może być puste");
        tools.add(tool);
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        WorkPlace workPlace = (WorkPlace) o;
        return Objects.equals(tools, workPlace.tools) && Objects.equals(shop, workPlace.shop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tools, shop);
    }

    @Override
    public String toString() {
        return "WorkPlace{" +
                "tools=" + tools +
                ", shop=" + shop +
                '}';
    }
}
