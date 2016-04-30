/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio
 */
public class SistemaLavaCar {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner(System.in);

        Company c = new Company("Lava Car T", "Rua Marechal Floriano, 57",
                            "3089-1452", "www.lavacart.com", "71.546.945/0001-53");
                        
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Employee> employees  = new ArrayList<>();
        
        // Lê as informações dos clientes e funcionários
        customers = (ArrayList<Customer>) WorkingFile.read(customers, "customers");
        employees = (ArrayList<Employee>) WorkingFile.read(employees, "employees");
        
        c.executar(customers, employees);
        
        //Salva os clientes e seus veículos em um arquivo e os empregados em outro
        WorkingFile.write(customers, "customers");
        WorkingFile.write(employees, "employees");
    }
}
