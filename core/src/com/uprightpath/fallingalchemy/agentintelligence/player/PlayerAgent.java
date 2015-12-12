package com.uprightpath.fallingalchemy.agentintelligence.player;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.ObjectMap;
import com.sun.glass.ui.SystemClipboard;
import com.uprightpath.fallingalchemy.effects.DamageEffect;
import com.uprightpath.fallingalchemy.physics.AgentEntity;
import com.uprightpath.fallingalchemy.physics.PhysicsWorld;
import com.uprightpath.fallingalchemy.physics.RailEntity;
import com.uprightpath.fallingalchemy.physics.TouchableEntity;
import com.uprightpath.fallingalchemy.physics.touchables.TimedTouchable;

/**
 * Created by Geo on 8/25/2014.
 */
public class PlayerAgent extends AgentEntity {
    protected PlayerState currentState;
    protected ObjectMap<PlayerState.PlayerStateType, PlayerState> playerStates = new ObjectMap<PlayerState.PlayerStateType, PlayerState>();

    public PlayerAgent() {
        playerStates.put(PlayerState.PlayerStateType.WALKING, new WalkingState(this));
        playerStates.put(PlayerState.PlayerStateType.FALLING, new FallingState(this));
        playerStates.put(PlayerState.PlayerStateType.CLIMBING, new ClimbingState(this));
        currentState = playerStates.get(PlayerState.PlayerStateType.FALLING);
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
                setState(PlayerState.PlayerStateType.CLIMBING);
            } else {
                setState(PlayerState.PlayerStateType.WALKING);
            }
        } else {
            setState(PlayerState.PlayerStateType.FALLING);
        }
    }

    @Override
    public Polygon getPlatformCollisionPolygon() {
        return currentState.getPlatformCollisionPolygon();
    }

    @Override
    public void collidedWithAgent(AgentEntity agentEntity) {
        currentState.collidedWithAgent(agentEntity);
    }

    public PlayerState getState() {
        return currentState;
    }

    public void setState(PlayerState.PlayerStateType playerStateType) {
        currentState = playerStates.get(playerStateType);
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
