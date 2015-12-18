package com.uprightpath.fallingalchemy.physics.agentstats;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.uprightpath.fallingalchemy.physics.world.effects.DamageEffect;
import com.uprightpath.fallingalchemy.physics.world.AgentEntity;
import com.uprightpath.fallingalchemy.physics.world.TouchableEntity;

/**
 * Created by Geo on 12/12/2015.
 */
public class DamageProfile {
    protected Polygon attackPolygon;

    protected int damage;

    public DamageProfile()
    {

    }

    public DamageProfile(Polygon attackShape, int damage)
    {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void createPolygon(AgentEntity agentEntity)
    {
        Rectangle boundingBox = agentEntity.getCollisionPolygon().getBoundingRectangle();
        attackPolygon = new Polygon(new float[]{0,0, 0,boundingBox.height, 1,boundingBox.height, 1,0});
    }

    public TouchableEntity getTouchableEntity(AgentEntity agentEntity)
    {
        TouchableEntity touchableEntity = new TouchableEntity();
        DamageEffect damageEffect = new DamageEffect();
        damageEffect.setDamage(damage);
        damageEffect.setOriginatingAgent(agentEntity);
        touchableEntity.setCollisionPolygon(new Polygon(attackPolygon.getVertices()));
        Vector2 attackPosition = new Vector2();
        if (agentEntity.isFacingLeft())
        {
            attackPosition.add(agentEntity.getLeftBoundingSide()).sub(attackPolygon.getBoundingRectangle().width, 0);
        }
        else
        {
            attackPosition.add(agentEntity.getRightBoundingSide());
        }
        touchableEntity.setPosition(attackPosition);
        return touchableEntity;
    }
}
