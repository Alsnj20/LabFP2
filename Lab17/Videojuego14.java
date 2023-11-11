//Lab16-Mariel Jara
import java.util.*;
public class Videojuego14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printMap();
            map.printStatus();
            map.playGame();
            System.out.println("Desea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
