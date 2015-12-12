package com.uprightpath.fallingalchemy.physics;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.uprightpath.fallingalchemy.agentstats.AgentStats;
import com.uprightpath.fallingalchemy.display.RollingDamageText;
import com.uprightpath.fallingalchemy.scenario.Faction;

/**
 * An entity that is controlled by some internal logic such as enemies and player characters.
 * Created by Geo on 8/25/2014.
 */
public abstract class AgentEntity extends PhysicsEntity {
    protected AgentStats agentStats;
    protected RailEntity rail;
    protected Vector2 velocity = new Vector2();
    protected Vector2 acceleration = new Vector2();
    protected Faction faction;
    protected boolean fallThrough;
    protected boolean facingLeft;
    protected int fallTime;
    protected Array<RollingDamageText> rollingDamageTexts = new Array<RollingDamageText>();

    public AgentEntity() {
    }

    public AgentEntity(AgentStats agentStats)
    {
        this.agentStats = agentStats;
    }

    public abstract void applyStateLogic(PhysicsWorld physicsWorld);

    public abstract void updateState();

    public RailEntity getRail() {
        return rail;
    }

    public void setRail(RailEntity rail) {
        this.rail = rail;
    }


    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    public void applyDeltaVelocity(Vector2 deltaVelocity) {
        applyDeltaVelocity(deltaVelocity.x, deltaVelocity.y);
    }

    public void applyDeltaVelocity(float x, float y) {
        this.velocity.add(x, y);
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    @Override
    public boolean canCollide(AgentEntity physicsAgent) {
        return faction.isAgressiveTowards(physicsAgent.getFaction());
    }

    public abstract Polygon getPlatformCollisionPolygon();

    @Override
    public boolean canCollide(Polygon polygon) {
        return Intersector.overlaps(this.getCollisionPolygon().getBoundingRectangle(), polygon.getBoundingRectangle());
    }

    public boolean isFallThrough() {
        return fallThrough;
    }

    public void setFallThrough(boolean fallThrough) {
        this.fallThrough = fallThrough;
    }

    public int getFallTime() {
        return fallTime;
    }

    public void setFallTime(int fallTime) {
        this.fallTime = fallTime;
    }

    public abstract void collidedWithAgent(AgentEntity agentEntity);

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Array<RollingDamageText> getRollingDamageTexts() {
        return rollingDamageTexts;
    }

    public void setRollingDamageTexts(Array<RollingDamageText> rollingDamageTexts) {
        this.rollingDamageTexts = rollingDamageTexts;
    }

    public AgentStats getAgentStats() {
        return agentStats;
    }

    public void setAgentStats(AgentStats agentStats) {
        this.agentStats = agentStats;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public boolean isDestroyedThisTurn() {
        return agentStats.getCurrentHealth() <= 0;
    }
}
