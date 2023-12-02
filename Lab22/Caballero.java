public class Caballero extends Soldado{
    private String armaActual = "Lanza";
    private boolean montando = true;
    public Caballero(String na, int row, int column){
        super(na, row, column);
        setNiveldeAtaque(13);
        setNiveldeDefensa(7);
        setVidaActual(generateLevel(10, 12));
    }
    public void changeArm(){
        if(armaActual == "Lanza") armaActual = "Espada";
        else armaActual = "Lanza";
    }
    public void desmontar(){
        if(montando == true){
            montando = false;
            super.defender();
            changeArm();
        }
    }
    public void montar(){
        if(montando == false){
            montando = true;
            changeArm();
            super.atacar();
        }
    }
    public void envestir(){
        int n = (montando == true) ? 3:2;
        for (int i = 0; i < n; i++)
            super.atacar();
    }
    public String toString(){
        return super.toString()+"|Arma: "+armaActual+"|Montar?: "+montando; 
    }
}
