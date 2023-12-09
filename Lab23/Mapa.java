import java.awt.*;
import java.util.*;
import javax.swing.*;
public class Mapa {
    private Graphic game = new Graphic();
    private final Soldado[][] t = new Soldado[10][10];
    private final String[][] KINGDOMS = {
            { "Inglaterra", "bosque" },
            { "Francia", "campo abierto" },
            { "Castilla-Aragon", "montaña" },
            { "Moros", "desierto" },
            { "Sacro Imperio Romano-Germánico", "bosque", "playa", "campo abierto" }
    };
    private final String[] MAP_TYPES = { "bosque", "campo abierto", "montaña", "desierto",
            "playa" };
    private String type;
    private Ejercito army1;
    private Ejercito army2;
    private int kingdomName1Id;
    private int kingdomName2Id;
    public Mapa() {
        type = MAP_TYPES[(int) (Math.random() * MAP_TYPES.length)];
        kingdomName1Id = (int) (Math.random() * KINGDOMS.length);
        kingdomName2Id = (int) (Math.random() * KINGDOMS.length);

        boolean buffed1 = isBuffed(kingdomName1Id);
        army1 = new Ejercito(game, getKingdomName1(), t, buffed1);

        boolean buffed2 = isBuffed(kingdomName2Id);
        army2 = new Ejercito(game, getKingdomName2(), t, buffed2);
    }
    public String getType() {return type;}
    public String getKingdomName1() {return KINGDOMS[(kingdomName1Id)][0];}
    public String getKingdomName2() {return KINGDOMS[(kingdomName2Id)][0];}
    public Graphic getGraphic(){return game;}
    public boolean isBuffed(int kingdomNameId) {
        for (int i = 0; i < KINGDOMS[kingdomNameId].length; i++) {
            if (type.equals(KINGDOMS[kingdomNameId][i]))
                return true;
        }
        return false;
    }
    public void printStatus() {
        System.out.println("Reino "+getKingdomName1()+" vs Reino "+getKingdomName2());
        System.out.println("El terreno es: " + type);
        if (isBuffed(kingdomName1Id))
            System.out.println(getKingdomName1() + " es beneficiado");
        if (isBuffed(kingdomName2Id))
            System.out.println(getKingdomName2() + " es beneficiado");
        displayCantTypeSoldado(army1, 1, getKingdomName1());
        displayCantTypeSoldado(army2, 2, getKingdomName2());
    }
    public void printMap() {
        JButton btn = new JButton("");
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] != null) {
                    Soldado army = t[i][j];
                    String w = ""; Color c = null;
                    if (army2.getArmy().contains(army)){
                        w = getKingdomName2().substring(0, 1); 
                        c = new Color(255, 0, 0);
                    }else{
                        w = getKingdomName1().substring(0, 1);
                        c = new Color(0, 255, 0);
                    }
                    btn = new JButton(w+":"+army.getName().substring(0, 2)+"-"+army.getVidaActual());
                    btn.setBackground(c);
                    btn.setSize(100, 60);
                    btn.setFont(new Font("Cascadia Code",Font.BOLD, 14));
                } else {
                    btn = new JButton("              ");
                    btn.setBackground(Color.WHITE);
                }
                game.addButton(btn, i, j);
            }
        }
    }
    public void handleBattle(Ejercito army1, Ejercito army2, int f, int c) {
        ArrayList<Soldado> k1 = army1.getArmy();
        ArrayList<Soldado> k2 = army2.getArmy();
        Soldado s1 = Ejercito.moveSoldier(t, f, c);
        int row = s1.getFila();
        int col = s1.getColumna();
        Soldado s2 = t[row][col];
        if (t[row][col] != null) {
            if (k1.contains(s2)) {
                System.out.println("Hay otro ejército del mismo reino");
            } else if (k2.contains(s2)) {
                System.out.println("HAY UNA BATALLA");
                isSpecialBattle(s1, s2);
                double w1 = Ejercito.winnerArmy(s1, s2);
                double w2 = Ejercito.winnerArmy(s2, s1);
                System.out.println("\nSOLDADO " + army1.getNameArmy() + " => " + w1 + "% de probabilidad de victoria");
                System.out.println("SOLDADO " + army2.getNameArmy() + " => " + w2 + "% de probabilidad de victoria");
                double luck = (Math.random() * 100);
                String winner = (luck >= w1) ? army1.getNameArmy() : army2.getNameArmy();
                System.out.println("\nGana el Ejercito " + winner + " Ya que al generar los");
                if (luck > w1) {
                    System.out.println("porcentajes de probabilidad de victoria basada en los niveles de");
                    System.out.println("vida de sus soldados y aplicando un experimento aleatorio salió vencedor.");
                    System.out.println("(Aleatorio generado: " + luck + ")");
                    s1.setVidaActual(s1.getVidaActual() + 1);
                    t[f][c] = null;
                    t[row][col] = s1;
                    k2.remove(s2);
                } else {
                    s2.setVidaActual(s2.getVidaActual() + 1);
                    t[f][c] = null;
                    k1.remove(s1);
                }
            }
        } else {
            t[f][c] = null;
            t[row][col] = s1;
        }
    }
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        int turno = 1;
        while (army1.getArmy().size() > 0 && army2.getArmy().size() > 0) {
            String w = (turno == 1) ? getKingdomName1() : getKingdomName2();
            game.addContentTable(w, "Ejercito", "ejercito");
            handleBattle((turno == 1) ? army1 : army2, (turno != 1) ? army1 : army2, game.getFila(), game.getColumna());
            //printMap();
            turno = (turno == 1) ? 2 : 1;
        }
        System.out.println("La batalla ha terminado");
    }
    public void isSpecialBattle(Soldado s1, Soldado s2) {
        if (s2 instanceof Arquero) {
            if (s1 instanceof Caballero)
                s1.setVidaActual(s1.getVidaActual() + 1);
            if (s1 instanceof CaballeroMoro || s1 instanceof CaballeroFranco)
                s1.setVidaActual(s1.getVidaActual() + 2);
        } else if (s1 instanceof Arquero && s2 instanceof Lancero) {
            s1.setVidaActual(s1.getVidaActual() + 1);
        } else if (s2 instanceof Lancero) {
            if (s1 instanceof Espadachin)
                s1.setVidaActual(s1.getVidaActual() + 1);
            if (s1 instanceof EspadachinConquistador || s1 instanceof EspadachinReal
                    || s1 instanceof EspadachinTeutonico)
                s1.setVidaActual(s1.getVidaActual() + 2);
        } else if (s1 instanceof Espadachin) {
            if (s2 instanceof EspadachinConquistador || s2 instanceof EspadachinReal
                    || s2 instanceof EspadachinTeutonico)
                s2.setVidaActual(s2.getVidaActual() + 1);
        } else {
            if (s1 instanceof Caballero) {
                if (s2 instanceof CaballeroMoro || s2 instanceof CaballeroFranco)
                    s2.setVidaActual(s2.getVidaActual() + 1);
            }
        }
        System.out.println("Batalla Especial: Modificando el tablero");
        printMap();
    }
    private void displayCantTypeSoldado(Ejercito army,int n, String kingdomName) {
        int esp = 0;
        int arq = 0;
        int cab = 0;
        int lan = 0;
        int espReal = 0;
        int espTeu = 0;
        int espConq = 0;
        int cabMoro = 0;
        int cabFran = 0;
        for (Soldado s : army.getArmy()) {
            if (s instanceof Espadachin) {
                esp++;
                if (s instanceof EspadachinReal) espReal++;
                if (s instanceof EspadachinTeutonico) espTeu++;
                if (s instanceof EspadachinConquistador) espConq++;
            }else if(s instanceof Caballero){
                cab++;
                if (s instanceof CaballeroMoro) cabMoro++;
                if (s instanceof CaballeroFranco) cabFran++;     
            } else if (s instanceof Arquero) {
                arq++;
            }else if (s instanceof Lancero) {
                lan++;
            }
        }
        esp -=(espReal+espTeu+espConq); cab -=(cabMoro+cabFran);
        System.out.println("\nEjercito "+n+": "+kingdomName);
        System.out.println("Cantidad total de soldados: " + army.getArmy().size());
        System.out.println("Espadachines: "+esp);
        System.out.println("Arqueros: "+arq);
        System.out.println("Caballeros: "+cab);
        System.out.println("Lanceros: "+lan);
        if(espReal > 0) System.out.println("Espadachín Real: "+espReal);
        if(espTeu > 0) System.out.println("Espadachín Teutónico: "+espTeu);
        if(espConq > 0) System.out.println("Espadachín Conquistador: "+espConq);
        if(cabMoro > 0) System.out.println("Caballero Moro: "+cabMoro);
        if(cabFran > 0) System.out.println("Caballero Franco: "+cabFran);
    }
}
