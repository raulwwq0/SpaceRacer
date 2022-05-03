package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class PanelNumeros {

    // Atributos ###################################################################################

    //Constantes
    private static final String NOMBRE_SPRITES = "visual/spritesDigitos/digito";
    private static final String EXT_SPRITES =".png";


    /*
        Para poder usar el mismo marcador en las clases EscenarioJuego y EscenarioFinal vamos a
        implementarlo usando un singleton, así solo tendremos un marcador para todas las clases
        que lo necesiten y siempre será el mismo.
     */
    public static PanelNumeros miSingleton;

    //Almacenaremos números de base en un array list "digitos"
    protected ArrayList<Dibujable> listaDigitos;

    //Almacenaremos los números necesarios para pintar el número asignado en otro arraylist
    protected ArrayList<Dibujable> listaMostrada;


    protected float fPosX;
    protected float fPosY;
    protected float fAncho;

    // Valor del contador
    protected int iValorAlmacenado;

    // Para saber si está activo (visible) o no, ya que en EscenarioInicio no se pinta el marcador
    private boolean bActivo;

    // Constructor #################################################################################
    private PanelNumeros() {
        fPosX = 0;
        fPosY = 0;
        fAncho = 0;
        bActivo = false;

        //creamos el array de base y el array a pintar (con el 0)
        listaDigitos = new ArrayList<Dibujable>();
        for (int i = 0;i<10;i++) {
            DibujableAdaptador nuevoDigito = new DibujableAdaptador(new Texture(NOMBRE_SPRITES + String.valueOf(i) + EXT_SPRITES));
            listaDigitos.add(nuevoDigito);
        }

        listaMostrada = new ArrayList<Dibujable>();
        listaMostrada.add(listaDigitos.get(0));
        iValorAlmacenado = 0;

    }

    // Singleton ###################################################################################
    public static PanelNumeros getSingleton() {
        if (miSingleton == null) {
            miSingleton = new PanelNumeros();
        }
        return miSingleton;
    }

    // Métodos #####################################################################################

    // Pintamos los dígitos del marcador
    public void pintarse(Lienzo miSB) {
        float pX,pY;
        pX = fPosX;
        pY = fPosY;
        for (Dibujable digito :listaMostrada) {
            miSB.draw(digito, pX, pY, fAncho,fAncho);
            pX += fAncho;
        }

    }

    //Método incremento
    public void incrementa(int iValor) {
        if (iValor > 0) {
            iValorAlmacenado+= iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValorAlmacenado);
            for (int i=0; i<sNumero.length();i++) {
                String digito = String.valueOf(sNumero.charAt(i));

                listaMostrada.add(listaDigitos.get(Integer.valueOf(digito)));
            }
        }
    }

    public void cambiarPosicionYAncho(float pX, float pY, float nAncho) {
        fPosX = pX;
        fPosY = pY;
        fAncho = nAncho;
    }

    // Borrar el marcador (para cuando se reinicia el juego)
    public void reiniciar() {
        iValorAlmacenado = 0;
        listaMostrada.clear();
        listaMostrada.add(listaDigitos.get(0));
    }

    //Método dispose. Para eliminar los recursos
    public void dispose() {
        for ( Dibujable t :listaDigitos) {
            t.dispose();
        }
    }

    // Para saber si el marcador está activo o no
    public boolean isbActivo() {
        return bActivo;
    }

    // Para poner el marcador activo o no
    public void setbActivo(boolean bActivo) {
        this.bActivo = bActivo;
    }
}


