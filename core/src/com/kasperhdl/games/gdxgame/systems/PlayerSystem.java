package com.kasperhdl.games.gdxgame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.kasperhdl.games.gdxgame.components.BodyComponent;
import com.kasperhdl.games.gdxgame.components.PlayerComponent;

/**
 * Created by @Kasper on 05/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class PlayerSystem extends EntitySystem implements InputProcessor{

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PlayerComponent> playerMap = ComponentMapper.getFor(PlayerComponent.class);
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    public void addedToEngine(Engine engine){
        //noinspection unchecked
        entities = engine.getEntitiesFor(Family.all(PlayerComponent.class,BodyComponent.class).get());
    }

    public void update(float deltaTime){

        for(int i = 0;i<entities.size();i++) {
            Entity entity = entities.get(i);

            PlayerComponent playerComp = playerMap.get(entity);
            Body body = bodyMap.get(entity).body;

            float inputX = 0;
            float inputY = 0;

            if(playerComp.rightDown) {
                inputX = 1;
            }else if(playerComp.leftDown) {
                inputX = -1;
            }

            if(playerComp.upDown) {
                inputY = 1;
            }else if(playerComp.downDown) {
                inputY = -1;
            }

            Vector2 force = playerComp.force;

            body.applyTorque(-inputX * force.x,true);

            //rotate vector
            Vector2 forward = new Vector2(1, 0);
            float angle = body.getAngle();
            forward.rotateRad(angle);

            forward.scl(force.y * inputY);
            body.applyForceToCenter(forward, true);


        }
    }





    @Override
    public boolean keyDown(int keycode) {

        for(int i = 0;i<entities.size();i++){
            Entity entity = entities.get(i);
            PlayerComponent playerComp = playerMap.get(entity);

            if(keycode == playerComp.leftKey)playerComp.leftDown    = true;
            if(keycode == playerComp.rightKey)playerComp.rightDown  = true;
            if(keycode == playerComp.upKey)playerComp.upDown        = true;
            if(keycode == playerComp.downKey)playerComp.downDown    = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for(int i = 0;i<entities.size();i++){
            Entity entity = entities.get(i);
            PlayerComponent playerComp = playerMap.get(entity);

            if(keycode == playerComp.leftKey)playerComp.leftDown    = false;
            if(keycode == playerComp.rightKey)playerComp.rightDown  = false;
            if(keycode == playerComp.upKey)playerComp.upDown        = false;
            if(keycode == playerComp.downKey)playerComp.downDown    = false;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
