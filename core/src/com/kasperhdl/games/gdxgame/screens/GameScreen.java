package com.kasperhdl.games.gdxgame.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kasperhdl.games.gdxgame.Assets;
import com.kasperhdl.games.gdxgame.GameWorld;
import com.kasperhdl.games.gdxgame.GdxGame;
import com.kasperhdl.games.gdxgame.Settings;
import com.kasperhdl.games.gdxgame.systems.RenderSystem;

/**
 * Created by @Kasper on 03/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class GameScreen extends ScreenAdapter {

    public enum Game_State{
        GAME_READY,
        GAME_RUNNING,
        GAME_PAUSED,
        GAME_OVER
    }

    private Game_State state;

    GdxGame game;

    OrthographicCamera guiCam;

    World world;

    GameWorld gameWorld;

    Engine engine;

    public GameScreen(GdxGame game){
        this.game = game;

        state = Game_State.GAME_READY;

        guiCam = new OrthographicCamera(Settings.screenWidth, Settings.screenHeight);
        guiCam.position.set(guiCam.viewportWidth/2,guiCam.viewportHeight/2,0);

        engine = new Engine();
        world = new World(new Vector2(0,0f),true);

        gameWorld = new GameWorld(engine,world);

        engine.addSystem(new RenderSystem(game.batch));

        gameWorld.create();



    }

    public void update(float deltaTime){

        engine.update(deltaTime);

        //camera control

        float velX = 0;
        float velY = 0;
        float speed = 10;

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            velX = -speed;
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            velX = speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            velY = -speed;
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            velY = speed;
        }


        engine.getSystem(RenderSystem.class).camera.translate(velX * deltaTime,velY * deltaTime);

    }

    public void drawUI () {
        guiCam.update();
        game.batch.setProjectionMatrix(guiCam.combined);
        game.batch.begin();

        float textWidth = Assets.font.getBounds("Game Running").width;
        Assets.font.draw(game.batch, "Game Running", Settings.screenWidth / 2 - textWidth / 2, 20);
        game.batch.end();

    }
    @Override
    public void render(float deltaTime){
        update(deltaTime);
        drawUI();
    }

}
