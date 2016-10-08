
package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Customer extends Person implements Serializable {
    private final String dateOfInsert;
    public ArrayList<Vehicle> vehiclesOfCustomer;

    public Customer(String n, String rg, String cpf, String d,
                    String a, String p1, String p2)
    {
        super(n, rg, cpf, d, a, p1, p2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        dateOfInsert = sdf.format(c.getTime());
        
        vehiclesOfCustomer = new ArrayList<>();
    }
    @Override
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Nome: " + name + "\tRG: " + rg + "\tCPF: " + cpf + "\n";
        rel = rel + "Telefone 1: " + phone1 + "\tTelefone 2: " + phone2 + "\n";
        rel = rel + "Endereço: " + address + "\tData de nascimento: " + dateOfBirth + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert;
        return rel;
    }
    public void adicionaVeiculo(String br, String bo, String m, String y,
                            String c, String d, int size)
    {
        Vehicle vehicle = new Vehicle(br, m, bo, y, c, d, size);
        vehiclesOfCustomer.add(vehicle);
    }
    public String getDateOfInsert() { return dateOfInsert; }
    public ArrayList<Vehicle> getVehiclesOfCustomer() { return vehiclesOfCustomer; }
}
