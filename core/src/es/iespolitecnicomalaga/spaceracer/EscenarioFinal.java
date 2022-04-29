package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioFinal extends Escenario{
    //ESTADO

    //Aquí definimos los objetos que estarán en la ventana inicial
    ObjetoVolador teMamaste;
    ObjetoVolador intentalo;
    ObjetoVolador puntuacion;

    //COMPORTAMIENTO
    public EscenarioFinal(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        super(iAnchoPant,iAltoPant,miLienzo,miCamara);
    }

    @Override
    public void create() {
        super.create();
        //Ahora ponemos aquí objetos y los añadimos al contenedor (arraylist) heredado de nuestro padre


        //Dibujable y estancia del dibujo te mamaste
        Dibujable dibujoTeMamaste = new DibujableAdaptador(new Texture("temamaste.png"));
        teMamaste = new ObjetoVolador(200,475,0.0f,0.0f,dibujoTeMamaste);
        //Dibujable y estancia de intentalo de nuevo
        Dibujable dibujoIntentalo = new DibujableAdaptador(new Texture("intentalo.png"));
        intentalo = new ObjetoVolador(230,100,0.0f,0.0f,dibujoIntentalo);
        //Dibujable y estancia del texto puntuacion
        Dibujable dibujoPuntuacion = new DibujableAdaptador(new Texture("puntuacion.png"));
        puntuacion = new ObjetoVolador(730,500,0.0f,0.0f,dibujoPuntuacion);
        //Añadimos a la array los distintos objetos voladores
        misObjetosEnPantalla.add(teMamaste);
        misObjetosEnPantalla.add(intentalo);
        misObjetosEnPantalla.add(puntuacion);

        //Y por último la música de este escenario
        miMusica = Gdx.audio.newMusic(Gdx.files.internal("musicaFinal.ogg"));
        miMusica.setLooping(true);
        miMusica.play();

    }

    //Aquí haremos override si es necesario

    //Los touchDown y touchUp, hay que ponerlos sí o sí


    @Override
    protected void controlEstado() {
        super.controlEstado();
        //Aquí debemos de animar los objetos: moverlos. Controlar las colisiones. Controlar si hemos finalizado, sumar puntos
        //La escena parallax
        miPE.animar();
        //colisiones
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //¿Qué pasa si pulsan la pantalla, pues que nos vamos a la pantalla del juego... Hay que notificar al controlador
        //principal para que cambie el escenario
        miMusica.stop();

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
