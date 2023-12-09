//Lab23-Mariel Jara
import java.util.*;
public class Videojuego20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            Graphic t = map.getGraphic();
            map.printMap();
            t.print();
            map.playGame();
            System.out.println("\nDesea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
