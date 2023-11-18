import java.util.ArrayList;
import java.util.HashMap;
public class Ejercito {
    private String nameArmy;
    private HashMap<String, Soldado> army  = new HashMap<>();
    public Ejercito(String name){
        nameArmy = name;
        for (int i = 0; i < Ejercito.generateSoldier(); i++)
            autogenerateSoldier();     
    };
    public HashMap<String, Soldado> getArmy() {
        return army;
    }
    public String getNameArmy() {
        return nameArmy;
    }
    public int getTotalHealthArmy(){
        int total = 0;
        for ( Soldado s : army.values())
            total += s.getVidaActual();
        return total;
    }
    public String toString() {
        return "EJERCITO "+nameArmy.toUpperCase();
    }
    public static int generateSoldier() {
        return (int) (Math.random() * Soldado.MAX_COUNT_SOLDIER + 1);
    }
    public int genlife(int a, int b){
        return (int)(Math.random()*(b-a)+(a));
    }
    public void autogenerateSoldier() {
        int life; String name;
        int row, col;
        do{
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }while(army.containsKey(Ejercito.generateKey(row, col)));
        int attack = (int) (Math.random() * 5 + 1);
        int defense = (int) (Math.random() * 5 + 1);
        int option = (int)(Math.random()*3+1);
        switch(option){
            case 1: life = genlife(3,4);
                    name = "Espadachin"+row+"X"+col;
                    int lesp = genlife(6,10);
                    army.put(generateKey(row, col), new Espadachin(name, life, defense, attack, row, col, lesp));
                    break;
            case 2: life = genlife(1, 3);
                    name = "Arquero"+row+"X"+col;
                    int nroFle = genlife(6, 10);
                    army.put(generateKey(row, col), new Arquero(name, life, defense, attack, row, col, nroFle));
                    break;
            case 3: life = genlife(3, 5);
                    name = "Caballero"+row+"X"+col;
                    army.put(generateKey(row, col), new Caballero(name, life, defense, attack, row, col));
                    break;
        }
    }
    public static String generateKey(int row, int col){
        return row+","+col;
    }
    public Soldado mostLife(){
        Soldado tempo = new Soldado();
        for(Soldado s: army.values()){
            if(s.getVidaActual()>tempo.getVidaActual())tempo = s;       
        }
        return tempo;
    }
    public double averageLife(){
        double suma = 0;
        for(Soldado s: army.values())
            suma +=s.getVidaActual();
        return suma / army.size();
    }
    public void displaySoldier() {
        ArrayList<Soldado> sold = new ArrayList<>();
        for (Soldado s : army.values()) {
            sold.add(s);
        }
        printSort(sold);
    }
    public void rankSoldierBubbleSort() {
        ArrayList<Soldado> sold = new ArrayList<>();
        for (Soldado s : army.values()) {
            sold.add(s);
        }
        for (int i = 0; i < sold.size(); i++) {
            for (int j = 0; j < sold.size() - 1 - i; j++) {
                if (sold.get(j).getVidaActual() < sold.get(j + 1).getVidaActual()) {
                    Soldado tempo = sold.get(j);
                    sold.set(j, sold.get(j + 1));
                    sold.set(j + 1, tempo);
                }
            }
        }
        printSort(sold);
    }
    public static void printSort(ArrayList<Soldado> sold) {
        for (int i = 0; i < sold.size(); i++)
            System.out.println(sold.get(i));
    }
}