public class Arquero extends Soldado{
    private int nroFlechas = generateLevel(6, 10);
    public Arquero(String na, int row, int column){
        super(na, row, column);
        setNiveldeAtaque(7);
        setNiveldeDefensa(3);
        setVidaActual(generateLevel(3, 5));
    }
    public void disparar(){
        if(nroFlechas>0) nroFlechas--;
    }
    public String toString(){
        return super.toString()+"|Flechas: "+nroFlechas;
    }
}
