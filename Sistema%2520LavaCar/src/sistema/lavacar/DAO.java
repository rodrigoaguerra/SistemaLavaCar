
package sistema.lavacar;

import java.util.ArrayList;

abstract class DAO {
    abstract public void saveCustomers(ArrayList<Customer> cust, String name);
    abstract public void saveEmployees(ArrayList<Employee> emp, String name);
    abstract public void saveVehicles(ArrayList<Customer> cust, String name);
    abstract public void saveEstoque(Estoque i, String name);
    abstract public void saveRelatorio(String rel, String name);
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    As funções saveData e recoverData possuem
    várias formas (polimorfismo). O Java consegue
    decidir qual chamar baseado nos parâmetros
    passados.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    abstract public void saveData(ArrayList obj, String name);
    abstract public void saveData(Estoque obj, String name);
    abstract public void saveData(Finanças obj, String name);
    abstract public void saveData(Service obj, String name);

    abstract public ArrayList recoverData(ArrayList obj, String name);
    abstract public Estoque recoverData(Estoque obj, String name);
    abstract public Finanças recoverData(Finanças obj, String name);
    abstract public Service recoverData(Service obj, String name);
}
