/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Services {
        private int id;
        private String name;
        private String description;
        private double valueSmall;
        private double valueMedium;
        private double valueBig;    
        private final String dateOfInsert;
        SimpleDateFormat dateToday;
        
        
        public Services(){
            Scanner input = new Scanner (System.in);  
        
            System.out.println("\tInformaçoes do Serviço");  
            
            System.out.print("Digite o codigo de Identificação do Serviço .: "); 
            id = input.nextInt(); 
           
            System.out.print("Nome .: "); 
            name = input.nextLine(); 

            System.out.print("Descrição .: "); 
            description = input.nextLine(); 

            System.out.print("Valor Para Veiculos Pequenos.: "); 
            valueSmall = input.nextDouble(); 

            System.out.print("Valor Para Veiculos Medios.: "); 
            valueMedium = input.nextDouble(); 

            System.out.print("Valor Para Veiculos Grandes"); 
            valueBig = input.nextDouble(); 

            dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
            dateOfInsert = dateToday.format(new Date());
        }     
        
        public String getName(){
           return  name;
        }
        
        public String getDescription(){
           return  description;
        }
        
        public double getValueSmall(){
           return  valueSmall;
        }
        
        public double getValueMedium(){
           return  valueMedium;
        }
        
        public double getValueBig(){
           return  valueBig;
        }
        
        public String getDateOfInsert(){
           return dateOfInsert;
        }
}
