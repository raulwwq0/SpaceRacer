package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Animacion {
    private ArrayList<Dibujable> frames;
    private float numeroFrames;
    private float tiempoCambioFrame;
    private int indiceFrameActual;
    private float tiempoFrame;

    public Animacion(String carpeta, String extensionFrames, int numeroFrames, float tiempoCambioFrame) {
        this.numeroFrames = numeroFrames;
        this.tiempoCambioFrame = tiempoCambioFrame;
        this.indiceFrameActual = 0;
        this.tiempoFrame = 0;
        frames = new ArrayList<Dibujable>();
        for (int i = 0; i < numeroFrames; i++) {
            Texture miTextura = new Texture(carpeta + "/" + i + "."+ extensionFrames);
            frames.add(new DibujableAdaptador(miTextura));
        }
    }

    public void pintarse(Lienzo miSB, int anchoPantalla, int altoPantalla) {
        miSB.draw(getFrameActual(), 0, 0, anchoPantalla, altoPantalla);
    }

    private Dibujable getFrameActual() {

        if (tiempoFrame < tiempoCambioFrame) {
            tiempoFrame += Gdx.graphics.getDeltaTime();
        } else {
            tiempoFrame = 0;

            if (indiceFrameActual >= numeroFrames - 1) {
                indiceFrameActual = 0;
            } else {
                indiceFrameActual++;
            }
        }

        return frames.get(indiceFrameActual);
    }
}
