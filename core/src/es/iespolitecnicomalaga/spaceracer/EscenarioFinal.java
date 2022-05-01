package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioFinal extends Escenario{
    //ESTADO

    //Aquí definimos los objetos que estarán en la ventana inicial
    ObjetoVolador bloque;

    //COMPORTAMIENTO
    public EscenarioFinal(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        super(iAnchoPant,iAltoPant,miLienzo,miCamara);
    }

    @Override
    public void create() {
        super.create();
        //Ahora ponemos aquí objetos y los añadimos al contenedor (arraylist) heredado de nuestro padre

        Animacion animacionFinal = new Animacion("visual/animacionFinalSprites", "png", 119, 0.01f);
        misAnimaciones.add(animacionFinal);

        //Dibujable y estancia del dibujo te mamaste
        Dibujable bloqueDibujo = new DibujableAdaptador(new Texture("visual/spritesVarios/bloqueImagen.png"));
        bloque = new ObjetoVolador(this.iAnchoPant/2,this.iAltoPant/2,0,0,bloqueDibujo);
        //añadimos el objeto en la array
        misObjetosEnPantalla.add(bloque);


    }

    //Aquí haremos override si es necesario

    //Los touchDown y touchUp, hay que ponerlos sí o sí


    @Override
    protected void controlEstado() {
        super.controlEstado();
        PanelNumeros.getSingleton().setbActivo(true);
        PanelNumeros.getSingleton().cambiarPosicionYAncho(30,45, 300);
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
        ControladorJuego.getSingleton().cambiarEscena(ControladorJuego.EstadoJuego.PANTALLA_INICIO);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void ponerMusica(){
        miMusica = Gdx.audio.newMusic(Gdx.files.internal("audio/musica/musicaFinal.ogg"));
        miMusica.setLooping(true);
        miMusica.play();

    }
    public void pararMusica(){
        miMusica.stop();
    }
}
