package com.dupuy.remi.outerspacemanager.Models;

/**
 * Created by rdupuy on 27/02/2018.
 */

public class Search {
    
    private long amountOfEffectByLevel;
    private long amountOfEffectLevel0;
    private boolean building;
    private String effect;
    private long gasCostByLevel;
    private long gasCostLevel0;
    private long level;
    private long mineralCostByLevel;
    private long mineralCostLevel0;
    private String name;
    private long timeToBuildByLevel;
    private long timeToBuildLevel0;

    public Search(long amountOfEffectByLevel, long amountOfEffectLevel0, boolean building, String effect, long gasCostByLevel, long gasCostLevel0, long level, long mineralCostByLevel, long mineralCostLevel0, String name, long timeToBuildByLevel, long timeToBuildLevel0) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel0 = gasCostLevel0;
        this.level = level;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel0 = mineralCostLevel0;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

    public long getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public void setAmountOfEffectByLevel(long amountOfEffectByLevel) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public long getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public void setAmountOfEffectLevel0(long amountOfEffectLevel0) {
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
    }

    public boolean isBuilding() {
        return building;
    }

    public void setBuilding(boolean building) {
        this.building = building;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public long getGasCostByLevel() {
        return gasCostByLevel;
    }

    public void setGasCostByLevel(long gasCostByLevel) {
        this.gasCostByLevel = gasCostByLevel;
    }

    public long getGasCostLevel0() {
        return gasCostLevel0;
    }

    public void setGasCostLevel0(long gasCostLevel0) {
        this.gasCostLevel0 = gasCostLevel0;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public void setMineralCostByLevel(long mineralCostByLevel) {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public long getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public void setMineralCostLevel0(long mineralCostLevel0) {
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel(long timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public long getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0(long timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }
}
