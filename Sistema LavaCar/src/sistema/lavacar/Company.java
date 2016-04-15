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
 * @author a1762281
 */
public class Company {
    private String name_company;
    private String address_company;
    private String phone_company;
    private String site_company;
    private String cnpj; 
   // private String logo_company; Não seria slogan?
            
    public Company(String name, String adress, String phone,
            String site, String cnpj)
    {
        name_company = name;
        address_company = adress;
        phone_company = phone;
        site_company = site;
        this.cnpj = cnpj;
    }
    public int menuPrincipal()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\t" + name_company);
        System.out.println("\tMENU PRINCIPAL");
        System.out.println("(1) Clientes");
        System.out.println("(2) Veículos");
        System.out.println("(3) Serviços");
        System.out.println("(4) Funcionários");
        System.out.println("(5) Insumos");
        System.out.println("(6) Informações da compania");
        System.out.println("(0) Sair");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        return answer;
    }
    public boolean menuCliente(ArrayList<Customer> people)
    {
        Scanner input = new Scanner(System.in);
        input.nextLine(); //Tira os possíveis \n
        System.out.println("\tCLIENTES");
        System.out.println("(1) Novo cliente");
        System.out.println("(2) Listar todos os clientes");
        System.out.println("(3) Editar as informações de um cliente");
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        
        switch(answer){
            case 1:
                Customer person = new Customer(); 
                people.add(person);
                System.out.println("Cliente cadastrado com sucesso!\n");
                break;
            case 2:
                System.out.println("\tLISTA DE CLIENTES");
                for(Customer p : people)
                {
                    String rel = p.gerarRelatorio();
                    System.out.println(rel);
                }
                break;
            case 3:
                System.out.print("Digite o nome do cliente: ");
                String name = input.nextLine();
                String address, phone1, phone2;
                for(Customer p : people)
                    if(name.equals(p.getName()))
                    {
                        System.out.println("Digite o endereço: ");
                        address = input.nextLine();
                        System.out.println("Digite o telefone1: ");
                        phone1 = input.nextLine();
                        System.out.println("Digite o telefone2: ");
                        phone2 = input.nextLine();
                    }
                break;
            case 0:
                return true;
            default:
                System.out.println("Opcao invalida.");
        }
        return false;
    }
    public boolean menuVeiculo(ArrayList<Customer> people)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\tVEICULOS");
        System.out.println("(1) Cadastro");
        System.out.println("(2) Listar todos os veículos");
        System.out.println("(3) Listar veículos de um cliente específico");
        System.out.println("(4) Editar informações de um veículo");
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        
        String name, rel;
        switch(answer){
            case 1:                
                System.out.print("Digite o nome do proprietario: ");
                name = input.nextLine();
                for(Customer p : people)
                    if(name.equals(p.getName()))
                    {
                        p.adicionaVeiculo();
                        break;
                    }
                System.out.println("Veículo cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("\tLISTA DE VEICULOS");
                for(Customer p : people)
                {
                    System.out.println("Proprietario: " + p.getName());
                    for(Vehicle v : p.vehiclesOfCustomer)
                    {
                        rel = v.gerarRelatorio();
                        System.out.println(rel);
                    }
                }
                break;
            case 3:
                System.out.print("Digite o nome do proprietario: ");
                name = input.nextLine();

                /*  Procura o cliente no ArrayList e mostra as
                    informações do(s) seu(s) veículo(s) */
                System.out.println("\tVEICULOS DE " + name);
                for(Customer p : people)
                    if(name.equals(p.getName()))
                    {
                        for(Vehicle v : p.vehiclesOfCustomer)
                        {
                            rel = v.gerarRelatorio();
                            System.out.println(rel);
                        }
                        break;
                    }
                break;
            case 4:
                System.out.print("Digite a placa do veículo a ser alterado: ");
                String board = input.nextLine();
                String color, description;
                for(Customer p : people)
                    for(Vehicle v : p.vehiclesOfCustomer)
                        if(board.equals(v.getBoard()))
                        {
                            System.out.print("Digite a cor do veiculo: ");
                            color = input.nextLine();
                            System.out.print("Digite a descrição: ");
                            description = input.nextLine();
                            v.setColor(color);
                            v.setDescription(description);
                        }
                break;
            case 0:
                return true;
            default:
                System.out.println("Opcao invalida.");
        }
        return false;
    }
    public boolean menuFuncionario(ArrayList<Employee> employees)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\tFUNCIONARIOS");
        System.out.println("(1) Novo funcionário");
        System.out.println("(2) Listar todos os funcionários");
        System.out.println("(3) Editar as informações de um funcionário")
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        
        String rel;
        switch(answer){
            case 1:
                Employee person = new Employee(); 
                employees.add(person);
                System.out.println("Funcionario contratado com sucesso!");
                break;
            case 2:
                System.out.println("\tLISTA DE FUNCIONARIOS");
                for(Employee em : employees){
                    rel = em.gerarRelatorio();
                    System.out.println(rel);
                }
                break;
            case 3:
                System.out.print("Digite o nome do funcionário: ");
                String name = input.nextLine();
                String address, phone1, phone2;
                for(Employee p : employees)
                    if(name.equals(p.getName()))
                    {
                        System.out.println("Digite o endereço: ");
                        address = input.nextLine();
                        System.out.println("Digite o telefone1: ");
                        phone1 = input.nextLine();
                        System.out.println("Digite o telefone2: ");
                        phone2 = input.nextLine();
                    }
                break;                
            case 0:
                return true;
            default:
                System.out.println("Opcao invalida.");
        }
        return false;
    }
    public void imprimeInfoEmpresa()
    {
        System.out.println("Nome: " + name_company);
        System.out.println("Endereço: " + address_company);
        System.out.println("Telefone: " + phone_company);
        System.out.println("Site: " + site_company);
        System.out.println("CNPJ: " + cnpj + "\n");
    }
}
