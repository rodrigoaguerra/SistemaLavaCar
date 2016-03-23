/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio
 */
public class SistemaLavaCar {
    public int cnpj; 
    String name_company;
    String anddress_company;
    String phone_company;
    String logo_company;
    boolean execute = true;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Clientes person = null;
        int answer;
        answer = 1;
        while(answer >= 1){
            Scanner input = new Scanner (System.in);
            System.out.print("Digite 1 para cadastar um cliente,\n"+
                             "Digite 2 para cadastar listar o cliente \n" + 
                             "ou  0  para sair\n .:");
            answer = input.nextInt();
            switch(answer){
                case 1 : // insert cliente
                    person = new Clientes();
                    break;
                case 2 : // list cliente     1
                    WorkingFile readFile = new WorkingFile();
                    readFile.open();
                    readFile.read();
                    readFile.close();
                    break;
                case 3 : 
                    WorkingFile teste = new WorkingFile();
                    teste.open();
                    teste.writer(person);
                    teste.close();
                    break;
            }
      
        }
        
    }
    
}

