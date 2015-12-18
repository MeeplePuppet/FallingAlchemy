package com.uprightpath.fallingalchemy.physics.world;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Geo on 12/11/2015.
 */
public class EffectEntity extends PhysicsEntity {
    @Override
    protected void updatePosition() {

    }

    @Override
    public Polygon getCollisionPolygon() {
        return null;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public boolean canCollide(AgentEntity agentEntity) {
        return false;
    }

    @Override
    public boolean canCollide(Polygon polygon) {
        return false;
    }

    @Override
    public Vector2 getVelocity() {
        return null;
    }
}
