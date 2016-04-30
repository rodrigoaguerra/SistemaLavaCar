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
      
    public Person(String n, String rg, String cpf, String d,
                    String a, String p1, String p2){
        name = n;
        this.rg = rg;
        this.cpf = cpf;
        dateOfBirth = d;
        address = a;
        phone1 = p1;
        phone2 = p2;
    }
    public String getName()
    {
        return name;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setPhone1(String phone1)
    {
        this.phone1 = phone1;
    }
    public void setPhone2(String phone2)
    {
        this.phone2 = phone2;
    }
}
