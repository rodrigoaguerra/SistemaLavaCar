/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Employee  extends Person implements Serializable{
    String cpf;
    
    public Employee(){
        Scanner input = new Scanner (System.in);

        System.out.print("Data De Aniversário .: ");
        dateOfBorn = input.nextLine();

        System.out.print("CPF .: ");
        cpf = input.nextLine();
    }
}
