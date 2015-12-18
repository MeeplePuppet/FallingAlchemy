package com.uprightpath.fallingalchemy.physics.world;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by Geo on 12/11/2015.
 */
public class TouchableEntity extends CollisionEntity {


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
}
