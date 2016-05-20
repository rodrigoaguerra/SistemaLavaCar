package sistema.lavacar;

import java.io.FileNotFoundException;

public class ProjetoLavaCar {
    public static void main(String[] args) throws FileNotFoundException{

        Company c = new Company("Lava Car T", "Rua Marechal Floriano, 57",
                            "3089-1452", "www.lavacart.com", "71.546.945/0001-53");
        
        c.executar();
    }
}
