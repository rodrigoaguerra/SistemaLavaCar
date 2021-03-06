
package lavacar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

public class DAOArquivos extends DAO{
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Salva os arquivos para leitura 
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void saveCustomers(ArrayList<Customer> cust, String nameFile)
    {
        Formatter arquivo;
        try{
            arquivo = new Formatter("relatorios/" + nameFile + ".txt");
            for(Customer c : cust){
                arquivo.format(c.gerarRelatorio());
                arquivo.format("\n___________________________________________\n\n");
            }    
            arquivo.close();
        }catch( SecurityException semPermissao){
            System.err.println(" Sem permissao para escrever no arquivo"+ nameFile +".txt");
        }catch( FileNotFoundException arquivoInexistente ){
            System.err.println(" Arquivo "+ nameFile +" inexistente ou arquivo não pode ser criado");
        }catch(FormatterClosedException formatoDesconhecido){
            System.err.println("Erro ao escrever no arquivo " + nameFile );
        }catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        }
    }
    public void saveEmployees(ArrayList<Employee> emp, String nameFile)
    {
        Formatter arquivo;
        try{
            arquivo = new Formatter("relatorios/" + nameFile + ".txt");
            for(Employee e : emp){
                arquivo.format(e.gerarRelatorio());
                arquivo.format("\n___________________________________________\n\n");
            }    
            arquivo.close();
        }catch( SecurityException semPermissao){
            System.err.println(" Sem permissao para escrever no arquivo"+ nameFile +".txt");
        }catch( FileNotFoundException arquivoInexistente ){
            System.err.println(" Arquivo "+ nameFile +" inexistente ou arquivo não pode ser criado");
        }catch(FormatterClosedException formatoDesconhecido){
            System.err.println("Erro ao escrever no arquivo " + nameFile );
        }catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        }
    }
    public void saveVehicles(ArrayList<Customer> cust, String nameFile)
    {
        Formatter arquivo;
        try{
            arquivo = new Formatter("relatorios/" + nameFile +".txt");
            for(Customer c : cust){
                for(Vehicle v : c.vehiclesOfCustomer){
                    arquivo.format(v.gerarRelatorio());
                    arquivo.format("\n___________________________________________\n\n");
                }
            }    
            arquivo.close();
        }catch( SecurityException semPermissao){
            System.err.println(" Sem permissao para escrever no arquivo"+ nameFile +".txt");
        }catch( FileNotFoundException arquivoInexistente ){
            System.err.println(" Arquivo "+ nameFile +" inexistente ou arquivo não pode ser criado");
        }catch(FormatterClosedException formatoDesconhecido){
            System.err.println("Erro ao escrever no arquivo " + nameFile );
        }catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        } 
    }
    public void saveEstoque(Estoque i, String nameFile)
    {
        Formatter arquivo;
        try{
            arquivo = new Formatter("relatorios/" + nameFile +".txt");
            arquivo.format(i.gerarRelatorio());
            arquivo.close();
        }catch( SecurityException semPermissao){
            System.err.println(" Sem permissao para escrever no arquivo"+ nameFile +".txt");
        }catch( FileNotFoundException arquivoInexistente ){
            System.err.println(" Arquivo "+ nameFile +" inexistente ou arquivo não pode ser criado");
        }catch(FormatterClosedException formatoDesconhecido){
            System.err.println("Erro ao escrever no arquivo " + nameFile );
        }catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        } 
    }
    public void saveRelatorio(String rel, String nameFile)
    {
        Formatter arquivo;
        try{
            arquivo = new Formatter("relatorios/" + nameFile + ".txt");
            arquivo.format(rel);
            arquivo.close();
        }catch( SecurityException semPermissao){
            System.err.println(" Sem permissao para escrever no arquivo"+ nameFile +".txt");
        }catch( FileNotFoundException arquivoInexistente ){
            System.err.println(" Arquivo "+ nameFile +" inexistente ou arquivo não pode ser criado");
        }catch(FormatterClosedException formatoDesconhecido){
            System.err.println("Erro ao escrever no arquivo " + nameFile );
        }catch(NoSuchElementException excecaoElemento){
            System.err.println("Entrada invalida. Por exemplo, era pra ser uma string, mas foi um inteiro");
        } 
    }
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    As funções saveData e recoverData possuem
    várias formas (polimorfismo). O Java consegue
    decidir qual chamar baseado nos parâmetros
    passados.
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Salva os arquivos para o compilador 
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /* ArrayLists (customers e employees) */
    public void saveData(ArrayList obj, String nameFile)
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".txt");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile);
        }      
    }
    /* Estoque */
    public void saveData(Estoque obj, String nameFile)
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".txt");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile + ".txt");
        }
    }
    /* Finanças */
    public void saveData(Finanças obj, String nameFile)
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".txt");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile + ".txt");
        }
    }    
    /* Lavagem (ou outros serviços) */
    public void saveData(Service obj, String nameFile)
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".txt");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile + ".txt");
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Recupera os dados dos arquivos 
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /* ArrayLists (customers e employees)*/
    public ArrayList recoverData(ArrayList obj, String nameFile)
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (ArrayList) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile);
        }
        return obj;
    }
    /* Estoque */
    public Estoque recoverData(Estoque obj, String nameFile)
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (Estoque) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile + ".txt");
            obj = new Estoque(100, 100, 100);
        }
        return obj;
    }    
    /* Finanças */
    public Finanças recoverData(Finanças obj, String nameFile)
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (Finanças) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile + ".txt");
            obj = new Finanças();
        }
        return obj;
    }    
    /* Lavagem (ou outros serviços) */
    public Service recoverData(Service obj, String nameFile)
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (Service) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile + ".txt");
            obj = new Lavagem(40.00, 50.00, 60.00);
        }
        return obj;
    }
}
