package com.uprightpath.fallingalchemy.event;

import com.uprightpath.fallingalchemy.physics.world.PhysicsWorld;

/**
 * Something that occurs that typically triggers other things within the world. These are
 * different from effects in that they may be scripted stories, the spawning of a new enemy
 * etc.
 */
public abstract class Event {

    /**
     * Whether the event interrupts control.
     */
    private boolean interruptsControl;

    /**
     * Getter for whether this event interrupts control.
     * @return
     */
    public boolean isInterruptsControl() {
        return interruptsControl;
    }

    /**
     * Setter for whether this event interrupts control.
     * @param interruptsControl
     */
    public void setInterruptsControl(boolean interruptsControl) {
        this.interruptsControl = interruptsControl;
    }

    /**
     * Checks whether the event can be performed.
     * @return True if the event can be performed.
     */
    public abstract boolean canPerformEvent();

    /**
     * Causes the event to be performed.
     * @param physicsWorld The world to trigger the event on.
     */
    public abstract void performEvent(PhysicsWorld physicsWorld);
}
