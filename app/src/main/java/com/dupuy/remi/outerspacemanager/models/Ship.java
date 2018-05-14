package com.dupuy.remi.outerspacemanager.models;

/**
 * Created by lledent on 23/01/2018.
 */

public class Ship {

    private Integer gasCost;
    private Integer life;
    private Integer maxAttack;
    private Integer minAttack;
    private Integer mineralCost;
    private String name;
    private Integer shipId;
    private Integer shield;
    private Integer spatioportLevelNeeded;
    private Integer speed;
    private Integer timeToBuild;

    public Ship() {
    }

    public Integer getGasCost() {
        return gasCost;
    }

    public void setGasCost(Integer gasCost) {
        this.gasCost = gasCost;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(Integer maxAttack) {
        this.maxAttack = maxAttack;
    }

    public Integer getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(Integer minAttack) {
        this.minAttack = minAttack;
    }

    public Integer getMineralCost() {
        return mineralCost;
    }

    public void setMineralCost(Integer mineralCost) {
        this.mineralCost = mineralCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public Integer getShield() {
        return shield;
    }

    public void setShield(Integer shield) {
        this.shield = shield;
    }

    public Integer getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public void setSpatioportLevelNeeded(Integer spatioportLevelNeeded) {
        this.spatioportLevelNeeded = spatioportLevelNeeded;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getTimeToBuild() {
        return timeToBuild;
    }

    public void setTimeToBuild(Integer timeToBuild) {
        this.timeToBuild = timeToBuild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (gasCost != null ? !gasCost.equals(ship.gasCost) : ship.gasCost != null) return false;
        if (life != null ? !life.equals(ship.life) : ship.life != null) return false;
        if (maxAttack != null ? !maxAttack.equals(ship.maxAttack) : ship.maxAttack != null)
            return false;
        if (minAttack != null ? !minAttack.equals(ship.minAttack) : ship.minAttack != null)
            return false;
        if (mineralCost != null ? !mineralCost.equals(ship.mineralCost) : ship.mineralCost != null)
            return false;
        if (name != null ? !name.equals(ship.name) : ship.name != null) return false;
        if (shipId != null ? !shipId.equals(ship.shipId) : ship.shipId != null) return false;
        if (shield != null ? !shield.equals(ship.shield) : ship.shield != null) return false;
        if (spatioportLevelNeeded != null ? !spatioportLevelNeeded.equals(ship.spatioportLevelNeeded) : ship.spatioportLevelNeeded != null)
            return false;
        if (speed != null ? !speed.equals(ship.speed) : ship.speed != null) return false;
        return timeToBuild != null ? timeToBuild.equals(ship.timeToBuild) : ship.timeToBuild == null;
    }

    @Override
    public int hashCode() {
        int result = gasCost != null ? gasCost.hashCode() : 0;
        result = 31 * result + (life != null ? life.hashCode() : 0);
        result = 31 * result + (maxAttack != null ? maxAttack.hashCode() : 0);
        result = 31 * result + (minAttack != null ? minAttack.hashCode() : 0);
        result = 31 * result + (mineralCost != null ? mineralCost.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (shipId != null ? shipId.hashCode() : 0);
        result = 31 * result + (shield != null ? shield.hashCode() : 0);
        result = 31 * result + (spatioportLevelNeeded != null ? spatioportLevelNeeded.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        result = 31 * result + (timeToBuild != null ? timeToBuild.hashCode() : 0);
        return result;
    }
}

