package com.uprightpath.fallingalchemy.physics.world;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

/**
 * A solid object within the world; used to designate floors, walls and similar.
 */
public class CollisionEntity extends PhysicsEntity {

    /**
     * The entity's collision polygon.
     */
    protected Polygon collisionPolygon;

    /**
     * The entity's velocity.
     */
    protected Vector2 velocity = new Vector2();

    /**
     * Default constructor.
     */
    public CollisionEntity() {

    }

    @Override
    protected void updatePosition() {
        this.collisionPolygon.setPosition(position.x, position.y);
    }

    @Override
    public Polygon getCollisionPolygon() {
        return collisionPolygon;
    }

    /**
     * Setter for the collision polygon.
     * @param collisionPolygon The collision polygon.
     */
    public void setCollisionPolygon(Polygon collisionPolygon) {
        this.collisionPolygon = collisionPolygon;
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public boolean canCollide(AgentEntity agentEntity) {
        return canCollide(agentEntity.getCollisionPolygon());
    }

    @Override
    public boolean canCollide(Polygon collisionPolygon) {
        return Intersector.overlaps(this.collisionPolygon.getBoundingRectangle(), collisionPolygon.getBoundingRectangle());
    }

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Setter for the velocity.
     * @param velocity The new velocity (copied.)
     */
    public void setVelocity(Vector2 velocity) {
        this.velocity.set(velocity);
    }

    /**
     * Applies a change in velocity.
     * @param deltaVelocity The change in velocity.
     */
    public void applyDeltaVelocity(Vector2 deltaVelocity) {
        applyDeltaVelocity(deltaVelocity.x, deltaVelocity.y);
    }

    /**
     * Applies a change in velocity
     * @param x The X change.
     * @param y The Y change.
     */
    public void applyDeltaVelocity(float x, float y) {
        this.velocity.add(x, y);
    }
}
