import java.util.ArrayList;
import java.util.List;

// Relation enum
enum RelationType {
    ASSOCIATION,
    GROUPING
}

// Relation class
class Relation {
    String name;
    RelationType type;
    Component comp1;
    Component comp2;
    List<Attribute> attributes = new ArrayList<>();

    public Relation(String name, RelationType type, Component c1, Component c2) {
        this.name = name;
        this.type = type;
        this.comp1 = c1;
        this.comp2 = c2;
    }

    void addAttribute(Attribute attr) {
        attributes.add(attr);
    }

    @Override
    public String toString() {
        return comp1.name + " --[" + name + " / " + type + "]--> " + comp2.name + " " + attributes;
    }

}
