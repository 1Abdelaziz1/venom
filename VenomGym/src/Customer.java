import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Customer implements Serializable {
    private String name;
    private int id;
    private double numberOfMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;

    // Constructor
    public Customer(String name, int id, int numberOfMonths) {
        this.name = name;
        this.id = id;
        this.numberOfMonths = numberOfMonths;
        this.startDate = LocalDate.now();
        this.endDate = startDate.plusMonths(numberOfMonths);
        this.calculatePrice();
    }

    // Calculate price with 10% discount if numberOfMonths is greater than or equal to 3
    private void calculatePrice() {
        double basePrice = 200.0; // Set your base price here
        double discountRate = 0.10; // 10% discount

        if (numberOfMonths >= 3.0) {
            this.price = (basePrice - (basePrice * discountRate)) * numberOfMonths;
        } else {
            this.price = basePrice * numberOfMonths;
        }
    }
    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getNumberOfMonths() {
        return numberOfMonths;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Customer{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", numberOfMonths=" + numberOfMonths +
                ", startDate=" + startDate.format(formatter) +
                ", endDate=" + endDate.format(formatter) +
                ", price=" + price +
                '}';
    }
    // Save customer data to a file
    public static void saveCustomers(List<Customer> customers, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
            System.out.println("Customer data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load customer data from a file
    public static List<Customer> loadCustomers(String fileName) {
        List<Customer> loadedCustomers = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                loadedCustomers = (List<Customer>) obj;
                System.out.println("Customer data loaded from " + fileName);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCustomers;
    }
}
