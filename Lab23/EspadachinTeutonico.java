/*Espadachín Teutónico, tipo especial 
de espadachín. Puede lanzar una jabalina (número y tamaño limitado, 
aumentan en cada evolución) y tiene una defensa especial de “modo 
tortuga”. 4 niveles de evolución */
public class EspadachinTeutonico extends Espadachin implements SpecialUnit {
    private int lanzarJabalina = 0;
    private int nivelEvolucion = 0;
    private boolean modoTortuga;

    public EspadachinTeutonico(String na, int row, int column) {
        super(na, row, column);
        setVidaActual(13);
        lanzarJabalina = generateLevel(6, 10);
        modoTortuga = false;
    }

    public void habilidadEspecial() {
        if (lanzarJabalina > 0)
            lanzarJabalina--;
        else
            aumentarNivelEvolucion();
    }

    public void aumentarNivelEvolucion() {
        if (SpecialUnit.maxNivel >= nivelEvolucion) {
            nivelEvolucion++;
            lanzarJabalina++;
        } else {
            System.out.println("Nivel de Evolucion Maximo");
        }
    }

    public void activarDefensa() {
        modoTortuga = true;
    }
}
