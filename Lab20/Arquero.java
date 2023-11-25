public class Arquero extends Soldado{
    private int nroFlechas;
    public Arquero(String na, int lif, int def, int ata, int row, int column, int nF){
        super(na, lif, def, ata, row, column);
        nroFlechas = nF;
    }
    public void disparar(){
        if(nroFlechas>0) nroFlechas--;
    }
    public String toString(){
        return super.toString()+"|Flechas: "+nroFlechas;
    }
}
