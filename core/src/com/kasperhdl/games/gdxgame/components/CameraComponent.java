package com.kasperhdl.games.gdxgame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by @Kasper on 03/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class CameraComponent extends Component{
    public Entity target;
    public OrthographicCamera camera;
}
