package sistema.lavacar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

/**
 *
 * @author rodrigo
 */
public class Arquivos {
    public static void saveCustomers(ArrayList<Customer> cust, String nameFile)
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
    public static void saveEmployees(ArrayList<Employee> emp, String nameFile)
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
    public static void saveVehicles(ArrayList<Customer> cust, String nameFile)
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
    public static void saveInsumos(Insumos i, String nameFile)
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
    public static void saveRelatorio(String rel, String nameFile)
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
    
    /* Salva os ArrayLists (customers e employees)
    em arquivo (que o compilador vai ler) */
    public static void saveData(ArrayList obj, String nameFile) throws FileNotFoundException
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
    /* Recupera os ArrayLists (customers e employees)
    do arquivo */
    public static ArrayList recoverData(ArrayList obj, String nameFile) throws FileNotFoundException
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
    
    /* Salva os insumos em arquivo (que o
    compilador vai ler) */
    public static void saveData(Insumos obj, String nameFile) throws FileNotFoundException
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
    /* Recupera os insumos do arquivo */
    public static Insumos recoverData(Insumos obj, String nameFile) throws FileNotFoundException
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (Insumos) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile + ".txt");
            obj = new Insumos(100, 100, 100);
        }
        return obj;
    }    
    
    /* Salva as finanças em um arquivo
    (que o compilador vai ler) */
    public static void saveData(Finanças obj, String nameFile) throws FileNotFoundException
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
    /* Recupera as finanças do arquivo */
    public static Finanças recoverData(Finanças obj, String nameFile) throws FileNotFoundException
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
    
    /* Salva as lavagems em um arquivo
    (que o compilador vai ler) */
    public static void saveData(Lavagem obj, String nameFile) throws FileNotFoundException
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
    /* Recupera o serviço Lavagem do arquivo */
    public static Lavagem recoverData(Lavagem obj, String nameFile) throws FileNotFoundException
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            obj = (Lavagem) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile + ".txt");
            obj = new Lavagem(40.00, 50.00, 60.00);
        }
        return obj;
    }
}
