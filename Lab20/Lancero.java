public class Lancero extends Soldado   {
    private int longLanza = 0;
    public Lancero(String na, int lif, int def, int ata, int row, int column, int lgL){
        super(na, lif, def, ata, row, column);
        longLanza = lgL;
    }
    public void schiltrom(){
        this.setNiveldeDefensa(getNiveldeDefensa()+1);
    }
    public String toString(){
        return super.toString()+"|Lanza : "+longLanza+"cm";
    }
}
