import java.util.*;
public class Ejercito {
    private String nameArmy;
    private ArrayList<Soldado> army  = new ArrayList<>();
    public Ejercito(String name, Soldado[][] t, boolean buffed){
        nameArmy = name;
        for(int i= 0; i<generateSoldier(); i++)
            autogenerateSoldier(t,buffed);  
    };
    public ArrayList<Soldado> getArmy() {
        return army;
    }
    public String getNameArmy() {
        return nameArmy;
    }
    public String toString() {
        return "EJERCITO "+nameArmy.toUpperCase();
    }
    public int generateSoldier(){
        return (int)(Math.random()*(Soldado.MAX_COUNT_SOLDIER)+1);
    }
    public void autogenerateSoldier(Soldado[][] table, boolean buffed) {
        int row = 0, col = 0; String name;
        int life = (buffed) ? 1:0;
        do{
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }while(table[row][col] != null);
        int option = (int)(Math.random()*4+1);
        Soldado s = null;
        switch(option){
            case 1: name = "Espadachin"+row+"X"+col;
                    s = new Espadachin(name, row, col);
                    s.setVidaActual(s.getVidaActual()+life);
                    break;
            case 2: name = "Arquero"+row+"X"+col;
                    s = new Arquero(name, row, col);
                    break;
            case 3: name = "Caballero"+row+"X"+col;
                    s = new Caballero(name, row, col);
                    break;
            case 4: name = "Lancero"+row+"X"+col;
                    s = new Lancero(name, row, col); 
                    break;
        }
        s.setVidaActual(s.getVidaActual()+life);
        army.add(s);
        table[row][col] = s;
    }
}