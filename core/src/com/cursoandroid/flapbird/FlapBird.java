package com.cursoandroid.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlapBird extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture passaro;
    private Texture fundo;
    private int largura;
    private int altura;
	private int movimento =0;

	@Override
	public void create () {

	    batch = new SpriteBatch();
	    passaro = new Texture("passaro1.png");
	    fundo = new Texture("fundo.png");

	    largura = Gdx.graphics.getWidth();
	    altura = Gdx.graphics.getHeight();
	}

	@Override
	public void render () {

	    movimento++;
	    batch.begin();

	    batch.draw(fundo,0,0,largura,altura);
	    batch.draw(passaro,movimento,0,280,188);

	    batch.end();

	}
	
	@Override
	public void dispose () {

	}
}
