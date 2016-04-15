/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class Customer extends Person implements Serializable {
    private final String dateOfInsert;
    public ArrayList<Vehicle> vehiclesOfCustomer;

    public SimpleDateFormat dateToday;

    public Customer(){
        dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateOfInsert = dateToday.format(new Date());
        
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
