package com.kasperhdl.games.gdxgame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.kasperhdl.games.gdxgame.Settings;
import com.kasperhdl.games.gdxgame.components.BodyComponent;
import com.kasperhdl.games.gdxgame.components.TextureComponent;
import com.kasperhdl.games.gdxgame.components.TransformComponent;

/**
 * Created by @Kasper on 03/03/2015
 *
 * Description:
 * ---
 *
 * Usage:
 * ---
 *
 */

 public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<TextureComponent> textureMap = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    private SpriteBatch batch;

    public OrthographicCamera camera;

    private Box2DDebugRenderer debugRenderer;
    private Matrix4 debugMatrix;


    public RenderSystem(SpriteBatch batch){
        this.batch = batch;

        camera = new OrthographicCamera(Gdx.graphics.getWidth() * Settings.pixelToMeter,Gdx.graphics.getHeight() * Settings.pixelToMeter);
        debugMatrix = new Matrix4(camera.combined);
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawVelocities(true);
        debugRenderer.setDrawBodies(true);

    }

    @Override
    public void addedToEngine(Engine engine){
        //noinspection unchecked
        entities = engine.getEntitiesFor(Family.all(TextureComponent.class, BodyComponent.class, TransformComponent.class).get());
    }

    public void update(float deltaTime){
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //debugRenderer.render(world, debugMatrix);

        for(int i = 0;i<entities.size();i++){
            Entity entity = entities.get(i);

            TextureRegion tex = textureMap.get(entity).region;
            if(tex == null)
                continue;
            Body body = bodyMap.get(entity).body;
            TransformComponent t = transformMap.get(entity);

            float width = tex.getRegionWidth() * Settings.pixelToMeter;
            float height = tex.getRegionHeight()* Settings.pixelToMeter;

            Vector2 origin = new Vector2(width/2,height/2);
            Vector2 bodyPos = body.getPosition().sub(origin);

            batch.draw(tex,bodyPos.x,bodyPos.y,origin.x,origin.y,width,height,t.scale.x,t.scale.y,(body.getAngle()/ MathUtils.PI) * 180);
        }

        batch.end();
    }

    public OrthographicCamera getCamera(){
        return camera;
    }
}
