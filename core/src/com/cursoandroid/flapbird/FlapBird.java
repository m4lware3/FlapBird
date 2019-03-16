package com.cursoandroid.flapbird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlapBird extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture[] passaros;
    private Texture fundo;
    private Texture canoBaixo;
    private Texture canoTopo;
    private Random numeroRandomico;
    private BitmapFont fonte;
    private Circle passaroCirculo;
    private Rectangle canoTopoRetangulo;
    private Rectangle canoBaixoRetangulo;
    private ShapeRenderer shapeRenderer;

    //Atributos de configuracao
    private int larguraDispositivo;
    private int alturaDispositivo;
    private int estadoDoJogo;
    private int pontuacao = 0;

    private float variacao = 0;
    private float velocidadeQueda=0;
    private float posicaoInicialVertical;
    private float posicaoMovimentoCanoHorizontal;
    private float espacoEntreCanos;
    private float deltaTime;
    private float alturaEntreCanosRandomica;

    private boolean marcouPonto = false;

    @Override
    public void create () {

        batch = new SpriteBatch();
        numeroRandomico = new Random();
        passaroCirculo = new Circle();
        canoTopoRetangulo = new Rectangle();
        canoBaixoRetangulo = new Rectangle();
        shapeRenderer = new ShapeRenderer();

        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(16);

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
        variacao += deltaTime * 10;
        if (variacao > 2) variacao = 0;

        if(estadoDoJogo == 0){ //Não iinicia

            if( Gdx.input.justTouched()){
                estadoDoJogo = 1;
            }

        }else {

            deltaTime = Gdx.graphics.getDeltaTime();

            variacao += deltaTime * 10;
            posicaoMovimentoCanoHorizontal -= deltaTime * 200;
            velocidadeQueda++;

            if (Gdx.input.justTouched()) {
                velocidadeQueda = -15;
            }

            if (posicaoInicialVertical > 0 || velocidadeQueda < 0)
                posicaoInicialVertical = posicaoInicialVertical - velocidadeQueda;

            //Verifica se o cano saiu inteiramente da tela
            if (posicaoMovimentoCanoHorizontal < -canoTopo.getWidth()) {
                posicaoMovimentoCanoHorizontal = larguraDispositivo;
                alturaEntreCanosRandomica = numeroRandomico.nextInt(1600) - 800;
                marcouPonto = false;
            }

            //Verifica a pontuação
            if(posicaoMovimentoCanoHorizontal < 120){
                if(!marcouPonto){
                    pontuacao++;
                    marcouPonto = true;
                }
            }

        }
        batch.begin();

        batch.draw(fundo, 0, 0, larguraDispositivo, alturaDispositivo);
        batch.draw( canoTopo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 + espacoEntreCanos / 2 + alturaEntreCanosRandomica );
        batch.draw(canoBaixo, posicaoMovimentoCanoHorizontal, alturaDispositivo / 2 - canoBaixo.getHeight() - espacoEntreCanos / 2 + alturaEntreCanosRandomica );
        batch.draw( passaros[ (int) variacao ] , 120, posicaoInicialVertical );
        fonte.draw(batch, String.valueOf(pontuacao),larguraDispositivo / 2 - 50,alturaDispositivo - 50 );

        passaroCirculo.set(120 + passaros[0].getWidth() / 2, posicaoInicialVertical + passaros[0].getHeight() / 2, passaros[0].getWidth() /2 );

        batch.end();

        //Desenhar Formas de colisão
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(passaroCirculo.x,passaroCirculo.y,passaroCirculo.radius);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.end();


    }
}