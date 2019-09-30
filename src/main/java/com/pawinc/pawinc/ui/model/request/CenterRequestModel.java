package com.pawinc.pawinc.ui.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "centers")
@Component
public class CenterRequestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long centerId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany()
    @JoinColumn(name = "centerID", nullable = false, insertable = false, updatable = false)
    private List<AnimalRequestModel> animals;

    public CenterRequestModel() {
    }

    public CenterRequestModel(String name){
        this.name = name;
    }

    public CenterRequestModel(@JsonProperty String name,
                              @JsonProperty String type) {
        this.name = name;
        this.type = type;
    }

    public CenterRequestModel(@JsonProperty long centerId,
                              @JsonProperty String name,
                              @JsonProperty String type) {
        this.centerId = centerId;
        this.name = name;
        this.type = type;
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
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

    public List<AnimalRequestModel> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalRequestModel> animals) {
        this.animals = animals;
    }
}
