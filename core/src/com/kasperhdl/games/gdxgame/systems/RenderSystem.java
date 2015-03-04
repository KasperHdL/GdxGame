package com.kasperhdl.games.gdxgame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kasperhdl.games.gdxgame.Settings;
import com.kasperhdl.games.gdxgame.components.SpriteComponent;
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
    //private ComponentMapper<SpriteComponent> spriteMap = ComponentMapper.getFor(SpriteComponent.class);
    private ComponentMapper<TextureComponent> textureMap = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<TransformComponent> transformMap = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    private SpriteBatch batch;

    public OrthographicCamera camera;

    static final float FRUSTUM_WIDTH = 10;
    static final float FRUSTUM_HEIGHT = 10;

    public float pixelsPerMeter = 1.0f / 100.0f;


    public RenderSystem(SpriteBatch batch){
        this.batch = batch;

        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(0, 0, 0);
    }

    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(TextureComponent.class, BodyComponent.class, TransformComponent.class).get());
    }

    public void update(float deltaTime){
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for(int i = 0;i<entities.size();i++){
            Entity entity = entities.get(i);
            
           //Sprite sprite = spriteMap.get(entity).sprite;
            TextureRegion tex = textureMap.get(entity).region;
            if(tex == null)continue;
            Body body = bodyMap.get(entity).body;
            TransformComponent t = transformMap.get(entity);

            //sprite.setPosition(body.getPosition().x, body.getPosition().y);


            //batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getWidth(),sprite.getHeight(),t.scale.x * pixelsPerMeter,t.scale.y * pixelsPerMeter, MathUtils.radiansToDegrees * t.rotation);

            float width = tex.getRegionWidth();
            float height = tex.getRegionHeight();

            Vector2 origin = new Vector2(width/2,height/2);
            Vector2 bodyPos = body.getPosition();
            batch.draw(tex,bodyPos.x,bodyPos.y,origin.x,origin.y,width,height,t.scale.x * pixelsPerMeter,t.scale.y * pixelsPerMeter,t.rotation);
        }

        batch.end();
    }
}
