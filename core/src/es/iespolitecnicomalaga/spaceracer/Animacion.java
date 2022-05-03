package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Animacion {
    // Atributos ###################################################################################

    // En este ArrayList se guardan los dibujables de cada frame de la animación
    private ArrayList<Dibujable> frames;

    // En esta variable se guarda el número frames (imagenes en la carpeta) de la animación
    private float numeroFrames;

    // En esta variable se guarda el tiempo que está visible el frame actual (contra menos tiempo va más fluido)
    private float tiempoCambioFrame;

    // Para saber que frame está actualmente visible
    private int indiceFrameActual;

    // Contador para saber si toca cambiar de frame
    private float tiempoFrame;

    // Constructor #################################################################################
    public Animacion(String carpeta, String extensionFrames, int numeroFrames, float tiempoCambioFrame) {
        this.numeroFrames = numeroFrames;
        this.tiempoCambioFrame = tiempoCambioFrame;
        this.indiceFrameActual = 0;
        this.tiempoFrame = 0;

        // Inicializamos el ArrayList
        frames = new ArrayList<Dibujable>();

        // Cargamos todas las imagenes de la carpeta indicada por parametros
        for (int i = 0; i < numeroFrames; i++) {
            Texture miTextura = new Texture(carpeta + "/" + i + "."+ extensionFrames);
            frames.add(new DibujableAdaptador(miTextura));
        }
    }

    // Métodos #####################################################################################

    // Pintar el frame actual
    public void pintarse(Lienzo miSB, int anchoPantalla, int altoPantalla) {
        miSB.draw(getFrameActual(), 0, 0, anchoPantalla, altoPantalla);
    }

    // Cambiar el frame actual
    private Dibujable getFrameActual() {

        if (tiempoFrame < tiempoCambioFrame) {

            /*
                Si el contador de tiempo es menor que el tiempo que está visible el frame actual,
                incrementamos el contador que sirve para saber si toca cambiar de frame

                El DeltaTime es un contador interno que se encarga de contar el tiempo que ha pasado
                desde el último frame, es decir, el tiempo que tarda en cambiar los frames de la
                animación.

                Lo usaremos para cuando cambiar al siguiente frame.
             */

            tiempoFrame += Gdx.graphics.getDeltaTime();
        } else {

            // Reseteamos el contador de tiempo
            tiempoFrame = 0;

            // Reseteamos el indice del frame actual para que no se pase del rango de frames del ArrayList
            if (indiceFrameActual >= numeroFrames - 1) {
                indiceFrameActual = 0;
            } else {
                indiceFrameActual++;
            }
        }

        // Devolvemos el frame actual
        return frames.get(indiceFrameActual);
    }

    public void dispose() {
        for (Dibujable frame : frames) {
            frame.dispose();
        }
    }
}
