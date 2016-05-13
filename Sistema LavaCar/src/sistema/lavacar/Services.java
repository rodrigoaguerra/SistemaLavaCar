/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.util.ArrayList;
import java.util.Calendar;

public class Services {
    private static double valueSmall; //Preço do serviço
    private static double valueMedium;
    private static double valueBig;
    private static Insumos insumos;
    private static int[] servDia;
    private static int[] servMes;
    private static ArrayList<Vehicle> fila;

    public static void create(double vs, double vm, double vb, Insumos i)
    {
        valueSmall = vs;
        valueMedium = vm;
        valueBig = vb;
        insumos = i;
        servDia = new int[31];
        servMes = new int[12];
        fila = new ArrayList<>();
    }
    public static double executar()
    {
        if(fila.isEmpty()) //if(fila.size()==0)
        {
            System.out.println("A fila está vazia.");
            return 0;
        }
        
        int sabao, esponjas, panos;
        double valor;
        Vehicle car = fila.get(0);
        if(car.getSize() == 1)
        {
            valor = valueSmall;
            sabao = 1;
            esponjas = 2;
            panos = 2;
        }
        else if(car.getSize() == 2)
        {
            valor = valueMedium;
            sabao = 2;
            esponjas = 4;
            panos = 4;
        }
        else //if(car.getSize() == 3)
        {
            valor = valueBig;
            sabao = 3;
            esponjas = 6;
            panos = 5;
        }

        //Vê se pode lavar com os insumos do estoque
        if(sabao > insumos.getSabao() || esponjas > insumos.getEsponjas() ||
           panos > insumos.getPanos())
        {
            System.out.println("Faltam insumos.");
            return 0;
        }
        
        Calendar c = Calendar.getInstance();
        servDia[c.get(Calendar.DAY_OF_MONTH)-1]++;
        servMes[c.get(Calendar.MONTH)]++;
        
        insumos.descontar(sabao, esponjas, panos);
        fila.remove(0);
        return valor;
    }
    public static Vehicle procurarVeiculoNoSistema(String board, ArrayList<Customer> customers)
    {
        for(Customer p : customers)
            for(Vehicle v : p.vehiclesOfCustomer)
                if(board.equals(v.getBoard()))
                    return v;
        return null;
    }
    public static ArrayList<Vehicle> getFila()
    {
        return fila;
    }
    public static void mostrarFila()
    {
        if(fila.isEmpty())
            return;
        System.out.println("\tVEÍCULOS NA FILA");
        for(int i=0; i < fila.size(); i++)
            System.out.println(fila.get(i).getBoard());
    }
    public static String estimarTempoDeEspera()
    {
        int min=0,
            size;
        for(int i=0; i < fila.size(); i++)
        {
            size = fila.get(i).getSize();
            min += 30;
            if(size==2)
                min += 10;
            else if(size == 3)
                min += 20;
        }
        String tempo = min/60 + "h" + min%60 + "min";
        return tempo;
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
    public static void setValues(double s, double m, double b)
    {
        valueSmall = s;
        valueMedium = m;
        valueBig = b;
    }
    public static int getServDia(int d) { return servDia[d]; }
    public static int getServMes(int m) { return servMes[m]; }
}
