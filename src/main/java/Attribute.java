// Attribute class
class Attribute {
    String name;
    String value;
    Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return name + "=" + value;
    }
}

