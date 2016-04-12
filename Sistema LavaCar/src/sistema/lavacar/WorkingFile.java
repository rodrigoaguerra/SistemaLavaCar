/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class WorkingFile {
    
    public static ArrayList read(ArrayList obj, String nameFile) throws FileNotFoundException
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".bin");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            // rescue the objects
            obj = (ArrayList) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile);
        }
        return obj;
    }
        
    public static void write(ArrayList obj, String nameFile) throws FileNotFoundException
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".bin");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception exc) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile);
        }      
    }
}
