package com.pawinc.pawinc.ui.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pawinc.pawinc.ui.model.request.CenterRequestModel;

public class CenterResponseModel {

    private long centerId;
    private String name;
    private String type;

    public CenterResponseModel() {
    }

    public CenterResponseModel(@JsonProperty long centerId,
                               @JsonProperty String name,
                               @JsonProperty String type) {
        this.centerId = centerId;
        this.name = name;
        this.type = type;
    }

    public CenterResponseModel(@JsonProperty CenterRequestModel centerRequestModel) {
        this.centerId = centerRequestModel.getCenterId();
        this.name = centerRequestModel.getName();
        this.type = centerRequestModel.getType();
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

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }
}
