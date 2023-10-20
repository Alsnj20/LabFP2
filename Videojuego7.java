//Lab10-MarielJara
import java.util.*;
public class Videojuego7 {
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
            while(true){
                System.out.println(cantidadS(navy1));
                System.out.println(cantidadS(navy2));
                System.out.println("EJERCITO 1:Ingrese la posicion del soldado a mover ");
                int f1 = sc.nextInt();
                int c1 = sc.nextInt();
                selectionSoldado(navy1, navy2, f1,c1, 1, 2);
                displaySoldierNavy(navy1, navy2);
                if(cantidadS(navy1) == 0 || cantidadS(navy2) == 0)break;
                System.out.println("EJERCITO 2:Ingrese la posicion del soldado a mover ");
                int f2 = sc.nextInt();
                int c2 = sc.nextInt();
                selectionSoldado(navy2, navy1, f2, c2, 2, 1);
                displaySoldierNavy(navy1, navy2);
                if(cantidadS(navy1) == 0 || cantidadS(navy2) == 0)break;  
            }
            System.out.println("El juego ha terminado");
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
        int n = 1;
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
    public static void selectionSoldado(Soldado[][] army1, Soldado[][] army2, int f, int c, int e1, int e2){
        for (int i = 0; i < army1.length; i++) {
            for (int j = 0; j < army1[i].length; j++) {
                if(army1[i][j] != null && army1[i][j].getFila() == f && army1[i][j].getColumna() == c ){
                    Soldado s = army1[i][j];
                    mover(s, army1.length, army1[i].length);
                    int fila = s.getFila();
                    int columna = s.getColumna();
                    if(hayotro(s, army1[fila][columna])){
                        System.out.println("Hay otro soldado");
                    }else if(hayotro(s, army2[fila][columna])){
                        System.out.println("----Hay una Batalla!!----");
                        int n = ganador(s, army2[fila][columna]);
                        if(n == 1){
                            System.out.println("EJERCITO "+e1+" GANA");
                            army1[i][j] = null;
                            army1[fila][columna] = s;
                            army2[fila][columna] = null;
                        }else{
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
    public static void mover(Soldado s, int f, int c) {
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
    public static boolean hayotro(Soldado s1, Soldado s2){
        return (s2 != null && s1.getFila() == s2.getFila() && s1.getColumna() == s2.getColumna());
    }
    /* Cuando un soldado se mueve a una posición donde hay un soldado rival, 
    se produce una batalla y gana el soldado que tenga mayor nivel de vida actual */
    public static int ganador(Soldado s1, Soldado s2){
        return (s1.getVidaActual() >= s2.getVidaActual())? 1: 2;
    }
    /* Gana el juego quien deje al otro ejército vacío */
    public static int cantidadS(Soldado[][] army){
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
}