package com.kasperhdl.games.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by @Kasper on 03/03/2015
 * <p/>
 * Description:
 * ---
 * <p/>
 * Usage:
 * ---
 */

public class Settings {
    public final static String file = ".savefile";
    public static boolean soundEnabled = true;

    public static float screenWidth = 320;
    public static float screenHeight = 480;

    public static void load(){
        try{
            FileHandle fileHandle = Gdx.files.external(file);

            String[] strings = fileHandle.readString().split("\n");
            System.out.println(strings[0].substring(strings[0].indexOf(":") + 1));
            soundEnabled = Boolean.parseBoolean(strings[0].substring(strings[0].indexOf(": ") + 2));

        }catch(Throwable e){

        }
    }

    public static void save(){
        try{
            FileHandle fileHandle = Gdx.files.external(file);

            fileHandle.writeString("soundEnabled: " + Boolean.toString(soundEnabled)+"\n", false);

        }catch(Throwable e){

        }
    }
}
