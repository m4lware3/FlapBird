package com.cursoandroid.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlapBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture[] passaro;
    private Texture fundo;
    private int largura;
    private int altura;
	private float variacao =0;

	@Override
	public void create () {

	    batch = new SpriteBatch();
	    passaro = new Texture[3];
	    passaro[0] = new Texture("passaro1.png");
        passaro[1] = new Texture("passaro2.png");
        passaro[2] = new Texture("passaro3.png");

        fundo = new Texture("fundo.png");


	    largura = Gdx.graphics.getWidth();
	    altura = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {

	    variacao += 0.1;
	    if (variacao > 2) variacao = 0;
	    batch.begin();

	    batch.draw(fundo,0,0,largura,altura);
	    batch.draw(passaro[(int)variacao],300,altura/2,280,188);

	    batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
