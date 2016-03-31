/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    SimpleDateFormat dateToday;
    public int  size;
    
    public Vehicles(){
        Scanner input = new Scanner (System.in);

        System.out.print("Placa Do Veículo .: ");
        board = input.nextLine();

        System.out.print("Marca Do Veículo .: ");
        brand = input.nextLine();

        System.out.print("Cor Do Veículo .: ");
        color = input.nextLine();

        System.out.print("Modelo Do Veículo .: ");
        model = input.nextLine();
        
        System.out.print("Decrição Do Veículo .: ");
        description = input.nextLine();
        
        System.out.print("Digete 1 Para Veículo Pequeno, 2 Para Veículo Médio e 3 Para Grande.");
        size = input.nextInt();
        
        dateToday = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateOfInsert = dateToday.format(new Date()); 
    }
}

