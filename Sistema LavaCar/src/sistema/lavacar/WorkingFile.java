/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author rodrigo
 */
public class WorkingFile {
    public static void saveData(ArrayList obj, String nameFile) throws FileNotFoundException
    {
        try {
            FileOutputStream saveFile = new FileOutputStream("data/" + nameFile +".txt");
            ObjectOutputStream stream = new ObjectOutputStream(saveFile);
            stream.writeObject(obj);
            stream.close();
        } catch (Exception exc) {
            System.out.println("Não foi possivel gravar o arquivo " + nameFile);
        }      
    }
    
    public static ArrayList recoverData(ArrayList obj, String nameFile) throws FileNotFoundException
    {     
        try {
            FileInputStream readFile = new FileInputStream("data/" + nameFile +".txt");       
            ObjectInputStream stream = new ObjectInputStream(readFile); 
            // rescue the objects
            obj = (ArrayList) stream.readObject();
            stream.close();
        } catch (Exception e) {
            System.out.println("Não foi possivel carregar o arquivo " + nameFile);
        }
        return obj;
    }
    
    //public static void saveData()
    //Salva os relatórios e insumos em .txt
    
    //public static void recoverData()
    //Lê os relatórios e insumos do .txt
    
    //Podem ter o mesmo nome das que salvam ArrayList,
    //se o cabeçalho for diferente o Java entende qual
    //nós queremos chamar.
}
