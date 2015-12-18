package com.uprightpath.fallingalchemy.physics.world.effects;

import com.uprightpath.fallingalchemy.physics.world.AgentEntity;

/**
 * A very general class for describing effects that may occur due to collisions.
 */
public abstract class Effect {
    /**
     * The agent that created the effect.
     */
    protected AgentEntity originatingAgent;


    public AgentEntity getOriginatingAgent() {
        return originatingAgent;
    }

    public void setOriginatingAgent(AgentEntity originatingAgent) {
        this.originatingAgent = originatingAgent;
    }

    /**
     * Checks whether the effect has completed.
     * @return True if the the effect is over.
     */
    public boolean isEffectOver() { return false; }

    /**
     * Applies the effect to the Agent.
     * @param agentEntity The agent.
     */
    public void applyEffect(AgentEntity agentEntity) {}

    /**
     * Checks whether the effect can be applies to the agent.
     * @param agentEntity The agent.
     * @return True if the effect can be applied.
     */
    public boolean canApplyEffect(AgentEntity agentEntity) { return false; }
}
