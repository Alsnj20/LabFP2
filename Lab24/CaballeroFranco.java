/* Caballero Franco, tiene la habilidad de lanzar lanzas (número y 
tamaño limitado, aumentan en cada evolución). 4 niveles de evolución */

public class CaballeroFranco extends Caballero implements SpecialUnit {
    private int lanzaLanzas = 0;
    private int nivelEvolucion = 0;
    public CaballeroFranco(String na, int row, int column){
        super(na, row, column);
        setVidaActual(15);
        lanzaLanzas =  generateLevel(6, 10); 
    }
    public void habilidadEspecial(){
        if(lanzaLanzas>0)lanzaLanzas--;
        else aumentarNivelEvolucion();
    }
    public void aumentarNivelEvolucion(){
        if(SpecialUnit.maxNivel >= nivelEvolucion) {
            nivelEvolucion++; lanzaLanzas++;
        }else{
            System.out.println("Nivel de Evolucion Maximo");
        }
    }
}

