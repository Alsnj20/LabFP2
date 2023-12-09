
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Graphic extends JFrame {
    private JButton[][] table = new JButton[10][10];
    private JLabel text1 = new JLabel();
    private JLabel row= new JLabel();
    private JLabel col = new JLabel();
    private JTextField inputRow = new JTextField(5);
    private JTextField inputCol = new JTextField(5);
    private JLabel direct = new JLabel("A que lado desea moverse?");
    private JTextField inputDir = new JTextField(5);
    private int fila; 
    private int column;
    public JButton[][] getTable(){return table;}
    public JLabel getText1() {return text1;}   
    public JLabel getInfoRow() {return row;  
    }
    public JLabel getInfoCol() {return col;
    }
    public JTextField getRow() {
        return inputRow;
    }
    public JTextField getCol() {
        return inputCol;
    }
    public void setRow(int f){
        fila = f;
    }
    public void setCol(int c){column = c;}
    public int getFila() {return fila;}
    public int getColumna() {return column;}

    public Graphic(){
        setTitle("videogame-V");
        setSize(1000, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);//cambiar tamaño
        setVisible(true);

    }
    public void addButton(JButton btn, int i, int j){
        table[i][j] = btn;
    }
    public void print(){
        JPanel box = new JPanel(new GridLayout(11, 11));
        for (int i = 0; i < 11; i++) {
            box.add(new JLabel(i + "", SwingConstants.CENTER));
        }
        for (int i = 0; i < 10; i++) {
            box.add(new JLabel(i + 1 + "", SwingConstants.CENTER));
            for (int j = 0; j < 10; j++) {
                box.add(table[i][j]);
            }
        }
        add(box, BorderLayout.CENTER);
        add(box);
        JButton move = new JButton("Mover");
        JPanel box2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        box2.add(text1);box2.add(row); box2.add(inputRow); box2.add(col); 
        box2.add(inputCol);box2.add(move);box2.add(direct);box2.add(inputDir);
        add(box2, BorderLayout.SOUTH);
        move.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(e.getSource() == move){
                    JButton firstClickedButton = null;
                    JButton clickedButton = (JButton) e.getSource();
                    if (firstClickedButton == null)
                        firstClickedButton = clickedButton; 
                }
            }
        });
    }
    public void addContentTable(String army, String typeE, String type2E){
        text1.setText(typeE+" "+army+": Ingrese el "+type2E+" a mover(f/c)");
        row.setText("Ingrese fila: ");
        col.setText("Ingrese la columna: ");
    }
}
