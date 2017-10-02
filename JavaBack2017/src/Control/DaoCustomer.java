package Control;

import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felipe
 */
public class DaoCustomer {

    private Connection conn;

    public DaoCustomer(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Customer customer) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("INSERT INTO tb_customer_account(id_customer, cpf_cnpj, nm_customer, is_active, vl_total) VALUES(?,?,?,?,?)");
            ps.setInt(1, customer.getId_customer());
            ps.setString(2, customer.getCpf_cnpj());
            ps.setString(3, customer.getNm_customer());
            ps.setInt(4, customer.getIs_active());
            ps.setDouble(5, customer.getVl_total());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.toString());

        }
    }

    public double consultar(ArrayList<Customer> list_customer) {
        double total = 0;
        Customer customer = null;
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM tb_customer_account " + "WHERE vl_total > 560" + " AND " + "id_customer BETWEEN 1500 AND 2700");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getInt("id_customer"), rs.getString("cpf_cnpj"), rs.getString("nm_customer"));
                customer.setIs_active(rs.getInt("is_active"));
                customer.setVl_total(Double.parseDouble(rs.getString("vl_total")));
                total += customer.getVl_total();

                list_customer.add(customer);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

        return total;
    }

}
