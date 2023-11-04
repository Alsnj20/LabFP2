package Lab14;
//Lab14-MarielJara
import java.util.*;
public class Videojuego11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Soldado[][] tableSoldier = new Soldado[10][10];
        Ejercito[][] tableArmies = new Ejercito[10][10];
        Boolean play = true;
        while (play) {
            Reino r1 = new Reino();
            Reino r2 = new Reino();
            System.out.println(r1 + " vs " + r2);
            fillArmies(tableArmies, tableSoldier, r1, r1.getNameKingdom());
            fillArmies(tableArmies, tableSoldier, r2, r2.getNameKingdom());
            displayArmiesTable(tableArmies, r1, r2);
            while(true){
               System.out.println(r1+": Ingrese la posicion del ejercito a mover ");
                int f1 = sc.nextInt();
                int c1 = sc.nextInt();
                selectionArmy(tableArmies, tableSoldier, r1, r2, f1, c1);
                displayArmiesTable(tableArmies, r1, r2);
                if (r1.getArmies().size() == 0 || r2.getArmies().size() == 0)
                break;
                System.out.println(r2+": Ingrese la posicion del ejercito a mover ");
                int f2 = sc.nextInt();
                int c2 = sc.nextInt();
                selectionArmy(tableArmies, tableSoldier, r2, r1, f2, c2);
                displayArmiesTable(tableArmies, r1, r2);
                if (r1.getArmies().size() == 0 || r2.getArmies().size() == 0)
                break; 
            }
            System.out.println("El juego ha terminado");
            System.out.println("Desea generar otro batalla? (s/n)");
            String answer = sc.next();
            if(answer.equalsIgnoreCase("n"))break;
        }
    }
    // juego
    public static void playGame(Reino r1, Reino r2,Soldado[][] table, Ejercito navy1, Ejercito navy2) {
        Scanner sc = new Scanner(System.in);
        String answer = "";
        while (true) {
            displayData(table, navy1, navy2);
            playAction(r1,r2, table, navy1, navy2);
            System.out.println("Desea generar otro batalla? (s/n)");
            answer = sc.next();
            if (answer.equalsIgnoreCase("n"))
                break;
        }
    }
    public static void displayData(Soldado[][] table, Ejercito navy1, Ejercito navy2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("EJERCITO 1: El soldado con mayor vida es: "+mostlifeN(navy1));
        System.out.println("EJERCITO 2: El soldado con mayor vida es: "+mostlifeN(navy2));
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
        displaySoldierTable(table, navy1, navy2);
    }
    public static void playAction(Reino r1, Reino r2,Soldado[][] table, Ejercito navy1, Ejercito navy2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------Mostrando Tablero-----");
        displaySoldierTable(table, navy1, navy2);
        System.out.println("Empezando la Batalla");
        while (true) {
            System.out.println("EJERCITO "+r1.getNameKingdom()+":Ingrese la posicion del soldado a mover ");
            int f1 = sc.nextInt();
            int c1 = sc.nextInt();
            selectionSoldier(table, navy1, navy2, f1, c1, 1, 2);
            if (navy1.getSoldiers().size() < 1 || navy2.getSoldiers().size() < 1)
                break;
            System.out.println("EJERCITO "+r2.getNameKingdom()+":Ingrese la posicion del soldado a mover ");
            int f2 = sc.nextInt();
            int c2 = sc.nextInt();
            selectionSoldier(table, navy2, navy1, f2, c2, 2, 1);
            displaySoldierTable(table, navy1, navy2);
            if (navy1.getSoldiers().size() < 1 || navy2.getSoldiers().size() < 1)
                break;
        }
        System.out.println("La Batalla ha terminado");
    }
    public static void customGame(Soldado[][] table, Ejercito navy1, Ejercito navy2) {
        Scanner sc = new Scanner(System.in);
        displayData(table, navy1, navy2);
        System.out.print("Ingrese el ejercito a gestionar(1/2):");
        int n = sc.nextInt();
        Ejercito elegido = (n == 1) ? navy1 : navy2;
        boolean play = true;
        while (play) {
            System.out.println("1.Crear Soldados\n2.Eliminar Soldado\n3.Clonar Soldado\n4.Modificar Soldado");
            System.out.println("5.Comparar Soldado\n6.Intercambiar Soldados\n7.Ver Soldados\n8.Ver ejercito");
            System.out.println("9.Sumar Niveles\n10.Jugar\n11.Volver");
            int option2 = sc.nextInt();
            switch (option2) {
                case 1:
                    createSoldier(elegido);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 2:
                    deleteSoldier(elegido);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 3:
                    cloneSoldier(elegido);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 4:
                    modifiedSoldier(elegido);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 5:
                    compareSoldier(elegido);
                    break;
                case 6:
                    changeSoldier(elegido);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 7:
                    sortName(elegido);
                    break;
                case 8:
                    displaySoldier(elegido);
                    break;
                case 9:
                    addlevel(elegido);
                    break;
                case 10:
                    displaySoldierTable(table, navy1, navy2);
                    displaySoldierTable(table, navy1, navy2);
                    break;
                case 11:
                    play = false;
                    break;
            }
        }
    }
    // SOLDADO
    /* Cada soldado tendrá un nombre autogenerado: Soldado0X1, Soldado1X1...vida
     * aleatoria[1..5], la fila y columnatambién (no puede haber 2 soldados en el mismo
     * cuadrado)*/
    public static void fillSoldier(Soldado[][] table, Ejercito army, String hol) {
        int n = Ejercito.generateSoldier();
        int i = 0;
        while (i < n) {
            int life = (int) (Math.random() * 5 + 1);
            int row = (int) (Math.random() * table.length);
            int col = (int) (Math.random() * table[0].length);
            int attack = (int) (Math.random() * 5 + 1);
            int defense = (int) (Math.random() * 5 + 1);
            if (table[row][col] == null) {
                String name = "Soldado" + row + "X" + col;
                Soldado s = new Soldado(name, life, defense, attack, row, col);
                s.setTeam(hol);
                army.getSoldiers().add(s);
                table[row][col] = s;
                i++;
            }
        }
    }
    /* Además de los datos del Soldado con mayor vida de cada ejército, */
    public static Soldado mostlifeN(Ejercito eje) {
        Soldado tempo = new Soldado();
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            if (tempo.getVidaActual() < eje.getSoldiers().get(i).getVidaActual())
                tempo = eje.getSoldiers().get(i);
        }
        return tempo;
    }
    /* el promedio de puntos de vida de todos los soldados creados por ejército */
    public static double averageLifeN(Ejercito eje) {
        return displayLifeN(eje) * 1.0 / eje.getSoldiers().size();
    }
    /* El nivel de vida de todo el ejército */
    public static int displayLifeN(Ejercito eje) {
        int lifeT = 0;
        for (Soldado s : eje.getSoldiers()) {
            lifeT += s.getVidaActual();
        }
        return lifeT;
    }
    /* los datos de todos los soldados en el orden que fueron creados */
    public static void displaySoldier(Ejercito eje) {
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            for (int j = 0; j < eje.getSoldiers().size() - 1 - i; j++) {
                String w1 = eje.getSoldiers().get(j).getName();
                String w2 = eje.getSoldiers().get(j + 1).getName();
                if (w1.compareTo(w2) > 0) {
                    Soldado tempo = eje.getSoldiers().get(j);
                    eje.getSoldiers().set(j, eje.getSoldiers().get(j + 1));
                    eje.getSoldiers().set(j + 1, tempo);
                }
            }
        }
        printSort(eje.getSoldiers());
    }
    /*un ranking de poder de los soldados creados mayor al menor (usar al
     *menos 2 algoritmos de ordenamiento).*/
    public static void rankSoldierBubbleSort(Ejercito eje) {
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            for (int j = 0; j < eje.getSoldiers().size() - 1 - i; j++) {
                if (eje.getSoldiers().get(j).getVidaActual() < eje.getSoldiers().get(j + 1).getVidaActual()) {
                    Soldado tempo = eje.getSoldiers().get(j);
                    eje.getSoldiers().set(j, eje.getSoldiers().get(j + 1));
                    eje.getSoldiers().set(j + 1, tempo);
                }
            }
        }
        printSort(eje.getSoldiers());
    }
    public static void rankSoldierSelectionSort(Ejercito eje) {
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            int idx = i;
            for (int j = i + 1; j < eje.getSoldiers().size(); j++) {
                if (eje.getSoldiers().get(j).getVidaActual() > eje.getSoldiers().get(idx).getVidaActual()) {
                    idx = j;
                }
            }
            Soldado tempo = eje.getSoldiers().get(i);
            eje.getSoldiers().set(i, eje.getSoldiers().get(idx));
            eje.getSoldiers().set(idx, tempo);
        }
        printSort(eje.getSoldiers());
    }
    public static void printSort(ArrayList<Soldado> sold) {
        for (int i = 0; i < sold.size(); i++)
            System.out.println(sold.get(i));
    }
    /* Muestre qué ejército ganará la batalla suma de vidas. */
    public static void betterWonArmy(Ejercito army1, Ejercito army2) {
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
    /* Mostrar el tablero (usar caracteres como| _ y otros) y diferenciarlos. */
    public static void displaySoldierTable(Soldado[][] table, Ejercito j1, Ejercito j2) {
        for (int i = 0; i < table[0].length; i++) {
            System.out.print("    " + i);
        }
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < table[0].length; j++) {
                Soldado s = table[i][j];
                if (s != null && j1.getSoldiers().contains(s)) {
                    System.out.print("|"+s.getTeam().charAt(0)+":" + s.getVidaActual() + "|");
                } else if (s != null && j2.getSoldiers().contains(s)) {
                    System.out.print("|"+s.getTeam().charAt(0)+":" + s.getVidaActual() + "|");
                } else {
                    System.out.print("|___|");
                }
            }
            System.out.println();
        }
    }
    /* El juego consistirá en mover un soldado por cada turno de cada jugador. */
    public static void selectionSoldier(Soldado[][] table, Ejercito navy1, Ejercito navy2, int f, int c, int e1, int e2) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                Soldado s = navy1.getSoldiers().get(searchSoldier(navy1, f, c));
                if (s != null) {
                    moveSoldiers(s, table.length, table[i].length);
                    int fila = s.getFila();
                    int columna = s.getColumna();
                    Soldado team = navy1.getSoldiers().get(searchSoldier(navy1, fila, columna));
                    Soldado enemy = navy2.getSoldiers().get(searchSoldier(navy2, fila, columna));
                    if (hasAnother(s, team)){
                        System.out.println("Hay otro soldado");
                    } else if (hasAnother(s, enemy)) {
                        System.out.println("----Hay una Batalla!!----");
                        double m1 = winnerSoldier(s, enemy);
                        double m2 = winnerSoldier(enemy, s);
                        System.out.println("E" + e1 + ":" + m1 + "% | E" + e2 + ":" + m2 + "%");
                        int n = (int) (Math.random() * (s.getVidaActual() + enemy.getVidaActual()));
                        if (s.getVidaActual() > n) {
                            System.out.println("Gana el Ejercito" + e1);
                            s.setVidaActual(s.getVidaActual() + 1);
                            table[i][j] = null;
                            table[fila][columna] = s;
                            navy2.getSoldiers().remove(enemy);
                        } else {
                            enemy.setVidaActual(enemy.getVidaActual() + 1);
                            System.out.println("EJERCITO " + e2 + " GANA");
                            navy1.getSoldiers().remove(s);
                        }
                    } else {
                        table[i][j] = null;
                        table[fila][columna] = s;
                    }
                    break;
                }
            }
        }
    }
    public static boolean hasAnother(Soldado s1, Soldado s2) {
        return (s2 != null && s1.getFila() == s2.getFila() && s1.getColumna() == s2.getColumna());
    }
    /* Se puede mover en cualquier dirección,dar coordenada y dirección dl soldado a
     * mover*/
    public static void moveSoldiers(Soldado s, int f, int c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SOLDADO: A que lado desea mover? (der/izq/arr/aba)");
        String dir = sc.next();
        System.out.println("Ingrese la cantidad de movimientos:");
        int n = sc.nextInt();
        switch (dir) {
            case "der":
                int der = s.getColumna() - n;
                if (der < 0)
                    System.out.println("No hay espacio");
                else
                    s.setColumna(der);
                break;
            case "izq":
                int izq = s.getColumna() + n;
                if (izq > c)
                    System.out.println("No hay espacio");
                else
                    s.setColumna(izq);
                break;
            case "arr":
                int arr = s.getFila() - n;
                if (arr < 0)
                    System.out.println("No hay espacio");
                else
                    s.setFila(arr);
                break;
            case "aba":
                int aba = s.getFila() + n;
                if (aba > f)
                    System.out.println("No hay espacio");
                else
                    s.setFila(aba);
                break;
        }
    }
    /* Gana el juego quien deje al otro ejército vacío */
    public static int countSoldiers(Ejercito eje) {
        return eje.getSoldiers().size();
    }
    /* Mostrar la probabilidad de cuanto tiene a ganar ejm: S1:62.5% S2:37.5% */
    public static double winnerSoldier(Soldado s1, Soldado s2) {
        return (s1.getVidaActual() * 100) / (s1.getVidaActual() + s2.getVidaActual());
    }
    public static void createSoldier(Ejercito eje) {
        Scanner sc = new Scanner(System.in);
        if (eje.getSoldiers().size() < Soldado.MAX_COUNT_SOLDIER) {
            System.out.println("Ingrese los datos del soldado a crear");
            System.out.print("Ingrese fila y columna:");
            int f = sc.nextInt();
            int c = sc.nextInt();
            System.out.print("Ingrese el nombre:");
            String name = sc.next();
            System.out.println("Ingrese el nivel de vida, nivel de defensa, nivel de ataque: ");
            int nV = sc.nextInt();
            int nD = sc.nextInt();
            int nA = sc.nextInt();
            Soldado s = new Soldado(name, nV, nD, nA, f, c);
            eje.getSoldiers().add(s);
            printSort(eje.getSoldiers());
        } else {
            System.out.println("No hay espacio");
        }
    }
    public static void deleteSoldier(Ejercito eje) {
        if (eje.getSoldiers().size() < 2) {
            System.out.println("Error delete");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese las coordenadas del soldado a eliminar: ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            int n = searchSoldier(eje, fila, columna);
            eje.getSoldiers().remove(n);
        }
    }
    public static int searchSoldier(Ejercito eje, int f, int c) {
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            Soldado s = eje.getSoldiers().get(i);
            if (s.getFila() == f && s.getColumna() == c)
                return i;
        }
        return -1;
    }
    public static void cloneSoldier(Ejercito eje) {
        if (eje.getSoldiers().size() == Soldado.MAX_COUNT_SOLDIER) {
            System.out.println("No hay espacio");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese las coordenadas del soldado a clonar: ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            Soldado s1 = eje.getSoldiers().get(searchSoldier(eje, fila, columna));
            System.out.print("Ingrese las nuevas coordenadas del soldado clonado ");
            int f2 = sc.nextInt();
            int c2 = sc.nextInt();
            Soldado s2 = new Soldado("Soldado" + f2 + "X" + c2, s1.getVidaActual(), s1.getNiveldeDefensa(),
                    s1.getNiveldeAtaque(), f2, c2);
            eje.getSoldiers().add(s2);
        }
    }
    public static void modifiedSoldier(Ejercito eje) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado a modificar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        Soldado s = eje.getSoldiers().get(searchSoldier(eje, fila, columna));
        System.out.println(s);
        System.out.println("Ingrese modificacion\n1.NivelAtaque\n2.NivelDefensa\n3.VidaActual");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.print("Ingrese el nuevo nivel de Ataque: ");
                int n = sc.nextInt();
                s.setNiveldeAtaque(n);
                break;
            case 2:
                System.out.print("Ingrese el nuevo nivel de Defensa: ");
                n = sc.nextInt();
                s.setNiveldeDefensa(n);
                break;
            case 3:
                System.out.print("Ingrese el nuevo nivel de VidaActual: ");
                n = sc.nextInt();
                s.setVidaActual(n);
                break;
        }
    }
    public static void compareSoldier(Ejercito eje) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado1 a comparar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        System.out.print("Ingrese las coordenadas del soldado2 a comparar: ");
        int fila2 = sc.nextInt();
        int columna2 = sc.nextInt();
        Soldado s1 = eje.getSoldiers().get(searchSoldier(eje, fila, columna));
        Soldado s2 = eje.getSoldiers().get(searchSoldier(eje, fila2, columna2));
        if (s1.compareS(s2)) {
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }
    }
    public static void changeSoldier(Ejercito eje) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado1 a intercambiar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        System.out.print("Ingrese las coordenadas del soldado2 a intercambiar: ");
        int fila2 = sc.nextInt();
        int columna2 = sc.nextInt();
        Soldado s1 = eje.getSoldiers().get(searchSoldier(eje, fila, columna));
        Soldado s2 = eje.getSoldiers().get(searchSoldier(eje, fila2, columna2));
        if (s1 != null && s2 != null) {
            Soldado tempo = s1;
            s1 = s2;
            s2 = tempo;
        } else {
            System.out.println("Coordenadas invalidas");
        }
    }
    public static void sortName(Ejercito eje) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre a buscar: ");
        String name = sc.next();
        boolean found = false;
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            Soldado s = eje.getSoldiers().get(i);
            if (s != null && s.getName().equalsIgnoreCase(name)) {
                System.out.println(s);
                found = true;
            }
        }
        if (!found)
            System.out.println("name not found");
    }
    public static void addlevel(Ejercito eje) {
        Soldado sBig = new Soldado(0, 0, 0, 0);
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            sBig = sBig.addTotal(eje.getSoldiers().get(i));
        }
        sBig.setName("SoldierBig");
        System.out.println("Se ha unido todo el ejercito!!!");
        sBig.data();
    }
    // EJERCITO
    public static void fillArmies(Ejercito[][] table, Soldado[][] tableS, Reino r, String w) {
        int n = Reino.generateArmies();
        int i = 0;
        while (i < n) {
            int row = (int) (Math.random() * table.length);
            int col = (int) (Math.random() * table[0].length);
            if (table[row][col] == null) {
                Ejercito eje = new Ejercito(row, col);
                fillSoldier(tableS, eje, w);
                r.getArmies().add(eje);
                table[row][col] = eje;
                i++;
            }
        }
    }
    public static void displayArmiesTable(Ejercito[][] table, Reino r1, Reino r2) {
        for (int i = 0; i < table[0].length; i++) {
            System.out.print("    " + i);
        }
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < table[0].length; j++) {
                Ejercito eje = table[i][j];
                if(eje != null && r1.getArmies().contains(eje)){
                    System.out.print("|" + r1.getNameKingdom().charAt(0) + ":" + eje.getSoldiers().size() + "|");  
                }else if(eje != null && r2.getArmies().contains(eje)){
                    System.out.print("|" + r2.getNameKingdom().charAt(0) + ":" + eje.getSoldiers().size() + "|");  
                }else{
                   System.out.print("|___|"); 
                }  
            }
            System.out.println();
        }
    }
    public static void selectionArmy(Ejercito[][] table, Soldado[][] sold, Reino r1, Reino r2, int f, int c) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                Ejercito eje = table[i][j];
                if (eje != null && i == f && j == c) {
                    moveArmy(eje, table.length, table[i].length);
                    int fil = eje.getRow();
                    int col = eje.getColumn();
                    if (hasAnotherArmy(r1.getArmies().contains(table[fil][col]))) {
                        System.out.println("Hay otro Ejercito del mismo Reino");
                    } else if (hasAnotherArmy(r2.getArmies().contains(table[fil][col]))) {
                        System.out.println("----Hay una Batalla entre Ejercitos!!----");
                        battleArmies(table, sold,r1,r2, eje, table[fil][col]);
                    } else {
                        table[i][j] = null;
                        table[fil][col] = eje;
                    }
                    break;
                }
            }
        }
    }
    public static void moveArmy(Ejercito eje, int f, int c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("EJERCITO: A que lado desea mover? (der/izq/arr/aba)");
        String mov = sc.next();
        System.out.println("Ingrese la cantidad de movimientos:");
        int n = sc.nextInt();
        switch (mov) {
            case "der":
                int der = eje.getColumn() - n;
                if (der < 0)
                    System.out.println("No hay espacio");
                else
                    eje.setColumn(der);
                break;
            case "izq":
                int izq = eje.getColumn() + n;
                if (izq > c)
                    System.out.println("No hay espacio");
                else
                    eje.setColumn(izq);
                break;
            case "arr":
                int arr = eje.getRow() - n;
                if (arr < 0)
                    System.out.println("No hay espacio");
                else
                    eje.setRow(arr);
                break;
            case "aba":
                int aba = eje.getRow() + n;
                if (aba > f)
                    System.out.println("No hay espacio");
                else
                    eje.setRow(aba);
                break;
        }
    }

    public static Boolean hasAnotherArmy(Boolean afirm) {
        return (afirm == true)? true: false;
    }

    public static void battleArmies(Ejercito[][] eje, Soldado[][] table, Reino r1, Reino r2, Ejercito eje1, Ejercito eje2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Resolver Batalla por: ");
        System.out.println("1.Cantidad de Soldado mayor\n2.Jugar en tablero");
        int n = sc.nextInt();
        switch (n) {
            case 1:
                winnerArmy(r1, r2, eje1, eje2);
                System.out.println("Cambios Actualizados");
                break;
            case 2:
                playGame(r1, r2, table, eje1, eje2);
                break;
        }
    }
    public static void winnerArmy(Reino r1, Reino r2, Ejercito eje1, Ejercito eje2){
        int n1 = eje1.getSoldiers().size();
        int n2 = eje2.getSoldiers().size();
        if(n1 >= n2){
            System.out.println("Gana el ejercito del "+r1);
            r2.getArmies().remove(eje2);
        }else{
            System.out.println("Gana el ejercito del "+r2);
            r1.getArmies().remove(eje1);
        }
     }
}
