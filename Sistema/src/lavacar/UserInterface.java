
package lavacar;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    public void menuFuncionario()
    {
        System.out.println("");
        System.out.println("\n\tFUNCIONARIO");
//        System.out.println("(1) Cadastrar cliente");
//        System.out.println("(2) Cadastrar veículo");
        System.out.println("(3) Lavar o próximo");
        System.out.println("(4) Colocar na fila");
        System.out.println("(5) Estimativa do tempo de espera");
        System.out.println("(6) Diagnóstico do veículo");
//        System.out.println("(7) Editar cliente");
//        System.out.println("(8) Editar veiculo");
        System.out.println("(0) Sair");
    }
    public void menuGerente()
    {
        System.out.println("");
        System.out.println("\n\tGERENTE");
        System.out.println("(1) Contratar um funcionário");
        System.out.println("(2) Listar dados");
        System.out.println("(3) Gerar relatórios / Encerrar");
        System.out.println("(4) Checar insumos");
        System.out.println("(5) Comprar insumos");
        System.out.println("(6) Mudar preços da lavagem");
        System.out.println("(0) Voltar ao menu principal");  
    }
//    public void mostrarFila(ArrayList<Vehicle> fila)
//    {
//        if(fila.isEmpty())
//            return;
//        
//        System.out.println("");
//        System.out.println("\tVEÍCULOS NA FILA");
//        for(int i=0; i < fila.size(); i++)
//            System.out.println(fila.get(i).getBoard());
//    }
//    public void mostrarPrecos(Service s)
//    {
//        System.out.println("");
//        System.out.println("Veiculo pequeno: R$ " + s.getPriceSmall());
//        System.out.println("Veiculo médio: R$ " + s.getPriceMedium());
//        System.out.println("Veiculo grande: R$ " + s.getPriceBig());
//    }
//    public void mostrarInfo(String n, String e, String t, String s, String c)
//    {
//        System.out.println("");
//        System.out.println("Nome: " + n);
//        System.out.println("Endereço: " + e);
//        System.out.println("Telefone: " + t);
//        System.out.println("Site: " + s);
//        System.out.println("CNPJ: " + c); 
//    }
    public void mostrarDados(int opcao, ArrayList<Customer> customers,
                            ArrayList<Employee> employees)
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
}
