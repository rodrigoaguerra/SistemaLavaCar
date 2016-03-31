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
        Scanner input = new Scanner (System.in);
        
        ArrayList<Customers> people = new ArrayList<>();
        
        // Lê as informações do sistema
        WorkingFile files = new WorkingFile();
  
        // Lê as informações dos clientes
        people = (ArrayList<Customers>) files.readCustumers(people, "customers");
        
        int answer = 0, answerCustomers;
        do{
            switch(answer){
                case 1 : // Clientes
                    System.out.println("(1) Cadastro De Cliente");
                    System.out.println("(2) Listar Clientes Cadastrados");
                    System.out.println("(0) Voltar ao menu inicial");
                    answerCustomers = input.nextInt();
                    switch(answerCustomers){
                        case 1:
                            Customers person = new Customers(); 
                            people.add(person);
                            break;
                        case 2:
                            for(Customers p : people){ /* Qual é a condição desse for? */
                                System.out.println("Nome: " + p.name);
                                System.out.println("RG: " + p.rg);
                                System.out.println("Celular: " + p.phone1);
                                System.out.println("Telefone: " + p.phone2);
                                System.out.println("Endereço: " + p.anddress);
                                System.out.println("Data de cadastro: " + p.dateOfInsert);
                                for(Vehicles v : p.vehiclesOfCustomer){ 
                                    System.out.println("Placa: " + v.board);
                                    System.out.println("Marca: " + v.brand);
                                    System.out.println("Modelo: " + v.model);
                                    System.out.println("Data de cadastro: " + v.dateOfInsert);
                                }
                            }
                            break;
                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("(1) Gerenciamento de Clientes");
                    System.out.println("(2) Gerenciamento de Veículos");
                    System.out.println("(0) Sair");
                    answer = input.nextInt();
            }
        }while(answer > 0);
        
        //Salva as informações dos clientes
        files.write(people, "customers"); /* Essa parte é o case 3 (Salvar Estado do Sistema)? */
    }
    
}


