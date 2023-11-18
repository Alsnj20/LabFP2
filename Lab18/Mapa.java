import java.util.*;
public class Mapa {
    public static final int SIZE = 10;
    private final String[] ARMIES = {"Inglaterra", "Francia", "Castilla-Aragon", "Moros", "Sacro Imperio Romano-Germanico"};
    private final String[] MAP_TYPES = { "bosque", "campo abierto", "montaña", "desierto", "playa" };
    private String type;
    private Ejercito army1;
    private Ejercito army2;
    public Mapa() {
        type = MAP_TYPES[(int) (Math.random() * MAP_TYPES.length)];
        int armyName1Id = (int)(Math.random()* ARMIES.length);
        int armyName2Id = (int)(Math.random()*ARMIES.length);
        army1 = new Ejercito(ARMIES[armyName1Id]);
        army2 = new Ejercito(ARMIES[armyName2Id]);
    }
    public String getType() {
        return type;
    }
    public void printStatus() {
        System.out.println(army1 + " vs " + army2);
        System.out.println("El terreno es: " + type);
    }
    public void printMap() {
        System.out.print("     0");
        for (int i = 1; i < SIZE; i++) {
            System.out.print("       "+i);
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 10; j++) {
                String key = Ejercito.generateKey(i, j);
                if (army1.getArmy().containsKey(key) || army2.getArmy().containsKey(key)) {
                    Soldado army = null;
                    String w = "";
                    if (army1.getArmy().containsKey(key)) {
                        army = army1.getArmy().get(key);
                        w = army1.getNameArmy().substring(0,1);
                    }if (army2.getArmy().containsKey(key)) {
                        army = army2.getArmy().get(key);
                        w = army2.getNameArmy().substring(0,1);
                    }
                    System.out.printf("|%s:%c-%02d|", w, army.getName().charAt(0),
                            army.getVidaActual());
                }else
                    System.out.print("|______|");
            }
            System.out.println();
        }
    }
    public void printData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n"+army1+":El soldado con mayor vida es: \n"+army1.mostLife());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(army2+":El soldado con mayor vida es: \n"+army2.mostLife());
        System.out.println("\n"+army1+": El promedio de nivel de vida de los soldados es: " + army1.averageLife());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(army2+": El promedio de nivel de vida de los soldados es: " +army2.averageLife());
        System.out.println("\n"+army1+": Datos de los soldados en orden de creación: ");
        army1.displaySoldier();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(army2+": Datos de los soldados en orden de creación: ");
        army2.displaySoldier();
        System.out.println("\n---------------------Bubble sort-------------------------");
        System.out.println(army1+":");
        army1.rankSoldierBubbleSort();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println(army2+":");
        army2.rankSoldierBubbleSort();
    }
    public void printWinner(){
        int health1 = army1.getTotalHealthArmy();
        int health2 = army2.getTotalHealthArmy();
        System.out.println(army1+":"+health1+" <=> "+army2+":"+health2);
        if(health1 == health2)System.out.println("Hay un empate");
        else if(health1>health2)System.out.println("Gana el "+army1);
        else System.out.println("Gana el "+army2);
    }
}
