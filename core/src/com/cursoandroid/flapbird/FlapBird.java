package com.cursoandroid.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlapBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;

    //Atributos de configuracao
    private int larguraDispositivo;
    private int alturaDispositivo;

    private float variacao = 0;
    private float velocidadeQueda=0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;

    @Override
    public void create () {

        batch = new SpriteBatch();
        numeroRandomico = new Random();
        passaros = new Texture[3];
        passaros[0] = new Texture("passaro1_2.png");
        passaros[1] = new Texture("passaro2_2.png");
        passaros[2] = new Texture("passaro3_2.png");

        fundo = new Texture("fundo.png");
        canoBaixo = new Texture("cano_baixo_2.png");
        canoTopo = new Texture("cano_topo_2.png");

        larguraDispositivo = Gdx.graphics.getWidth();
        alturaDispositivo  = Gdx.graphics.getHeight();
        posicaoInicialVertical = alturaDispositivo / 2;
        posicaoMovimentoCanoHorizontal = larguraDispositivo;
        espacoEntreCanos = 600;

    }

    @Override
    public void render () {

        deltaTime = Gdx.graphics.getDeltaTime();

        variacao += deltaTime * 10 ;
        posicaoMovimentoCanoHorizontal -= deltaTime * 200;
        velocidadeQueda++;

        if(variacao > 2) variacao = 0;

        if( Gdx.input.justTouched() ){
            velocidadeQueda = -15;
        }

        if(posicaoInicialVertical > 0 || velocidadeQueda < 0 )
            posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

        //Verifica se o cano saiu inteiramente da tela
        if(posicaoMovimentoCanoHorizontal < -canoTopo.getWidth() ){
            posicaoMovimentoCanoHorizontal = larguraDispositivo;
            alturaEntreCanosRandomica = numeroRandomico.nextInt(1600) - 800;
        }

        batch.begin();

        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw( canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica );
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2- canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica );
        batch.draw( passaros[ (int) variacao ] , 120, posicaoInicialVertical );

        batch.end();

    }
}