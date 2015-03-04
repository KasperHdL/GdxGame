package com.kasperhdl.games.gdxgame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kasperhdl.games.gdxgame.components.BodyComponent;
import com.kasperhdl.games.gdxgame.components.SpriteComponent;
import com.kasperhdl.games.gdxgame.components.TextureComponent;
import com.kasperhdl.games.gdxgame.components.TransformComponent;
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

public class GameWorld {

    public enum World_State{
        RUNNING,
        NEXT_LEVEL,
        GAME_OVER
    }

    public World_State state;

    private World world;
    private Engine engine;

    public GameWorld(Engine engine,World world){
        this.engine = engine;
        this.world = world;
    }

    public void create(){
        //Entities
        for(int i = 0;i<1;i++){

            Vector2 pos = new Vector2(i*10, 0);
            Entity player = create_player(pos);

            engine.addEntity(player);

        }

    }
    public Entity create_player(Vector2 pos) {
        Entity entity = new Entity();

        Vector2 size = new Vector2(100, 100);
        pos.x -= size.x/2;
        pos.y -= size.y/2;

        TransformComponent transform = new TransformComponent();

        entity.add(transform);


        TextureComponent texture = new TextureComponent(0xbada55ff);
        texture.region.setRegionWidth((int) size.x);
        texture.region.setRegionHeight((int)size.y);

        entity.add(texture);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);

        BodyComponent bodyComp = new BodyComponent(world, BodyDef.BodyType.DynamicBody, shape, pos, 1f, 0.4f);
        entity.add(bodyComp);
        shape.dispose();
        return entity;

    }

}
