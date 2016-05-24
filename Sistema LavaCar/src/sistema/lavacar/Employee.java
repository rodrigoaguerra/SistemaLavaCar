
package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Employee  extends Person implements Serializable{
    private final String hiringDate;
    private double payment;
    
    public Employee(String n, String rg, String cpf, String d,
                    String a, String p1, String p2, double p){
        super(n, rg, cpf, d, a, p1, p2);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Calendar c = Calendar.getInstance();
        hiringDate = sdf.format(c.getTime());
        
        payment = p;
    }
    @Override
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Nome: " + name + "\tRG: " + rg + "\tCPF: " + cpf + "\n";
        rel = rel + "Telefone 1: " + phone1 + "\tTelefone 2: " + phone2 + "\n";
        rel = rel + "Endereço: " + address + "\tData de nascimento: " + dateOfBirth + "\n";
        rel = rel + "Data de contratação: " + hiringDate;
        return rel;
    }
    public double getPayment() { return payment; }
    public String getHiringDate() { return hiringDate; }
}
