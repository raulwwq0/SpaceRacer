package es.iespolitecnicomalaga.spaceracer.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import es.iespolitecnicomalaga.spaceracer.ControladorJuego;
import es.iespolitecnicomalaga.spaceracer.GdxGameSpaceRacer;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Space Racer");
		config.setWindowedMode(ControladorJuego.PANTALLA_ANCHO,ControladorJuego.PANTALLA_ALTO);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new GdxGameSpaceRacer(), config);
	}
}
