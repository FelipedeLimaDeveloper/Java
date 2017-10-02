package application;

import Control.Compara;
import java.text.DecimalFormat;
import java.util.Scanner;
import Model.Customer;
import Control.ConnectionMySQL;
import Control.DaoCustomer;
import Control.VerificaCNPJ_CPF;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author felipe
 */
public class Aplic {

    public static void main(String[] args) {
        DaoCustomer daoCustomer = null;
        ConnectionMySQL Conexao = new ConnectionMySQL("sql10196919", "9TnBckktCM");
        Conexao.setDriver("com.mysql.jdbc.Driver");
        Conexao.setConnectionString("jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10196919");
        daoCustomer = new DaoCustomer(Conexao.conectar());

        ArrayList<Customer> list_customer = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("#,##0.00");
        Scanner entrada = new Scanner(System.in);
        int opc = 2;
        int i;
        double total = 0;
        String cpf_cnpj = "";
        int status;
        do {
            System.out.println("\n-----Adicionando Clientes-----\n");
            System.out.println("Digite 1 -> para adicionar um cliente");
            System.out.println("Digite 2 -> para exibir media final e encerrar");
            opc = entrada.nextInt();
            status = 0;
            switch (opc) {
                case 1:

                    System.out.println("Insira o ID do Cliente:");
                    int id = entrada.nextInt();
                    do {
                        System.out.println("Insira o CPF ou CNPJ do Cliente:");
                        cpf_cnpj = entrada.next();
                        if (VerificaCNPJ_CPF.isValidCPF(cpf_cnpj)) {
                            status = 1;
                        } else if (VerificaCNPJ_CPF.isCnpjValido(cpf_cnpj)) {
                            status = 1;
                        } else {
                            System.out.println("CPF ou CNPJ inválido, digite novamente...Tecle ENTER para tentar novamente.");
                            entrada.nextLine();
                            entrada.nextLine();
                        }
                    } while (status == 0);

                    System.out.println("Insira o Nome do Cliente:");
                    entrada.nextLine();
                    String nome = entrada.nextLine();

                    Customer customer = new Customer(id, cpf_cnpj, nome); //construtor cliente

                    System.out.println("Insira o Status do Cliente, 1 = Ativo, 0 = Inativo");
                    customer.setIs_active(entrada.nextInt());

                    System.out.println("Insira o valor de saldo do Cliente");
                    customer.setVl_total(entrada.nextDouble());

                    daoCustomer.inserir(customer); //insere cliente
                    break;

                case 2:
                    total = daoCustomer.consultar(list_customer);
                    Collections.sort(list_customer, new Compara());
                    System.out.print("\n\t\t>>>>>> Media final: " + df.format(total / list_customer.size()) + " <<<<<<\n\n");
                    System.out.print("Id_Customer" + "\t" + "Name_Customer" + "\t" + "CPF_CNPJ" + "\t" + "Value_total" + "\t" + "Status\n");

                    for (i = 0; i < list_customer.size(); i++) {
                        System.out.print("" + list_customer.get(i).getId_customer() + "\t\t" + list_customer.get(i).getNm_customer() + "\t" + list_customer.get(i).getCpf_cnpj() + "\t" + df.format(list_customer.get(i).getVl_total()) + "\t" + (list_customer.get(i).getIs_active() == 1 ? "Ativo" : "Inativo") + "\n");

                    }
                    break;

            }
        } while (opc != 2);
    }
}
