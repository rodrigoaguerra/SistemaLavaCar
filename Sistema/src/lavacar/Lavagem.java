
package lavacar;

import java.io.Serializable;
import java.util.Calendar;

public class Lavagem extends Service implements Serializable{
    private int tempoSmall;
    private int tempoMedium;
    private int tempoBig;
    
    public Lavagem(double vs, double vm, double vb)
    {
        super("Lavagem", vs, vm, vb);
        tempoSmall = 30;
        tempoMedium = 40;
        tempoBig = 50;
    }
    public double executar()
    {
        int sabao, esponjas, panos;
        double valor;
        Vehicle car = fila.get(0);
        if(car.getSize() == 1)
        {
            valor = priceSmall;
            sabao = 1;
            esponjas = 2;
            panos = 2;
        }
        else if(car.getSize() == 2)
        {
            valor = priceMedium;
            sabao = 2;
            esponjas = 4;
            panos = 4;
        }
        else //if(car.getSize() == 3)
        {
            valor = priceBig;
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
    public String estimarTempoDeEspera(Vehicle v)
    {
        int min=0,
            size;
        for(int i=0; i < fila.indexOf(v) && i < fila.size(); i++)
        {
            size = fila.get(i).getSize();
            if(size==1)
                min += tempoSmall;
            else if(size==2)
                min += tempoMedium;
            else if(size == 3)
                min += tempoBig;
        }
        String tempo = min/60 + "h" + min%60 + "min";
        return tempo;
    }
}

