package Lab15;
//Lab15-MarielJara
import java.util.*;
public class Videojuego12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Soldado[][] tableSoldier = new Soldado[10][10];
        Boolean play = true;
        while (play) {
            Ejercito army1 = new Ejercito();
            Ejercito army2 = new Ejercito();
            fillSoldier(tableSoldier, army1, army1.getNameArmy());
            fillSoldier(tableSoldier, army2, army2.getNameArmy());
            displayData(tableSoldier, army1, army2);
            System.out.println("Ingrese modificacion para que ejercito");
            int n = sc.nextInt();
            Ejercito elegido = (n == 1) ? army1 : army2;
            Boolean continuar = true;
            while (continuar) {
                System.out.println("-----------MENU---------");
                System.out.println("1. Agregar Soldado Manualmente");
                System.out.println("2. Agregar Soldado Autogenerado");
                System.out.println("3. Modificar Soldado");
                System.out.println("4. Eliminar Soldado");
                System.out.println("5. Soldado con Mayor Ataque");
                System.out.println("6. Ranking de Poder");
                System.out.println("7. Mostrar Información del Ejército");
                System.out.println("8. Salir");
                System.out.print("Seleccione una opción: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        elegido.createSoldier(tableSoldier);
                        displaySoldierTable(tableSoldier, army1, army2);
                        elegido.printDataSoldier();
                        break;
                    case 2:
                        elegido.autogenerateSoldier(tableSoldier);
                        displaySoldierTable(tableSoldier, army1, army2);
                        break;
                    case 3:
                        elegido.modifiedSoldier(tableSoldier);
                        displaySoldierTable(tableSoldier, army1, army2);
                        break;
                    case 4:
                        elegido.deleteSoldier(tableSoldier);
                        displaySoldierTable(tableSoldier, army1, army2);
                        break;
                    case 5:
                        elegido.mostLevelAttack();
                        displaySoldierTable(tableSoldier, army1, army2);
                        break;
                    case 6:
                        elegido.powerRanking();
                        displaySoldierTable(tableSoldier, army1, army2);
                        break;
                    case 7:
                        elegido.printDataSoldier();
                        break;
                    case 8:
                        continuar = false;
                        break;
                }
            }
            System.out.println("Deseas generar otros ejercitos(s/n)?");
            String ans = sc.next();
            if(ans.equalsIgnoreCase("n"))break;
        }
    }
    // juego
    public static void displayData(Soldado[][] table, Ejercito navy1, Ejercito navy2) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println(navy1 + ": El soldado con mayor vida es: \n" + mostlifeN(navy1));
        System.out.println(navy2 + ": El soldado con mayor vida es: \n" + mostlifeN(navy2));
        System.out.println("-----------------------------");
        System.out.println(navy1 + ": El promedio de nivel de vida de los soldados es: " + averageLifeN(navy1));
        System.out.println(navy2 + ": El promedio de nivel de vida de los soldados es: " + averageLifeN(navy2));
        System.out.println("-----------------------------");
        System.out.println(navy1 + ": Nivel de vida de todo el ejercito es: " + displayLifeN(navy1));
        System.out.println(navy2 + ": Nivel de vida de todo el ejercito es: " + displayLifeN(navy2));
        System.out.println("-----------------------------");
        System.out.println(navy1 + ": Datos de los soldados en orden de creación: ");
        displaySoldier(navy1);
        System.out.println(navy2 + ": Datos de los soldados en orden de creación: ");
        displaySoldier(navy2);
        System.out.println("---------Bubble sort--------");
        System.out.println(navy1);
        rankSoldierBubbleSort(navy1);
        System.out.println(navy2);
        rankSoldierBubbleSort(navy2);
        System.out.println("---------Selection sort--------");
        System.out.println(navy1);
        rankSoldierSelectionSort(navy1);
        System.out.println(navy2);
        rankSoldierSelectionSort(navy2);
        displaySoldierTable(table, navy1, navy2);
    }
    // Soldado
    /*
     * Cada soldado tendrá un nombre autogenerado: Soldado0X1, Soldado1X1...vida
     * aleatoria
     * [1..5], la fila y columnatambién (no puede haber 2 soldados en el mismo
     * cuadrado)
     */
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
                army.addSoldier(s);
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

    /*
     * un ranking de poder de los soldados creados mayor al menor (usar al
     * menos 2 algoritmos de ordenamiento).
     */
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
                    System.out.print("|" + j1.getNameArmy().charAt(0) + ":" + s.getVidaActual() + "|");
                } else if (s != null && j2.getSoldiers().contains(s)) {
                    System.out.print("|" + j2.getNameArmy().charAt(0) + ":" + s.getVidaActual() + "|");
                } else {
                    System.out.print("|___|");
                }
            }
            System.out.println();
        }
    }

    /* El juego consistirá en mover un soldado por cada turno de cada jugador. */
    public static void selectionSoldier(Soldado[][] table, Ejercito navy1, Ejercito navy2, int f, int c, int e1,
            int e2) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                Soldado s = table[i][j];
                if (s != null && s.getFila() == f && s.getColumna() == c) {
                    moveSoldier(s, table.length, table[i].length);
                    int fila = s.getFila();
                    int columna = s.getColumna();
                    if (navy1.getSoldiers().contains(s)) {
                        System.out.println("Hay otro soldado");
                    } else if (navy2.getSoldiers().contains(s)) {
                        System.out.println("----Hay una Batalla!!----");
                        Soldado enemy = navy2.getSoldiers().get(searchSoldier(navy2, f, c));
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
                            Soldado.setCountArmy(e2);
                        } else {
                            enemy.setVidaActual(enemy.getVidaActual() + 1);
                            System.out.println("EJERCITO " + e2 + " GANA");
                            navy1.getSoldiers().remove(s);
                            Soldado.setCountArmy(e1);
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

    /*
     * Se puede mover en cualquier dirección,dar coordenada y dirección dl soldado a
     * mover
     */
    public static void moveSoldier(Soldado s, int f, int c) {
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
}
