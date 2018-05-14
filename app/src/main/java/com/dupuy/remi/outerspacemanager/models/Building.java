package com.dupuy.remi.outerspacemanager.models;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by lledent on 23/01/2018.
 */

public class Building extends BaseModel {
    private Integer level;
    private Integer amountOfEffectByLevel;
    private Integer amountOfEffectLevel0;
    private Integer buildingId;
    private Boolean building;
    private String effect;
    private Integer gasCostByLevel ;
    private Integer gasCostLevel0;
    private String imageUrl;
    private Integer mineralCostByLevel;
    private Integer mineralCostLevel0;
    private String name;
    private Integer timeToBuildByLevel;
    private Integer timeToBuildLevel0;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public void setAmountOfEffectByLevel(Integer amountOfEffectByLevel) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public Integer getAmountOfEffectLevel0() {
        return amountOfEffectLevel0;
    }

    public void setAmountOfEffectLevel0(Integer amountOfEffectLevel0) {
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Boolean getBuilding() {
        return building;
    }

    public void setBuilding(Boolean building) {
        this.building = building;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Integer getGasCostByLevel() {
        return gasCostByLevel;
    }

    public void setGasCostByLevel(Integer gasCostByLevel) {
        this.gasCostByLevel = gasCostByLevel;
    }

    public Integer getGasCostLevel0() {
        return gasCostLevel0;
    }

    public void setGasCostLevel0(Integer gasCostLevel0) {
        this.gasCostLevel0 = gasCostLevel0;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public void setMineralCostByLevel(Integer mineralCostByLevel) {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public Integer getMineralCostLevel0() {
        return mineralCostLevel0;
    }

    public void setMineralCostLevel0(Integer mineralCostLevel0) {
        this.mineralCostLevel0 = mineralCostLevel0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel(Integer timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public Integer getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0(Integer timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }

    public Building(Integer level, Integer amountOfEffectByLevel, Integer amountOfEffectLevel0, Integer buildingId, Boolean building, String effect, Integer gasCostByLevel, Integer gasCostLevel0, String imageUrl, Integer mineralCostByLevel, Integer mineralCostLevel0, String name, Integer timeToBuildByLevel, Integer timeToBuildLevel0) {
        this.level = level;
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.amountOfEffectLevel0 = amountOfEffectLevel0;
        this.buildingId = buildingId;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel0 = gasCostLevel0;
        this.imageUrl = imageUrl;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel0 = mineralCostLevel0;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }
}
