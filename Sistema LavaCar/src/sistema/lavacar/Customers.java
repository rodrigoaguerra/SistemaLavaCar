/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class Customers extends Person implements Serializable {
    ArrayList<Vehicles> vehiclesOfCustomer = new ArrayList<>();
    
    public Customers(){
        Vehicles vehicle = new Vehicles();
        vehiclesOfCustomer.add(vehicle);
    }
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Nome: " + name + "\tRG: " + rg + "\tData de nascimento: " +
                dateOfBirth + "\n";
        rel = rel + "Telefone 1: " + phone1 + "\tTelefone 2: " + phone2 + "\n";
        rel = rel + "Endereço: " + address;
        rel = rel + "Data de cadastro: " + dateOfInsert + "\n";
        return rel;
    }
}



