package Control;

import Model.Customer;
import java.util.Comparator;

/**
 *
 * @author felipe
 */
public class Compara implements Comparator<Customer> {

    public int compare(Customer c1, Customer c2) {
        if (c1.getVl_total() > c2.getVl_total()) {
            return -1;
        } else if (c1.getVl_total() < c2.getVl_total()) {
            return +1;
        } else {
            return 0;
        }
    }
}
