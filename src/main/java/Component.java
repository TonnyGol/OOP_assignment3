import java.util.ArrayList;
import java.util.List;

// Component class
class Component {
    String name;
    String type; // e.g., Business Process, Activity, Customer
    List<Attribute> attributes;

    public Component(String name, String type) {
        this.name = name;

                this.type = type;
        attributes = new ArrayList<>();
    }

    void addAttribute(Attribute attr) {
        attributes.add(attr);
    }
    public String toString() {
        return name + " [" + type + "] " + attributes;
    }
}
