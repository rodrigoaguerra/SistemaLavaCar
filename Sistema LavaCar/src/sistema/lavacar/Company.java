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
    private Insumos insumos;
   // private String logo_company; Não seria slogan?
            
    public Company(String name, String adress, String phone,
            String site, String cnpj)
    {
        name_company = name;
        address_company = adress;
        phone_company = phone;
        site_company = site;
        this.cnpj = cnpj;
        insumos = new Insumos(100, 100, 100);
    }
    public void executar(ArrayList<Customer> customers,
                         ArrayList<Employee> employees)
    {
        Scanner input = new Scanner(System.in);
        int answer = 0; //Reposta do menu principal
        boolean querVoltar=false;
        do{
            switch(answer){
                case 1:
                    querVoltar = menuCliente(customers);
                    break;
                case 2: 
                    querVoltar = menuVeiculo(customers);
                    break;
                case 3: 
                    /* "Executar"
                    "estimativa de tempo de espera"
                    "diagnóstico do veículo e serviços futuros" */
                    break;
                case 4:
                    querVoltar = menuFuncionario(employees);
                    break;
                case 5:
                    querVoltar = menuInsumos();
                    break;
                case 6:
                    /* Vai precisar conhecer a classe finanças
                    {to be created) para ver os lucros e gastos
                    "Diário"
                    "Mensal"
                    "Anual" */
                    break;
                case 7:
                    imprimeInfoEmpresa();
                    answer = 10;
                    break;
                default:
                    System.out.println("\n\t" + name_company);
                    System.out.println("\tMENU PRINCIPAL");
                    System.out.println("(1) Clientes");
                    System.out.println("(2) Veículos");
                    System.out.println("(3) Serviços");
                    System.out.println("(4) Funcionários");
                    System.out.println("(5) Insumos");
                    System.out.println("(6) Relatório");
                    System.out.println("(7) Informações da compania");
                    System.out.println("(0) Sair");
                    answer = input.nextInt();
                    input.nextLine(); //Tira o \n
            }
            if(querVoltar == true)
                answer = 10;   
        }while(answer > 0);
        System.out.println("Saindo...");
    }
    public boolean menuCliente(ArrayList<Customer> customers)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\tCLIENTES");
        System.out.println("(1) Novo cliente");
        System.out.println("(2) Listar todos os clientes");
        System.out.println("(3) Editar as informações de um cliente");
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        
        String name, address, phone1, phone2, rel;
        switch(answer){
            case 1:        
                System.out.print("Nome .: ");
                name = input.nextLine();

                System.out.print("RG .: ");
                String rg = input.nextLine();

                System.out.print("CPF .: ");
                String cpf = input.nextLine();

                System.out.print("Data de nascimento (DD MM AA).: ");   
                int dia = input.nextInt();
                int mes = input.nextInt();
                int ano = input.nextInt();
                input.nextLine(); //Tira o \n
                /* É melhor como int para poder verificar se é válida */
                String dateOfBirth = dia + "/" + mes + "/" + ano;

                System.out.print("Endereço .: ");
                address = input.nextLine();

                System.out.print("Telefone1 .: ");
                phone1 = input.nextLine();

                System.out.print("Telefone2 .: ");
                phone2 = input.nextLine();
                
                Customer person = new Customer(name, rg, cpf, dateOfBirth,
                                              address, phone1, phone2); 
                customers.add(person);
                System.out.println("Cliente cadastrado com sucesso!\n");
                break;
            case 2:
                System.out.println("\tLISTA DE CLIENTES");
                for(Customer p : customers)
                {
                    rel = p.gerarRelatorio();
                    System.out.println(rel);
                }
                break;
            case 3:
                System.out.print("Digite o nome do cliente: ");
                name = input.nextLine();
                for(Customer p : customers)
                    if(name.equals(p.getName()))
                    {
                        System.out.print("Digite o endereço: ");
                        address = input.nextLine();
                        System.out.print("Digite o telefone1: ");
                        phone1 = input.nextLine();
                        System.out.print("Digite o telefone2: ");
                        phone2 = input.nextLine();
                        p.setAddress(address);
                        p.setPhone1(phone1);
                        p.setPhone2(phone2);
                    }
                break;
            case 0:
                return true;
            default:
                System.out.println("Opcao invalida.");
        }
        return false;
    }
    public boolean menuVeiculo(ArrayList<Customer> customers)
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
                for(Customer p : customers)
                    if(name.equals(p.getName()))
                    {
                        p.adicionaVeiculo();
                        break;
                    }
                System.out.println("Veículo cadastrado com sucesso!");
                break;
            case 2:
                System.out.println("\tLISTA DE VEICULOS");
                for(Customer p : customers)
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
                for(Customer p : customers)
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
                for(Customer p : customers)
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
        System.out.println("(3) Editar as informações de um funcionário");
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        
        String name, address, phone1, phone2, rel;
        switch(answer){
            case 1:
                System.out.print("Nome .: ");
                name = input.nextLine();

                System.out.print("RG .: ");
                String rg = input.nextLine();

                System.out.print("CPF .: ");
                String cpf = input.nextLine();

                System.out.print("Data de nascimento (DD MM AA).: ");   
                int dia = input.nextInt();
                int mes = input.nextInt();
                int ano = input.nextInt();
                input.nextLine(); //Tira o \n
                /* É melhor como int para poder verificar se é válida */
                String dateOfBirth = dia + "/" + mes + "/" + ano;

                System.out.print("Endereço .: ");
                address = input.nextLine();

                System.out.print("Telefone1 .: ");
                phone1 = input.nextLine();

                System.out.print("Telefone2 .: ");
                phone2 = input.nextLine();

                System.out.print("Salario .; ");
                double payment = input.nextDouble();
                
                Employee person = new Employee(name, rg, cpf, dateOfBirth,
                                              address, phone1, phone2, payment); 
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
                name = input.nextLine();
                for(Employee p : employees)
                    if(name.equals(p.getName()))
                    {
                        System.out.print("Digite o endereço: ");
                        address = input.nextLine();
                        System.out.print("Digite o telefone1: ");
                        phone1 = input.nextLine();
                        System.out.print("Digite o telefone2: ");
                        phone2 = input.nextLine();
                        p.setAddress(address);
                        p.setPhone1(phone1);
                        p.setPhone2(phone2);
                    }
                break;                
            case 0:
                return true;
            default:
                System.out.println("Opcao invalida.");
        }
        return false;
    }
    public boolean menuInsumos()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\tINSUMOS");
        System.out.println("(1) Checar");
        System.out.println("(2) Adicionar");
        System.out.println("(0) Voltar ao menu principal");
        int answer = input.nextInt();
        input.nextLine();
        
        switch(answer)
        {
            case 1:
                System.out.println("Sabao: " + insumos.getSabao());
                System.out.println("Esponjas: " + insumos.getEsponjas());
                System.out.println("Panos: " + insumos.getPanos());
                break;
            case 2:
                System.out.println("Quantidade de sabao comprado: ");
                int sabao = input.nextInt();
                System.out.println("Quantidade de esponjas compradas: ");
                int esponjas = input.nextInt();
                System.out.println("Quantidade de panos comprados: ");
                int panos = input.nextInt();
                insumos.adicionar(sabao, esponjas, panos);
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
