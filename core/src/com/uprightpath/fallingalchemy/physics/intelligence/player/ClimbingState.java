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
public class ClimbingState extends AgentState {
    protected Vector2 previousVelocity = new Vector2();
    protected Vector2 maximum = new Vector2(.01f, Float.MAX_VALUE);
    protected Vector2 minimum = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);


    public ClimbingState() {

    }

    public ClimbingState(PlayerAgent playerAgent) {
        super(playerAgent);
    }

    @Override
    public void applyLogic(PhysicsWorld physicsWorld) {
        previousVelocity.set(playerAgent.getVelocity());
        playerAgent.getVelocity().y = 0;
        playerAgent.getAcceleration().set(playerAgent.getRail().getSlip());
        if (Control.JUMP.isJustDown() && Control.DOWN.isDown()) {
            playerAgent.setRail(null);
            playerAgent.setFallThrough(true);
        } else if (Control.JUMP.isJustDown()) {
            playerAgent.getAcceleration().y = this.playerAgent.getAgentStats().getMovementProfile().getClimbAcceleration().y;
            playerAgent.setRail(null);
        }

        if (Control.LEFT.isDown()) {
            playerAgent.getAcceleration().x = -this.playerAgent.getAgentStats().getMovementProfile().getClimbAcceleration().x;
        } else if (Control.RIGHT.isDown()) {
            playerAgent.getAcceleration().x = this.playerAgent.getAgentStats().getMovementProfile().getClimbAcceleration().x;
        }
        if (!Control.DOWN.isDown()) {
            playerAgent.setFallThrough(false);
        }
        playerAgent.applyDeltaVelocity(playerAgent.getAcceleration());
    }

    @Override
    public void applyLimits(PhysicsWorld physicsWorld) {
        if (!(Control.LEFT.isDown() || Control.RIGHT.isDown())) {
            this.playerAgent.getVelocity().x = this.playerAgent.getVelocity().x * this.playerAgent.getRail().getFriction();
        }
        float maxX = Math.max(Math.abs(previousVelocity.x * playerAgent.getRail().getFriction()), Math.min(this.playerAgent.getAgentStats().getMovementProfile().getMaxClimbVelocity().x, maximum.x));
        float maxY = Math.max(Math.abs(previousVelocity.y * playerAgent.getRail().getFriction()), Math.min(this.playerAgent.getAgentStats().getMovementProfile().getMaxClimbVelocity().y, maximum.y));
        playerAgent.getVelocity().x = MathUtils.clamp(playerAgent.getVelocity().x, -maxX, maxX);
        playerAgent.getVelocity().y = MathUtils.clamp(playerAgent.getVelocity().y, -maxY, maxY);
        if (!(Control.LEFT.isDown() || Control.RIGHT.isDown()) && Math.abs(playerAgent.getVelocity().x) <= Math.min(physicsWorld.getGroundSpeedMin().x, minimum.x)) {
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
