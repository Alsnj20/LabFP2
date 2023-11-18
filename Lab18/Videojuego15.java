//Lab18-Mariel Jara
import java.util.*;
public class Videojuego15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printStatus();
            map.printMap();
            map.printData();
            System.out.println("\n---------METRICA--------");
            System.out.println("Ganador: Vida total de todos el ejercito");
            map.printWinner();
            System.out.println("--------------------------");
            System.out.println("\nDesea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
