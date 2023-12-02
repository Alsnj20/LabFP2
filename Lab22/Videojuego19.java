//Lab22-Mariel Jara
import java.util.*;
public class Videojuego19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printStatus();
            map.printMap();
            map.playGame();
            System.out.println("\nDesea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
