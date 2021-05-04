package com.gag.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gag.game.Juego;

import java.awt.*;

public class PantallaLoading extends Pantalla{
    private Texture fondo;
    private Texture botonJugar;
    private Vector2 posicionBoton;
    private Rectangle areaBoton;

    public PantallaLoading() {
        super();
        fondo=new Texture("Fondo.png");
        botonJugar=new Texture("BotonPlay.png");
        //Para ubicar el botón en el centro
        posicionBoton=new Vector2(Juego.ANCHO/2-botonJugar.getWidth()/2,Juego.ALTO/2-botonJugar.getHeight()/2);
        //Para definir su "área de click"
        areaBoton=new Rectangle((int)posicionBoton.x,(int)posicionBoton.y,botonJugar.getWidth(),botonJugar.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void leerEntrada(float delta) {
        if(Gdx.input.justTouched()){
            //Debo hacer el unproject porque la vista puede estar escalada!!
            Vector2 puntoClickado=juego.getVista().unproject(new Vector2(Gdx.input.getX(),Gdx.input.getY()));
            if(areaBoton.contains(puntoClickado.x, puntoClickado.y)){
                juego.cambiarPantalla(this,new PantallaAccion());
            }
        }
    }

    @Override
    public void actualizar(float delta) {

    }

    @Override
    public void dibujar(float delta) {
        sb.begin();
        sb.draw(fondo,0,0, Juego.ANCHO, Juego.ALTO);
        sb.draw(botonJugar, posicionBoton.x,posicionBoton.y);//sin ancho-alto -> dim. de la texture
        sb.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        fondo.dispose();
        botonJugar.dispose();
    }
}
