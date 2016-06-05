
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
    private UserInterface interf;
    private DAO dao;
    private Insumos insumos;
    private Finanças finanças;
    private Service lavagem;
    private ArrayList<Customer> customers;
    private ArrayList<Employee> employees;
            
    public Company(String name, String adress, String phone,
            String site, String cnpj, UserInterface interf)
    {
        name_company = name;
        address_company = adress;
        phone_company = phone;
        site_company = site;
        this.cnpj = cnpj;
        this.interf = interf;
        dao = new DAOArquivos();
        
        /* Cria objetos padrão na construtora,
        depois substitui pelos lidos do arquivo.
        
        Podemos chamar os métodos recoverData do
        DAO dentro do construtor? */
        
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
        customers = (ArrayList<Customer>) dao.recoverData(customers, "customersData");
        employees = (ArrayList<Employee>) dao.recoverData(employees, "employeesData");
        insumos = dao.recoverData(insumos, "insumosData");
        finanças = dao.recoverData(finanças, "financasData");
        lavagem = dao.recoverData(lavagem, "lavagemData");
        lavagem.setInsumos(insumos);
        
        int opcao = 0; //Reposta do menu principal
        int answer = 0; //Resposta dos outros menus
        boolean querVoltar = false;
        do{
            switch(opcao){
                case 1:
                    interf.mostrarFila(lavagem.getFila());
                    interf.menuFuncionario();
                    answer = input.nextInt();
                    querVoltar = executarFuncionario(answer);
                    break;
                case 2: 
                    interf.menuGerente();
                    answer = input.nextInt();
                    querVoltar = executarGerente(answer);
                    break;
                case 3:
                    interf.mostrarPrecos(lavagem);
                    querVoltar = true;
                    break;
                case 4:
                    interf.mostrarInfo(name_company, address_company, phone_company,
                                       site_company, cnpj);
                    querVoltar = true;
                    break;
                default:
                    interf.menuPrincipal(name_company);
                    opcao = input.nextInt();
            }
            if(querVoltar == true)
            {
                opcao = 10; //Volta pro menu principal
                querVoltar = false;
            }
        }while(opcao > 0);
                
        /* Salva as informações para poder
        recuperar na próxima vez que executar
        o programa */
        System.out.println("Salvando...");
        dao.saveData(customers, "customersData");
        dao.saveData(employees, "employeesData");
        dao.saveData(insumos, "insumosData");
        dao.saveData(finanças, "financasData");
        dao.saveData(lavagem, "lavagemData");
        
        /* Salva os arquivos para leitura */
        dao.saveCustomers(customers, "Clientes");
        dao.saveEmployees(employees, "Funcionários");
        dao.saveVehicles(customers, "Veículos");
        dao.saveInsumos(insumos, "Insumos");
        
        System.out.println("Saindo...");
    }
    public boolean executarFuncionario(int opcao)
    {
        Scanner input = new Scanner(System.in);

        String board;
        ArrayList<Vehicle> fila;
        Vehicle car;
        switch(opcao)
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
                if(car != null) //Tudo OK
                {
                    String rel = Service.fazDiagnostico(car);
                    System.out.println(rel);
                }
                else //O veículo não está cadastrado no sistema
                {
                    System.out.println("Veiculo nao encontrado.");
                    cadastrarVeiculo();
                }

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
    public boolean executarGerente(int opcao)
    {
        Scanner input = new Scanner(System.in);
        
        int answer;
        switch(opcao)
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
                answer = input.nextInt();
                interf.mostrarDados(answer, customers, employees);
                break;
            case 3:
                System.out.println("(1) Diário / Encerra dia");
                System.out.println("(2) Mensal / Encerra mes");
                System.out.println("(3) Anual / Encerra ano");
                answer = input.nextInt();
                gerarRelatorio(answer);
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
                System.out.println("Serviços realizados: " + lavagem.getServDia(dia));
                System.out.println("Lucros: " + finanças.getLucroDia(dia));
                break;
            case 2:
                finanças.caixaOut(finanças.getValorContas(), 0);
                finanças.caixaOut(finanças.calculaSalarios(employees), 0);
                rel = "DADOS DO MES " + nomeMes(mes) + "\n";
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
                dao.saveRelatorio(rel, nomeMes(mes));
                
                //Reseta os dias para começar um novo mês
                finanças.resetaDias();
                finanças.resetaGastosInsumos();
                lavagem.resetaDias();
                break;
            case 3:
                //Anual
                rel = "DADOS DE " + c.get(Calendar.YEAR) + "\n";
                rel = rel + "MES\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 12; i++)
                    rel = rel + nomeMes(i) + "\t" + lavagem.getServMes(i) + "\t" +
                            finanças.getLucroMes(i) + "\n";
                rel = rel + finanças.getSaldoAtual() + " Saldo atual";
                //Reseta os meses para começar um novo ano
                finanças.resetaMeses();
                lavagem.resetaMeses();
                dao.saveRelatorio(rel, "Relatório Anual");
                break;
            case 0:
                break;
            default:
                System.out.println("Opçao inválida");
                break;
        }
    }
    public String nomeMes(int mes)
    {
        String nomeMes;
        switch(mes)
        {
            case 0:
                return "Janeiro";
            case 1:
                return "Fevereiro";
            case 2:
                return "Março";
            case 3:
                return "Abril";
            case 4:
                return "Maio";
            case 5:
                return "Junho";
            case 6:
                return "Julho";
            case 7:
                return "Agosto";
            case 8:
                return "Setembro";
            case 9:
                return "Outubro";
            case 10:
                return "Novembro";
            default:
                return "Dezembro";
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
