package com.uprightpath.fallingalchemy.physics.intelligence.player;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ObjectMap;
import com.uprightpath.fallingalchemy.physics.world.effects.DamageEffect;
import com.uprightpath.fallingalchemy.physics.intelligence.AgentLogic;
import com.uprightpath.fallingalchemy.physics.intelligence.AgentState;
import com.uprightpath.fallingalchemy.physics.world.PhysicsWorld;
import com.uprightpath.fallingalchemy.physics.world.TouchableEntity;
import com.uprightpath.fallingalchemy.physics.world.touchables.TimedTouchable;

/**
 * Created by Geo on 8/25/2014.
 */
public class PlayerAgent extends AgentLogic {
    protected AgentState currentState;
    protected ObjectMap<AgentState.AgentStateType, AgentState> AgentStates = new ObjectMap<AgentState.AgentStateType, AgentState>();

    public PlayerAgent() {
        AgentStates.put(AgentState.AgentStateType.WALKING, new WalkingState(this));
        AgentStates.put(AgentState.AgentStateType.FALLING, new FallingState(this));
        AgentStates.put(AgentState.AgentStateType.CLIMBING, new ClimbingState(this));
        currentState = AgentStates.get(AgentState.AgentStateType.FALLING);
    }

    public void useCurrentAttack(PhysicsWorld physicsWorld) {
        DamageEffect effect = new DamageEffect();
        effect.setOriginatingAgent(this);
        TouchableEntity entity = new TimedTouchable(effect, 5);
        entity.setCollisionPolygon(new Polygon(new float[] {0,0, 1,0, 1,1, 0,1}));
        if (this.isFacingLeft()) {
            entity.translate(this.getLeftBoundingSide());
            entity.translate(-1, 0);
        } else {
            entity.translate(this.getRightBoundingSide());
        }
        physicsWorld.getTouchables().add(entity);
    }
}
