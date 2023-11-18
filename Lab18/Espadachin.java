public class Espadachin extends Soldado{
    private int longitudEspada;
    private boolean muroEscudo = false;
    public Espadachin(String na, int lif, int def, int ata, int row, int column, int le){
        super(na, lif, def, le, row, column);
        longitudEspada = le;
    }
    public void crearMuroEscudos(){
        if(muroEscudo == true){
            System.out.println("Muro de escudo activado");
        }else{
            muroEscudo = false;
        }
    }
    public String toString(){
        return super.toString()+"|Espada: "+longitudEspada+"|MuroEscudo: "+muroEscudo;
    }
}
