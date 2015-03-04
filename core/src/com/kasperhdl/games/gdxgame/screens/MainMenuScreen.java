package com.kasperhdl.games.gdxgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.kasperhdl.games.gdxgame.Assets;
import com.kasperhdl.games.gdxgame.GdxGame;
import com.kasperhdl.games.gdxgame.Settings;

import static com.badlogic.gdx.Input.*;

/**
 * Created by @Kasper on 03/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class MainMenuScreen extends ScreenAdapter{
    GdxGame game;
    OrthographicCamera guiCam;

    public MainMenuScreen(GdxGame game){
        this.game = game;

        guiCam = new OrthographicCamera(Settings.screenWidth,Settings.screenHeight);
        guiCam.position.set(guiCam.viewportWidth/2,guiCam.viewportHeight/2,0);
    }

    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new GameScreen(game));
            return;
        }
    }

    public void draw(){
        GL20 gl = Gdx.gl;
        gl.glClearColor(.2f,.2f,.2f,1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);

        game.batch.enableBlending();
        game.batch.begin();
        float textWidth = Assets.font.getBounds("Press space to Start").width;
        Assets.font.draw(game.batch, "Press space to Start", Settings.screenWidth / 2 - textWidth / 2, 20);
        game.batch.end();

    }

    @Override
    public void render(float deltaTime){
        update();
        draw();
    }

    @Override
    public void pause () {
        Settings.save();
    }

}
