
package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Finanças implements Serializable{
    private double[] lucroDia;
    private double[] lucroMes;
    private double saldoAtual;
    private double valorContas;
    private double gastosEstoque;
    
    public Finanças()
    {
        lucroDia = new double[31];
        lucroMes = new double[12];
        saldoAtual = 0;
        valorContas = 1000.00;
        gastosEstoque = 0;
    }
    public void caixaIn(double valor)
    {
        //Soma o valor recebido no dia e mes atuais
        //e ao saldoAtual
        Calendar c = Calendar.getInstance();
        lucroDia[c.get(Calendar.DAY_OF_MONTH)-1] += valor;
        saldoAtual += valor;
    }
    public void caixaOut(double valor, int tipo)
    {
        //Desconta gastos com contas
        saldoAtual -= valor;
        if(tipo == 1)
            gastosEstoque += valor;
    }
    public void resetaDias()
    {
        for(int i=0; i < 31; i++)
            lucroDia[i] = 0;
    }
    public void resetaMeses()
    {
        for(int i=0; i < 12; i++)
            lucroMes[i] = 0;
    }
    public void resetaGastosEstoque()
    {
        gastosEstoque = 0;
    }
    public void setLucroMes(int m, double valor)
    {
        lucroMes[m] = valor;
    }
    public double getLucroDia(int d) { return lucroDia[d]; }
    public double getLucroMes(int m) { return lucroMes[m]; }
    public double getGastosEstoque() { return gastosEstoque; }
    public double getValorContas() { return valorContas; }
    public double calculaSalarios(ArrayList<Employee> employees)
    {
        double valor=0;
        for(Employee e : employees)
            valor += e.getPayment();
        return valor;
    }
    public double getSaldoAtual() { return saldoAtual; }
}
