package com.uprightpath.fallingalchemy.physics.intelligence;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ObjectMap;
import com.uprightpath.fallingalchemy.physics.agentstats.DamageProfile;
import com.uprightpath.fallingalchemy.physics.agentstats.MovementProfile;
import com.uprightpath.fallingalchemy.physics.world.effects.DamageEffect;
import com.uprightpath.fallingalchemy.physics.world.AgentEntity;
import com.uprightpath.fallingalchemy.physics.world.PhysicsWorld;
import com.uprightpath.fallingalchemy.physics.world.RailEntity;
import com.uprightpath.fallingalchemy.physics.world.TouchableEntity;
import com.uprightpath.fallingalchemy.physics.world.touchables.TimedTouchable;

/**
 * Created by Geo on 8/25/2014.
 */
public class AgentLogic extends AgentEntity {
    protected AgentState currentState;
    protected ObjectMap<AgentState.AgentStateType, AgentState> agentStates = new ObjectMap<AgentState.AgentStateType, AgentState>();
    protected MovementProfile movementProfile = new MovementProfile();
    protected DamageProfile damageProfile = new DamageProfile();

    public AgentLogic() {
    }

    public void setPolygons(Polygon collisionPolygon, Polygon platformCollisionPolygon)
    {
        for (ObjectMap.Entry<AgentState.AgentStateType, AgentState> state : agentStates)
        {
            state.value.setPolygons(collisionPolygon, platformCollisionPolygon);
        }
    }

    @Override
    protected void updatePosition() {
        currentState.updatePosition();
    }

    @Override
    public Polygon getCollisionPolygon() {
        return currentState.getCollisionPolygon();
    }

    @Override
    public boolean isSolid() {
        return currentState.isSolid();
    }

    @Override
    public void applyStateLogic(PhysicsWorld physicsWorld) {
        acceleration.set(0, 0);
        currentState.applyLogic(physicsWorld);
        updateState();
        currentState.applyLimits(physicsWorld);
    }

    @Override
    public void updateState() {
        if (getRail() != null) {
            if (getRail().getRailType() == RailEntity.RailType.LADDER) {
                setState(AgentState.AgentStateType.CLIMBING);
            } else {
                setState(AgentState.AgentStateType.WALKING);
            }
        } else {
            setState(AgentState.AgentStateType.FALLING);
        }
        currentState.updatePosition();
    }

    @Override
    public Polygon getPlatformCollisionPolygon() {
        return currentState.getPlatformCollisionPolygon();
    }

    @Override
    public void collidedWithAgent(AgentEntity agentEntity) {
        currentState.collidedWithAgent(agentEntity);
    }

    public AgentState getState() {
        return currentState;
    }

    public void setState(AgentState.AgentStateType AgentStateType) {
        currentState = agentStates.get(AgentStateType);
        currentState.updatePosition();
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
