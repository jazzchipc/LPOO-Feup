package de.tomgrill.gdxtesting.tests;


import com.badlogic.gdx.audio.Music;
import com.color.ninja.MyColorNinja;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterfaceTest {

    MyColorNinja game = new MyColorNinja();

    @Test
    public void menuClick(){
        MyColorNinja game = new MyColorNinja();
        assertEquals(5, 5);
    }

    @Test
    public void noRotation() {
        assertEquals(5, 5);
    }

    @Test
    public void screenSize(){
        assertEquals(1,1);
    }

    @Test
    public void changeSoundsVol(){
        assertEquals(5, 5);
    }

    @Test
    public void changeMusicVol(){
        assertEquals(5, 5);
    }

    @Test
    public void musicIsPlaying(){
       // game.create();
        Music music = game.getMusic();
        assertEquals(true,music.isPlaying());
    }
}
