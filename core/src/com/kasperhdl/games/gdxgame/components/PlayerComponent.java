package com.kasperhdl.games.gdxgame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Kasper on 05/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class PlayerComponent extends Component {
    public int leftKey;
    public int rightKey;
    public int upKey;
    public int downKey;

    public boolean leftDown = false;
    public boolean rightDown = false;
    public boolean upDown = false;
    public boolean downDown = false;

    public Vector2 force = new Vector2(.2f,5f);
}
