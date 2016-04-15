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
        
        System.out.println("\tINFORMACOES DA EMPRESA");
        System.out.print("Nome: ");
        String name = input.nextLine();
        System.out.print("Endereço: ");
        String address = input.nextLine();
        System.out.print("Telefone: ");
        String phone = input.nextLine();
        System.out.print("Site: ");
        String site = input.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = input.nextLine();

        Company c = new Company(name, address, phone, site, cnpj);
                        
        ArrayList<Customer> people = new ArrayList<>();
        ArrayList<Employee> employees  = new ArrayList<>();
        
        // Lê as informações dos clientes, funcionários
        people = (ArrayList<Customer>) WorkingFile.read(people, "customers");
        employees = (ArrayList<Employee>) WorkingFile.read(employees, "employees");
        
        int answer = 0; //Reposta do menu principal
        boolean volta;
        do{
            switch(answer){
                case 1 : // Gerenciamento de clientes
                    volta = c.menuCliente(people);
                    if(volta == true)
                        answer = 10;
                    break;
                case 2: //Gerenciamento de veículos
                    volta = c.menuVeiculo(people);
                    if(volta == true)
                        answer = 10;
                    break;
                case 3: //Gerenciamento de serviços
                    /* Alguns dos itens daqui vão ser "estimativa de tempo de
                    espera" e "diagnóstico do veículo e serviços futuros" e
                    "geração de relatórios".
                    Quando for lavar o veículo pode ter algo como
                    setFuncionario, onde selecionamos um aleatoriamente */
                    break;
                case 4: //Gerenciamento de funcionários
                    volta = c.menuFuncionario(employees);
                    if(volta == true)
                        answer = 10;                    
                    break;
                case 5: //Gerenciamento de insumos
                    
                    break;
                case 6:
                    c.imprimeInfoEmpresa();
                    answer = 10;
                    break;
                default:
                    answer = c.menuPrincipal();
            }
        }while(answer > 0);
        
        //Salva os clientes e seus veículos em um arquivo e os empregados em outro
        WorkingFile.write(people, "customers");
        WorkingFile.write(employees, "employees");
    }
}
