package com.uprightpath.fallingalchemy.agentstats;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Geo on 9/2/2014.
 */
public abstract class AgentStats {
    protected int currentHealth;

    protected int maxHealth;

    protected HashMap<String, Integer> additionalStats;

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

}