package com.kasperhdl.games.gdxgame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kasperhdl.games.gdxgame.screens.MainMenuScreen;


public class GdxGame extends Game {

    public SpriteBatch batch;

    @Override
	public void create () {

        batch = new SpriteBatch();

        Settings.load();
        Assets.load();

        setScreen(new MainMenuScreen(this));

    }

	@Override
	public void render () {
        GL20 gl = Gdx.gl;
        gl.glClearColor(.2f, .2f, .2f, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }

}
