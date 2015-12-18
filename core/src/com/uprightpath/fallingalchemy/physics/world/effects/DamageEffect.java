package com.uprightpath.fallingalchemy.physics.world.effects;

import com.uprightpath.fallingalchemy.physics.world.AgentEntity;

/**
 * Created by Geo on 12/11/2015.
 */
public class DamageEffect extends WorldEffect {
    protected int damage;

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Applies the effect to the Agent.
     *
     * @param agentEntity The agent.
     */
    public void applyEffect(AgentEntity agentEntity) {
        super.applyEffect(agentEntity);
        agentEntity.getAgentStats().setCurrentHealth(agentEntity.getAgentStats().getCurrentHealth() - damage);
    }
}
