public class Espadachin extends Soldado{
    private int longitudEspada = generateLevel(6, 10);
    private boolean muroEscudo = true;
    public Espadachin(String na, int row, int column){
        super(na, row, column);
        setNiveldeAtaque(10);
        setNiveldeDefensa(8);
        setVidaActual(generateLevel(8, 10));
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
