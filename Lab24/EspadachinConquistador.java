/*Espadachín Conquistador, espadachín que también 
maneja hachas lanzables (número y tamaño limitado, aumentan en 
cada evolución). 4 niveles de evolución */
public class EspadachinConquistador extends Espadachin implements SpecialUnit {
    private int lanzarHacha = 0;
    private int nivelEvolucion = 0;

    public EspadachinConquistador(String na, int row, int column) {
        super(na, row, column);
        setVidaActual(14);
        lanzarHacha = generateLevel(6, 10);
    }

    public void habilidadEspecial() {
        if (lanzarHacha > 0)
            lanzarHacha--;
        else
            aumentarNivelEvolucion();
    }

    public void aumentarNivelEvolucion() {
        if (SpecialUnit.maxNivel >= nivelEvolucion) {
            nivelEvolucion++;
            lanzarHacha++;
        } else {
            System.out.println("Nivel de Evolucion Maximo");
        }
    }
}