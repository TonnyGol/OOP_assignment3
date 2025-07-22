import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Component createCustomer = new Component("Create New Customer", "Business Process");
        Component getCustomerInfo = new Component("Get Customer Information", "Activity");
        Component validateCustomerInfo = new Component("Validate Customer Information", "Activity");
        Component storeCustomer = new Component("Store Customer", "Activity");
        Component customer1 = new Component("Customer 1", "Customer");
        Component microservice = new Component("Microservice A", "Microservice");


        microservice.addAttribute(new Attribute("version", "1.0"));
        microservice.addAttribute(new Attribute("owner", "TeamX"));


        Metadata meta = new Metadata();
        meta.addComponent(createCustomer);
        meta.addComponent(getCustomerInfo);
        meta.addComponent(validateCustomerInfo);
        meta.addComponent(storeCustomer);
        meta.addComponent(customer1);
        meta.addComponent(microservice);


        meta.addRelation(new Relation("flows to", RelationType.ASSOCIATION, createCustomer, getCustomerInfo));
        meta.addRelation(new Relation("flows to", RelationType.ASSOCIATION, getCustomerInfo, validateCustomerInfo));
        meta.addRelation(new Relation("flows to", RelationType.ASSOCIATION, validateCustomerInfo, storeCustomer));
        meta.addRelation(new Relation("outputs", RelationType.ASSOCIATION, storeCustomer, customer1));
        meta.addRelation(new Relation("uses", RelationType.GROUPING, storeCustomer, microservice));


        meta.printRelations();


        System.out.println("\nGraphviz DOT format:");
        System.out.println(meta.toDotFormat());


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Search component");
            System.out.println("2. Add relation");
            System.out.println("3. Remove relation");
            System.out.println("4. Show relations");
            System.out.println("5. Show DOT format");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter component name to search: ");
                    meta.searchComponentByName(scanner.nextLine());
                    break;
                case "2":
                    System.out.print("Enter relation name: ");
                    String relName = scanner.nextLine();
                    System.out.print("Enter relation type (ASSOCIATION, GROUPING): ");
                    String relType = scanner.nextLine();
                    System.out.print("From component: ");
                    String from = scanner.nextLine();
                    System.out.print("To component: ");
                    String to = scanner.nextLine();
                    if (meta.addRelationByName(relName, relType, from, to)) {
                        System.out.println("Relation added.");
                    }
                    break;
                case "3":
                    System.out.print("From component: ");
                    String delFrom = scanner.nextLine();
                    System.out.print("To component: ");
                    String delTo = scanner.nextLine();
                    if (meta.removeRelation(delFrom, delTo)) {
                        System.out.println("Relation removed.");
                    } else {
                        System.out.println("Relation not found.");
                    }
                    break;
                case "4":
                    meta.printRelations();
                    break;
                case "5":
                    System.out.println(meta.toDotFormat());
                    break;
                case "6":
                    System.out.println("Exiting.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
