
package sistema.lavacar;

import java.io.Serializable;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Vehicle implements Serializable {
    private final String brand;
    private final String model;
    private final String board;
    private final String year;
    private String color;
    private final String dateOfInsert;
    private String description;
    private final int size;
    
    public Vehicle(){
        Scanner input = new Scanner (System.in);  
        
        System.out.println("\tInformaçoes do veiculo");  
        System.out.print("Marca .: "); 
        brand = input.nextLine(); 
          
        System.out.print("Modelo .: "); 
        model = input.nextLine(); 
        
        System.out.print("Placa .: "); 
        board = input.nextLine(); 
        
        System.out.print("Ano .: "); 
        year = input.nextLine(); 
         
        System.out.print("Cor .: "); 
        color = input.nextLine(); 
  
        System.out.print("Decrição .: ");   
        description = input.nextLine();   
         
        System.out.print("Tamanho (1 - Pequeno, 2 - Médio, 3 - Grande) .: ");   
        size = input.nextInt();
        input.nextLine(); //Tira o \n

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        dateOfInsert = sdf.format(c.getTime());
    }
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Marca: " + brand + "\tModelo: " + model + "\n";
        rel = rel + "Placa: " + board + "\tAno: " + year + "\tCor: " + color + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert + "\n";
        rel = rel + "Descrição: " + description + "\n\n";
        return rel;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getBoard() {  return board;   }
    public int getSize() {   return size;   }
}

