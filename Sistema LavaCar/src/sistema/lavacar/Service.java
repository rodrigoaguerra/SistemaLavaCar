package sistema.lavacar;

import java.io.Serializable;
import java.util.ArrayList;

public class Service implements Serializable{
    protected double priceSmall; //Preço do serviço
    protected double priceMedium;
    protected double priceBig;
    protected int[] servDia;
    protected int[] servMes;
    protected ArrayList<Vehicle> fila;

    public Service(double vs, double vm, double vb)
    {
        priceSmall = vs;
        priceMedium = vm;
        priceBig = vb;
        servDia = new int[31];
        servMes = new int[12];
        fila = new ArrayList<>();
    }
    public double executar()
    {
        //Abstract
        return 0;
    }
    public String estimarTempoDeEspera() {
        //Abstract
        return null;
    }
    
    public static String fazDiagnostico (Vehicle v)
    {
        String rel = "DIAGNOSTICO do veículo " + v.getBoard() + "\n";
        if(v.getMotor() == true)
            rel = rel + "Motor: OK \n";
        else
            rel = rel + "Falha no motor \n";
        if(v.getPneus() == true)
            rel = rel + "Pneus: OK \n";
        else
            rel = rel + "Pneus gastos \n";
        if(v.getOleo() == false)
            rel = rel + "Vazando óleo \n";
        if(v.getCombustivel() == false)
            rel = rel + "Vazando combustível \n";
        if(v.getTanque() == true)
            rel = rel + "Tanque: cheio \n";
        else
            rel = rel + "Tanque: gasolina quase acabando \n";
        if(v.getBateria() == true)
            rel = rel + "Bateria: OK \n";
        else
            rel = rel + "Problema na bateria \n";
        
        rel = rel + "\nSUGESTOES DE SERVIÇOS FUTUROS \n";
        if(v.getMotor()==false)
            rel = rel + "Arrumar o motor \n";
        if(v.getPneus()==false)
            rel = rel + "Trocar pneus \n";
        if(v.getOleo()==false)
            rel = rel + "Fixar o vasamento de óleo \n";
        if(v.getCombustivel()==false)
            rel = rel + "Fixar o vasamento de combustível \n";
        if(v.getBateria()==false)
            rel = rel + "Arrumar a bateria \n";
        if(v.getPolimento() == false)
            rel = rel + "Polimento \n";
        if(v.getPintura() == false)
            rel = rel + "Pintura \n";
        v.randomizeState(); //Muda os atributos para não ficar sempre igual
        return rel;
    }
    public ArrayList<Vehicle> getFila()
    {
        return fila;
    }
    public void mostrarFila()
    {
        if(fila.isEmpty())
            return;
        System.out.println("\tVEÍCULOS NA FILA");
        for(int i=0; i < fila.size(); i++)
            System.out.println(fila.get(i).getBoard());
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
    public double getPriceSmall() { return priceSmall; }
    public double getPriceMedium(){ return priceMedium; }
    public double getPriceBig(){ return priceBig; }
    public int getServDia(int d) { return servDia[d]; }
    public int getServMes(int m) { return servMes[m]; }
}
