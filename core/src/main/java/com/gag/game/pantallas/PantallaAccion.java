package com.gag.game.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.gag.game.Juego;
import com.gag.game.Pajaro;
import com.gag.game.ParTubos;
import sun.java2d.pipe.SpanClipRenderer;

public class PantallaAccion extends Pantalla{
    private Pajaro pajaro;
    private Rectangle mascaraPajaro;
    Texture fondo;
    ParTubos pt1,pt2,pt3;

    public PantallaAccion() {
        super();
        pajaro=new Pajaro(50,300);
        mascaraPajaro=pajaro.getMascara();
        fondo=new Texture("Fondo.png");
        pt1=new ParTubos(ParTubos.x1);
        pt2=new ParTubos(ParTubos.x2);
        pt3=new ParTubos(ParTubos.x3);

    }

    @Override
    public void leerEntrada(float delta) {
        if(Gdx.input.justTouched()){
            pajaro.saltar();
        }
    }

    @Override
    public void actualizar(float delta) {
        pajaro.actualizar(delta);
        pt1.actualizar(delta,mascaraPajaro);
        pt2.actualizar(delta,mascaraPajaro);
        pt3.actualizar(delta,mascaraPajaro);
    }

    @Override
    public void dibujar(float delta) {
        sb.begin();
        sb.draw(fondo,0,0);
        pajaro.dibujar(sb);
        pt1.dibujar(sb);
        pt2.dibujar(sb);
        pt3.dibujar(sb);
        sb.end();
    }

    @Override
    public void show() {

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

    }

}
