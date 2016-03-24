/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio
 */
public class Person implements Serializable {
    public String name, rg,
                  anddress,
                  phone1,
                  phone2,
                  dateOfInsert,
                  dateOfBorn;
    SimpleDateFormat dateToday;
    public Person(){
        Scanner input = new Scanner (System.in);

        System.out.print("Nome .: ");
        name = input.nextLine();

        System.out.print("RG .: ");
        rg = input.nextLine();

        System.out.print("Endereço .: ");
        anddress = input.nextLine();

        System.out.print("Telefone1 .: ");
        phone1 = input.nextLine();

        System.out.print("Telefone2 .: ");
        phone2 = input.nextLine();

        dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateOfInsert = dateToday.format(new Date());
    }
}
