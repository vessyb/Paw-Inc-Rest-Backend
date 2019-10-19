package com.pawinc.pawinc.ui.model.request;

import com.pawinc.pawinc.shared.AnimalStatus;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "animals")
@Component
public class AnimalRequestModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long animalId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "type", nullable = false)
    private String type;


    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "centerID", nullable = false, insertable = false )
    private CenterRequestModel center;

    @Transient
    private String centerName;

    @Column(name = "centerID", updatable = false, insertable = false, nullable = false)
    private long centerId;

    public AnimalRequestModel() {
        this.status = AnimalStatus.REGISTERED.toString();
    }

    public long getCenterId() {
        return centerId;
    }

    public void setCenterId(long centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CenterRequestModel getCenter() {
        return center;
    }

    public void setCenter(CenterRequestModel center) {
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
}
