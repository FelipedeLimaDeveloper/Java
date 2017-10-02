package Model;

/**
 *
 * @author felipe
 */
public class Customer {

    private int id_customer;
    private String cpf_cnpj;
    private String nm_customer;
    private int is_active;
    private double vl_total;

    public Customer(int id_customer, String cpf_cnpj, String nm_customer) {
        this.id_customer = id_customer;
        this.cpf_cnpj = cpf_cnpj;
        this.nm_customer = nm_customer;
    }

    public int getId_customer() {
        return id_customer;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public String getNm_customer() {
        return nm_customer;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public double getVl_total() {
        return vl_total;
    }

    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }

}
