package sistema.lavacar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Company {
    private String name_company;
    private String address_company;
    private String phone_company;
    private String site_company;
    private String cnpj;
    private Insumos insumos;
    private Finanças finanças;
    private Lavagem lavagem;
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
            
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
        lavagem = new Lavagem(40.00, 50.00, 60.00);
        customers = new ArrayList<>();
        employees = new ArrayList<>();
    }
    public void executar() throws FileNotFoundException
    {
        Scanner input = new Scanner(System.in);        
        
        /* Recupera as informações dos clientes,
        funcionários, insumos, finanças e serviços
        dos arquivos. */
        customers = (ArrayList<Customer>) Arquivos.recoverData(customers, "customersData");
        employees = (ArrayList<Employee>) Arquivos.recoverData(employees, "employeesData");
        insumos = Arquivos.recoverData(insumos, "insumosData");
        finanças = Arquivos.recoverData(finanças, "financasData");
        lavagem = Arquivos.recoverData(lavagem, "lavagemData");
        lavagem.setInsumos(insumos);
        
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
                    System.out.println("Veiculo pequeno: R$ " + lavagem.getPriceSmall());
                    System.out.println("Veiculo médio: R$ " + lavagem.getPriceMedium());
                    System.out.println("Veiculo grande: R$ " + lavagem.getPriceBig());
                    answer = 10;
                    break;
                case 4:
                    System.out.println("Nome: " + name_company);
                    System.out.println("Endereço: " + address_company);
                    System.out.println("Telefone: " + phone_company);
                    System.out.println("Site: " + site_company);
                    System.out.println("CNPJ: " + cnpj); 
                    answer = 10;
                    break;
                default:
                    System.out.println("\n\t" + name_company);
                    System.out.println("\tMENU PRINCIPAL");
                    System.out.println("(1) Funcionário");
                    System.out.println("(2) Gerente");
                    System.out.println("(3) Ver preços");
                    System.out.println("(4) Ver informações da empresa");
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
                
        /* Salva as informações para poder
        recuperar na próxima vez que executar
        o programa */
        System.out.println("Salvando...");
        Arquivos.saveData(customers, "customersData");
        Arquivos.saveData(employees, "employeesData");
        Arquivos.saveData(insumos, "insumosData");
        Arquivos.saveData(finanças, "financasData");
        Arquivos.saveData(lavagem, "lavagemData");
        
        /* Salva os arquivos para leitura */
        Arquivos.saveCustomers(customers, "Clientes");
        Arquivos.saveEmployees(employees, "Funcionários");
        Arquivos.saveVehicles(customers, "Veículos");
        Arquivos.saveInsumos(insumos, "Insumos");
        
        System.out.println("Saindo...");
    }
    public boolean menuFuncionario()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        lavagem.mostrarFila();
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
                double valor = lavagem.executar();
                if(valor != 0)
                    finanças.caixaIn(valor);
                else
                    System.out.println("Não foi possível realizar o serviço.");
                break;
            case 4:
                System.out.print("Digite a placa do veículo: ");
                board = input.nextLine();
                car = procurarVeiculoNoSistema(board, customers);
                if(car != null) //Tudo OK
                {
                    fila = lavagem.getFila();
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
                String tempo = lavagem.estimarTempoDeEspera();
                System.out.println(tempo);
                break;
            case 6:
                System.out.print("Digite a placa do veículo: ");
                board = input.nextLine();
                car = procurarVeiculoNoSistema(board, customers);
                String rel = Service.fazDiagnostico(car);
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
        System.out.println("(1) Contratar um funcionário");
        System.out.println("(2) Listar dados");
        System.out.println("(3) Gerar relatórios / Encerrar");
        System.out.println("(4) Checar insumos");
        System.out.println("(5) Comprar insumos");
        System.out.println("(6) Mudar preços da lavagem");
        System.out.println("(0) Voltar ao menu principal");  
        int answer = input.nextInt();
        input.nextLine(); //Tira o \n
        System.out.println("");

        int opcao;
        switch(answer)
        {
            case 1:
                cadastrarFuncionario();
                break;
            case 2:
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
            case 3:
                System.out.println("(1) Diário / Encerra dia");
                System.out.println("(2) Mensal / Encerra mes");
                System.out.println("(3) Anual / Encerra ano");
                opcao = input.nextInt();
                System.out.println("");
                gerarRelatorio(opcao);
                break;
            case 4:
                String rel = insumos.gerarRelatorio();
                System.out.println(rel);
                break;
            case 5:
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
            case 6:
                System.out.println("  Digite os novos preços");
                System.out.print("Veículos pequenos: ");
                double prcSmall = input.nextDouble();
                System.out.print("Veículos médios: ");
                double prcMed = input.nextDouble();
                System.out.print("Veículos grandes: ");
                double prcBig = input.nextDouble();
                lavagem.setPrices(prcSmall, prcMed, prcBig);
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
                return;
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
                rel = "\tLISTA DE CLIENTES\n";
                for(Customer p : customers)
                    rel += p.getName() + "\t" + p.getDateOfInsert() + "\n";
                System.out.println(rel);
                break;
            case 2:
                rel = "\tLISTA DE FUNCIONARIOS\n";
                for(Employee em : employees)
                    rel += em.getName() + "\t" + em.getHiringDate() + "\n";
                System.out.println(rel);
                break;
            case 3:
                rel = "\tLISTA DE VEICULOS\n";
                for(Customer p : customers)
                {
                    rel += "Proprietario: " + p.getName() + "\n";
                    for(Vehicle v : p.vehiclesOfCustomer)
                        rel += v.getBoard() + "\t" + v.getBrand() + "\t" + v.getModel() + "\n";
                }
                System.out.println(rel);
                break;
            case 4:
                System.out.print("Digite o nome do proprietario: ");
                String name = input.nextLine();
                boolean achou=false;
                for(Customer p : customers)
                    if(name.equals(p.getName()))
                    {
                        achou = true;
                        rel = "\tVEICULOS DE " + name + "\n";
                        for(Vehicle v : p.vehiclesOfCustomer)
                            rel += v.getBoard() + "\t" + v.getBrand() + "\t" + v.getModel() + "\n";
                        System.out.println(rel);
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
        String rel,
               nomeMes;
        switch(mes)
        {
            case 0:
                nomeMes = "Janeiro";
                break;
            case 1:
                nomeMes = "Fevereiro";
                break;
            case 2:
                nomeMes = "Março";
                break;
            case 3:
                nomeMes = "Abril";
                break;
            case 4:
                nomeMes = "Maio";
                break;
            case 5:
                nomeMes = "Junho";
                break;
            case 6:
                nomeMes = "Julho";
                break;
            case 7:
                nomeMes = "Agosto";
                break;
            case 8:
                nomeMes = "Setembro";
                break;
            case 9:
                nomeMes = "Outubro";
                break;
            case 10:
                nomeMes = "Novembro";
                break;
            default:
                nomeMes = "Dezembro";
        }
        
        double valor=0;
        switch (opcao) {
            case 1:
                //Não vale a pena guardar relatórios diários
                //em arquivos, basta mostrar na tela se o gerente pedir
                System.out.println("RELATÓRIO DO DIA " + (dia+1));
                System.out.println("Serviços realizados: " + lavagem.getServDia(dia));
                System.out.println("Lucros: " + finanças.getLucroDia(dia));
                break;
            case 2:
                finanças.caixaOut(finanças.getValorContas(), 0);
                finanças.caixaOut(finanças.calculaSalarios(employees), 0);
                rel = "DADOS DO MES " + nomeMes + "\n";
                rel = rel + "DIA\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 31; i++)
                {
                    rel = rel + (i+1) + "\t" + lavagem.getServDia(i) + "\t" +
                            finanças.getLucroDia(i) + "\n";
                    valor += finanças.getLucroDia(i);
                } 
                rel = rel + "+" + valor + "\tTotal \n";
                rel = rel + "-" + finanças.getGastosInsumos() + "\tInsumos \n";
                rel = rel + "-" + finanças.calculaSalarios(employees) + "\tPagamentos \n";
                rel = rel + "-" + finanças.getValorContas() + "\tContas \n";
                rel = rel + "____________________________\n";
                valor = valor - finanças.getGastosInsumos()
                        - finanças.calculaSalarios(employees)
                        - finanças.getValorContas();
                rel = rel + valor + " Lucro do mês \n";
                finanças.setLucroMes(mes, valor);
                rel = rel + finanças.getSaldoAtual() + " Saldo atual";
                Arquivos.saveRelatorio(rel, nomeMes);
                
                //Reseta os dias para começar um novo mês
                finanças.resetaDias();
                lavagem.resetaDias();
                break;
            case 3:
                //Anual
                rel = "DADOS DE " + c.get(Calendar.YEAR) + "\n";
                rel = rel + "MES\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 12; i++)
                    rel = rel + nomeMes + "\t" + lavagem.getServMes(i) + "\t" +
                            finanças.getLucroMes(i) + "\n";
                rel = rel + finanças.getSaldoAtual() + " Saldo atual";
                //Reseta os meses para começar um novo ano
                finanças.resetaMeses();
                lavagem.resetaMeses();
                Arquivos.saveRelatorio(rel, "Relatório Anual");
                break;
            case 0:
                break;
            default:
                System.out.println("Opçao inválida");
                break;
        }
    }
    public Vehicle procurarVeiculoNoSistema(String board, ArrayList<Customer> customers)
    {
        for(Customer p : customers)
            for(Vehicle v : p.vehiclesOfCustomer)
                if(board.equals(v.getBoard()))
                    return v;
        return null;
    }
}
