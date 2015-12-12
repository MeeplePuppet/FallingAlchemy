package com.uprightpath.fallingalchemy.physics.touchables;

import com.uprightpath.fallingalchemy.effects.Effect;
import com.uprightpath.fallingalchemy.physics.TouchableEntity;

/**
 * Created by Geo on 12/11/2015.
 */
public class TimedTouchable extends TouchableEntity{
    protected Effect attackEffect;
    protected int frames;

    public TimedTouchable(Effect attackEffect, int frames)
    {
        this.attackEffect = attackEffect;
        this.frames = frames;
    }

    /**
     * The possible effect for colliding with the entity.
     * @return The effect.
     */
    public Effect collisionEffect() { return attackEffect; }

    public boolean isDestroyedThisTurn() {
        return frames-- <= 0;
    }

    /**
     * The possible effect for colliding with the entity.
     * @return The effect.
     */
}
