package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author rodrigo
 */
public class Vehicles implements Serializable {
    public String board, 
                  brand,
                  color,
                  model,
                  dateOfInsert,
                  description;
    public int  size;
    
    SimpleDateFormat dateToday;
    
    public Vehicles(){
        Scanner input = new Scanner (System.in);

        System.out.println("\tInformacoes do veiculo");
        System.out.print("Placa .: ");
        board = input.nextLine();

        System.out.print("Marca .: ");
        brand = input.nextLine();

        System.out.print("Cor .: ");
        color = input.nextLine();

        System.out.print("Modelo .: ");
        model = input.nextLine();
        
        dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateOfInsert = dateToday.format(new Date()); 
        
        System.out.print("Decrição .: ");
        description = input.nextLine();
        do{
            System.out.print("Tamanho (1 - Pequeno, 2 - Médio, 3 - Grande) .: ");
            size = input.nextInt();
        }while(size <= 0 || size >= 4)
    }
}

