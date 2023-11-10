import java.util.HashMap;
public class Mapa {
    private final int SIZE = 10;
    private final String[][] KINGDOMS = {
        {"Inglaterra", "bosque"},
        {"Francia", "campo abierto"},
        {"Castilla-Aragon", "montaña"},
        {"Moros", "desierto"},
        {"Sacro Imperio Romano-Germánico", "bosque", "playa", "campo abierto"}
    };
    private final String[] MAP_TYPES = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
    private String type;
    private HashMap<String, Ejercito> kingdom1 = new HashMap<>();
    private HashMap<String, Ejercito> kingdom2 = new HashMap<>();
    private int kingdomName1Id;
    private int kingdomName2Id;
    public Mapa(){
        type = MAP_TYPES[(int)(Math.random()*MAP_TYPES.length)];
        kingdomName1Id = (int)(Math.random()*KINGDOMS.length);
        kingdomName2Id = (int)(Math.random()*KINGDOMS.length);
        int amount1 = (int)(Math.random()*10+1);
        int amount2 = (int)(Math.random()*10+1);
        for (int i = 0; i < amount1; i++)
            generateArmy(kingdom1, kingdomName1Id);
        for (int i = 0; i < amount2; i++)
            generateArmy(kingdom2, kingdomName2Id);
    }
    public String getType(){
        return type;
    }
    public HashMap<String, Ejercito> getKingdom1(){
        return kingdom1;
    }
    public HashMap<String, Ejercito> getKingdom2(){
        return kingdom2;
    }
    public String getKingdomName1(){
        return KINGDOMS[(kingdomName1Id)][0];
    }
    public String getKingdomName2(){
        return KINGDOMS[(kingdomName2Id)][0];
    }
    private boolean isBuffed(int kingdomNameId){
        for (int i = 0; i < KINGDOMS[kingdomNameId].length; i++) {
            if(type.equals(KINGDOMS[kingdomNameId][i])) return true;
        }
        return false;
    }
    private void generateArmy(HashMap<String, Ejercito> kingdom, int kingdomNameId){
       int row, col;
       do{
        row = (int) (Math.random() * 10);
        col = (int) (Math.random() * 10);
        }while(kingdom.containsKey(Ejercito.generateKey(row, col)));
        int amount = (int) (Math.random() * 10 + 1);
        boolean buffed = isBuffed(kingdomNameId);
        Ejercito army = new Ejercito(amount, KINGDOMS[kingdomNameId][0] , buffed);
        kingdom.put(Ejercito.generateKey(row, col), army);
    }
    public void printStatus(){
        System.out.println(getKingdomName1()+" vs "+getKingdomName2());
        System.out.println("El terreno es: "+type);
        if(isBuffed(kingdomName1Id))
            System.out.println(getKingdomName1()+" es beneficiado");
        if(isBuffed(kingdomName2Id))
            System.out.println(getKingdomName2()+" es beneficiado");   
    }
    public void printMap(){
        System.out.print("      "+0);
        for (int i = 1; i < SIZE; i++) {
            System.out.print("         "+i);
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i+" ");
            for (int j = 0; j < SIZE; j++) {
                String key = Ejercito.generateKey(i, j);
                if(kingdom1.containsKey(key) || kingdom2.containsKey(key)){
                    Ejercito army = null;
                    if(kingdom1.containsKey(key))
                        army = kingdom1.get(key);
                    if(kingdom2.containsKey(key))
                        army = kingdom2.get(key);
                    System.out.printf("|%c:%02d-%03d|", army.getNameArmy().charAt(0),
                    army.getTotalSoldiers(), army.getTotalHealthArmy() 
                    );                                                             
                }else{
                    System.out.print("|________|");
                }  
            }
            System.out.println();
        }
    }
    public int getTotalArmies(HashMap<String, Ejercito> kingdom){
        return kingdom.size();
    }
    public void printWinner1(){
        int total1 = getTotalArmies(kingdom1);
        int total2 = getTotalArmies(kingdom2);
        if(total1 == total2)System.out.println("Hay un empate");
        else if(total1>total2)System.out.println("Gana el ejercito "+getKingdomName1());
        else System.out.println("Gana el reino "+getKingdomName2());
    }
    public int getTotalHealthKingdom(HashMap<String, Ejercito> kingdom){
        int total = 0;
        for (Ejercito army : kingdom.values()) {
            total += army.getTotalHealthArmy();
        }
        return total;
    }
    public void printWinner2(){
        int health1 = getTotalHealthKingdom(kingdom1);
        int health2 = getTotalHealthKingdom(kingdom2);
        if(health1 == health2)System.out.println("Hay un empate");
        else if(health1>health2)System.out.println("Gana el ejercito "+getKingdomName1());
        else System.out.println("Gana el reino "+getKingdomName2());
    }
    public String getRandomArmy(HashMap<String, Ejercito> kingdom){
        int idx = (int)(Math.random()*kingdom.size());
        int count = 0;
        for (String key : kingdom.keySet()) {
            if(count == idx)return key;
            count++;
        }
        return null;
    }
    public void printWinner3(){
        while(kingdom1.size()>0 && kingdom2.size()>0){
            String key1 = getRandomArmy(kingdom1);
            String key2 = getRandomArmy(kingdom2);
            int health1 = kingdom1.get(key1).getTotalHealthArmy();
            int health2 = kingdom2.get(key2).getTotalHealthArmy();
            if(health1 == health2){
                kingdom1.remove(key1); kingdom2.remove(key2);
            }else if(health1>health2){
                kingdom2.remove(key2);
            }else{
                kingdom1.remove(key1);
            }
        }
        if(kingdom1.size() ==0 && kingdom2.size() == 0)System.out.println("Hay un empate");
        else if(kingdom2.size() == 0)System.out.println("Gana el ejercito "+getKingdomName1());
        else System.out.println("Gana el reino "+getKingdomName2());
    } 
}

