package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Clase PanelNumeros. Se trata de crear una estructura de datos de texture con los números del 0 al 9
 * precargados. Tendrá un método que dado un integer (ahora mismo sólo positivos) prepare un arraylist
 * de imágenes que puedan después pintarse en el SpriteBatch para formar ese número en pantalla
 * Se usará posteriormente para pintar la puntuación, vidas, etc en pantalla
 */

public class PanelNumeros {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Constantes

    private static final String NOMBRE_SPRITES = "digito";
    private static final String EXT_SPRITES =".png";


    public static PanelNumeros miSingleton;

    //Almacenaremos números de base en un array list "digitos"
    protected ArrayList<Dibujable> listaDigitos;

    //Almacenaremos los números necesarios para pintar el número asignado en otro arraylist
    protected ArrayList<Dibujable> listaMostrada;


    protected float fPosX;
    protected float fPosY;
    protected float fAncho;

    protected int iValorAlmacenado;

    private boolean bActivo;
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES
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

    public static PanelNumeros getSingleton() {
        if (miSingleton == null) {
            miSingleton = new PanelNumeros();
        }
        return miSingleton;
    }

    //Resto de comportamientos

    //Método pintarse
    public void pintarse(Lienzo miSB) {
        float pX,pY;
        pX = fPosX;
        pY = fPosY;
        for (Dibujable digito :listaMostrada) {
            miSB.draw(digito, pX, pY, fAncho,fAncho);
            pX += fAncho;
        }

    }

    //Asignar el número:
    public void setData(int iValor) {

        if (iValor > 0) {
            iValorAlmacenado = iValor;
            listaMostrada.clear();
            String sNumero = String.valueOf(iValor);
            for (int i=0; i<sNumero.length();i++) {
                String digito = String.valueOf(sNumero.charAt(i));

                listaMostrada.add(listaDigitos.get(Integer.valueOf(digito)));
            }
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

    //Método dispose. Para eliminar los recursos
    public void dispose() {
        for ( Dibujable t :listaDigitos) {
            t.dispose();
        }
    }

    public boolean isbActivo() {
        return bActivo;
    }

    public void setbActivo(boolean bActivo) {
        this.bActivo = bActivo;
    }

    public int getValor() {
        return iValorAlmacenado;
    }
}


