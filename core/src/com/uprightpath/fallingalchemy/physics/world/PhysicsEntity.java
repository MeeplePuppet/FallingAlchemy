package com.uprightpath.fallingalchemy.physics.world;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.uprightpath.fallingalchemy.physics.world.effects.Effect;

/**
 * An entity that exists within the physics world. These objects are used to control collisions/interactions.
 * Created by Geo on 8/25/2014.
 */
public abstract class PhysicsEntity {
    /**
     * The entity's name.
     */
    protected String name;

    /**
     * The entity's current position (bottom left.)
     */
    protected Vector2 position = new Vector2();

    /**
     * The entity's left bounding side (used for rail collisions.)
     */
    protected Vector2 leftBoundingSide = new Vector2();

    /**
     * The entity's right bounding side (used for rail collisions.)
     */
    protected Vector2 rightBoundingSide = new Vector2();

    /**
     * The previous entity's left bounding side (used for rail collisions.)
     */
    protected Vector2 leftBoundingSidePrevious = new Vector2();

    /**
     * The previous entity's right bounding side (used for rail collisions.)
     */
    protected Vector2 rightBoundingSidePrevious = new Vector2();

    /**
     * Default constructor.
     */
    public PhysicsEntity() {

    }

    /**
     * Constructor taking the name.
     * @param name The name of the entity.
     */
    public PhysicsEntity(String name) {
        this.name = name;
    }

    /**
     * Getter for the position.
     * @return The position.
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * Setter for the position.
     * @param position The new position (copied not assigned.)
     */
    public void setPosition(Vector2 position) {
        this.setPosition(position.x, position.y);
    }

    /**
     * Updates the previous bounds; used for rail collisions.
     */
    public void updatePreviousBoundings() {
        leftBoundingSidePrevious.set(leftBoundingSide);
        rightBoundingSidePrevious.set(rightBoundingSide);
    }

    /**
     * Setter for the position.
     * @param x The X position.
     * @param y The Y position.
     */
    public void setPosition(float x, float y) {
        this.position.set(x, y);
        this.updatePosition();
    }

    /**
     * Translates the entity's position by the vector.
     * @param translationVector The vector that determines the distance of translation.
     */
    public void translate(Vector2 translationVector) {
        this.translate(translationVector.x, translationVector.y);
    }

    /**
     * Translate the entity's position.
     * @param x The X component of translation.
     * @param y The Y component of translation.
     */
    public void translate(float x, float y) {
        this.position.add(x, y);
        this.updatePosition();
    }

    /**
     * Updates the object's collision polygon based on updates to the position.
     */
    protected abstract void updatePosition();

    /**
     * The object's collision polygon.
     * @return The collision polygon.
     */
    public abstract Polygon getCollisionPolygon();

    /**
     * Getter for the name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name.
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Indicates whether colliding with this object triggers displacement.
     * @return True if the object is solid in the physics engine.
     */
    public abstract boolean isSolid();

    /**
     * Indicates whether an agent can collide with this entity.
     * @param agentEntity The possibly colliding agent.
     * @return True if the agent can collide.
     */
    public abstract boolean canCollide(AgentEntity agentEntity);

    /**
     * Indicates whether a polygon can collide with this entity.
     * @param polygon The possibly colliding polygon.
     * @return True if the polygon can collide.
     */
    public abstract boolean canCollide(Polygon polygon);

    /**
     * Getter for the velocity of the entity.
     * @return The entity's velocity.
     */
    public abstract Vector2 getVelocity();

    /**
     * Getter for the left bounding side.
     * @return
     */
    public Vector2 getLeftBoundingSide() {
        return leftBoundingSide.set(position);
    }

    /**
     * Getter for the right bounding side.
     * @return
     */
    public Vector2 getRightBoundingSide() {
        return rightBoundingSide.set(position).add(getCollisionPolygon().getBoundingRectangle().width, 0);
    }

    /**
     * The possible effect for colliding with the entity.
     * @return The effect.
     */
    public Effect collisionEffect() { return null; }

    public boolean isDestroyedThisTurn() {
        return false;
    }
}
