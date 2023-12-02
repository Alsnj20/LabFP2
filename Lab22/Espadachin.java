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
public interface SpecialUnit {
    int maxNivel = 4;
    void habilidadEspecial();
    void aumentarNivelEvolucion(); 
}
public class EspadachinConquistador extends Espadachin implements SpecialUnit {
        private int lanzarHacha = 0;
        private int nivelEvolucion = 0;
        public EspadachinConquistador(String na, int row, int column){
            super(na, row, column);
            setVidaActual(14);
            lanzarHacha =  generateLevel(6, 10);
        }
        public void habilidadEspecial(){
            if(lanzarHacha>0)lanzarHacha--;
            else aumentarNivelEvolucion();
        }
        public void aumentarNivelEvolucion(){
            if(SpecialUnit.maxNivel >= nivelEvolucion) {
                nivelEvolucion++; lanzarHacha++;
            }else{
                System.out.println("Nivel de Evolucion Maximo");
            }
        }
}
public class EspadachinReal extends Espadachin implements SpecialUnit {
    private int lanzaCuchillos;
    private int nivelEvolucion = 0; 
    public EspadachinReal (String na, int row, int column){
    super(na, row, column);
    setVidaActual(12);
     lanzaCuchillos =  generateLevel(6, 10);  
    }
    public void habilidadEspecial(){
        if(lanzaCuchillos>0)lanzaCuchillos--;
        else aumentarNivelEvolucion();
    }
    public void aumentarNivelEvolucion(){
        if(SpecialUnit.maxNivel >= nivelEvolucion) {
            nivelEvolucion++; lanzaCuchillos++;
        }else{
            System.out.println("Nivel de Evolucion Maximo");
        }
    }
}
public class EspadachinTeutonico extends Espadachin implements SpecialUnit {
        private int lanzarJabalina = 0;
        private int nivelEvolucion = 0;
        private boolean modoTortuga;
        public EspadachinTeutonico(String na, int row, int column){
            super(na, row, column);
            setVidaActual(13);
            lanzarJabalina =  generateLevel(6, 10);
            modoTortuga = false; 
        }
        public void habilidadEspecial(){
            if(lanzarJabalina>0)lanzarJabalina--;
            else aumentarNivelEvolucion();
        }
        public void aumentarNivelEvolucion(){
            if(SpecialUnit.maxNivel >= nivelEvolucion) {
                nivelEvolucion++; lanzarJabalina++;
            }else{
                System.out.println("Nivel de Evolucion Maximo");
            }
        }
        public void activarDefensa(){modoTortuga = true;}    
}