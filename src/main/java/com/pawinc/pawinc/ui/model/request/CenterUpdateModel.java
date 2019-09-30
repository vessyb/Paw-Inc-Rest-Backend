package com.pawinc.pawinc.ui.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CenterUpdateModel {

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    public CenterUpdateModel() {
    }

    public CenterUpdateModel(@JsonProperty String name,
                             @JsonProperty String type) {
        this.name = name;
        this.type = type;
    }

    private List<String> animals;

    public List<String> getAnimals() {
        return animals;
    }

    public void setAnimals(List<String> animals) {
        this.animals = animals;
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
}
