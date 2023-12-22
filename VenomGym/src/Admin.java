import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Admin implements Serializable {
    private final String adminName = "admin";
    private final String adminPassword = "admin";
    private final List<Customer> customers;

    // Constructor
    public Admin() {
        this.customers = new ArrayList<>();
    }

    // Add a customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Show all customers subscribed on given day
    public void showCustomersOnDay(LocalDate date) {
        System.out.println("Customers on " + date + ":");
        for (Customer customer : customers) {
            if (customer.getStartDate().equals(date)) {
                System.out.println(customer);
            }
        }
    }


    // Show all customers in a given month
    public List<Customer> showCustomersInMonth(LocalDate date) {
        List<Customer> monthCustomers = new ArrayList<>();
        System.out.println("Customers in " + date.getYear() + "-" + date.getMonthValue() + ":");
        for (Customer customer : customers) {
            if (customer.getStartDate().getYear() == date.getYear() &&
                    customer.getStartDate().getMonthValue() == date.getMonthValue()) {
                monthCustomers.add(customer);
            }
        }
        return monthCustomers;
    }

    // Show income on a given day
    public void showIncomeOnDay(LocalDate date) {
        double totalIncome = 0.0;
        for (Customer customer : customers) {
            if (customer.getStartDate().equals(date)) {
                totalIncome += customer.getPrice();
            }
        }
        System.out.println("Income on " + date + ": LE " + totalIncome);
    }

    // Show income in a given month
    public void showIncomeInMonth(LocalDate date) {
        double totalIncome = 0.0;
        for (Customer customer : customers) {
            if (customer.getStartDate().getYear() == date.getYear() &&
                    customer.getStartDate().getMonthValue() == date.getMonthValue()) {
                totalIncome += customer.getPrice();
            }
        }
        System.out.println("Income in " + date.getYear() + "-" + date.getMonthValue() + ": LE " + totalIncome);
    }
    public void showCustomerById(int customerId) {
        try {
            System.out.println("Customer with ID " + customerId + ":");
            boolean customerFound = false;
            for (Customer customer : customers) {
                if (customer.getId() == customerId) {
                    System.out.println(customer);
                    customerFound = true;
                }
            }
            if (!customerFound) {
                throw new CustomerNotFoundException("No customer found with ID " + customerId);
            }
        } catch (CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    // Save admin data to a file
    public static void saveAdmin(Admin admin, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(admin);
            System.out.println("Admin data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load admin data from a file
    public static Admin loadAdmin(String fileName) {
        Admin loadedAdmin = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof Admin) {
                loadedAdmin = (Admin) obj;
                System.out.println("Admin data loaded from " + fileName);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedAdmin;
    }


}