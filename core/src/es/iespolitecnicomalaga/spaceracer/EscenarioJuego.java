package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

import org.graalvm.compiler.replacements.nodes.BitScanForwardNode;

public class EscenarioJuego extends Escenario{

    //ESTADOS
    private NavesAliadas miNave;

    private Dibujable imagenObstaculo;
    private Dibujable miDibujoNormal;

    //Contador Tiempo
    private int tiempo; //Segundos, por si los necesitamos
    private int frames; //Para contar segundos, 60frames = 1segundo (tiempo +=1)

    //Tasa de obstaculos
    private float tasaObstaculos; //Cuanto mayor es, mas aumentan los obstaculos


    //Tasa de meteoritos
    public EscenarioJuego(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara){
        super(iAnchoPant, iAltoPant, miLienzo, miCamara);
    }

    public void create() {
        super.create();
        //Ahora ponemos aquí objetos y los añadimos al contenedor (arraylist) heredado de nuestro padre

        //Nave principal
        miDibujoNormal = new DibujableAdaptador(new Texture("aliada5azul.png"));
        Dibujable miExplosion = new DibujableAdaptador(new Texture("explosion.png"));
        miNave = new NavesAliadas(this.iAnchoPant/2, this.iAltoPant / 8, this.iAnchoPant, miDibujoNormal, miExplosion);

        imagenObstaculo = new DibujableAdaptador(new Texture("pedrada.png"));

        misObjetosEnPantalla.add(miNave);
        tiempo = 0;
        frames = 0;
        tasaObstaculos = 0.00f;
    }
    @Override
    protected void controlEstado() {

        //Aquí debemos de animar los objetos: moverlos. Controlar las colisiones. Controlar si hemos finalizado, sumar puntos
        miNave.moverse();
        noPuedeMoverse();
        //Tiempo
        contarSegundos();

        miPE.animar(); //La escena parallax
        moverObstaculos();

        //Crear Obstaculos
        crearObstaculo();

        //colisiones
        comprobarColisiones();
        comprobarFuera();
    }

    //Crear obstaculos - Minimo 2 simultaneos, maximo 20
    private void crearObstaculo(){

        //El puesto 1 del array es nuestra Nave
        if(misObjetosEnPantalla.size() < 3){

        //Esto para tener un minimo de 2 obstaculos
        crearMeteoro();

        //Implementación del match Random para la probabilidad de mas obstaculos conforme pasa el tiempo
        } else {
            if (Math.random() < tasaObstaculos && misObjetosEnPantalla.size() < 21){
                crearMeteoro();
            }

        }
    }
    //Para agilizar el codigo
    private void crearMeteoro(){
        Obstaculo obstaculoTemp;

        obstaculoTemp = new Obstaculo((float)(iAnchoPant - Math.random()*1000), iAltoPant + 70, imagenObstaculo);
        misObjetosEnPantalla.add(obstaculoTemp);
    }

    private void moverObstaculos(){
        int i;

        if (misObjetosEnPantalla.get(0) == miNave){
            for (i = 1;i<misObjetosEnPantalla.size(); i++){
                misObjetosEnPantalla.get(i).moverse();
            }
        }else {
            for(ObjetoVolador obstaculo: misObjetosEnPantalla){
                obstaculo.moverse();
            }
        }

    }

    //Cuando un Obstaculo deja sale por debajo de l apantalla, se elimina del array.
    public void comprobarFuera(){
        int i;
        ObjetoVolador obstaculo;
        for (i = 1;i<misObjetosEnPantalla.size(); i++){
            obstaculo = misObjetosEnPantalla.get(i);
            if (obstaculo.getPosY() < -60){
                misObjetosEnPantalla.remove(obstaculo);
            };
        }
    }

    //Colision básica con el Xwing
    public void comprobarColisiones(){
        int i;

        ObjetoVolador obstaculo;
        ObjetoVolador xwing;
        xwing = misObjetosEnPantalla.get(0);
        for (i = 1;i<misObjetosEnPantalla.size(); i++){
            obstaculo = misObjetosEnPantalla.get(i);
            if (obstaculo.colisiona(xwing) && (miNave.explotar == false)){
                miNave.explota();
            };
        }
    }


    //El método se encarga de contar los segundos y de actualizar la tasa de aparicion de obstaculos

    public void contarSegundos(){

        this.frames++;
        if (frames == 60){
            frames  = 0;
            tiempo++;
            tasaObstaculos += 0.0005f;
        };
    }

    public void noPuedeMoverse() {
        if ((miNave.posX + (miDibujoNormal.getWidth() / 2)) > iAnchoPant){
            miNave.posX = iAnchoPant - (miDibujoNormal.getWidth() / 2);
        }else if ((miNave.posX - (miDibujoNormal.getWidth() / 2)) < 0) miNave.posX = (miDibujoNormal.getWidth() / 2);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenX > (iAnchoPant/2)) miNave.velX = 7.0F;
        else miNave.velX = -7.0F;
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        miNave.velX = 0F;
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public NavesAliadas getNave(){
        return miNave;
    }
}
