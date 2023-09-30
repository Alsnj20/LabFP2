import java.util.*;
public class Videojuego2 {
    public static void main(String[] args) {
        Soldado[][] navy = new Soldado[4][5];
        fillSoldier(navy);
        displaySoldierNavy(navy);
        System.out.println("-----------------------------");
        System.out.println("El soldado con mayor vida es: \n"+mostlifeN(navy));
        System.out.println("-----------------------------");
        System.out.println("El promedio de nivel de vida de los soldados es: "+averageLifeN(navy));
        System.out.println("-----------------------------");
        System.out.println("Nivel de vida de todo el ejercito es: "+displayLifeN(navy));
        System.out.println("-----------------------------");
        System.out.println("Datos de los soldados en orden de creación: ");
        displaySoldier(navy);
        System.out.println("---------Bubble sort--------");
        rankSoldierBubbleSort(navy);
        System.out.println("---------Selection sort--------");
        rankSoldierSelectionSort(navy);
        
    }
    /* Cada soldado tendrá un nombre autogenerado: Soldado0,etc,un valor de nivel de vida autogenerado 
    aleatoriamente [1..5], la fila y columna también autogenerados aleatoriamente (verificar que 
    no puede haber 2 soldados en el mismo cuadrado) */
    public static void fillSoldier(Soldado[][] army){
        int fila = army.length;
        int columna = army[0].length;
        int n= generateSoldier();
        int i=0;
        System.out.println(n);
        while(i<n){
            int lifeN = (int)(Math.random()*5+1);
            int row = (int)(Math.random()*fila);
            int col = (int)(Math.random()*columna);
            if(army[row][col] == null){
                String name = "Soldado"+i;
                army[row][col] = new Soldado(name, lifeN, row, columna);
                i++;   
            }
        }
    }
    /*Inicializar el tablero con n soldados aleatorios entre 1 y 10 */
    public static int generateSoldier(){
        return (int)(Math.random()*10+1);
    }
    /*, mostrar los datos del Soldado con mayor nivel de vida */
    public static Soldado mostlifeN(Soldado[][] army){
        Soldado tempo = new Soldado("", 0, 0, 0);
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if(army[i][j] !=null && tempo.getLifeN()<army[i][j].getLifeN()){
                    tempo = army[i][j];
                }
            }
        }
        return tempo;
    }
    /*El promedio de nivel de vida de todos los soldados creados */
    public static double averageLifeN(Soldado[][] army){
        int suma = 0, total = 0;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if(army[i][j] !=null){
                suma +=army[i][j].getLifeN();
                total++;
                }
            }
        }
        return (suma*1.0)/total;
    }
    /*El nivel de vida de todo el ejército */
    public static int displayLifeN(Soldado[][] army){
        int lifeT = 0;
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[i].length; j++) {
                if(army[i][j] !=null){
                lifeT +=army[i][j].getLifeN();
                }
            }
        }   
        return lifeT;
    }
    /*los datos de todos los soldados en el orden que fueron creados */
    public static void displaySoldier(Soldado[][] army){
        ArrayList<Soldado> sold = bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            for (int j = 0; j < sold.size() - 1 - i; j++) {
                String w1 = sold.get(j).getName();
                String w2 = sold.get(j+1).getName();
                if(w1.compareTo(w2)>0){
                Soldado tempo = sold.get(j);
                sold.set(j,sold.get(j+1));
                sold.set(j+1, tempo);
                }
            }
        }
        for (int i = 0; i < sold.size(); i++) {
            System.out.println(sold.get(i)); 
        }
    }
    /*Se debe mostrar el tablero con todos los soldados creados (usar caracteres como | _ y otros */
    public static void displaySoldierNavy(Soldado[][] army){
        for (int i = 0; i < army[0].length; i++) {
            System.out.print("     "+i);
        }
        System.out.println();
        for (int i = 0; i < army.length; i++) {
            System.out.print(i+" ");
            for (int j = 0; j < army[i].length; j++) {
                if (army[i][j] != null) {
                    String word= army[i][j].getName();
                    System.out.print("|S"+word.charAt(word.length()-1)+":"+army[i][j].getLifeN()+"|");
                } else {
                    System.out.print("|____|");
                }
            }
            System.out.println();
        }
    }
    /*un ranking de poder de todos los soldados creados, mayor al menor (usar al menos 2 algoritmos de ordenamiento). */
    public static void rankSoldierBubbleSort(Soldado[][] army) {
        ArrayList<Soldado> sold = bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            for (int j = 0; j < sold.size()-1-i; j++) {  
                if (sold.get(j).getLifeN() < sold.get(j+1).getLifeN()) {
                    Soldado tempo = sold.get(j);
                    sold.set(j,sold.get(j+1));
                    sold.set(j+1, tempo);
                }
            }
        }
        for(int i=0; i<sold.size(); i++){
            System.out.println(sold.get(i));
        }
    }
    public static ArrayList<Soldado> bidToUni(Soldado[][] army){
        ArrayList<Soldado> sold = new ArrayList<>();
        for (int i = 0; i < army.length; i++) {
            for (int j = 0; j < army[0].length; j++) {
                if(army[i][j] != null){
                    sold.add(army[i][j]);
                }
            }
        }
        return sold;
    }
    public static void rankSoldierSelectionSort(Soldado[][] army){
        ArrayList<Soldado> sold= bidToUni(army);
        for (int i = 0; i < sold.size(); i++) {
            int idx = i;
            for (int j = i+1; j < sold.size(); j++) {
                if(sold.get(j).getLifeN() > sold.get(idx).getLifeN()){
                    idx = j;
                }
            }
            Soldado tempo = sold.get(i);
            sold.set(i, sold.get(idx));
            sold.set(idx, tempo);
            
        }
        for(int i = 0; i<sold.size(); i++){
            System.out.println(sold.get(i));
        }
    }
}
