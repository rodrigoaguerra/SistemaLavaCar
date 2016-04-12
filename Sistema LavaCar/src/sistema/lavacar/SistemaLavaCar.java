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
    private String name_company;
    private String address_company;
    private String phone_company;
    private String site_company;
    private String cnpj; 
   /* private String logo_company; Não seria slogan? */
      
    /*
    Pensei em fazer uma construtora para a classe principal, dando os valores
    da companhia (nome, phone, cnpj...), mas não deu certo.
    
    public SistemaLavaCar()
    {
        Scanner input = new Scanner (System.in);
        
        System.out.println("\tINFORMACOES DA EMPRESA");
        System.out.print("Nome: ");
        name_company = input.nextLine();
        System.out.print("Endereço: ");
        address_company = input.nextLine();
        System.out.print("Telefone: ");
        phone_company = input.nextLine();
        System.out.print("Site: ");
        site_company = input.nextLine();
        System.out.print("CNPJ: ");
        cnpj = input.nextLine();
    }*/
    public static void main(String[] args) throws FileNotFoundException{
        Scanner input = new Scanner (System.in);
        
        ArrayList<Customers> people = new ArrayList<>();
        ArrayList<Employee> employees  = new ArrayList<>();

        // Lê as informações dos clientes, funcionários
        people = (ArrayList<Customers>) WorkingFile.read(people, "customers");
        employees = (ArrayList<Employee>) WorkingFile.read(employees, "employees");
        
        int answer = 0, //Reposta do menu principal
            answer2; //Resposta dos sub-menus
        String name, rel;
        do{
            switch(answer){
                case 1 : // Gerenciamento de clientes
                    System.out.println("\tCLIENTES");
                    System.out.println("(1) Cadastrar um novo cliente");
                    System.out.println("(2) Listar clientes cadastrados");
                    System.out.println("(0) Voltar ao menu principal");
                    answer2 = input.nextInt();
                    switch(answer2){
                        case 1:
                            Customers person = new Customers(); 
                            people.add(person);
                            System.out.println("Cliente cadastrado com sucesso!");
                            break;
                        case 2:
                            System.out.println("\tLISTA DE CLIENTES");
                            /* Aqui poderíamos imprimir só o nome, para parecer
                            mais com uma lista. Afinal, todas as informações vão
                            ficar salvas no relatório depois. */
                            for(Customers p : people){
                                rel = p.gerarRelatorio();
                                System.out.println(rel);
                                }
                            break;
                        case 0:
                            answer = 9;
                            break;
                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break;
                case 2: //Gerenciamento de veículos
                    System.out.println("\tVEICULOS");
                    System.out.println("(1) Cadastrar veículo em um cliente já existente");
                    System.out.println("(2) Listar veículos de um cliente");
                    System.out.println("(0) Voltar ao menu principal");
                    answer2 = input.nextInt();
                    switch(answer2){
                        case 1:
                            System.out.print("Digite o nome do proprietario: ");
                            name = input.nextLine();
                            /* Procura o nome no Arraylist. Não tenho certeza
                            como fazer isso ainda.
                            Depois cria um novo Vehicle, pede as informações
                            e guarda no Arraylist de veículos do cliente. */
                            System.out.println("Veículo cadastrado com sucesso!");
                            break;
                        case 2:
                            System.out.print("Digite o nome do proprietario: ");
                            name = input.nextLine();
                            /* Procura o nome no Arraylist de novo. Ainda não
                            sei como.
                            
                            for(Vehicles v: name.vehiclesOfCostumer){
                                rel = v.gerarRelatorio();
                                System.out.println(rel);
                            }*/
                            break;
                        case 0:
                            answer = 9;
                            break;
                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break;
                case 3: //Gerenciamento de serviços
                    /* Podemos ter duas opções aqui, realizar serviços e
                    checar insumos, ou 2 cases separados no menu
                    principal. */
                    answer = 9;
                    break;
                case 4: //Gerenciamento de funcionários
                    System.out.println("\tFUNCIONARIOS");
                    System.out.println("(1) Cadastrar um novo funcionário");
                    System.out.println("(2) Listar funcionários Cadastrados");
                    System.out.println("(0) Voltar ao menu principal");
                    answer2 = input.nextInt();
                    switch(answer2){
                        case 1:
                            Employee p = new Employee(); 
                            employees.add(p);
                            System.out.println("Funcionario contratado com sucesso!");
                            break;
                        case 2:
                            System.out.println("\tLISTA DE FUNCIONARIOS");
                            for(Employee em : employees){
                                rel = em.gerarRelatorio();
                                System.out.println(rel);
                            }
                            break;
                        case 0:
                            answer = 9;
                            break;
                        default:
                            System.out.println("Opcao invalida.");
                    }
                    break;
                case 5:
                    answer = 9;
                    break;
                default:
                    System.out.println("\tMENU PRINCIPAL");
                    /*Posteriormente pode ser o name_company*/
                    System.out.println("(1) Clientes");
                    System.out.println("(2) Veículos");
                    System.out.println("(3) Serviços");
                    System.out.println("(4) Funcionários");
                    System.out.println("(5) Relatórios");
                    System.out.println("(0) Sair");
                    answer = input.nextInt();
            }
            System.out.println("");
        }while(answer > 0);
        
        //Salva os clientes e seus veículos em um arquivo e os empregados em outro
        WorkingFile.write(people, "customers");
        WorkingFile.write(employees, "employees");
    }
}
