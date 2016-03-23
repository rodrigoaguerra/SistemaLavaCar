/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.lavacar;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.FormatterClosedException;
import java.lang.SecurityException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *
 * @author rodrigo
 */
public class WorkingFile {
    private Formatter arquivo;

        public void open()
        {
            try
            {
                arquivo = new Formatter("texto.txt");
            }
            catch( SecurityException semPermissao)
            {
                System.err.println(" Sem permissao para escrever no arquivo ");
                System.exit(1); //exit(0) é sucesso, outro número significa que terminou com problemas
            }
            catch( FileNotFoundException arquivoInexistente )
            {
                System.err.println(" Arquivo inexistente ou arquivo não pode ser criado");
                System.exit(1);
            }
        }
        public void read(){     
            try {
                Clientes person = null;
                FileInputStream restFile = new FileInputStream("clientes.txt");
                ObjectInputStream stream = new ObjectInputStream(restFile);
                // recupera o objeto

                person = (Clientes) stream.readObject();
                stream.close();
                System.out.printf("nome .: \"%s\" \n", person.name); 
                System.out.printf("Phone .: \"%s\" \n", person.phone1);
                System.out.printf("Andress .: \"%s\" \n", person.anddress);
                System.out.println(person.dateOfInsert.format(new Date()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void writer(Clientes p) throws FileNotFoundException
        {
             try {

                 FileOutputStream saveFile = new FileOutputStream("clientes.txt");

                 // salva o objeto
                 try (ObjectOutputStream stream = new ObjectOutputStream(saveFile)) {
                     // salva o objet
                     stream.writeObject(p);
                 }

           } catch (Exception exc) {

             exc.printStackTrace();

           }      
        }
     
       public void close()
       {
           arquivo.close();
       }
}
