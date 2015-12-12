package com.uprightpath.fallingalchemy.effects;

import com.badlogic.gdx.utils.Array;
import com.uprightpath.fallingalchemy.physics.AgentEntity;

/**
 * Created by Geo on 12/11/2015.
 */
public class WorldEffect extends Effect {
    /**
     * Agents that have already been affected by the effect.
     */
    protected Array<AgentEntity> hasAffected = new Array<AgentEntity>();

    /**
     * Applies the effect to the Agent.
     * @param agentEntity The agent.
     */
    public void applyEffect(AgentEntity agentEntity) {
        hasAffected.add(agentEntity);
    }

    /**
     * Checks whether the effect can be applies to the agent.
     * @param agentEntity The agent.
     * @return True if the effect can be applied.
     */
    public boolean canApplyEffect(AgentEntity agentEntity) { return agentEntity != originatingAgent && !hasAffected.contains(agentEntity, true); }
}
