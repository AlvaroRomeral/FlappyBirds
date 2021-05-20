package com.gag.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.gag.game.pantallas.Pantalla;
import com.gag.game.pantallas.PantallaLoading;

import java.util.Stack;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

/**
 * Esta es la clase que sirve de nexo para todo
 * **/
public class Juego extends Game {
	public static final int ANCHO=450;
	public static final int ALTO=800;
	public static final String TITULO="Pajarraco";
	public static final String ICON="Pajaro.png";

	private OrthographicCamera camara;
	private FitViewport vista;
	private SpriteBatch sb;

	private static Juego instancia;
	private Pantalla actual;
	private Music musica;

	@Override
	public void create() {
		instancia=this;

		camara = new OrthographicCamera();
		vista= new FitViewport(ANCHO,ALTO,camara);
		sb=new SpriteBatch();

		musica= Gdx.audio.newMusic(Gdx.files.internal("ACDC_TNT.ogg"));
		musica.setLooping(true);
		musica.setVolume(0.3f);
		musica.play();

		cambiarPantalla(null,new PantallaLoading());
	}

	@Override
	public void resize(int width, int height) {
		vista.update(width, height,true);
	}

	// --------------- Getters y Setters ----------------------------
	public SpriteBatch getSb() {
		return sb;
	}

	public FitViewport getVista() {
		return vista;
	}

	public static Juego getInstancia(){ return instancia; }

	public Pantalla getActual() {
		return actual;
	}

	//---------------- Gestión de pantallas --------------------------------
	public void cambiarPantalla(Pantalla antigua, Pantalla nueva){
		if(antigua!=null){
			antigua.dispose();
		}
		setScreen(nueva);
		actual=nueva;
	}

}