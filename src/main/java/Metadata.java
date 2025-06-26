import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Metadata {
    List<Component> components = new ArrayList<>();
    List<Relation> relations = new ArrayList<>();

    void addComponent(Component c) {
        components.add(c);
    }

    void addRelation(Relation r) {
        relations.add(r);
    }

    Optional<Component> findComponentByName(String name) {
        return components.stream().filter(c -> c.name.equalsIgnoreCase(name)).findFirst();
    }

    void printRelations() {
        System.out.println("Workflow Links:");
        for (Relation r : relations) {
            System.out.println(r);
        }
    }

    // יצוא לפורמט DOT
    String toDotFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph Workflow {\n");
        for (Relation r : relations) {
            sb.append("  \"").append(r.comp1.name).append("\" -> \"")
                    .append(r.comp2.name).append("\" [label=\"")
                    .append(r.name).append("\"];\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    // חיפוש מרכיב לפי שם
    void searchComponentByName(String name) {
        Optional<Component> result = findComponentByName(name);
        if (result.isPresent()) {
            System.out.println("נמצא רכיב:");
            System.out.println(result.get());
        } else {
            System.out.println("לא נמצא רכיב בשם " + name);
        }
    }
    public boolean removeRelation(String fromComponent, String toComponent) {
        return relations.removeIf(r ->
                r.comp1.name.equalsIgnoreCase(fromComponent) &&
                        r.comp2.name.equalsIgnoreCase(toComponent)
        );
    }

    public boolean addRelationByName(String relationName, String typeStr, String fromName, String toName) {
        Optional<Component> c1 = findComponentByName(fromName);
        Optional<Component> c2 = findComponentByName(toName);
        if (c1.isEmpty() || c2.isEmpty()) {
            System.out.println("One or both components not found.");
            return false;
        }

        RelationType type;
        try {
            type = RelationType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid relation type. Use: CONNECTS, ASSOCIATION, GROUPING");
            return false;
        }

        Relation newRel = new Relation(relationName, type, c1.get(), c2.get());
        addRelation(newRel);
        return true;
    }
}

// ליצוא ל-Graphviz DOT
