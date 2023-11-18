public class Caballero extends Soldado{
    private String armaActual = "Lanza";
    private boolean montando = true;
    public Caballero(String na, int lif, int def, int ata, int row, int column){
        super(na, lif, def, ata, row, column); 
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
        if(montando == true){
            for (int i = 0; i < 3; i++) {
                super.atacar();
            }
        }else{
            for (int i = 0; i < 2; i++) {
                super.atacar();
            }
        }
    }
    public String toString(){
        return super.toString()+"|Arma: "+armaActual+"|Montar?: "+montando; 
    }
}

