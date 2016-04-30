/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author rodrigo
 */
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
        Vehicle vehicle = new Vehicle();
        vehiclesOfCustomer.add(vehicle);
    }
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Nome: " + name + "\tRG: " + rg + "\tCPF: " + cpf + "\n";
        rel = rel + "Telefone 1: " + phone1 + "\tTelefone 2: " + phone2 + "\n";
        rel = rel + "Endereço: " + address + "\tData de nascimento: " + dateOfBirth + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert + "\n";
        return rel;
    }
    public void adicionaVeiculo()
    {
        Vehicle vehicle = new Vehicle();
        vehiclesOfCustomer.add(vehicle);
    }
}
