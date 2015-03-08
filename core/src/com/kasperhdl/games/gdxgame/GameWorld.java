package com.kasperhdl.games.gdxgame;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kasperhdl.games.gdxgame.components.*;
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

        Vector2 pos = new Vector2(0, 0);

        Entity player = create_player(pos);
        create_camera(player);

        engine.addEntity(player);

        //create_box(10,10,1);
        create_box(1001,6,1);

        for(int i = 0;i<100;i++){
            create_box_filled(new Vector2(i*10,-5),new Vector2(1,1));
            create_box_filled(new Vector2(i*10,5),new Vector2(1,1));
        }


    }
    public Entity create_player(Vector2 pos) {
        Entity entity = new Entity();

        Vector2 size = new Vector2(1, .3f);

        TransformComponent transform = new TransformComponent();

        entity.add(transform);

        PlayerComponent player = new PlayerComponent();
        player.leftKey = Input.Keys.LEFT;
        player.rightKey = Input.Keys.RIGHT;
        player.upKey = Input.Keys.UP;
        player.downKey= Input.Keys.DOWN;

        entity.add(player);

        TextureComponent texture = new TextureComponent(0xbadaffff);
        texture.region.setRegionWidth((int) (Settings.meterToPixel * size.x));
        texture.region.setRegionHeight((int)(Settings.meterToPixel *  size.y));

        entity.add(texture);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);



        BodyComponent bodyComp = new BodyComponent(world, BodyDef.BodyType.DynamicBody, shape, pos, .4f, .1f);
        bodyComp.body.setLinearDamping(0.8f);

        entity.add(bodyComp);
        shape.dispose();
        return entity;

    }

    public void create_box(float width, float height, float thickness){
        Vector2 size = new Vector2(width,thickness);
        Vector2 pos = new Vector2(0,-height/2);
        create_box_filled(pos, size);
        pos = new Vector2(0,height/2);
        create_box_filled(pos, size);

        size = new Vector2(thickness,height);
        pos = new Vector2(width/2,0);
        create_box_filled(pos, size);
        pos = new Vector2(-width/2,0);
        create_box_filled(pos, size);
    }

    public void create_box_filled(Vector2 pos, Vector2 size){

        Entity entity = new Entity();

        TransformComponent transform = new TransformComponent();
        entity.add(transform);

        TextureComponent texture = new TextureComponent(0xffda55ff);
        texture.region.setRegionWidth((int) (Settings.meterToPixel * size.x));
        texture.region.setRegionHeight((int)(Settings.meterToPixel *  size.y));

        entity.add(texture);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);

        BodyComponent body = new BodyComponent(world, BodyDef.BodyType.StaticBody, shape, pos, 1f, 0f);
        entity.add(body);
        shape.dispose();

        engine.addEntity(entity);
    }

    public void create_camera(Entity target){
        Entity entity = new Entity();

        CameraComponent camera = new CameraComponent();
        camera.camera = engine.getSystem(RenderSystem.class).getCamera();
        camera.target = target;
        camera.drag = 0.4f;

        entity.add(camera);

        engine.addEntity(entity);
    }

}
