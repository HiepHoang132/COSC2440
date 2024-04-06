import java.util.ArrayList;
import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public class CustomerService implements ProcessManager<Customer, String>{
    private static List<Customer> customers;
    private static CustomerService instance;
    public CustomerService() {
        customers = new ArrayList<>();
    }
    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }
    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void delete(String id) {
        Customer customer = Utilities.getById(customers, id);
        if (customer != null) {
            customers.remove(customer);
        }
    }
    @Override
    public Customer getOne(String id) {
        return Utilities.getById(customers, id);
    }
    @Override
    public List<Customer> getAll() {
        return customers;
    }
}
