package com.uprightpath.fallingalchemy.physics.agentstats;

import java.util.HashMap;

/**
 * Created by Geo on 9/2/2014.
 */
public abstract class AgentStats {
    protected int currentHealth;

    protected int maxHealth;
    protected DamageProfile damageProfile;

    protected MovementProfile movementProfile;

    protected HashMap<String, Integer> additionalStats = new HashMap<String, Integer>();

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        System.out.println("Health changed!");
    }

    public HashMap<String, Integer> getAdditionalStats() {
        return additionalStats;
    }

    public void setAdditionalStats(String statName, int statValue) {
        additionalStats.put(statName, statValue);
    }

    public void incrementAdditionalStats(String statName, int statValue)
    {
        additionalStats.put(statName, additionalStats.getOrDefault(statName, 0) + statValue);
    }

    public MovementProfile getMovementProfile() {
        return movementProfile;
    }

    public void setMovementProfile(MovementProfile movementProfile) {
        this.movementProfile = movementProfile;
    }

    public DamageProfile getDamageProfile() {
        return damageProfile;
    }

    public void setDamageProfile(DamageProfile damageProfile) {
        this.damageProfile = damageProfile;
    }

    public abstract void updateProfiles();


}