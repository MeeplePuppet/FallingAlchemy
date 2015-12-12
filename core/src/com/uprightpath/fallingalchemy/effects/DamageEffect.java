package com.uprightpath.fallingalchemy.effects;

import com.uprightpath.fallingalchemy.physics.AgentEntity;

/**
 * Created by Geo on 12/11/2015.
 */
public class DamageEffect extends WorldEffect {
    /**
     * Applies the effect to the Agent.
     * @param agentEntity The agent.
     */
    public void applyEffect(AgentEntity agentEntity) {
        super.applyEffect(agentEntity);
        agentEntity.getAgentStats().setCurrentHealth(agentEntity.getAgentStats().getCurrentHealth() - 1);
    }
}
