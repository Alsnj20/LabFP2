import java.util.*;
public class Mapa {
    public static final int SIZE = 10;
    private final String[] ARMIES = {"Inglaterra", "Francia", "Castilla-Aragon", "Moros",
    "Sacro Imperio Romano-Germanico"};
    private final String[] MAP_TYPES = { "bosque", "campo abierto", "montaña", "desierto",
     "playa" };
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
    private int getTotalSoldier(Ejercito eje){
        return eje.getArmy().size();
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
    private String moveSoldier(int row, int col) {
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
    
    private void handleBattle(Ejercito eje1, Ejercito eje2, int t, int f, int c) {
        HashMap<String, Soldado> e1 = eje1.getArmy();
        HashMap<String, Soldado> e2 = eje2.getArmy();
        String key = Ejercito.generateKey(f, c);
        String newKey = moveSoldier(f, c);
        if (e1.containsKey(key) || e2.containsKey(key)) {
            Soldado s = e1.get(key);
            if (e1.containsKey(newKey)) {
                System.out.println("Hay otro soldado del mismo ejercito");
            } else if (e2.containsKey(newKey)) {
                System.out.println("HAY UNA BATALLA");
                Soldado enemy = e2.get(newKey);
                double w1 = winnerArmy(s, enemy);
                double w2 = winnerArmy(enemy, s);
                System.out.print(eje1+" " + w1 + "%");
                System.out.println(" <==> "+eje2+ " " + w2 + "%");
                if(w1 >= w2){
                    System.out.println("El ejercito con mayor probabilidad es: "+eje1.getNameArmy());
                    System.out.println("Gana el "+eje1);
                    s.setVidaActual(s.getVidaActual()+1);s.setFila(f);s.setColumna(c);
                    e1.put(newKey, s);
                    e1.remove(key);
                    e2.remove(newKey);
                }else{
                    System.out.println("El ejercito con mayor probabilidad es: "+eje2.getNameArmy());
                    System.out.println("Gana el "+eje2);
                    enemy.setVidaActual(enemy.getVidaActual()+1);
                    e1.remove(key);
                }
            } else {
                e1.put(newKey, s);
                e1.remove(key);
            }
        }
    }
    private double winnerArmy(Soldado s1, Soldado s2) {
        return (double) (s1.getVidaActual() * 100) / (s1.getVidaActual() + s2.getVidaActual());
    }
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        int turno = 1;
        while (getTotalSoldier(army1) > 0 && getTotalSoldier(army2) > 0) {
            String w = (turno == 1) ? army1+"" : army2+"";
            System.out.println(w + ": Ingrese el soldado a mover(f/c)");
            int f = sc.nextInt();
            int c = sc.nextInt();
            handleBattle((turno == 1) ? army1 : army2, (turno != 1) ? army1 : army2, turno, f, c);
            printMap();
            turno = (turno == 1) ? 2 : 1;
        }
        System.out.println("La batalla ha terminado");
    } 
}
