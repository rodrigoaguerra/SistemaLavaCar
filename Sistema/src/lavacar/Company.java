
package lavacar;

import java.awt.CardLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.*;

public class Company {
    private String name_company;
    private String address_company;
    private String phone_company;
    private String site_company;
    private String cnpj;
    private UserInterface interf;
    private DAO dao;
    private Estoque estoque;
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
        
        estoque = new Estoque(100, 100, 100);
        finanças = new Finanças();
        lavagem = new Lavagem(40.00, 50.00, 60.00);
        customers = new ArrayList<>();
        employees = new ArrayList<>();
    }
    public void executar() throws FileNotFoundException
    {
        Scanner input = new Scanner(System.in);        
                
        /* Recupera as informações dos clientes,
        funcionários, estoque, finanças e serviços
        dos arquivos. */
        customers = (ArrayList<Customer>) dao.recoverData(customers, "customersData");
        employees = (ArrayList<Employee>) dao.recoverData(employees, "employeesData");
        estoque = dao.recoverData(estoque, "estoqueData");
        finanças = dao.recoverData(finanças, "financasData");
        lavagem = dao.recoverData(getService(), "lavagemData");
        getService().setEstoque(estoque);
        
        MenuPrincipal menu = new MenuPrincipal(this);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setVisible(true);
    }
    
    public void encerrar() 
    {
        /* Salva as informações para poder
        recuperar na próxima vez que executar
        o programa */
        System.out.println("Salvando...");
        dao.saveData(customers, "customersData");
        dao.saveData(employees, "employeesData");
        dao.saveData(estoque, "estoqueData");
        dao.saveData(finanças, "financasData");
        dao.saveData(getService(), "lavagemData");
        
        /* Salva os arquivos para leitura */
        dao.saveCustomers(customers, "Clientes");
        dao.saveEmployees(employees, "Funcionários");
        dao.saveVehicles(customers, "Veículos");
        dao.saveEstoque(estoque, "Estoque");
        
        System.out.println("Saindo...");
    }
    public Customer criaPessoa(String n, String rg, String cpf,
                    String birth,String a, String p1, String p2)
    {
        Customer person = new Customer(n, rg, cpf,
                            birth, a, p1, p2); 
        customers.add(person);
        return person;
    }
    public Customer procuraPessoa(String nome)
    {
        for(Customer p : customers)
            if(nome.equals(p.getName()))
                return p;
        return null;
    }
    public Vehicle procuraVeiculo(String board)
    {
        for(Customer p : customers)
            for(Vehicle v : p.vehiclesOfCustomer)
                if(board.equals(v.getBoard()))
                    return v;
        return null;
    }
    
    public boolean verificarMotor (Vehicle v) { return v.getMotor(); }
    public boolean verificarPneus (Vehicle v) { return v.getPneus(); }
    public boolean verificarOleo (Vehicle v) { return v.getOleo(); }
    public boolean verificarCombustivel (Vehicle v) { return v.getCombustivel(); }
    public boolean verificarTanque (Vehicle v) { return v.getTanque(); }
    public boolean verificarBateria (Vehicle v) { return v.getBateria(); }
    public boolean verificarPolimento (Vehicle v) { return v.getPolimento(); }
    public boolean verificarPintura (Vehicle v) { return v.getPintura(); }
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~ DAQUI PRA BAIXO É~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~ O QUE AINDA FALTA "MIGRAR" PRA~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~ INTERFACE ~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    
    public boolean executarFuncionario(int opcao)
    {
//            case 5:
//                String tempo = getService().estimarTempoDeEspera();
//                System.out.println(tempo);
//                break;
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
                String rel = estoque.gerarRelatorio();
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
                estoque.adicionar(sabao, esponjas, panos);
                double valorGasto = sabao*estoque.getPrecoSabao() +
                                    esponjas*estoque.getEsponjas() +
                                    panos*estoque.getPanos();
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
                getService().setPrices(prcSmall, prcMed, prcBig);
                break;
            case 0:
                return true;
            default:
                System.out.println("Opçao inválida");
        }
        return false;
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
                System.out.println("Serviços realizados: " + getService().getServDia(dia));
                System.out.println("Lucros: " + finanças.getLucroDia(dia));
                break;
            case 2:
                finanças.caixaOut(finanças.getValorContas(), 0);
                finanças.caixaOut(finanças.calculaSalarios(employees), 0);
                rel = "DADOS DO MES " + nomeMes(mes) + "\n";
                rel = rel + "DIA\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 31; i++)
                {
                    rel = rel + (i+1) + "\t" + getService().getServDia(i) + "\t" +
                            finanças.getLucroDia(i) + "\n";
                    valor += finanças.getLucroDia(i);
                } 
                rel = rel + "+" + valor + "\tTotal \n";
                rel = rel + "-" + finanças.getGastosEstoque() + "\tEstoque \n";
                rel = rel + "-" + finanças.calculaSalarios(employees) + "\tPagamentos \n";
                rel = rel + "-" + finanças.getValorContas() + "\tContas \n";
                rel = rel + "____________________________\n";
                valor = valor - finanças.getGastosEstoque()
                        - finanças.calculaSalarios(employees)
                        - finanças.getValorContas();
                rel = rel + valor + " Lucro do mês \n";
                finanças.setLucroMes(mes, valor);
                rel = rel + finanças.getSaldoAtual() + " Saldo atual";
                dao.saveRelatorio(rel, nomeMes(mes));
                
                //Reseta os dias para começar um novo mês
                finanças.resetaDias();
                finanças.resetaGastosEstoque();
                getService().resetaDias();
                break;
            case 3:
                //Anual
                rel = "DADOS DE " + c.get(Calendar.YEAR) + "\n";
                rel = rel + "MES\tSERVIÇOS\tLUCRO \n";
                for(int i=0; i < 12; i++)
                    rel = rel + nomeMes(i) + "\t" + getService().getServMes(i) + "\t" +
                            finanças.getLucroMes(i) + "\n";
                rel = rel + finanças.getSaldoAtual() + " Saldo atual";
                //Reseta os meses para começar um novo ano
                finanças.resetaMeses();
                getService().resetaMeses();
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

    
    
    /* Os getters são esses mesmos */
    public String getName_company() { return name_company; }
    public String getAddress_company() { return address_company; }
    public String getPhone_company() { return phone_company; }
    public String getSite_company() { return site_company; }
    public String getCnpj() { return cnpj; }
    public Service getService() { return lavagem; }
    public ArrayList getCustomers() { return customers; }
}
