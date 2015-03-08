package com.kasperhdl.games.gdxgame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.kasperhdl.games.gdxgame.components.BodyComponent;
import com.kasperhdl.games.gdxgame.components.CameraComponent;

/**
 * Created by @Kasper on 05/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class CameraSystem extends IteratingSystem {
    private ComponentMapper<CameraComponent> cameraMap = ComponentMapper.getFor(CameraComponent.class);
    private ComponentMapper<BodyComponent> bodyMap = ComponentMapper.getFor(BodyComponent.class);

    public CameraSystem() {
        //noinspection unchecked
        super(Family.all(CameraComponent.class).get());

        cameraMap = ComponentMapper.getFor(CameraComponent.class);
        bodyMap = ComponentMapper.getFor(BodyComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent cam = cameraMap.get(entity);

        if (cam.target == null) {
            return;
        }

        Body target = bodyMap.get(cam.target).body;

        if (target == null) {
            return;
        }
        Vector2 camPos = new Vector2(cam.camera.position.x,cam.camera.position.y);
        Vector2 diff = target.getPosition().sub(camPos);
        cam.camera.position.x = camPos.x + (diff.x * cam.drag);
        cam.camera.position.y = camPos.y + (diff.y * cam.drag);
    }
}
