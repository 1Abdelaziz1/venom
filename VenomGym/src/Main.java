import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create an Admin
        Admin admin = new Admin();

        float x =.5f;
        System.out.println(x);
        // Add customers
        Customer customer1 = new Customer("احمد حسن", 1, 3);
        Customer customer2 = new Customer("شكري", 2, 2);
        Customer customer3 = new Customer("مصطفي", 3, 3);
        Customer customer4 = new Customer("زيزو", 4, 2);
        Customer customer5 = new Customer("زياد", 5, 8);
        Customer customer6 = new Customer("محسن", 6, 1);

        admin.addCustomer(customer1);
        admin.addCustomer(customer2);
        admin.addCustomer(customer3);
        admin.addCustomer(customer4);
        admin.addCustomer(customer5);
        admin.addCustomer(customer6);

        LocalDate date = LocalDate.of(2023,12,22);

        admin.showCustomersOnDay(date);
        admin.showCustomersInMonth(date);
        admin.showIncomeInMonth(date);
        admin.showIncomeOnDay(date);
        admin.showCustomerById(1);

    }
}
