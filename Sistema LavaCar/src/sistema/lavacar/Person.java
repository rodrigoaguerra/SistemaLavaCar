package sistema.lavacar;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio
 */
public class Person implements Serializable {
    public String name, rg,
                  address,
                  phone1,
                  phone2,
                  dateOfBorn;
          /* Eu acho que não precisa do DateOfInsert na classe pessoa, seja ele a primeira vez que a pessoa veio
          ao lavacar ou o dia em que ela lavou o carro. Faz mais sentido colocar isso nos funcionários
          (quando foi contratado) e nos veículos (quando o carro foi lavado) */

    public Person(){
        Scanner input = new Scanner (System.in);

        System.out.print("Nome .: ");
        name = input.nextLine();

        System.out.print("RG .: ");
        rg = input.nextLine();

        System.out.print("Endereço .: ");
        anddress = input.nextLine();

        System.out.print("Telefone1 .: ");
        phone1 = input.nextLine();

        System.out.print("Telefone2 .: ");
        phone2 = input.nextLine();

        System.out.print("Data de nascimento (DD MM AA) .: ");
        int dia = input.nextInt();
        int mes = input.nextInt();
        int ano = input.nextInt();
        dateOfBorn = dia + "/" + mes + "/" + ano;
    }
}
