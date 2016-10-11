
package lavacar;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Service implements Serializable{
    protected String name;
    protected double priceSmall; //Preço do serviço
    protected double priceMedium;
    protected double priceBig;
    protected int[] servDia;
    protected int[] servMes;
    protected ArrayList<Vehicle> fila;
    protected Estoque insumos;

    public Service(String n, double vs, double vm, double vb)
    {
        name = n;
        priceSmall = vs;
        priceMedium = vm;
        priceBig = vb;
        servDia = new int[31];
        servMes = new int[12];
        fila = new ArrayList<>();
    }
    
    abstract public double executar();
    abstract public String estimarTempoDeEspera(Vehicle v);

    public boolean estaNaFila(Vehicle v)
    {
        for(int i = 0; i < fila.size(); i++)
            if(v == fila.get(i))
                return true;
        return false;
    }
    public void setPrices(double s, double m, double b)
    {
        priceSmall = s;
        priceMedium = m;
        priceBig = b;
    }
    public void resetaDias()
    {
        for(int i=0; i < 31; i++)
            servDia[i]=0;
    }
    public void resetaMeses()
    {
        for(int i=0; i < 12; i++)
            servMes[i]=0;
    }
    public String getName() { return name; }
    public double getPriceSmall() { return priceSmall; }
    public double getPriceMedium(){ return priceMedium; }
    public double getPriceBig(){ return priceBig; }
    public int getServDia(int d) { return servDia[d]; }
    public int getServMes(int m) { return servMes[m]; }
    public ArrayList<Vehicle> getFila() { return fila; }    
    public void setEstoque(Estoque i)
    {
        insumos = i;
    }
    public Estoque getEstoque() { return insumos; }
}
