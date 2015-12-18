package com.uprightpath.fallingalchemy.physics.intelligence.player;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.uprightpath.fallingalchemy.controls.Control;
import com.uprightpath.fallingalchemy.physics.intelligence.AgentState;
import com.uprightpath.fallingalchemy.physics.world.AgentEntity;
import com.uprightpath.fallingalchemy.physics.world.PhysicsWorld;

/**
 * Created by Geo on 8/31/2014.
 */
public class FallingState extends AgentState {
    protected Vector2 previousVelocity = new Vector2();
    protected Vector2 minimum = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);

    public FallingState() {
    }

    public FallingState(PlayerAgent playerAgent) {
        super(playerAgent);
    }

    @Override
    public void applyLogic(PhysicsWorld physicsWorld) {
        previousVelocity.set(playerAgent.getVelocity());
        if (Control.JUMP.isDown() && playerAgent.getVelocity().y > 0) {
            playerAgent.getAcceleration().y = this.playerAgent.getAgentStats().getMovementProfile().getFallAcceleration().y;
        }
        if (!Control.DOWN.isDown()) {
            playerAgent.setFallThrough(false);
        }
        if (Control.LEFT.isDown()) {
            playerAgent.getAcceleration().x = -this.playerAgent.getAgentStats().getMovementProfile().getFallAcceleration().x;
        } else if (Control.RIGHT.isDown()) {
            playerAgent.getAcceleration().x = this.playerAgent.getAgentStats().getMovementProfile().getFallAcceleration().x;
        }
        if (!Control.DOWN.isDown()) {
            playerAgent.setFallThrough(false);
        }
        playerAgent.applyDeltaVelocity(playerAgent.getAcceleration());
    }

    @Override
    public void applyLimits(PhysicsWorld physicsWorld) {
        if (Control.ATTACK.isJustDown())
        {
            this.playerAgent.useCurrentAttack(physicsWorld);
        }
        if (Control.LEFT.isDown())
        {
            this.playerAgent.setFacingLeft(true);
        } else if (Control.RIGHT.isDown())
        {
            this.playerAgent.setFacingLeft(false);
        }
        if (!(Control.LEFT.isDown() || Control.RIGHT.isDown())) {
            this.playerAgent.getVelocity().x = this.playerAgent.getVelocity().x * physicsWorld.getAirFriction().x;
        }
        float maxX = Math.max(Math.abs(previousVelocity.x * physicsWorld.getAirFriction().x), Math.min(physicsWorld.getAirSpeedMax().x, this.playerAgent.getAgentStats().getMovementProfile().getMaxFallVelocity().x));
        float maxY = Math.max(Math.abs(previousVelocity.y), Math.min(physicsWorld.getAirSpeedMax().y, this.playerAgent.getAgentStats().getMovementProfile().getMaxFallVelocity().y));
        playerAgent.getVelocity().x = MathUtils.clamp(playerAgent.getVelocity().x, -maxX, maxX);
        playerAgent.getVelocity().y = MathUtils.clamp(playerAgent.getVelocity().y, -maxY, maxY);
        if (!(Control.LEFT.isDown() || Control.RIGHT.isDown()) && Math.abs(playerAgent.getVelocity().x) <= Math.min(physicsWorld.getAirSpeedMin().x, minimum.x)) {
            playerAgent.getVelocity().x = 0;
        }
    }

    @Override
    public boolean isSolid() {
        return true;
    }

    @Override
    public void collidedWithAgent(AgentEntity agentEntity) {

    }
}
