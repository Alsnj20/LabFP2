//Lab13-MarielJara
import java.util.*;
public class Videojuego10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int f = 8; int c = 8;
        Soldado[][] navy1 = new Soldado[f][c];
        Soldado[][] navy2 = new Soldado[f][c];
        Boolean play = true;
        while (play) {
            System.out.println("----Menu de Opciones----");
            System.out.println("1.Juego rápido\n2.Juego personalizado\n3.Salir");
            System.out.println("Ingrese una opción");
            int option = sc.nextInt();
            switch (option){
                case 1: playGame(navy1, navy2);
                        break;
                case 2: fillSoldier(navy1, navy2, generateSoldier(), "A");
                        fillSoldier(navy2, navy1, generateSoldier(), "B");
                        customGame(navy1, navy2);
                        break;
                case 3: play = false;
                        break;      
            }
            sc.close();
        }
    }
    public static void playGame(Soldado[][] navy1, Soldado[][] navy2) {
        Scanner sc = new Scanner(System.in);
        String answer = "";
        while (true) {
            fillSoldier(navy1, navy2, generateSoldier(), "A");
            fillSoldier(navy2, navy1, generateSoldier(), "B");
            displayData(navy1, navy2);
            playAction(navy1, navy2);
            System.out.println("Desea generar otro batalla? (s/n)");
            answer = sc.next();
            if (answer.equalsIgnoreCase("n"))
                break;
        }
    }
    public static void displayData(Soldado[][] navy1, Soldado[][] navy2){
        Scanner sc = new Scanner(System.in);
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
        displaySoldierNavy(navy1, navy2);
        System.out.println("Cantidad de soldados totales: "+Soldado.getCountSoldierTotal());
        Soldado.printCountSoldier();
    }
    public static void playAction(Soldado[][] navy1, Soldado[][] navy2){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------Mostrando Tablero-----");
        displaySoldierNavy(navy1, navy2);
        System.out.println("Empezando la Batalla");
        while (true) {
            System.out.println("EJERCITO 1:Ingrese la posicion del soldado a mover ");
            int f1 = sc.nextInt();
            int c1 = sc.nextInt();
            selectionSoldier(navy1, navy2, f1, c1, 1, 2);
            displaySoldierNavy(navy1, navy2);
            Soldado.printCountSoldier();
            if (Soldado.getCountArmy1() < 1 || Soldado.getCountArmy2() < 1)break; 
            System.out.println("EJERCITO 2:Ingrese la posicion del soldado a mover ");
            int f2 = sc.nextInt();
            int c2 = sc.nextInt();
            selectionSoldier(navy2, navy1, f2, c2, 2, 1);
            displaySoldierNavy(navy1, navy2);
            Soldado.printCountSoldier();
            if (Soldado.getCountArmy1() < 1 || Soldado.getCountArmy2() < 1)break;  
        }
        System.out.println("La Batalla ha terminado"); 
    }
    public static void customGame(Soldado[][] army1, Soldado[][] army2) {
        Scanner sc = new Scanner(System.in);
        displayData(army1, army2);
        System.out.print("Ingrese el ejercito a gestionar(1/2):");
        int n = sc.nextInt();
        Soldado[][] elegido = (n==1)? army1: army2;
        boolean play = true;
        while (play) {
            System.out.println("1.Crear Soldados\n2.Eliminar Soldado\n3.Clonar Soldado\n4.Modificar Soldado");
            System.out.println("5.Comparar Soldado\n6.Intercambiar Soldados\n7.Ver Soldados\n8.Ver ejercito");
            System.out.println("9.Sumar Niveles\n10.Jugar\n11.Volver");
            int option2 = sc.nextInt();
            switch (option2) {
                case 1:createSoldier(elegido); displaySoldierNavy(army1, army2); break;
                case 2:deleteSoldier(elegido); displaySoldierNavy(army1, army2); break;
                case 3:cloneSoldier(elegido); displaySoldierNavy(army1, army2);break;
                case 4:modifiedSoldier(elegido); displaySoldierNavy(army1, army2);break;
                case 5:compareSoldier(elegido); break;
                case 6:changeSoldier(elegido); displaySoldierNavy(army1, army2);break;
                case 7:sortName(elegido); break;
                case 8:displaySoldier(elegido); break;
                case 9:addlevel(elegido);break;
                case 10: displayData(army1, army2); playAction(army1, army2); break;
                case 11:play = false; break;
            }
        }
    }
    /*Cada soldado tendrá un nombre autogenerado: Soldado0X1, Soldado1X1...vida aleatoria
     * [1..5], la fila y columnatambién (no puede haber 2 soldados en el mismo cuadrado)*/
    public static void fillSoldier(Soldado[][] army1, Soldado[][] army2, int n,String team) {
        int fila = army1.length;
        int columna = army1[0].length;
        int i = 0;
        while (i < n) {
            int lifeN = (int) (Math.random() * 5 + 1);
            int row = (int) (Math.random() * fila);
            int col = (int) (Math.random() * columna);
            int attack = (int) (Math.random() * 5 + 1);
            int defense = (int) (Math.random() * 5 + 1);
            if (army1[row][col] == null && army2[row][col] == null) {
                String name = "Soldado" + row + "X" + col;
                Soldado s = new Soldado(name, lifeN, attack, defense, row, col);
                army1[row][col] = s;
                i++;
                Soldado.setTeam(team);
            }
        }
    }
    /* Inicializar el tablero con n soldados aleatorios entre 1 y 10 */
    public static int generateSoldier() {
        return (int) (Math.random() * Soldado.MAX_COUNT_SOLDIER + 1);
    }
    /* Además de los datos del Soldado con mayor vida de cada ejército, */
    public static Soldado mostlifeN(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        Soldado tempo = new Soldado();
        for (int i = 0; i < sold.size(); i++) {
            if (tempo.getVidaActual() < sold.get(i).getVidaActual())
                tempo = sold.get(i);
        }
        return tempo;
    }
    /* el promedio de puntos de vida de todos los soldados creados por ejército */
    public static double averageLifeN(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        return displayLifeN(army) * 1.0 / sold.size();
    }
    /* El nivel de vida de todo el ejército */
    public static int displayLifeN(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        int lifeT = 0;
        for (Soldado s : sold) {
            lifeT += s.getVidaActual();
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
        printSort(sold);
    }
    /*un ranking de poder de los soldados creados mayor al menor (usar al
      menos 2 algoritmos de ordenamiento).*/
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
        printSort(sold);
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
        printSort(sold);
    }
    public static void printSort(ArrayList<Soldado> sold) {
        for (int i = 0; i < sold.size(); i++)
            System.out.println(sold.get(i));
    }
    /* Muestre qué ejército ganará la batalla suma de vidas. */
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
    /* Mostrar el tablero (usar caracteres como| _ y otros) y diferenciarlos. */
    public static void displaySoldierNavy(Soldado[][] army1, Soldado[][] army2) {
        for (int i = 0; i < army1[0].length; i++) {
            System.out.print("    " + i);
        }
        System.out.println();
        for (int i = 0; i < army1.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < army1[0].length; j++) {
                if (army1[i][j] != null) {
                    System.out.print("|A:" + army1[i][j].getVidaActual() + "|");
                } else if (army2[i][j] != null) {
                    System.out.print("|B:" + army2[i][j].getVidaActual() + "|");
                } else {
                    System.out.print("|___|");
                }
            }
            System.out.println();
        }
    }
    /* El juego consistirá en mover un soldado por cada turno de cada jugador. */
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
                        int n = (int)(Math.random()*(s.getVidaActual()+army2[fila][columna].getVidaActual()));
                        if(s.getVidaActual()>n){
                                System.out.println("Gana el Ejercito"+e1);
                                s.setVidaActual(s.getVidaActual()+1);
                                army1[i][j] = null;
                                army1[fila][columna] = s;
                                army2[fila][columna] = null;
                                Soldado.setCountArmy(e2);
                        }else{
                            army2[fila][columna].setVidaActual(army2[fila][columna].getVidaActual()+1);
                            System.out.println("EJERCITO "+e2+" GANA");
                            army1[i][j] = null;
                            Soldado.setCountArmy(e1);
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
    /* Se puede mover en cualquier dirección,dar coordenada y dirección dl soldado a
     * mover*/
    public static void move(Soldado s, int f, int c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("A que lado desea mover? (der/izq/arr/aba)");
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
    /* No puede haber 2 soldados del mismo ejército en el cuadrado */
    public static boolean hasAnother(Soldado s1, Soldado s2) {
        return (s2 != null && s1.getFila() == s2.getFila() && s1.getColumna() == s2.getColumna());
    }
    /* Gana el juego quien deje al otro ejército vacío */
    public static int countSoldiers(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        return sold.size();
    }
    /* Mostrar la probabilidad de cuanto tiene a ganar ejm: S1:62.5% S2:37.5% */
    public static double winner(Soldado s1, Soldado s2) {
        return (s1.getVidaActual() * 100) / (s1.getVidaActual() + s2.getVidaActual());
    }
    public static void createSoldier(Soldado[][] army) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Soldado> eje1 = bidToUni(army);
        if (eje1.size() < Soldado.MAX_COUNT_SOLDIER) {
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
            army[f][c] = s;
            eje1.add(s);
            printSort(eje1);
        } else {
            System.out.println("No hay espacio");
        }
    }
    public static void deleteSoldier(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        if (sold.size() < 2) {
            System.out.println("Error delete");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese las coordenadas del soldado a eliminar: ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            army[fila][columna] = null;
        }
    }
    public static void cloneSoldier(Soldado[][] army) {
        ArrayList<Soldado> eje1 = bidToUni(army);
        if (eje1.size() == Soldado.MAX_COUNT_SOLDIER) {
            System.out.println("No hay espacio");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese las coordenadas del soldado a clonar: ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            Soldado s1 = army[fila][columna];
            System.out.print("Ingrese las nuevas coordenadas del soldado clonado ");
            int f2 = sc.nextInt();
            int c2 = sc.nextInt();
            Soldado s2 = new Soldado("Soldado" + f2 + "X" + c2, s1.getVidaActual(), s1.getNiveldeDefensa(),
                    s1.getNiveldeAtaque(), f2, c2);
            army[f2][c2] = s2;
        }
    }
    public static void modifiedSoldier(Soldado[][] army) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado a modificar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        System.out.println(army[fila][columna]);
        System.out.println("Ingrese modificacion\n1.NivelAtaque\n2.NivelDefensa\n3.VidaActual");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.print("Ingrese el nuevo nivel de Ataque: ");
                int n = sc.nextInt();
                army[fila][columna].setNiveldeAtaque(n);
                break;
            case 2:
                System.out.print("Ingrese el nuevo nivel de Defensa: ");
                n = sc.nextInt();
                army[fila][columna].setNiveldeDefensa(n);
                break;
            case 3:
                System.out.print("Ingrese el nuevo nivel de VidaActual: ");
                n = sc.nextInt();
                army[fila][columna].setVidaActual(n);
                break;
        }
    }
    public static void compareSoldier(Soldado[][] army) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado1 a comparar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        System.out.print("Ingrese las coordenadas del soldado2 a comparar: ");
        int fila2 = sc.nextInt();
        int columna2 = sc.nextInt();
        if (army[fila][columna].compareS(army[fila2][columna2])) {
            System.out.println("Son iguales");
        } else {
            System.out.println("No son iguales");
        }
    }
    public static void changeSoldier(Soldado[][] army) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado1 a intercambiar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        System.out.print("Ingrese las coordenadas del soldado2 a intercambiar: ");
        int fila2 = sc.nextInt();
        int columna2 = sc.nextInt();
        if(army[fila][columna] != null && army[fila2][columna2] != null){
            Soldado tempo = army[fila][columna];
            army[fila][columna] = army[fila2][columna2];
            army[fila2][columna2] = tempo;
        }else{System.out.println("Coordenadas invalidas");}   
    }
    public static void sortName(Soldado[][] army) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el nombre a buscar: ");
        String name = sc.next();
        boolean found = false;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null && army[i][j].getName().equalsIgnoreCase(name)){ 
                    System.out.println(army[i][j]);
                } found = true;
            }
        }
        if(!found) System.out.println("name not found");
    }
    public static void addlevel(Soldado[][] army) {
        ArrayList<Soldado> list = bidToUni(army);
        Soldado sBig = new Soldado(0, 0, 0, 0);
        for (int i = 0; i < list.size(); i++) {
            sBig = sBig.addTotal(list.get(i));
        }
        sBig.setName("SoldierBig");
        System.out.println("Se ha unido todo el ejercito!!!");
        sBig.data();
    }
}
