package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioInicio extends Escenario {
    //ESTADO

    //COMPORTAMIENTO
    public EscenarioInicio(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        super(iAnchoPant,iAltoPant,miLienzo,miCamara);
    }

    @Override
    public void create() {
        super.create();
        //Ahora ponemos aquí objetos y los añadimos al contenedor (arraylist) heredado de nuestro padre
        ControladorAnimaciones animacionInicio = new ControladorAnimaciones("animacionIntroSprites", "gif", 144, 0.01f);
        misAnimaciones.add(animacionInicio);

        //Aquí ponemos más objetos que se pintarán al principio
        Dibujable imgInicio = new DibujableAdaptador(new Texture("muchoTexto.png"));
        ObjetoVolador imgI = new ObjetoVolador(this.iAnchoPant/2,this.iAltoPant/2,0,0,imgInicio);
        misObjetosEnPantalla.add(imgI);
        //...

        //Y por último la música de este escenario
        miMusica = Gdx.audio.newMusic(Gdx.files.internal("intro.ogg"));
        miMusica.setLooping(true);
        miMusica.play();

    }

    //Aquí haremos override si es necesario

    //Los touchDown y touchUp, hay que ponerlos sí o sí


    @Override
    protected void controlEstado() {
        super.controlEstado();

        PanelNumeros.getSingleton().setbActivo(false);
        PanelNumeros.getSingleton().reiniciar();

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
        ControladorJuego.getSingleton().cambiarEscena(ControladorJuego.EstadoJuego.JUGANDO);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
