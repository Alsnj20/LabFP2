import java.util.*;
public class Videojuego4 {
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
            displaySoldierNavy(navy1, navy2);
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
            System.out.println("--------El Ganador de acuerdo al nivel de vida es:-------");
            betterWonArmy(navy1, navy2);
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
            if (army1[row][col] == null && army2[row][col] == null) {
                String name = "Soldado" + row + "X" + col;
                army1[row][col] = new Soldado(name, lifeN, row, col);
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
        Soldado tempo = new Soldado("", 0, 0, 0);
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null && tempo.getLifeN() < army[i][j].getLifeN()) {
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
                    suma += army[i][j].getLifeN();
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
                    lifeT += army[i][j].getLifeN();
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
     * Se debe mostrar el tablero con todos los soldados creados (distinguir los de
     * un ejército de los del otro
     * ejército)
     */
    public static void displaySoldierNavy(Soldado[][] army1, Soldado[][] army2) {
        for (int i = 0; i < army1[0].length; i++) {
            System.out.print("     " + i);
        }
        System.out.println();
        for (int i = 0; i < army1.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < army1[0].length; j++) {
                if (army1[i][j] != null) {
                    System.out.print("|E1:" + army1[i][j].getLifeN() + "|");
                } else if (army2[i][j] != null) {
                    System.out.print("|E2:" + army2[i][j].getLifeN() + "|");
                } else {
                    System.out.print("|____|");
                }
            }
            System.out.println();
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
                if (sold.get(j).getLifeN() < sold.get(j + 1).getLifeN()) {
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
                if (sold.get(j).getLifeN() > sold.get(idx).getLifeN()) {
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
}
