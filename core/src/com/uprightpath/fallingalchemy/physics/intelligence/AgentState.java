package com.uprightpath.fallingalchemy.physics.intelligence;

import com.badlogic.gdx.math.Polygon;
import com.uprightpath.fallingalchemy.physics.intelligence.player.PlayerAgent;
import com.uprightpath.fallingalchemy.physics.world.AgentEntity;
import com.uprightpath.fallingalchemy.physics.world.PhysicsWorld;

/**
 * Created by Geo on 8/31/2014.
 */
public abstract class AgentState {
    protected Polygon collisionPolygon;
    protected Polygon platformCollisionPolygon;
    protected PlayerAgent playerAgent;

    public AgentState() {
    }

    public AgentState(PlayerAgent playerAgent) {
        this.playerAgent = playerAgent;
    }

    public void setPolygons(Polygon collisionPolygon, Polygon platformCollisionPolygon) {
        this.collisionPolygon = collisionPolygon;
        this.platformCollisionPolygon = platformCollisionPolygon;
    }

    public PlayerAgent getPlayerAgent() {
        return playerAgent;
    }

    public void setPlayerAgent(PlayerAgent playerAgent) {
        this.playerAgent = playerAgent;
    }

    public abstract void applyLogic(PhysicsWorld physicsWorld);

    public abstract void applyLimits(PhysicsWorld physicsWorld);

    public void updatePosition() {
        collisionPolygon.setPosition(playerAgent.getPosition().x, playerAgent.getPosition().y);
        platformCollisionPolygon.setPosition(playerAgent.getPosition().x, playerAgent.getPosition().y);
    }

    public Polygon getCollisionPolygon() {
        return collisionPolygon;
    }

    public abstract boolean isSolid();

    public Polygon getPlatformCollisionPolygon() {
        return platformCollisionPolygon;
    }

    public abstract void collidedWithAgent(AgentEntity agentEntity);

    public enum AgentStateType {FALLING, WALKING, CLIMBING}
}
