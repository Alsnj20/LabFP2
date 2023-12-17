public class Lancero extends Soldado   {
    private int longLanza = generateLevel(6, 10);
    public Lancero(String na, int row, int column){
        super(na, row, column);
        setNiveldeAtaque(5);
        setNiveldeDefensa(10);
        setVidaActual(generateLevel(5, 8));
    }
    public void schiltrom(){
        this.setNiveldeDefensa(getNiveldeDefensa()+1);
    }
    public String toString(){
        return super.toString()+"|Lanza : "+longLanza+"cm";
    }
}
