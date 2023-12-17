import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private Mapa mapa = new Mapa();
    private JComboBox kingdom1;
    private JComboBox kingdom2;
    private ButtonGroup radiGroup = new ButtonGroup();
    private JRadioButton[]types = new JRadioButton[5];
    public Menu() {
        setSize(200, 300);
        setTitle("Videojuego_24");
        setLayout(new BorderLayout());
        createContents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void setKingdoms(String[][] k) {
        String[] columnValues = new String[k.length];
        for (int i = 0; i < k.length; i++) {
            columnValues[i] = k[i][0];
    }
    kingdom1 = new JComboBox<>(columnValues);
    kingdom2 = new JComboBox<>(columnValues);
    }
    public void setMapTypeS(String[] k) {
        for (int i = 0; i < k.length; i++) {
            types[i] = new JRadioButton(); /*inicializar */
            types[i].setText(k[i]);
            radiGroup.add(types[i]);
        }
    }
    public String getKingdom1Content() {
        return (kingdom1.getSelectedItem() != null) ? kingdom1.getSelectedItem().toString() : "Nada seleccionado";
    }
    public String getKingdom2Content() {
        return (kingdom2.getSelectedItem() != null) ? kingdom2.getSelectedItem().toString() : "Nada seleccionado";
    }
    public String getMapType() {
        for (int i = 0; i < types.length; i++) {
            if (types[i] != null && types[i].isSelected())
                return types[i].getText();
        }
        return "No seleccionó nada";
    }
    public void createContents() {
        JLabel titleGame = new JLabel("Videojuego uwu");

        JPanel box1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel king1 = new JLabel("JUGADOR1: Seleccione el reino ");
        JLabel king2 = new JLabel("JUGADOR2: Seleccion al reino ");

        setKingdoms(mapa.getKINGDOMS());

        JLabel map = new JLabel("Elija el tipo de Mapa");

        box1.add(king1);
        box1.add(kingdom1);
        box1.add(king2);
        box1.add(kingdom2);
        box1.add(map);

        setMapTypeS(mapa.getTypesMap());
        for (int i = 0; i < types.length; i++) {
            box1.add(types[i]);
        }

        JButton play = new JButton("JUGAR");

        
        play.setBackground(Color.PINK);

        add(titleGame, BorderLayout.NORTH);
        add(box1, BorderLayout.CENTER);
        add(play, BorderLayout.SOUTH);

        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mapa.startPlay(getKingdom1Content(), getKingdom2Content(), getMapType());
                ArmyBoard board = new ArmyBoard(mapa);
            }
        });
    }
    /*Lab24-Mariel Jara */
    
    public static void main(String[] args) {
        new Menu();
    }
}
