import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArmyBoard extends JFrame {
    private JButton[][] armies = new JButton[10][10];
    private JButton selectBtn = null;
    private JPanel box2;
    private Mapa map = new Mapa();
    private JButton btn1, btn2, btn3;
    public ArmyBoard(Mapa mapa) {
        map = mapa;
        setSize(1010, 600);
        setTitle("Videogame_V");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createContents();
        setVisible(true);
    }
    public static Color setColor(String mapaName) {
        if (mapaName.equals("bosque"))return Color.GREEN;   
        if (mapaName.equals("campo abierto"))return Color.WHITE;   
        if (mapaName.equals("montaña"))return Color.GRAY;   
        if (mapaName.equals("desierto"))return Color.ORANGE;
        if (mapaName.equals("playa"))return Color.CYAN;
            
        return Color.WHITE;
    }
    public void setColorBtn(JButton btn) {
        String c = btn.getText().substring(0, 1);
        if (c.equals("I"))btn.setBackground(Color.RED);  
        if (c.equals("F"))btn.setBackground(Color.BLUE);   
        if (c.equals("C"))btn.setBackground(Color.PINK);  
        if (c.equals("M"))btn.setBackground(Color.MAGENTA);  
        if (c.equals("S"))btn.setBackground(Color.ORANGE);   
        if (c.equals(" "))btn.setBackground(Color.WHITE);       
    }
    public void addBtns(String[][] t) {
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[0].length; j++) {
                armies[i][j] = new JButton(t[i][j]);
                setColorBtn(armies[i][j]);
            }
        }
    }
    public void createContents() {
        JPanel box1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextArea status = new JTextArea(map.printStatus());
        JTextArea cant1 = new JTextArea(map.displayCantTypeSoldado(map.getArmy1(), map.getKingdomName1()));
        JTextArea cant2 = new JTextArea(map.displayCantTypeSoldado(map.getArmy2(), map.getKingdomName2()));
        JPanel box11 = new JPanel(new BorderLayout());
        JLabel down = new JLabel("DESCARGAR");
        btn1 = new JButton("Lista de Soldados");
        btn2 = new JButton("Soldado con mas vida");
        btn3 = new JButton("Ranking de Soldados");
        box1.add(status);
        box1.add(cant1);
        box1.add(cant2);
        box1.add(down);
        box11.add(btn1,BorderLayout.NORTH);box11.add(btn2, BorderLayout.CENTER); box11.add(btn3, BorderLayout.SOUTH);
        box1.add(box11);
        add(box1, BorderLayout.NORTH);
        btn1.addActionListener(new OpenFile());
        btn2.addActionListener(new OpenFile());
        btn3.addActionListener(new OpenFile());
        box2 = new JPanel(new GridLayout(11, 11));
        box2.setBackground(setColor(map.getType()));
        addBtns(map.printMap());
        for (int i = 0; i < 11; i++) {
            JLabel label = new JLabel(i + "", SwingConstants.CENTER);
            box2.add(label);
        }
        for (int i = 0; i < 10; i++) {
            JLabel rowLabel = new JLabel(i + 1 + "", SwingConstants.CENTER);
            box2.add(rowLabel);
            for (int j = 0; j < 10; j++) {
                armies[i][j].addActionListener(new ListenerBtn());
                box2.add(armies[i][j]);
            }
        }
        add(box2, BorderLayout.CENTER);
        setVisible(true);
    }
    private class OpenFile implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JButton btn = (JButton)e.getSource();
            if(btn.equals(btn1)) map.openFileListSoldier();
            if(btn.equals(btn2)) map.openFileTextMostLife(); 
            if(btn.equals(btn3)) map.openFileRankSoldier();
        }
    }
    private class ListenerBtn implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton clickedBtn = (JButton) e.getSource();
            if (selectBtn == null) {
                selectBtn = clickedBtn;
            } else {
                printRule(map.getKingdomName1(), map.getKingdomName2(), clickedBtn, selectBtn);
                selectBtn = null;
                String king = map.getKingdomName1();
                String king2 = map.getKingdomName2();
                map.setKingdom(king2, king);  
            }
        }
    }
    public void printRule(String army1, String army2, JButton btn1, JButton btn2) {
        Color c1 = btn1.getBackground();
        Color c2 = btn2.getBackground();
        if (!c1.equals(Color.WHITE) && !c2.equals(Color.WHITE)) {
            String txt = " ";
            if (c1.equals(c2)) {
                txt = "Hay otro soldado del mismo reino :(\nPerdio turno";
                JOptionPane.showMessageDialog(null, "Game Rules: " + txt);
            } else {
                txt = "HAY UNA BATALLA";
                map.findBtn(army1, army2,txt, btn1, btn2);
                JOptionPane.showMessageDialog(null, txt); 
            }
        } else {
            String btnT1 = btn1.getText();
            btn1.setText(btn2.getText());
            btn1.setBackground(c2);
            btn2.setText(btnT1);
            btn2.setBackground(c1);
        }
    }
} 