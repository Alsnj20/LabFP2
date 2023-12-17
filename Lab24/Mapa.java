import java.awt.Color;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Mapa {
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
    private String kingdomName1Id;
    private String kingdomName2Id;

    public Mapa() {}

    public void startPlay(String king1, String king2, String typ) {
        kingdomName1Id = king1;
        kingdomName2Id = king2;
        type = typ;
        boolean buffed1 = isBuffed(kingdomName1Id);
        army1 = new Ejercito(getKingdomName1(), t, buffed1);

        boolean buffed2 = isBuffed(kingdomName2Id);
        army2 = new Ejercito(getKingdomName2(), t, buffed2);
    }

    public String[][] getKINGDOMS() {
        return KINGDOMS;
    }

    public String[] getTypesMap() {
        return MAP_TYPES;
    }

    public String getType() {
        return type;
    }

    public String getKingdomName1() {
        return kingdomName1Id;
    }

    public String getKingdomName2() {
        return kingdomName2Id;
    }
    public void setKingdom(String t, String t2){
        kingdomName1Id = t;
        kingdomName2Id = t2;
    }

    public Ejercito getArmy1() {
        return army1;
    }

    public Ejercito getArmy2() {
        return army2;
    }

    public boolean isBuffed(String kingdomNameId) {
        /*
         * for (int i = 0; i < KINGDOMS[kingdomNameId].length; i++) {
         * if (type.equals(KINGDOMS[kingdomNameId][i]))
         * return true;
         * }
         */
        return false;
    }

    public String printStatus() {
        String text = "Reino " + getKingdomName1() + "\nVS\nReino " + getKingdomName2() +
                "\nEl terreno es: " + type;
        if (isBuffed(kingdomName1Id))
            text += "\n" + getKingdomName1() + " es beneficiado";
        if (isBuffed(kingdomName2Id))
            text += "\n" + getKingdomName2() + " es beneficiado";
        return text;
    }

    public String[][] printMap() {
        String[][] tag = new String[10][10];
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                if (t[i][j] != null) {
                    Soldado army = t[i][j];
                    String w = "";
                    if (army2.getArmy().contains(army)) {
                        w = getKingdomName2().substring(0, 1);
                    } else {
                        w = getKingdomName1().substring(0, 1);
                    }
                    tag[i][j] = w + ":" + army.getName().substring(0, 2) + "-" + army.getVidaActual();
                } else {
                    tag[i][j] = "              ";
                }
            }
        }
        return tag;
    }

    public void findBtn(String army, String armyt, String text, JButton btn, JButton btn2) {
        Soldado s1 = null;
        Soldado s2 = null;
        String[][] txt = printMap();
        for (int i = 0; i < txt.length; i++) {
            for (int j = 0; j < txt.length; j++) {
                if (txt[i][j].equals(btn.getText())) {
                    s1 = t[i][j];
                }
                if (txt[i][j].equals(btn2.getText())) {
                    s2 = t[i][j];
                }
            }
        }
        double w1 = Ejercito.winnerArmy(s1, s2);
        double w2 = Ejercito.winnerArmy(s2, s1);
        text += "\nSOLDADO " + army1.getNameArmy() + " => " + w1 + 
                        "% de probabilidad de victoria\nSOLDADO " + army2.getNameArmy() + " => " + w2 + 
                        "% de probabilidad de victoria";
        String winner = (w1>=w2) ? army:armyt;
        text += "\n\nGana el Ejercito " + winner + 
                "\nYa que al generar los porcentajes de\nprobabilidad de victoria basada en los niveles de\n"+
                "vida de sus soldados y aplicando un \nexperimento del que tiene mayor \nnivel de vida salió vencedor.";
        String w = army.substring(0, 1);
        String txtt = ""; 
        Color c1 = btn.getBackground();
        Color c2 = btn2.getBackground();
        if(s1.getVidaActual()>s2.getVidaActual()){
            s1.setVidaActual(s1.getVidaActual()+1);
            txtt= w + ":" + s1.getName().substring(0, 2) + "-" + s1.getVidaActual();
            btn2.setText(txtt);
            btn2.setBackground(c1);
            btn.setText("");
            btn.setBackground(Color.WHITE);
            
        }else{
            s2.setVidaActual(s2.getVidaActual()+1);
            txtt= w + ":" + s2.getName().substring(0, 2) + "-" + s2.getVidaActual();
            btn2.setText(txtt);
            btn.setText("");
            btn.setBackground(Color.WHITE);
        }
    }
    public String displayCantTypeSoldado(Ejercito army, String kingdomName) {
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
                if (s instanceof EspadachinReal)
                    espReal++;
                if (s instanceof EspadachinTeutonico)
                    espTeu++;
                if (s instanceof EspadachinConquistador)
                    espConq++;
            } else if (s instanceof Caballero) {
                cab++;
                if (s instanceof CaballeroMoro)
                    cabMoro++;
                if (s instanceof CaballeroFranco)
                    cabFran++;
            } else if (s instanceof Arquero) {
                arq++;
            } else if (s instanceof Lancero) {
                lan++;
            }
        }
        esp -= (espReal + espTeu + espConq);
        cab -= (cabMoro + cabFran);
        String data = "\nEjercito: " + kingdomName +
                "\nCantidad de soldados: " + army.getArmy().size() +
                "\nEspadachines: " + esp +
                "\nArqueros: " + arq +
                "\nCaballeros: " + cab +
                "\nLanceros: " + lan +
                ((espReal > 0) ? "\nEspadachín Real: " + espReal : "") +
                ((espTeu > 0) ? "\nEspadachín Teutónico: " + espTeu : "") +
                ((espConq > 0) ? "\nEspadachín Conquistador: " + espConq : "") +
                ((cabMoro > 0) ? "\nCaballero Moro: " + cabMoro : "") +
                ((cabFran > 0) ? "\nCaballero Franco: " + cabFran : "");
        return data;
    }
    public void openFileTextMostLife(){
            String nameFile = JOptionPane.showInputDialog("Enter file name:");
            ObjectOutputStream fileOut;
        try {  
            fileOut = new ObjectOutputStream(new FileOutputStream(nameFile, true));
            fileOut.writeChars(getKingdomName1()+": El soldado con mayor nivel de vida es:");
            fileOut.writeObject("\n"+army1.mostLife());
            fileOut.writeChars("\n"+getKingdomName2()+": El soldado con mayor nivel de vida es:");
            fileOut.writeObject("\n"+army2.mostLife());
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Save File");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage());
        }
    }
    public void openFileListSoldier(){
            String nameFile = JOptionPane.showInputDialog("Enter file name:");
            ObjectOutputStream fileOut;
        try {  
            fileOut = new ObjectOutputStream(new FileOutputStream(nameFile,true));
            fileOut.writeChars("LISTA DE SOLDADOS\n");
            fileOut.writeChars(getKingdomName1()+":\n");
            for (int i = 0; i < army1.getArmy().size(); i++) {
                fileOut.writeObject(army1.getArmy().get(i)+"\n");
            }
            fileOut.writeChars(getKingdomName2()+":\n");
            for (int i = 0; i < army2.getArmy().size(); i++) {
                fileOut.writeObject(army2.getArmy().get(i)+"\n");
            }
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Save File");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage());
        }
    }
    public void openFileRankSoldier(){
        String nameFile = JOptionPane.showInputDialog("Enter file name:");
            ObjectOutputStream fileOut;
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream(nameFile,true));
            fileOut.writeChars("RANKING DE SOLDADOS del mayor al menor nivel de vida\n");
            fileOut.writeChars(getKingdomName1()+":\n");
            army1.rankSoldierBubbleSort();
            for (int i = 0; i < army1.getArmy().size(); i++) {
                fileOut.writeObject(army1.getArmy().get(i)+"\n");
            }
            fileOut.writeChars(getKingdomName2()+":\n");
            army2.rankSoldierBubbleSort();
            for (int i = 0; i < army2.getArmy().size(); i++) {
                fileOut.writeObject(army2.getArmy().get(i)+"\n");
            }
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Save File");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage());
        }
    }
}
