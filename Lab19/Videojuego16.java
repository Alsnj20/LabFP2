//Lab19-Mariel Jara
import java.util.*;
public class Videojuego16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printStatus();
            map.printMap();
            map.playGame();
            System.out.println("Desea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
