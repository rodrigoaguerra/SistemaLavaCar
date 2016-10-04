
package sistema.lavacar;

import java.io.Serializable;

public class Estoque implements Serializable{
    private int sabao;
    private int esponjas;
    private int panos;
    private double precoSabao;
    private double precoEsponja;
    private double precoPano;
    
    public Estoque(int s, int e, int p)
    {
        sabao = s;
        esponjas = e;
        panos = p;
        precoSabao = 5.00;
        precoEsponja = 7.00;
        precoPano = 3.00;
    }
    
    public void adicionar(int s, int e, int p)
    {
        sabao += s;
        esponjas += e;
        panos += p;
    }
    public boolean descontar(int s, int e, int p)
    {
        /* Retorna true se tem insumos suficientes e
        false se não tem (e, portanto, não conseguiu lavar */
        if(sabao-s >= 0 && esponjas-e >= 0 && panos-p >= 0)
        {
            sabao -= s;
            esponjas -= e;
            panos -= p;
            return true;
        }
        return false;
    }
    public String gerarRelatorio()
    {
        String rel = "INSUMOS" + "\n";
        rel = rel + "Sabão: " + sabao + "\n";
        rel = rel + "Esponjas: " + esponjas + "\n";
        rel = rel + "Panos: " + panos + "\n";
        return rel;
    }    
    public int getSabao() { return sabao; }
    public int getEsponjas() { return esponjas; }
    public int getPanos() { return panos; }
    public double getPrecoSabao() { return precoSabao; }
    public double getPrecoEsponja() { return precoEsponja; }
    public double getPrecoPano() { return precoPano; }
}
