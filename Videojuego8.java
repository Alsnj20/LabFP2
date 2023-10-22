//Lab11-MarielJara
import java.util.*;
public class Videojuego8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String answer = "";
        while (true) {
            System.out.println("Ingrese las dimensiones del tablero (fila/columna)");
            int f = sc.nextInt();
            int c = sc.nextInt();
            Soldado[][] navy1 = new Soldado[f][c];
            Soldado[][] navy2 = new Soldado[f][c];
            fillSoldier(navy1, navy2);
            fillSoldier(navy2, navy1);
            System.out.println("-----------------------------");
            System.out.println("EJERCITO 1: El soldado con mayor vida es: \n" + mostlifeN(navy1));
            System.out.println("EJERCITO 2: El soldado con mayor vida es: \n" + mostlifeN(navy2));
            System.out.println("-----------------------------");
            System.out.println("EJERCITO 1: El promedio de nivel de vida de los soldados es: " + averageLifeN(navy1));
            System.out.println("EJERCITO 2: El promedio de nivel de vida de los soldados es: " + averageLifeN(navy2));
            System.out.println("-----------------------------");
            System.out.println("EJERCITO 1: Nivel de vida de todo el ejercito es: " + displayLifeN(navy1));
            System.out.println("EJERCITO 2: Nivel de vida de todo el ejercito es: " + displayLifeN(navy2));
            System.out.println("-----------------------------");
            System.out.println("EJERCITO 1: Datos de los soldados en orden de creación: ");
            displaySoldier(navy1);
            System.out.println("EJERCITO 2: Datos de los soldados en orden de creación: ");
            displaySoldier(navy2);
            System.out.println("---------Bubble sort--------");
            System.out.println("EJERCITO 1:");
            rankSoldierBubbleSort(navy1);
            System.out.println("EJERCITO 2:");
            rankSoldierBubbleSort(navy2);
            System.out.println("---------Selection sort--------");
            System.out.println("EJERCITO 1:");
            rankSoldierSelectionSort(navy1);
            System.out.println("EJERCITO 2:");
            rankSoldierSelectionSort(navy2);
            System.out.println("---------Mostrando Tablero-----");
            displaySoldierNavy(navy1, navy2);
            System.out.println("Empezando la Batalla");
            while(true){
                System.out.println("EJERCITO 1:Ingrese la posicion del soldado a mover ");
                int f1 = sc.nextInt();
                int c1 = sc.nextInt();
                selectionSoldier(navy1, navy2, f1,c1, 1, 2);
                displaySoldierNavy(navy1, navy2);
                if(countSoldiers(navy1) == 0 || countSoldiers(navy2) == 0)break;
                System.out.println("EJERCITO 2:Ingrese la posicion del soldado a mover ");
                int f2 = sc.nextInt();
                int c2 = sc.nextInt();
                selectionSoldier(navy2, navy1, f2, c2, 2, 1);
                displaySoldierNavy(navy1, navy2);
                if(countSoldiers(navy1) == 0 || countSoldiers(navy2) == 0)break;  
            }
            System.out.println("La Batalla ha terminado");
            System.out.println("Desea generar otro batalla? (s/n)");
            answer = sc.next();
            if(answer.equalsIgnoreCase("n")) break;   
        }
        sc.close();
    }
    /*
     * Cada soldado tendrá un nombre autogenerado: Soldado0X1, Soldado1X1, etc., un
     * valor de puntos de vida autogenerado aleatoriamente [1..5], la fila y columna
     * también
     * autogenerados aleatoriamente (no puede haber 2 soldados en el mismo
     * cuadrado).
     */
    public static void fillSoldier(Soldado[][] army1, Soldado[][] army2) {
        int fila = army1.length;
        int columna = army1[0].length;
        int n = generateSoldier();
        int i = 0;
        while (i < n) {
            int lifeN = (int) (Math.random() * 5 + 1);
            int row = (int) (Math.random() * fila);
            int col = (int) (Math.random() * columna);
            int attack = (int) (Math.random() * 5 + 1);
            int defense = (int) (Math.random() * 5 +1);
            if (army1[row][col] == null && army2[row][col] == null) {
                String name = "Soldado" + row + "X" + col;
                Soldado s = new Soldado(name, lifeN, attack, defense, row, col);
                army1[row][col] = s;
                i++;
            }
        }
    }
    /* Inicializar el tablero con n soldados aleatorios entre 1 y 10 */
    public static int generateSoldier() {
        return (int) (Math.random() * 10 + 1);
    }
    /* Además de los datos del Soldado con mayor vida de cada ejército, */
    public static Soldado mostlifeN(Soldado[][] army) {
        Soldado tempo = new Soldado();
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null && tempo.getVidaActual() < army[i][j].getVidaActual()) {
                    tempo = army[i][j];
                }
            }
        }
        return tempo;
    }
    /* el promedio de puntos de vida de todos los soldados creados por ejército */
    public static double averageLifeN(Soldado[][] army) {
        int suma = 0, total = 0;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null) {
                    suma += army[i][j].getVidaActual();
                    total++;
                }
            }
        }
        return (suma * 1.0) / total;
    }
    /* El nivel de vida de todo el ejército */
    public static int displayLifeN(Soldado[][] army) {
        int lifeT = 0;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null) {
                    lifeT += army[i][j].getVidaActual();
                }
            }
        }
        return lifeT;
    }
    /* los datos de todos los soldados en el orden que fueron creados */
    public static void displaySoldier(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            for (int j = 0; j < sold.size() - 1 - i; j++) {
                String w1 = sold.get(j).getName();
                String w2 = sold.get(j + 1).getName();
                if (w1.compareTo(w2) > 0) {
                    Soldado tempo = sold.get(j);
                    sold.set(j, sold.get(j + 1));
                    sold.set(j + 1, tempo);
                }
            }
        }
        for (int i = 0; i < sold.size(); i++) {
            System.out.println(sold.get(i));
        }
    }
    /*
     * un ranking de poder de todos los soldados creados, mayor al menor (usar al
     * menos 2 algoritmos de ordenamiento).
     */
    public static void rankSoldierBubbleSort(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            for (int j = 0; j < sold.size() - 1 - i; j++) {
                if (sold.get(j).getVidaActual() < sold.get(j + 1).getVidaActual()) {
                    Soldado tempo = sold.get(j);
                    sold.set(j, sold.get(j + 1));
                    sold.set(j + 1, tempo);
                }
            }
        }
        for (int i = 0; i < sold.size(); i++) {
            System.out.println(sold.get(i));
        }
    }
    public static ArrayList<Soldado> bidToUni(Soldado[][] army) {
        ArrayList<Soldado> sold = new ArrayList<>();
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[0].length; j++) {
                if (army[i][j] != null) {
                    sold.add(army[i][j]);
                }
            }
        }
        return sold;
    }
    public static void rankSoldierSelectionSort(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            int idx = i;
            for (int j = i + 1; j < sold.size(); j++) {
                if (sold.get(j).getVidaActual() > sold.get(idx).getVidaActual()) {
                    idx = j;
                }
            }
            Soldado tempo = sold.get(i);
            sold.set(i, sold.get(idx));
            sold.set(idx, tempo);

        }
        for (int i = 0; i < sold.size(); i++) {
            System.out.println(sold.get(i));
        }
    }
    /*
     * Finalmente, que muestre qué ejército ganará la batalla (indicar la métrica
     * usada para decidir al ganador
     * de la batalla).
     */
    public static void betterWonArmy(Soldado[][] army1, Soldado[][] army2) {
        int pointArmy1 = displayLifeN(army1);
        int pointArmy2 = displayLifeN(army2);
        if (pointArmy1 == pointArmy2) {
            System.out.println("EMPATE");
        } else if (pointArmy1 > pointArmy2) {
            System.out.println("El Ejercito 1 gana");
        } else {
            System.out.println("El Ejercito 2 gana");
        }
    }
    /*Se
    debe mostrar el tablero con todos los soldados creados (usar caracteres como
    | _ y otros) y distinguir los de un ejército de los del otro ejército. */
    public static void displaySoldierNavy(Soldado[][] army1, Soldado[][] army2) {
        for (int i = 0; i < army1[0].length; i++) {
            System.out.print("    "+i);
        }
        System.out.println();
        for (int i = 0; i < army1.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < army1[0].length; j++) {
                if (army1[i][j] != null) {
                    System.out.print("|A:"+army1[i][j].getVidaActual()+"|");
                } else if (army2[i][j] != null) {
                    System.out.print("|B:"+army2[i][j].getVidaActual()+"|");
                } else {
                    System.out.print("|___|");
                }
            }
            System.out.println();
        }
    }
    /*El juego es humano contra humano y consistirá en mover un soldado por cada
    turno de cada jugador.*/
    public static void selectionSoldier(Soldado[][] army1, Soldado[][] army2, int f, int c, int e1, int e2){
        for (int i = 0; i < army1.length; i++) {
            for (int j = 0; j < army1[i].length; j++) {
                Soldado s = army1[i][j];
                if(s != null && s.getFila() == f && s.getColumna() == c ){
                    move(s, army1.length, army1[i].length);
                    int fila = s.getFila();
                    int columna = s.getColumna();
                    if(hasAnother(s, army1[fila][columna])){
                        System.out.println("Hay otro soldado");
                    }else if(hasAnother(s, army2[fila][columna])){
                        System.out.println("----Hay una Batalla!!----");
                        double m1 = winner(s, army2[fila][columna]);
                        double m2 = winner(army2[fila][columna], s);
                        System.out.println("E"+e1+":"+m1+"% | E"+e2+":"+m2+"%");
                        if(m1 == m2){
                            int n = (int)(Math.random()*2+1);
                            System.out.println("EMPATE!!...gana aleatoriamente");
                            if(n == e1){
                                System.out.println("Gana el Ejercito"+e1);
                                s.setVidaActual(s.getVidaActual()+1);
                                army1[i][j] = null;
                                army1[fila][columna] = s;
                                army2[fila][columna] = null;
                            }else{
                                System.out.println("Gana el Ejercito"+e2);
                                army2[fila][columna].setVidaActual(army2[fila][columna].getVidaActual()+1);
                                army1[i][j] = null;
                            }                   
                        }else if(m1>m2){
                            System.out.println("EJERCITO "+e1+" GANA");
                            s.setVidaActual(s.getVidaActual()+1);
                                army1[i][j] = null;
                                army1[fila][columna] = s;
                                army2[fila][columna] = null;
                        }else{
                            army2[fila][columna].setVidaActual(army2[fila][columna].getVidaActual()+1);
                            System.out.println("EJERCITO "+e2+" GANA");
                            army1[i][j] = null;
                        }
                    }else{
                        army1[i][j] = null;
                        army1[fila][columna] = s;
                    }
                    break;
                }
            }
        }
    }
    /*Se puede mover en cualquier dirección, Ud. deberá darle
    la coordenada del soldado a mover y la dirección de movimiento */
    public static void move(Soldado s, int f, int c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A que lado desea mover? (der/izq/arr/aba)");
        String dir = sc.next();
        System.out.println("Ingrese la cantidad de movimientos:");
        int n = sc.nextInt();
        switch (dir) {
            case "der":
                int der = s.getColumna() - n;
                if (der < 0) {
                    System.out.println("No hay espacio");
                } else {
                    s.setColumna(der);
                }
                break;
            case "izq":
                int izq = s.getColumna() + n;
                if (izq > c) {
                    System.out.println("No hay espacio");
                } else {
                    s.setColumna(izq);
                }
                break;
            case "arr":
                int arr = s.getFila() - n;
                if (arr < 0) {
                    System.out.println("No hay espacio");
                } else {
                    s.setFila(arr);
                }
                break;
            case "aba":
                int aba = s.getFila() + n;
                if (aba > f) {
                    System.out.println("No hay espacio");
                } else {
                    s.setFila(aba);
                }
                break;
        }
    }
    /*(no puede haber 2 soldados del mismo ejército en el cuadrado) */
    public static boolean hasAnother(Soldado s1, Soldado s2){
        return (s2 != null && s1.getFila() == s2.getFila() && s1.getColumna() == s2.getColumna());
    }
    /* Gana el juego quien deje al otro ejército vacío */
    public static int countSoldiers(Soldado[][] army){
        int n = 0;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if(army[i][j] != null){
                    n++;
                }
            }
        }
        return n;
    }
    /*Cuando un soldado se
    mueve a una posición donde hay un soldado rival, se produce una batalla y
    gana el soldado siguiendo la siguiente métrica: la suma de los 2 niveles de vida
    actual de los soldados que luchan son el 100% y se le debe dar la probabilidad
    correspondiente de vencer para cada soldado (ejemplo S1:5 S2:3, las
    probabilidades de vencer serían S1:62.5% S2:37.5%) y de acuerdo a dichas
    probabilidades se decidirá el ganador aleatoriamente. El ganador ocupará dicho
    cuadrado (se le aumentará su nivel de vida actual en 1)  */
    public static double winner(Soldado s1, Soldado s2){
        return (s1.getVidaActual()*100)/(s1.getVidaActual() + s2.getVidaActual());
    }
}
