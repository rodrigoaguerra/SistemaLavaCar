
package sistema.lavacar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Vehicle implements Serializable {
    private String brand;
    private String model;
    private String board;
    private String year;
    private String color;
    private String description;
    private int size;
    private String dateOfInsert;
    
    private boolean motor;
    private boolean pneus;
    private boolean oleo;
    private boolean combustivel;
    private boolean tanque;
    private boolean bateria;
    private boolean polimento;
    private boolean pintura;
    
    public Vehicle(String br, String m, String bo, String y,
                   String co, String d, int s)
    {
        brand = br; 
        model = m; 
        board = bo; 
        year = y; 
        color = co;
        description = d;   
        size = s;
        randomizeState();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar c = Calendar.getInstance();
        dateOfInsert = sdf.format(c.getTime());
    }
    public void randomizeState() {
        Random r = new Random();
        motor = r.nextBoolean();
        pneus = r.nextBoolean();
        oleo = r.nextBoolean();
        combustivel = r.nextBoolean();
        tanque = r.nextBoolean();
        bateria = r.nextBoolean();
        polimento = r.nextBoolean();
        pintura = r.nextBoolean();
    }
    public String gerarRelatorio()
    {
        String rel = "";
        rel = rel + "Marca: " + brand + "\tModelo: " + model + "\n";
        rel = rel + "Placa: " + board + "\tAno: " + year + "\tCor: " + color + "\n";
        rel = rel + "Data de cadastro: " + dateOfInsert + "\n";
        rel = rel + "Descrição: " + description;
        return rel;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getBoard() { return board; }
    public String getYear() { return year; }
    public String getColor() { return color; }
    public String getDescription() { return description; }
    public int getSize() { return size; }

    public boolean getMotor() { return motor; }
    public boolean getPneus() { return pneus; }
    public boolean getOleo() { return oleo; }
    public boolean getCombustivel() { return combustivel; }
    public boolean getTanque() { return tanque; }
    public boolean getBateria() { return bateria; }
    public boolean getPolimento() { return polimento; }
    public boolean getPintura() { return pintura; }
}

