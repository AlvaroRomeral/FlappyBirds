package com.gag.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gag.game.pantallas.PantallaLoading;

public class Pajaro {
    public static final int GRAVEDAD=-10;
    public static final int IMPULSO=300;
    private Vector2 posicion;
    private Vector2 velocidad;
    private Texture hojaOriginal;
    private TextureRegion aspecto;
    private Animation<TextureRegion> animacionAleteo;
    private float lapso;
    private final float DURACION=0.5f;
    private final int ANCHO=4;
    private final int ALTO=1;
    private Rectangle mascara;
    private float altoPajaro;

    public Pajaro(int x, int y) {
        posicion= new Vector2(x,y);
        velocidad= new Vector2(0,0);
        animacionAleteo=crearAnimacion(DURACION);
        lapso=0;
        mascara = new Rectangle(x,y,
                hojaOriginal.getWidth()/ANCHO,
                hojaOriginal.getHeight()/ALTO);
        altoPajaro=hojaOriginal.getHeight()/ALTO;
    }

    public void actualizar(float delta) {
        lapso+=delta;
        velocidad.add(new Vector2(0,GRAVEDAD));
        posicion.add(0,delta*velocidad.y);

        //Actualizo posicion de hitbox
        mascara.y=posicion.y;

//        if(posicion.y<=0 || posicion.y+altoPajaro>=Juego.ALTO)
//            posicion.y=Juego.ALTO ;

        //Chequeo de muerte
        if(posicion.y<=0 || posicion.y+altoPajaro>=Juego.ALTO){

            Juego.getInstancia().cambiarPantalla(
                    Juego.getInstancia().getActual(),
                    new PantallaLoading());
        }

        if(velocidad.y>0){
            aspecto=animacionAleteo.getKeyFrame(lapso,true);
        }else{
            aspecto=animacionAleteo.getKeyFrame(0);
        }
    }

    public void dibujar(SpriteBatch sb) {
        sb.draw(aspecto,posicion.x,posicion.y);
    }

    public void saltar() {
        velocidad.y=IMPULSO;
        lapso=DURACION/(ANCHO*ALTO);
    }

    private Animation<TextureRegion> crearAnimacion(float duracion){
        Animation<TextureRegion> animation;

        hojaOriginal=new Texture("PajaroAnimado.png");
        TextureRegion[][] intermedio= TextureRegion.split(
                hojaOriginal,
                hojaOriginal.getWidth()/ANCHO,
                hojaOriginal.getHeight()/ALTO
        );
        TextureRegion[] definitiva=new TextureRegion[ANCHO*ALTO];
        int indice=0;
        for (int i=0;i<ALTO;i++){//para cada fila
            for (int j=0;j<ANCHO;j++){//para cada imagen de esa fila
                definitiva[indice++]=intermedio[i][j];
            }
        }
        animation=new Animation<TextureRegion>(duracion/(ANCHO*ALTO),definitiva);
        return animation;
    }

    public Rectangle getMascara() {
        return mascara;
    }
}
