//Lab21-Mariel Jara
import java.util.*;
public class Videojuego18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printStatus();
            map.mostLife();
            map.averageLife();
            map.displaySoldier();
            map.rankSoldierBubbleSort();
            System.out.println();
            map.printMap();
            map.printData();
            System.out.println("\nDesea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
