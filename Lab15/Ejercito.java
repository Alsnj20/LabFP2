package Lab15;
import java.util.ArrayList;
import java.util.Scanner;
public class Ejercito {
    private String nameArmy;
    private ArrayList<Soldado> army = new ArrayList<>();
    public Ejercito() {
        nameArmy = generateKingdomName();

    };
    public ArrayList<Soldado> getSoldiers() {
        return army;
    }
    public String getNameArmy() {
        return nameArmy;
    }
    public void addSoldier(Soldado s) {
        if (army.size() >= Soldado.MAX_COUNT_SOLDIER)
            System.out.println("Has Alcanzado el limite");
        else
            army.add(s);s.setTeam(nameArmy);
            
    }
    public static int generateSoldier() {
        return (int) (Math.random() * Soldado.MAX_COUNT_SOLDIER + 1);
    }

    public String generateKingdomName() {
        String[] nameReino = { "Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros" };
        int n = (int) (Math.random() * nameReino.length - 1);
        return nameReino[n];
    }

    public String toString() {
        return "EJERCITO "+nameArmy.toUpperCase();
    }

    public void printDataSoldier() {
        System.out.println(this);
        System.out.println("--------Lista de Soldados------");
        for (Soldado s : this.getSoldiers()) {
            System.out.println(s);
        }
    }

    public void createSoldier(Soldado[][] t) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese los datos del soldado a crear");
        System.out.print("Ingrese fila y columna:");
        int f = sc.nextInt();
        int c = sc.nextInt();
        System.out.print("Ingrese el nombre:");
        String name = sc.next();
        System.out.print("Ingrese el nivel de vida, nivel de defensa, nivel de ataque: ");
        int nV = sc.nextInt();
        int nD = sc.nextInt();
        int nA = sc.nextInt();
        Soldado s = new Soldado(name, nV, nD, nA, f, c);
        System.out.println(s);
        this.addSoldier(s);
        t[f][c] = s;
    }

    public void autogenerateSoldier(Soldado[][] t) {
        int life = (int) (Math.random() * 5 + 1);
        int row = (int) (Math.random() * 10);
        int col = (int) (Math.random() * 10);
        int attack = (int) (Math.random() * 5 + 1);
        int defense = (int) (Math.random() * 5 + 1);
        String name = "Soldado" + row + "X" + col;
        Soldado s = new Soldado(name, life, defense, attack, row, col);
        if (!this.getSoldiers().contains(s)) {
            this.addSoldier(s);
            t[row][col] = s;
        } else {
            System.out.println("Ya existe otro soldado con estos datos");
        }
    }

    public void deleteSoldier(Soldado[][] t) {
        if (this.getSoldiers().size() < 2) {
            System.out.println("Error delete");
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Ingrese las coordenadas del soldado a eliminar: ");
            int fila = sc.nextInt();
            int columna = sc.nextInt();
            int n = searchSoldier(this, fila, columna);
            this.getSoldiers().remove(n);
            t[fila][columna] = null;
        }
    }
    public int searchSoldier(Ejercito eje, int f, int c) {
        for (int i = 0; i < eje.getSoldiers().size(); i++) {
            Soldado s = eje.getSoldiers().get(i);
            if (s.getFila() == f && s.getColumna() == c)
                return i;
        }
        return -1;
    }

    public void modifiedSoldier(Soldado[][] t) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese las coordenadas del soldado a modificar: ");
        int fila = sc.nextInt();
        int columna = sc.nextInt();
        Soldado s = this.getSoldiers().get(searchSoldier(this, fila, columna));
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
        t[fila][columna] = s;
    }

    public void mostLevelAttack() {
        Soldado s = new Soldado();
        for (int i = 0; i < getSoldiers().size(); i++) {
            if (s.getNiveldeAtaque() < getSoldiers().get(i).getNiveldeAtaque())
                s = getSoldiers().get(i);
        }
        System.out.println("Mayor Nivel de Ataque del " + this.getNameArmy());
        System.out.println(s);
    }

    public void powerRanking() {
        for (int i = 0; i < getSoldiers().size(); i++) {
            for (int j = 0; j < getSoldiers().size() - 1 - i; j++) {
                if (getSoldiers().get(j).getVidaActual() < getSoldiers().get(j + 1).getVidaActual()) {
                    Soldado tempo = getSoldiers().get(j);
                    getSoldiers().set(j, getSoldiers().get(j + 1));
                    getSoldiers().set(j + 1, tempo);
                }
            }
        }
        printDataSoldier();
    }
}
