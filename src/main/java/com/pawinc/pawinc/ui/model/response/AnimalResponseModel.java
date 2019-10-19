package com.pawinc.pawinc.ui.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pawinc.pawinc.ui.model.request.AnimalRequestModel;
import com.pawinc.pawinc.ui.model.request.CenterRequestModel;

public class AnimalResponseModel {
    private String name;
    private String type;
    private String status;
    private String center;
    private long animalId;

    public AnimalResponseModel() {
    }

    public AnimalResponseModel(@JsonProperty long animalId,
                               @JsonProperty String name,
                               @JsonProperty String type,
                               @JsonProperty String status,
                               @JsonProperty CenterRequestModel center) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.center = center.getName();
        this.animalId = animalId;
    }

    public AnimalResponseModel(@JsonProperty AnimalRequestModel animalRequestModel) {
        this.animalId = animalRequestModel.getAnimalId();
        this.name = animalRequestModel.getName();
        this.type = animalRequestModel.getType();
        this.status = animalRequestModel.getStatus();
        this.center = animalRequestModel.getCenter().getName();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }
}
