import java.util.*;
public class Ejercito {
    private String nameArmy;
    private ArrayList<Soldado> army  = new ArrayList<>();
    public Ejercito(String name, Soldado[][] t, boolean buffed){
        nameArmy = name;
        for(int i= 0; i<generateSoldier(); i++)
            autogenerateSoldier(t,buffed);
    };
    public ArrayList<Soldado> getArmy() {
        return army;
    }
    public String getNameArmy() {
        return nameArmy;
    }
    public String toString() {
        return "EJERCITO "+nameArmy.toUpperCase();
    }
    public int generateSoldier(){
        return (int)(Math.random()*(Soldado.MAX_COUNT_SOLDIER)+1);
    }
    public void autogenerateSoldier(Soldado[][] table, boolean buffed) {
        int row = 0, col = 0; String name;
        int life = (buffed) ? 1:0;
        do{
            row = (int) (Math.random() * 10);
            col = (int) (Math.random() * 10);
        }while(table[row][col] != null);
        int option = (int)(Math.random()*9+1);
        Soldado s = null;
        switch(option){
            case 1: name = "Espadachin"+row+"X"+col;
                    s = new Espadachin(name, row, col);
                    s.setVidaActual(s.getVidaActual()+life);
                    break;
            case 2: name = "Arquero"+row+"X"+col;
                    s = new Arquero(name, row, col);
                    break;
            case 3: name = "Caballero"+row+"X"+col;
                    s = new Caballero(name, row, col);
                    break;
            case 4: name = "Lancero"+row+"X"+col;
                    s = new Lancero(name, row, col); 
                    break;
            case 5: name = "ER"+row+"X"+col;
                    s = new EspadachinReal(name, row, col);
                    break;
            case 6: name = "CF"+row+"X"+col;
                    s = new CaballeroFranco(name, row, col);
                    break;
            case 7: name = "ET"+row+"X"+col;
                    s = new EspadachinTeutonico(name, row, col);
                    break;
            case 8: name = "EC"+row+"X"+col;
                    s = new EspadachinConquistador(name, row, col);
                    break;
            case 9: name = "CM"+row+"X"+col;
                    s = new CaballeroMoro(name, row, col);
                    break;
        }
        if ((option<5 && option>0) || ((option > 4 && option < 10) && isSpecialCaseInstance(getNameArmy(), s))) {
            s.setVidaActual(s.getVidaActual() + life);
            army.add(s);
            table[row][col] = s;

        }    
    }
    
    // Función para verificar si un soldado pertenece al mismo reino que el ejército
    public static boolean isSpecialCaseInstance(String name, Soldado s){
        if(name.equals("Inglaterra") && s instanceof EspadachinReal){return true;
        }else if(name.equals("Francia") && s instanceof CaballeroFranco){ return true;
        }else if(name.equals("Sacro Imperio Romano-Germánico") && s instanceof EspadachinTeutonico){return true;
        }else if(name.equals("Castilla Aragon") && s instanceof EspadachinConquistador){return true;
        }else if (name.equals("Moros") && s instanceof CaballeroMoro){return true;
        }else{return false;}   
    }
    public static Soldado moveSoldier(Soldado[][] table, int row, int col) {
        Scanner sc = new Scanner(System.in);
        System.out.println("EJERCITO: A que lado desea mover? (der/izq/arr/aba)");
        String dir = sc.next();
        System.out.println("Ingrese la cantidad de movimientos:");
        int n = sc.nextInt();
        Soldado w = table[row][col];
        switch (dir) {
            case "izq":
                int izq = col - n;
                if (izq < 0)
                    System.out.println("No hay espacio");
                else
                    w.setFila(row);
                w.setColumna(izq);
                break;
            case "der":
                int der = col + n;
                if (der > table.length)
                    System.out.println("No hay espacio");
                else
                    w.setFila(row);
                w.setColumna(der);
                break;
            case "arr":
                int arr = row - n;
                if (arr < 0)
                    System.out.println("No hay espacio");
                else
                    w.setFila(arr);
                w.setColumna(col);
                break;
            case "aba":
                int aba = row + n;
                if (aba > table.length)
                    System.out.println("No hay espacio");
                else
                    w.setFila(aba);
                w.setColumna(col);
                break;
        }
        return w;
    }
    public static double winnerArmy(Soldado s1, Soldado s2) {
        return (double) (s1.getVidaActual() * 100) / (s1.getVidaActual() + s2.getVidaActual());
    }
}