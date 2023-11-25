import java.util.*;
public class Mapa {
    public static final Soldado[][] table = new Soldado[10][10];
    private final String[][] KINGDOMS = {
            { "Inglaterra", "bosque" },
            { "Francia", "campo abierto" },
            { "Castilla-Aragon", "montaña" },
            { "Moros", "desierto" },
            { "Sacro Imperio Romano-Germánico", "bosque", "playa", "campo abierto" }
    };
    private final String[] MAP_TYPES = { "bosque", "campo abierto", "montaña", "desierto",
            "playa" };
    private String type;
    private Soldado[] army1;
    private ArrayList<Soldado> army2;
    private int kingdomName1Id;
    private int kingdomName2Id;
    private int sumLifeArmy1 = 0;
    private int sumLifeArmy2 = 0;

    public Mapa() {
        type = MAP_TYPES[(int) (Math.random() * MAP_TYPES.length)];
        kingdomName1Id = (int) (Math.random() * KINGDOMS.length);
        kingdomName2Id = (int) (Math.random() * KINGDOMS.length);
        generateArmies();
    }
    public String getType() {return type;}
    public String getKingdomName1() {return KINGDOMS[(kingdomName1Id)][0].toUpperCase();}
    public String getKingdomName2() {return KINGDOMS[(kingdomName2Id)][0].toUpperCase();}
    private boolean isBuffed(int kingdomNameId) {
        for (int i = 0; i < KINGDOMS[kingdomNameId].length; i++) {
            if (type.equals(KINGDOMS[kingdomNameId][i]))
                return true;
        }
        return false;
    }
    private void generateArmies() {
        // arreglo 1;
        boolean buffed1 = isBuffed(kingdomName1Id);
        Ejercito eje1 = new Ejercito(getKingdomName1(), table, buffed1);
        army1 = new Soldado[eje1.getArmy().size()];
        for(int i=0; i<army1.length; i++){
            army1[i] = eje1.getArmy().get(i);
        }
        // arreglo2
        boolean buffed2 = isBuffed(kingdomName2Id);
        Ejercito eje2 = new Ejercito(getKingdomName2(), table, buffed2);
        army2 = eje2.getArmy();
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
        System.out.print("     0");
        for (int i = 1; i < table.length; i++) {
            System.out.print("       "+i);
        }
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < table[i].length; j++) {
                if(table[i][j] !=null){
                    Soldado army = table[i][j]; String w = "";
                    if(army2.contains(army))
                        w = getKingdomName2().substring(0,1);
                    else
                        w = getKingdomName1().substring(0,1);
                    System.out.printf("|%s:%c-%02d|", w, army.getName().charAt(0),
                    army.getVidaActual()); 
                }else{
                    System.out.print("|______|");
                }
            }
            System.out.println();
        }
    }
    public void mostLife(){
        Soldado tempo1 = new Soldado();
        for(Soldado s: army1){
            if(s.getVidaActual() > tempo1.getVidaActual()) tempo1 = s;       
        }
        Soldado tempo2 = new Soldado();
        for(Soldado s: army2){
            if(s.getVidaActual() > tempo2.getVidaActual()) tempo2 = s;       
        }
        System.out.println("\nEJERCITO "+getKingdomName1()+":El soldado con mayor vida es: \n" + tempo1);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("EJERCITO "+getKingdomName2()+":El soldado con mayor vida es: \n" + tempo2);
    }
    public void averageLife(){
        int total1 = 0;
        for (Soldado s : army1) total1 += s.getVidaActual();
        int total2 = 0;
        for (Soldado s : army2) total2 += s.getVidaActual();
        System.out.println("\nEJERCITO "+getKingdomName1()+": El promedio de nivel de vida de los soldados es: "+total1/army1.length);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("EJERCITO "+getKingdomName2()+": El promedio de nivel de vida de los soldados es: "+total2/army2.size());
    }
    public void displaySoldier() {
        System.out.println("\nEJERCITO "+getKingdomName1()+": Datos de los soldados en orden de creación: ");
        for (Soldado s: army1) {System.out.println(s);}
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("EJERCITO "+getKingdomName2()+": Datos de los soldados en orden de creación: ");
        for (Soldado s: army2) {System.out.println(s);}
    }
    public void rankSoldierBubbleSort() {
        //ejercito1
        for (int i = 0; i < army1.length; i++) {
            for (int j = 0; j < army1.length - 1 - i; j++) {
                if (army1[j].getVidaActual() < army1[j+1].getVidaActual()) {
                    Soldado tempo = army1[j];
                    army1[j] = army1[j+1];
                    army1[j+1] = tempo;
                }
            }
        }
        //ejercito2
        for (int i = 0; i < army2.size(); i++) {
            for (int j = 0; j < army2.size() - 1 - i; j++) {
                if (army2.get(j).getVidaActual() < army2.get(j + 1).getVidaActual()) {
                    Soldado tempo = army2.get(j);
                    army2.set(j, army2.get(j + 1));
                    army2.set(j + 1, tempo);
                }
            }
        }
        System.out.println("\n---------------------Bubble sort-------------------------");
        System.out.println("EJERCITO "+getKingdomName1()+ ":");
        for (int i= 0; i<army1.length; i++) System.out.println(army1[i]);
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("EJERCITO "+getKingdomName2()+ ":");
        for (Soldado s: army2) System.out.println(s);
    }
    private void cantTypeSoldado(){
        int esp1 = 0; int arq1 = 0; int cab1 = 0; int lan1 = 0;
        for(Soldado s: army1){
            if(s instanceof Espadachin) esp1++;
            if(s instanceof Arquero) arq1++;
            if(s instanceof Caballero) cab1++;
            if(s instanceof Lancero) lan1++;
        }
        System.out.println("\nEjercito 1: "+getKingdomName1());
        System.out.println("Cantidad de Soldado creados: "+army1.length);
        System.out.println("Espadachines: "+esp1);
        System.out.println("Arqueros: "+arq1);
        System.out.println("Caballeros: "+cab1);
        System.out.println("Lanceros: "+lan1);
        int esp2 = 0; int arq2 = 0; int cab2 = 0; int lan2 = 0;
        for(Soldado s: army2){
            if(s instanceof Espadachin) esp2++;
            if(s instanceof Arquero) arq2++;
            if(s instanceof Caballero) cab2++;
            if(s instanceof Lancero) lan2++;
        }
        System.out.println("\nEjercito 2: "+getKingdomName2());
        System.out.println("Cantidad de Soldado creados: "+army2.size());
        System.out.println("Espadachines: "+esp2);
        System.out.println("Arqueros: "+arq2);
        System.out.println("Caballeros: "+cab2);
        System.out.println("Lanceros: "+lan2);
    }
    private void getTotalHealthArmy(){
        for (int i = 0; i < army1.length; i++) sumLifeArmy1 +=army1[i].getVidaActual();
        for (Soldado s: army2) sumLifeArmy2 +=s.getVidaActual();
    }
    private static double winner(int n, int m) {
        return (n*100)/(n+m);
    }
    public void printData(){
        cantTypeSoldado();
        getTotalHealthArmy();
        double arm1 = winner(sumLifeArmy1, sumLifeArmy2);
        double arm2 = winner(sumLifeArmy2, sumLifeArmy1);
        System.out.println("\nEjercito 1: "+getKingdomName1()+": "+sumLifeArmy1
        +"  => "+arm1+"% de probabilidad de victoria");
        System.out.println("Ejercito 2: "+getKingdomName2()+": "+sumLifeArmy2
        +"  => "+arm2+"% de probabilidad de victoria");
        double n = (Math.random()*(arm1+arm2));
        String winner = (n > arm2)?"Ejercito 1 de "+getKingdomName1():"Ejercito 2 de "+getKingdomName2();
        System.out.println("El ganador es el "+winner+" Ya que al generar los");
        System.out.println("porcentajes de probabilidad de victoria basada en los niveles de");
        System.out.println("vida de sus soldados y aplicando un experimento aleatorio salió vencedor.");
        System.out.println("(Aleatorio generado: "+n+")");
    }
    
    
}
