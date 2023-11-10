import java.util.HashMap;
public class Ejercito {
    private String nameArmy;
    private HashMap<String, Soldado> army  = new HashMap<>();
    public Ejercito(int soldierAmount, String name, boolean buffed) {
        nameArmy = name;
        for (int i = 0; i < soldierAmount; i++)
            autogenerateSoldier(buffed);      
    };
    public HashMap<String, Soldado> getArmy() {
        return army;
    }
    public int getTotalSoldiers(){
        return army.size();
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
    public void autogenerateSoldier(boolean buffed) {
        int life = (int) (Math.random() * 5 + 1);
        if(buffed) life++;
        int row, col;
        do{
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }while(army.containsKey(Ejercito.generateKey(row, col)));
        int attack = (int) (Math.random() * 5 + 1);
        int defense = (int) (Math.random() * 5 + 1);
        String name = "Soldado" + row + "X" + col;
        Soldado s = new Soldado(name, life, defense, attack, row, col);
        army.put(generateKey(row,col), s);
    }
    public static String generateKey(int row, int col){
        return row+","+col;
    }
    //buffed: es un control de los casos especiales
}