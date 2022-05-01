package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase Fondo. Se encarga de dibujar y avanzar una simulación de "camara" por una imagen dada
 * En una escena tendremos varios fondos.
 */
public class ParallaxEscena {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    private int tamVentanaX;  //La ventana es lo que cabe en la pantalla real
    private int tamVentanaY;

    private int tamEscenaX;  //Tamaño de la escena real, sobrepasará la ventana
    private int tamEscenaY;

    private Fondo fondo1;
    private Fondo fondo2;

    private static final String FILE_FONDO1 = "visual/spritesParallax/fondo.png";
    private static final String FILE_FONDO2 = "visual/spritesParallax/luna.png";
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR
    public  ParallaxEscena() {

        tamVentanaX = Gdx.graphics.getWidth();
        tamVentanaY = Gdx.graphics.getHeight();
        fondo1 = new Fondo(FILE_FONDO1, 0, -0.4f, 0, 0, tamVentanaX, tamVentanaY);
        fondo2 = new Fondo(FILE_FONDO2, 0, -0.8f, 0, 0, tamVentanaX, tamVentanaY);

        tamEscenaY = fondo1.getAltoFondo();
        tamEscenaX = fondo1.getAnchoFondo();


    }

    //RESTO DE MÉTODOS

    public void animar() {

        fondo1.avanzar();
        fondo2.avanzar();


    }

    public void render(Lienzo miSB) {

        fondo1.pintate(miSB);
        fondo2.pintate(miSB);


    }

    public void dispose() {
        fondo1.dispose();
        fondo2.dispose();
    }
}




