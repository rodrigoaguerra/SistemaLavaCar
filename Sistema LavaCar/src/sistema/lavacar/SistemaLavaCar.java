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
    private String cnpj; 
    private String name_company;
    private String anddress_company;
    private String phone_company;
    private String logo_company;
    
      
    public static void main(String[] args) throws FileNotFoundException{
        int answer, answerCustomers;
        ArrayList<Customers> people = new ArrayList<>();
        
        // first read data from system
        WorkingFile files = new WorkingFile();
  
        // read data from customers
        people = (ArrayList<Customers>) files.readCustumers(people, "customers");
        
        answer = 9;
        Scanner input = new Scanner (System.in);
        while(answer > 0){
            switch(answer){
                case 1 : // insert cliente
                    System.out.println("Digite 1 Cadastro De Cliente\n" +
                                       "Digite 2 Listar Clientes Cadastrados\n" +
                                       "ou  0  para voltar ao menu inicial .:");
                    answerCustomers = input.nextInt();
                    switch(answerCustomers){
                        case 1:
                            Customers person = new Customers(); 
                            people.add(person);
                        break;
                        case 2:
                            
                            for(Customers p : people){
                                System.out.println("Name .:" + p.name);
                                System.out.println("RG .:" + p.rg);
                                System.out.println("Phone1 .:" + p.phone1);
                                System.out.println("Phone1 .:" + p.phone2);
                                System.out.println("Andress .:" + p.anddress);
                                System.out.println("DateOfInsert" + p.dateOfInsert);
                            }
                        break;
                        default:
                            answer = 9;
                        break;
                    }
                break;
                default:
                    System.out.println("Digite 1 Gerenciamento De Clientes\n" +
                                       "Digite 2 Gerenciamento De Veículos\n" + 
                                       "Digite 3 Salvar Estado Do Sistema\n" +
                                       "ou  0  para sair .:");
                    answer = input.nextInt();
                break;
            }
        }
        
        //save data from customers
        files.write(people, "customers");
    }
    
}

