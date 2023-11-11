import java.util.HashMap;
import java.util.Scanner;

public class Mapa {
    public static final int SIZE = 10;
    private final String[][] KINGDOMS = {
            { "Inglaterra", "bosque" },
            { "Francia", "campo abierto" },
            { "Castilla-Aragon", "montaña" },
            { "Moros", "desierto" },
            { "Sacro Imperio Romano-Germánico", "bosque", "playa", "campo abierto" }
    };
    private final String[] MAP_TYPES = { "bosque", "campo abierto", "montaña", "desierto", "playa" };
    private String type;
    private HashMap<String, Ejercito> kingdom1 = new HashMap<>();
    private HashMap<String, Ejercito> kingdom2 = new HashMap<>();
    private int kingdomName1Id;
    private int kingdomName2Id;

    public Mapa() {
        type = MAP_TYPES[(int) (Math.random() * MAP_TYPES.length)];
        kingdomName1Id = (int) (Math.random() * KINGDOMS.length);
        kingdomName2Id = (int) (Math.random() * KINGDOMS.length);
        int amount1 = (int) (Math.random() * 10 + 1);
        int amount2 = (int) (Math.random() * 10 + 1);
        for (int i = 0; i < 3; i++)
            generateArmy(kingdom1, kingdomName1Id);
        for (int i = 0; i < 2; i++)
            generateArmy(kingdom2, kingdomName2Id);
    }

    public String getType() {
        return type;
    }

    public HashMap<String, Ejercito> getKingdom1() {
        return kingdom1;
    }

    public HashMap<String, Ejercito> getKingdom2() {
        return kingdom2;
    }

    public String getKingdomName1() {
        return KINGDOMS[(kingdomName1Id)][0];
    }

    public String getKingdomName2() {
        return KINGDOMS[(kingdomName2Id)][0];
    }
    public int getTotalArmies(HashMap<String, Ejercito> kingdom) {
        return kingdom.size();
    }
    private boolean isBuffed(int kingdomNameId) {
        for (int i = 0; i < KINGDOMS[kingdomNameId].length; i++) {
            if (type.equals(KINGDOMS[kingdomNameId][i]))
                return true;
        }
        return false;
    }
    private void generateArmy(HashMap<String, Ejercito> kingdom, int kingdomNameId) {
        int row, col;
        do {
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        } while (kingdom.containsKey(Ejercito.generateKey(row, col)));
        int amount = (int) (Math.random() * 10 + 1);
        boolean buffed = isBuffed(kingdomNameId);
        Ejercito army = new Ejercito(amount, KINGDOMS[kingdomNameId][0], buffed);
        kingdom.put(Ejercito.generateKey(row, col), army);
    }
    public void printStatus() {
        System.out.println(getKingdomName1() + " vs " + getKingdomName2());
        System.out.println("El terreno es: " + type);
        if (isBuffed(kingdomName1Id))
            System.out.println(getKingdomName1() + " es beneficiado");
        if (isBuffed(kingdomName2Id))
            System.out.println(getKingdomName2() + " es beneficiado");
    }
    public void printMap() {
        System.out.print("      " + 0);
        for (int i = 1; i < SIZE; i++) {
            System.out.print("         " + i);
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                String key = Ejercito.generateKey(i, j);
                if (kingdom1.containsKey(key) || kingdom2.containsKey(key)) {
                    Ejercito army = null;
                    if (kingdom1.containsKey(key))
                        army = kingdom1.get(key);
                    if (kingdom2.containsKey(key))
                        army = kingdom2.get(key);
                    System.out.printf("|%c:%02d-%03d|", army.getNameArmy().charAt(0),
                            army.getTotalSoldiers(), army.getTotalHealthArmy());
                } else {
                    System.out.print("|________|");
                }
            }
            System.out.println();
        }
    }
    private String moveArmy(int row, int col) {
        Scanner sc = new Scanner(System.in);
        System.out.println("EJERCITO: A que lado desea mover? (der/izq/arr/aba)");
        String dir = sc.next();
        System.out.println("Ingrese la cantidad de movimientos:");
        int n = sc.nextInt();
        String w = null;
        switch (dir) {
            case "der":
                int der = col - n;
                if (der < 0) System.out.println("No hay espacio");
                else w = Ejercito.generateKey(row, der);
                break;
            case "izq":
                int izq = col + n;
                if (izq > SIZE) System.out.println("No hay espacio");
                else w = Ejercito.generateKey(row, izq);
                break;
            case "arr":
                int arr = row - n;
                if (arr < 0)System.out.println("No hay espacio");
                else w = Ejercito.generateKey(arr, col);
                break;
            case "aba":
                int aba = row + n;
                if (aba > SIZE) System.out.println("No hay espacio");
                else w = Ejercito.generateKey(aba, col);
                break;
        }
        return w;
    }
    private double winnerArmy(Ejercito army1, Ejercito army2) {
        return (double) (army1.getTotalHealthArmy() * 100) / (army1.getTotalHealthArmy() + army2.getTotalHealthArmy());
    }
    private void handleBattle(HashMap<String, Ejercito> k1, HashMap<String, Ejercito> k2, int t, int f, int c) {
        String key = Ejercito.generateKey(f, c);
        String newKey = moveArmy(f, c);
        if (k1.containsKey(key) || k2.containsKey(key)) {
            if (k1.containsKey(newKey)) {
                System.out.println("Hay otro ejército del mismo reino");
            } else if (k2.containsKey(newKey)) {
                System.out.println("HAY UNA BATALLA");
                Ejercito army1 = k1.get(key);
                Ejercito army2 = k2.get(newKey);
                double w1 = winnerArmy(army1, army2);
                double w2 = winnerArmy(army2, army1);
                System.out.print("EJÉRCITO " + getKingdomName1() + " " + w1 + "%");
                System.out.println("<==>EJÉRCITO " + getKingdomName2() + " " + w2 + "%");
                String winner = (t == 1) ? getKingdomName1() : getKingdomName2();
                if(w1 == w2){
                    System.out.println("Hay un empate");
                }else if(w1>w2){
                    printMap();
                    k1.put(newKey, army1);
                    k1.get(newKey).increaseHealth();
                    k1.remove(key);
                    k2.remove(newKey);
                    System.out.println("Gana el Ejercito: "+winner);
                }else{
                    printMap();
                    k2.get(newKey).increaseHealth();
                    k1.remove(key);
                }
            } else {
                k1.put(newKey, k1.get(key));
                k1.remove(key);
            }
        }
    }
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        int turno = 1;
        while (getTotalArmies(kingdom1) > 0 && getTotalArmies(kingdom2) > 0) {
            String w = (turno == 1) ? getKingdomName1() : getKingdomName2();
            System.out.println("Reino " + w + ": Ingrese el ejercito a mover(f/c)");
            int f = sc.nextInt();
            int c = sc.nextInt();
            handleBattle((turno == 1) ? kingdom1 : kingdom2, (turno != 1) ? kingdom1 : kingdom2, turno, f, c);
            printMap();
            turno = (turno == 1) ? 2 : 1;
        }
        System.out.println("La batalla ha terminado");
    }  
}
