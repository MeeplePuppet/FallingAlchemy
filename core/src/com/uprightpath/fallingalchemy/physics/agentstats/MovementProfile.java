package com.uprightpath.fallingalchemy.physics.agentstats;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Geo on 12/12/2015.
 */
public class MovementProfile {
    protected Vector2 groundAcceleration = new Vector2();
    protected Vector2 fallAcceleration = new Vector2();
    protected Vector2 climbAcceleration = new Vector2();
    protected Vector2 maxGroundVelocity = new Vector2();
    protected Vector2 maxFallVelocity = new Vector2();
    protected Vector2 maxClimbVelocity = new Vector2();

    public Vector2 getMaxClimbVelocity() {
        return maxClimbVelocity;
    }

    public void setMaxClimbVelocity(Vector2 maxClimbVelocity) { this.maxClimbVelocity = maxClimbVelocity; }

    public Vector2 getGroundAcceleration() {
        return groundAcceleration;
    }

    public void setGroundAcceleration(Vector2 groundAcceleration) {
        this.groundAcceleration = groundAcceleration;
    }

    public Vector2 getFallAcceleration() {
        return fallAcceleration;
    }

    public void setFallAcceleration(Vector2 fallAcceleration) {
        this.fallAcceleration = fallAcceleration;
    }

    public Vector2 getClimbAcceleration() {
        return climbAcceleration;
    }

    public void setClimbAcceleration(Vector2 climbAcceleration) {
        this.climbAcceleration = climbAcceleration;
    }

    public Vector2 getMaxGroundVelocity() {
        return maxGroundVelocity;
    }

    public void setMaxGroundVelocity(Vector2 maxGroundVelocity) {
        this.maxGroundVelocity = maxGroundVelocity;
    }

    public Vector2 getMaxFallVelocity() {
        return maxFallVelocity;
    }

    public void setMaxFallVelocity(Vector2 maxFallVelocity) {
        this.maxFallVelocity = maxFallVelocity;
    }
}
