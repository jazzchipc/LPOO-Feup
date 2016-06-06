package com.color.ninja.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Animation
 */
public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    /**
     * Constructor
     * @param region All the frames combined into one image
     * @param frameCount Number of frames in the region
     * @param cycleTime How long it takes to go around the entire region
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth()/frameCount;
        for(int i = 0; i < frameCount;i++){
            frames.add(new TextureRegion(region,i * frameWidth,0,frameWidth,region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime/frameCount;
        frame = 0;
    }

    /**
     *Updates the animation
     * @param dt The change in time between render cycles
     */
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }

    /**
     *
     * @return The frame the animation is currently on
     */
    public  TextureRegion getFrame(){
        return frames.get(frame);
    }

}
