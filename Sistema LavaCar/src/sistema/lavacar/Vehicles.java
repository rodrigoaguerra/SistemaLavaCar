
package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Vehicles implements Serializable {
    public String board, 
                  brand,
                  model,
                  year,
                  color,
                  description,
                  dateOfInsert;
    public int  size;
    
    SimpleDateFormat dateToday;

    public Vehicles(){
        Scanner input = new Scanner (System.in);

        System.out.println("\tInformaçoes do veiculo");
        System.out.print("Placa .: ");
        board = input.nextLine();

        System.out.print("Marca .: ");
        brand = input.nextLine();
        
        System.out.print("Modelo .: ");
        model = input.nextLine();
        
        System.out.print("Ano .: ");
        year = input.nextLine();
        
        System.out.print("Cor .: ");
        color = input.nextLine();

        System.out.print("Decrição .: ");  
        description = input.nextLine();  
        
        System.out.print("Tamanho (1 - Pequeno, 2 - Médio, 3 - Grande) .: ");  
        size = input.nextInt();  
        
        dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateOfInsert = dateToday.format(new Date()); 
    }
    public String gerarRelatorio()
    {
        String rel = "";
        /*nome = dono.getNome()? Vai depender de como é a associação entre
        cliente e veículo.
        rel = rel + "Proprietario: " + nome + "\n";
        */
        rel = rel + "Marca: " + brand + "\tModelo: " + model + "\n";
        rel = rel + "Ano: " + year + "\tCor: " + color + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert + "\n";
        rel = rel + "Descrição: " + description + "\n";
        return rel;
    }
}
