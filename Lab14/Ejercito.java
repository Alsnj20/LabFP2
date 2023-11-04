package Lab14;
import java.util.*;
public class Ejercito {
    private ArrayList<Soldado> army = new ArrayList<>();
    private int row;
    private int column;
    public Ejercito() {
        this(0, 0);
    };
    public Ejercito(int f, int c) {
        setRow(f);
        setColumn(c);
    }    
    public void setRow(int f) {
        row = f;
    }
    public void setColumn(int c) {
        column = c;
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }
    public ArrayList<Soldado> getSoldiers() {
        return army;
    }
    public static int generateSoldier() {
        return (int) (Math.random() * Soldado.MAX_COUNT_SOLDIER + 1);
    }
}
