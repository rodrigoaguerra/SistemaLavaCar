/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
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
    private Finanças finanças;
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
    private String[] relMensal;
    private String relAnual;
    private String relInsumos;
            
    public Company(String name, String adress, String phone,
            String site, String cnpj)
    {
        name_company = name;
        address_company = adress;
        phone_company = phone;
        site_company = site;
        this.cnpj = cnpj;
        
        insumos = new Insumos(100, 100, 100);
        finanças = new Finanças();
        Services.create(40.00, 50.00, 60.00, insumos);
        
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        relMensal = new String[12];
        relAnual = null;
    }
    public void executar() throws FileNotFoundException
    {
        Scanner input = new Scanner(System.in);        
        
        // Lê as informações dos clientes e funcionários
        customers = (ArrayList<Customer>) WorkingFile.recoverData(customers, "customersData");
        employees = (ArrayList<Employee>) WorkingFile.recoverData(employees, "employeesData");
        
        
        //Lê os relatórios em arquivo e passa pros
        //atributos de serviço, finanças e insumos
        
        
        int answer = 0; //Reposta do menu principal
        boolean querVoltar=false;
        do{
            switch(answer){
                case 1:
                    querVoltar = menuFuncionario();
                    break;
                case 2: 
                    querVoltar = menuGerente();
                    break;
                case 3:
                    imprimeInfoEmpresa();
                    answer = 10;
                    break;
                default:
                    System.out.println("\n\t" + name_company);
                    System.out.println("\tMENU PRINCIPAL");
                    System.out.println("(1) Funcionário");
                    System.out.println("(2) Gerente");
                    System.out.println("(3) Ver informações da empresa");
                    System.out.println("(0) Sair");
                    answer = input.nextInt();
                    input.nextLine(); //Tira o \n
            }
            if(querVoltar == true)
            {
                answer = 10;
                querVoltar = false;
            }
        }while(answer > 0);
                
        //Salva os clientes e seus veículos em um arquivo e os empregados em outro
        WorkingFile.saveData(customers, "customersData");
        WorkingFile.saveData(employees, "employeesData");
        
        
        //Salva também como um texto formatado, para facilitar a visualização
        //WorkingFile.write()
        //WorkingFile.write()
        //Vou pesquisar para ver como manipular arquivos em Java
        
        
        //Salva os relatórios e insumos para poder
        //recuperar na próxima execução

        
        System.out.println("Saindo...");
    }
    public boolean menuFuncionario()
    {
        Scanner input = new Scanner(System.in);
        Services.mostrarFila();
        System.out.println("\n\tFUNCIONARIO");
        System.out.println("(1) Cadastrar cliente");
        System.out.println("(2) Cadastrar veículo");
        System.out.println("(3) Lavar o próximo");
        System.out.println("(4) Colocar na fila");
        System.out.println("(5) Estimativa do tempo de espera");
        System.out.println("(6) Diagnóstico do veículo");
        System.out.println("(7) Editar cliente");
        System.out.println("(8) Editar veiculo");
        System.out.println("(0) Sair");
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        System.out.println("");
        String board;
        ArrayList<Vehicle> fila;
        Vehicle car;
        switch(answer)
        {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarVeiculo();
                break;
            case 3:
                double valor = Services.executar();
                if(valor != 0)
                    finanças.caixaIn(valor);
                else
                    System.out.println("Não foi possível realizar o serviço.");
                break;
            case 4:
                System.out.print("Digite a placa do veículo: ");
                board = input.nextLine();
                car = Services.procurarVeiculoNoSistema(board, customers);
                if(car != null) //Tudo OK
                {
                    fila = Services.getFila();
                    fila.add(car);
                    System.out.println("Veiculo adicionado à lista com sucesso!");
                }
                else //O veículo não está cadastrado no sistema
                {
                    System.out.println("Veiculo nao encontrado.");
                    cadastrarVeiculo();
                }
                break;
            case 5:
                String tempo = Services.estimarTempoDeEspera();
                System.out.println(tempo);
                break;
            case 6:
                System.out.print("Digite a placa do veículo: ");
                board = input.nextLine();
                car = Services.procurarVeiculoNoSistema(board, customers);
                String rel = Services.fazDiagnostico(car);
                System.out.println(rel);
                break;
            case 7:
                editarDados(1);
                break;
            case 8:
                editarDados(2);
                break;
            case 0:
                return true;
            default:
                System.out.println("Opçao inválida");
        }
        return false;
    }
    public boolean menuGerente()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n\tGERENTE");
        System.out.println("(1) Mudar preços da lavagem");
        System.out.println("(2) Checar insumos");
        System.out.println("(3) Comprar insumos");
        System.out.println("(4) Gerar relatórios / Encerrar");
        System.out.println("(5) Contratar um funcionário");
        System.out.println("(6) Listar dados");
        System.out.println("(0) Voltar ao menu principal");  
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        System.out.println("");

        int opcao;
        switch(answer)
        {
            case 1:
                System.out.println("  Digite os novos preços");
                System.out.print("Veículos pequenos: ");
                double valSmall = input.nextDouble();
                System.out.print("Veículos médios: ");
                double valMed = input.nextDouble();
                System.out.print("Veículos grandes: ");
                double valBig = input.nextDouble();
                Services.setValues(valSmall, valMed, valBig);
                break;
            case 2:
                String rel = insumos.gerarRelatorio();
                System.out.println(rel);
                break;
            case 3:
                System.out.println("Quantidade de cada insumo comprado");
                System.out.print("Sabao: ");
                int sabao = input.nextInt();
                System.out.print("Esponjas: ");
                int esponjas = input.nextInt();
                System.out.print("Panos: ");
                int panos = input.nextInt();
                insumos.adicionar(sabao, esponjas, panos);
                double valorGasto = sabao*insumos.getPrecoSabao() +
                                    esponjas*insumos.getEsponjas() +
                                    panos*insumos.getPanos();
                finanças.caixaOut(valorGasto, 1);
                break;
            case 4:
                System.out.println("(1) Diário / Encerra dia");
                System.out.println("(2) Mensal / Encerra mes");
                System.out.println("(3) Anual / Encerra ano");
                opcao = input.nextInt();
                System.out.println("");
                gerarRelatorio(opcao);
                break;
            case 5:
                cadastrarFuncionario();
                break;
            case 6:
                System.out.println("(1) Clientes");
                System.out.println("(2) Funcionários");
                System.out.println("(3) Todos os veículos");
                System.out.println("(4) Veículos de um cliente");
                System.out.println("(0) Voltar");
                opcao = input.nextInt();
                input.nextLine(); //Tira o \n
                System.out.println("");
                listarDados(opcao);
                break;
            case 0:
                return true;
            default:
                System.out.println("Opçao inválida");
        }
        return false;
    }
    public void cadastrarCliente()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Nome .: ");
        String name = input.nextLine();
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
        String address = input.nextLine();
        System.out.print("Telefone1 .: ");
        String phone1 = input.nextLine();
        System.out.print("Telefone2 .: ");
        String phone2 = input.nextLine();

        Customer person = new Customer(name, rg, cpf, dateOfBirth,
                                      address, phone1, phone2); 
        customers.add(person);
        System.out.println("Cliente cadastrado com sucesso!");
    }
    public void cadastrarVeiculo()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o nome do proprietario: ");
        String name = input.nextLine();
        for(Customer p : customers)
            if(name.equals(p.getName()))
            {
                p.adicionaVeiculo();
                System.out.println("Veículo cadastrado com sucesso!");
            }
        System.out.println("Cliente não encontrado!");
    }
    public void cadastrarFuncionario()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Nome .: ");
        String name = input.nextLine();
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
        String address = input.nextLine();
        System.out.print("Telefone1 .: ");
        String phone1 = input.nextLine();
        System.out.print("Telefone2 .: ");
        String phone2 = input.nextLine();
        System.out.print("Salario .; ");
        double payment = input.nextDouble();

        Employee person = new Employee(name, rg, cpf, dateOfBirth,
                                      address, phone1, phone2, payment); 
        employees.add(person);
        System.out.println("Funcionario contratado com sucesso!");
    }
    public void listarDados(int opcao)
    {
        Scanner input = new Scanner(System.in);
        String rel;
        switch(opcao)
        {
            case 1:
                System.out.println("\tLISTA DE CLIENTES");
                for(Customer p : customers)
                {
                    rel = p.gerarRelatorio();
                    System.out.println(rel);
                }
                break;
            case 2:
                System.out.println("\tLISTA DE FUNCIONARIOS");
                for(Employee em : employees){
                    rel = em.gerarRelatorio();
                    System.out.println(rel);
                }
                break;
            case 3:
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
            case 4:
                System.out.print("Digite o nome do proprietario: ");
                String name = input.nextLine();
                boolean achou=false;
                for(Customer p : customers)
                    if(name.equals(p.getName()))
                    {
                        achou = true;
                        System.out.println("\tVEICULOS DE " + name);
                        for(Vehicle v : p.vehiclesOfCustomer)
                        {
                            rel = v.gerarRelatorio();
                            System.out.println(rel);
                        }
                        break;
                    }
                if(achou==false)
                    System.out.println("Cliente não cadastrado.");
                break;
            case 0:
                break;
            default:
                System.out.println("Opcao inválida");
        }
    }
    public void editarDados(int opcao)
    {
        Scanner input = new Scanner(System.in);
        switch(opcao)
        {
            case 1:
                System.out.print("Digite o nome do cliente: ");
                String name = input.nextLine();
                String address, phone1, phone2;
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
                        break;
                    }
                break;
            case 2:
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
                break;
            default:
                System.out.println("Opcao inválida");
        }
    }
    public void gerarRelatorio(int opcao)
    {
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DAY_OF_MONTH)-1;
        int mes = c.get(Calendar.MONTH);
        String rel;
        
        double valor=0;
        switch (opcao) {
            case 1:
                //Não vale a pena guardar relatórios diários
                //em arquivos, basta mostrar na tela se o gerente pedir
                System.out.println("RELATÓRIO DO DIA " + (dia+1));
                System.out.println("Serviços realizados: " + Services.getServDia(dia));
                System.out.println("Lucros: " + finanças.getLucroDia(dia));
                break;
            case 2:
                //Mensal
                //Paga contas como luz, água, etc
                //E os funcionários
                finanças.caixaOut(finanças.getValorContas(), 0);
                finanças.caixaOut(finanças.calculaSalarios(employees), 0);
                rel = "DADOS DO MES " + (mes+1) + "\n";
                rel = rel + "DIA\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 31; i++)
                {
                    rel = rel + (i+1) + "\t" + Services.getServDia(i) + "\t" +
                            finanças.getLucroDia(i) + "\n";
                    valor += finanças.getLucroDia(i);
                }   rel = rel + "+" + valor + "\tTotal \n";
                rel = rel + "-" + finanças.getGastosInsumos() + "\tInsumos \n";
                rel = rel + "-" + finanças.calculaSalarios(employees) + "\tPagamentos \n";
                rel = rel + "-" + finanças.getValorContas() + "\tContas \n";
                rel = rel + "____________________________\n";
                valor = valor - finanças.getGastosInsumos()
                        - finanças.calculaSalarios(employees)
                        - finanças.getValorContas();
                rel = rel + valor + " Lucro do mês \n";
                finanças.setLucroMes(mes, valor);
                rel = rel + finanças.getSaldoAtual() + " Saldo atual \n";
                System.out.println(rel);
                relMensal[mes] = rel;
                //Reseta os dias para começar um novo mês
                finanças.resetaDias();
                break;
            case 3:
                //Anual
                rel = "DADOS DE " + c.get(Calendar.YEAR) + "\n";
                rel = rel + "MES\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 12; i++)
                    rel = rel + i + "\t" + Services.getServMes(i) + "\t" +
                            finanças.getLucroMes(i) + "\n";
                rel = rel + finanças.getSaldoAtual() + " Saldo atual \n";
                System.out.println(rel);
                relAnual = rel;
                //Reseta os meses para começar um novo ano
                finanças.resetaMeses();
                break;
            case 0:
                break;
            default:
                System.out.println("Opçao inválida");
                break;
        }
    }
    public void imprimeInfoEmpresa()
    {
        System.out.println("Nome: " + name_company);
        System.out.println("Endereço: " + address_company);
        System.out.println("Telefone: " + phone_company);
        System.out.println("Site: " + site_company);
        System.out.println("CNPJ: " + cnpj);
    }
}
