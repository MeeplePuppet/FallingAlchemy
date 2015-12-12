package com.uprightpath.fallingalchemy.physics;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.uprightpath.fallingalchemy.effects.Effect;

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
