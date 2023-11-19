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
    public String toString() {
        return "EJERCITO "+nameArmy.toUpperCase();
    }
    public static int generateSoldier() {
        return (int) (Math.random() * Soldado.MAX_COUNT_SOLDIER + 1);
    }
    public int generateLife(int a, int b){
        return (int)(Math.random()*(b-a)+(a));
    }
    public void autogenerateSoldier() {
        int row, col, life; String name;
        do{
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }while(army.containsKey(Ejercito.generateKey(row, col)));
        int attack = (int) (Math.random() * 5 + 1);
        int defense = (int) (Math.random() * 5 + 1);
        int option = (int)(Math.random()*3+1);
        switch(option){
            case 1: life = generateLife(3,4);
                    name = "Espadachin"+row+"X"+col;
                    int lesp = generateLife(6,10);
                    army.put(generateKey(row, col), new Espadachin(name, life, defense, attack, row, col, lesp));
                    break;
            case 2: life = generateLife(1, 3);
                    name = "Arquero"+row+"X"+col;
                    int nroFle = generateLife(6, 10);
                    army.put(generateKey(row, col), new Arquero(name, life, defense, attack, row, col, nroFle));
                    break;
            case 3: life = generateLife(3, 5);
                    name = "Caballero"+row+"X"+col;
                    army.put(generateKey(row, col), new Caballero(name, life, defense, attack, row, col));
                    break;
        }
    }
    public static String generateKey(int row, int col){
        return row+","+col;
    }
}