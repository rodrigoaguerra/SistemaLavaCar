/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio
 */
public class Person implements Serializable {
    protected final String name;
    protected final String rg;
    protected final String cpf;
    protected String address;
    protected String phone1;
    protected String phone2;
    protected final String dateOfBirth;
    /* Se os atributos forem private, não dá para acessar nos objetos
    customer e employee. O Protected deixa visível para as sub-classes.
    É um public mais restrito pelo que eu entendi. */
      
    public Person(){
        Scanner input = new Scanner (System.in);
        
        System.out.print("Nome .: ");
        name = input.nextLine();

        System.out.print("RG .: ");
        rg = input.nextLine();
        
        System.out.print("CPF .: ");
        cpf = input.nextLine();
        
        System.out.print("Data de nascimento (DD MM AA).: ");   
        int dia = input.nextInt();
        int mes = input.nextInt();
        int ano = input.nextInt();
        input.nextLine(); //Tira o \n
        /* É melhor como int para poder verificar se é válida */
        dateOfBirth = dia + "/" + mes + "/" + ano;

        System.out.print("Endereço .: ");
        address = input.nextLine();

        System.out.print("Telefone1 .: ");
        phone1 = input.nextLine();

        System.out.print("Telefone2 .: ");
        phone2 = input.nextLine();
    }
    public String getName()
    {
        return name;
    }
}
