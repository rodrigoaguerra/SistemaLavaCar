
package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Customer extends Person implements Serializable {
    private final String dateOfInsert;
    public ArrayList<Vehicle> vehiclesOfCustomer;

    public Customer(String n, String rg, String cpf, String d,
                    String a, String p1, String p2)
    {
        super(n, rg, cpf, d, a, p1, p2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        dateOfInsert = sdf.format(c.getTime());
        
        vehiclesOfCustomer = new ArrayList<>();
        adicionaVeiculo();
    }
    @Override
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Nome: " + name + "\tRG: " + rg + "\tCPF: " + cpf + "\n";
        rel = rel + "Telefone 1: " + phone1 + "\tTelefone 2: " + phone2 + "\n";
        rel = rel + "Endereço: " + address + "\tData de nascimento: " + dateOfBirth + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert;
        return rel;
    }
    public void adicionaVeiculo()
    {
        Scanner input = new Scanner (System.in);  
        String br, m, bo, y, c, d;
        int size;
        System.out.println("\tInformaçoes do veiculo");  
        System.out.print("Marca .: "); 
        br = input.nextLine(); 
        System.out.print("Modelo .: "); 
        m = input.nextLine(); 
        System.out.print("Placa .: "); 
        bo = input.nextLine(); 
        System.out.print("Ano .: "); 
        y = input.nextLine(); 
        System.out.print("Cor .: "); 
        c = input.nextLine(); 
        System.out.print("Decrição .: ");   
        d = input.nextLine();   
        System.out.print("Tamanho (1 - Pequeno, 2 - Médio, 3 - Grande) .: ");   
        size = input.nextInt();
        
        Vehicle vehicle = new Vehicle(br, m, bo, y, c, d, size);
        vehiclesOfCustomer.add(vehicle);
    }
    public String getDateOfInsert() { return dateOfInsert; }
}
