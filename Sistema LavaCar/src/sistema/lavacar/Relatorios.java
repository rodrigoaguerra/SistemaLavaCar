/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;

/**
 *
 * @author rodrigo
 */
public class Relatorios {
    
    static void gerarRelatorioClientes(ArrayList<Customer> cs, String nameFile) throws FileNotFoundException
    {
        Formatter arquivo;
        
        try{
            arquivo = new Formatter("relatorios/" + nameFile +".txt");
            for(Customer c : cs){
                arquivo.format(c.gerarRelatorio());
                arquivo.format("___________________________________________________________________\n");
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
    
    static void gerarRelatorioVehicles(ArrayList<Customer> cs, String nameFile) throws FileNotFoundException
        {
        Formatter arquivo;
        
        try{
            arquivo = new Formatter("relatorios/" + nameFile +".txt");
            for(Customer c : cs){
                for(Vehicle v : c.vehiclesOfCustomer){
                    arquivo.format(v.gerarRelatorio());
                    arquivo.format("___________________________________________________________________\n");
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
    
    public static void gerarRelatorioInsumos(Insumos i, String nameFile) throws FileNotFoundException
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

    
}
