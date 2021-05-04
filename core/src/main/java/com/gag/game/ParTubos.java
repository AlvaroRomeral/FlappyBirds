package com.gag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gag.game.pantallas.PantallaLoading;

import java.util.Random;

public class ParTubos {
    public static final int x1=500;
    public static final int x2=800;
    public static final int x3=1100;
    public static final int VELOCIDAD=-300;
    public static final Texture TUBO_SUPERIOR=new Texture("TuboSuperior.png");
    public static final Texture TUBO_INFERIOR=new Texture("TuboInferior.png");
    public static final int ANCHO_TUBO=TUBO_SUPERIOR.getWidth();
    public static final int ALTO_TUBO=TUBO_SUPERIOR.getHeight();
    //Aleatoriedad
    public static final int PUNTO_BASE_SUPERIOR=400;
    public static final int RANGO_SUPERIOR=200;
    public static final int HUECO_BASE=200;
    public static final int RANGO_HUECO=200;
    private Random aleatorio;
    private Vector2 posicionSuperior;
    private Vector2 posicionInferior;

    //para las colidiones
    private Rectangle mascaraSuperior;
    private Rectangle mascaraInferior;

    public ParTubos(int x) {
        aleatorio=new Random();
        mascaraSuperior=new Rectangle(0,0,ANCHO_TUBO,ALTO_TUBO);
        mascaraInferior=new Rectangle(0,0,ANCHO_TUBO,ALTO_TUBO);
        colocar (x);
    }
    public void actualizar(float delta, Rectangle rectangulo){
        posicionSuperior.x+=VELOCIDAD*delta;
        posicionInferior.x+=VELOCIDAD*delta;

        //mascaras
        mascaraSuperior.x=posicionSuperior.x;
        mascaraInferior.x=posicionInferior.x;

        //Colison
        if(mascaraSuperior.overlaps(rectangulo) || mascaraInferior.overlaps(rectangulo)){
            Juego.getInstancia().cambiarPantalla(Juego.getInstancia().getActual(), new PantallaLoading());
        }
        if(posicionSuperior.x+ANCHO_TUBO<0) colocar(x2);
    }

    public void dibujar(SpriteBatch sb) {
        sb.draw(TUBO_SUPERIOR,posicionSuperior.x,posicionSuperior.y);
        sb.draw(TUBO_INFERIOR, posicionInferior.x, posicionInferior.y);
    }

    private void colocar(int x){
        //altura a la que se coloca el tubo superior
        int y=PUNTO_BASE_SUPERIOR+aleatorio.nextInt(RANGO_SUPERIOR);
        posicionSuperior=new Vector2(x,y);
        mascaraSuperior.x=posicionSuperior.x;
        mascaraSuperior.y=posicionSuperior.y;
        //altura a la que se coloca el tubo inferior
        y-=HUECO_BASE+aleatorio.nextInt(RANGO_HUECO)+ALTO_TUBO;
        posicionInferior =new Vector2(x,y);
        mascaraInferior.x=posicionInferior.x;
        mascaraInferior.y=posicionInferior.y;
    }
}

