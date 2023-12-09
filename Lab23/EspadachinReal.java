/*Espadachín Real, tiene la habilidad de lanzar cuchillos 
(número y tamaño limitado, aumentan en cada evolución). 4 niveles de 
evolución */
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

