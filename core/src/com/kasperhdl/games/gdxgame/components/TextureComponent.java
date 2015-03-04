package com.kasperhdl.games.gdxgame.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by @Kasper on 04/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class TextureComponent extends Component {
    public TextureRegion region = null;

    public TextureComponent(){}
    public TextureComponent(int color){
        Pixmap pix = new Pixmap(1,1,Pixmap.Format.RGBA8888);
        pix.setColor(color);
        pix.fill();
        region = new TextureRegion(new Texture(pix));

    }

}
