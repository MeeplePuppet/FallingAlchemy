package com.uprightpath.fallingalchemy.physics;

import com.uprightpath.fallingalchemy.controls.Control;

/**
 * Defines an object that can only be collided with from above or when the correct key-stroke is applied. The intent is
 * for this object to be used to define stairs that require a modifier to climb (IE- ones that can be walked through
 * when that modifier is not held down.)
 */
public class EdgePlatformEntity extends PlatformEntity {
    private boolean leftSide;
    private Control requiredModifier;

    public EdgePlatformEntity() {

    }

    public EdgePlatformEntity(boolean leftSide) {
        this.leftSide = leftSide;
    }

    public boolean isLeftSide() {
        return leftSide;
    }

    public void setLeftSide(boolean leftSide) {
        this.leftSide = leftSide;
    }

    public Control getRequiredModifier() {
        return requiredModifier;
    }

    public void setRequiredModifier(Control requiredModifier) {
        this.requiredModifier = requiredModifier;
    }

    public boolean canPlatformCollide(AgentEntity agentEntity) {
        return (leftSide ? (start.x < agentEntity.getRightBoundingSide().x && end.x >= agentEntity.getRightBoundingSide().x) : (start.x <= agentEntity.getLeftBoundingSide().x && end.x > agentEntity.getLeftBoundingSide().x)) && super.canPlatformCollide(agentEntity);
    }

    public int getPriorityImplement(AgentEntity agentEntity) {
        return requiredModifier.isDown() ? 1 : -1;
    }
}
