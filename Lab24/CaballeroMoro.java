/* caballero que lanza flechas (número y tamaño 
limitado, aumentan en cada evolución) y enviste con mayor poder. 4 
niveles de evolución */
public class CaballeroMoro extends Caballero implements SpecialUnit {
    private int lanzaFlechas = 0;
    private int nivelEvolucion = 0;
    public CaballeroMoro(String na, int row, int column){
        super(na, row, column);
        setVidaActual(13);
        lanzaFlechas =  generateLevel(6, 10); 
    }
    public void habilidadEspecial(){
        if(lanzaFlechas>0)lanzaFlechas--;
        else aumentarNivelEvolucion();
    }
    public void aumentarNivelEvolucion(){
        if(SpecialUnit.maxNivel >= nivelEvolucion) {
            nivelEvolucion++; lanzaFlechas++;
        }else{
            System.out.println("Nivel de Evolucion Maximo");
        }
    }
}