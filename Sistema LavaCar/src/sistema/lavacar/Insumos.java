/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

/**
 *
 * @author Eragon Geb
 */
public class Insumos {
    private int sabao;
    private int esponjas;
    private int panos;
    
    public Insumos(int s, int e, int p)
    {
        sabao = s;
        esponjas = e;
        panos = p;
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
            sabao-=s;
            esponjas-=e;
            panos-=p;
            return true;
        }
        return false;
    }
    public int getSabao()
    {
        return sabao;
    }
    public int getEsponjas()
    {
        return esponjas;
    }
    public int getPanos()
    {
        return panos;
    }
}
