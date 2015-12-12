package com.uprightpath.fallingalchemy.physics;

import com.badlogic.gdx.utils.Array;

/**
 * A set of connected rail entities (typically 'floors') that are used to control lateral motion without requiring the
 * use of the physics engine to 'hold' the entity on the ground.
 */
public class RailSeries {
    /**
     * The rail series name.
     */
    protected String name;

    /**
     * The set of entities that belong to this rail series.
     */
    protected Array<RailEntity> railEntities = new Array<RailEntity>();

    /**
     * Default constructor.
     */
    public RailSeries() {

    }

    /**
     * Constructor that supplies a name.
     * @param name The name.
     */
    public RailSeries(String name) {
        this.name = name;
    }

    /**
     * Getter for the name.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name.
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds a rail entity to the series and associates it with this series.
     * @param railEntity The rail entity to add.
     */
    public void addRailEntity(RailEntity railEntity) {
        railEntities.removeValue(railEntity, true);
        railEntities.add(railEntity);
        railEntity.setRailSeries(this);
    }

    /**
     * Getter for the rail entities.
     * @return
     */
    public Array<RailEntity> getRailEntities() {
        return railEntities;
    }

    /**
     * Removes a rail entity from the series and removes the association.
     * @param railEntity
     */
    public void removeRailEntity(RailEntity railEntity) {
        railEntities.removeValue(railEntity, true);
        railEntity.setRailSeries(null);
    }
}
