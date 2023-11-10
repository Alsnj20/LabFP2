//Lab16-Mariel Jara
import java.util.*;
public class Videojuego13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean play = true;
        while(play){
            Mapa map = new Mapa();
            map.printMap();
            map.printStatus();
            System.out.println("-------1RA METRICA--------");
            System.out.println("Ganador: Cantidad de ejercitos");
            map.printWinner1();
            System.out.println("-------2DA METRICA--------");
            System.out.println("Ganador: Vida total");
            map.printWinner2();
            System.out.println("-------3RA METRICA--------");
            System.out.println("Ganador: Compate al azar");
            map.printWinner3();
            System.out.println("--------------------------");
            System.out.println("Desea generar otro combate?(s/n)");
            play = sc.next().equalsIgnoreCase("s");
        }
    }
}
