package com.pawinc.pawinc.services.impl;

import com.pawinc.pawinc.exceptions.CenterServiceException;
import com.pawinc.pawinc.repository.AnimalRepository;
import com.pawinc.pawinc.repository.CenterRepository;
import com.pawinc.pawinc.services.CenterService;
import com.pawinc.pawinc.shared.CenterType;
import com.pawinc.pawinc.ui.model.request.CenterRequestModel;
import com.pawinc.pawinc.ui.model.request.CenterUpdateModel;
import com.pawinc.pawinc.ui.model.response.CenterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CenterServiceImpl implements CenterService {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    CenterRepository centerRepository;
    @Autowired
    CenterService centerService;

    public CenterServiceImpl(){}

    @Override
    public List<CenterResponseModel> getAllCenters() {
        if(!centerRepository.findAll().isEmpty()){
            List<CenterResponseModel> centers = new ArrayList<>();
            centerRepository.findAll().forEach(center -> {
                centers.add(new CenterResponseModel(
                        center.getCenterId(),
                        center.getName(),
                        center.getType()));
            });
            return centers;
        } else {
            throw new CenterServiceException("There are no centers in the database.");
        }
    }

    @Override
    public CenterRequestModel getCenterById(long centerId) {
        if(centerRepository.existsById(centerId)){
            return centerRepository.findById(centerId).get();

        } else {
            throw new CenterServiceException("Center with id " + centerId + "doesn't exist in the datavase");
        }
    }

    @Override
    public CenterResponseModel registerCenter(CenterRequestModel centerRequestModel) {
        CenterRequestModel center = centerRepository.save(centerRequestModel);
        return new CenterResponseModel(center);
    }

    @Override
    public CenterResponseModel updateCenter(long centerId, CenterUpdateModel centerDetails) {
        if(centerRepository.existsById(centerId)){
            CenterRequestModel center = centerRepository.findById(centerId).get();
            center.setName(centerDetails.getName());
            center.setType(centerDetails.getType());
            centerRepository.save(center);
            return new CenterResponseModel(center);
        } else {
            throw new CenterServiceException("Center with id " + centerId + " couldn't be updated.");
        }
    }

    @Override
    public CenterResponseModel deleteCenter(long centerId) {
        if(centerRepository.existsById(centerId)){
            CenterRequestModel center = centerRepository.findById(centerId).get();
            centerRepository.deleteById(centerId);
            return new CenterResponseModel(center);
        } else {
            throw new CenterServiceException("Couldn't delete center with id " + centerId);
        }
    }
/*
    public CenterResponseModel getCenterByName(String name) {
        CenterResponseModel center = null;
        if (!centerRepository.findAll().isEmpty()) {
            for (CenterRequestModel c : centerRepository.findAll()) {
                if (name.toLowerCase().equals(c.getName().toLowerCase())) {
                    center = new CenterResponseModel(c);
                    break;
                }
            }
            return center;
        } else {
            throw new CenterServiceException("There are no centers in the database.");
        }
    }*/

    @Override
    public long getCenterIdByName(String name) {
        long centerId = -1;
        if (!centerRepository.findAll().isEmpty()) {
            for (CenterRequestModel center : centerRepository.findAll()) {
                if (name.toLowerCase().equals(center.getName().toLowerCase())) {
                    centerId = center.getCenterId();
                    break;
                }
            }
            if(centerId == -1){
                throw new CenterServiceException("There is no center: " + name);
            }
            return centerId;
        } else {
            throw new CenterServiceException("There are no centers in the database.");
        }
    }



    @Override
    public List<CenterRequestModel> shuffleAdoptionCenters() {
        List<CenterRequestModel> centerRequestModels = new ArrayList<>();

        List<CenterResponseModel> centerResponseModels = centerService.getAllCenters()
                .stream()
                .filter(center -> CenterType.ADOPTION.toString()
                        .equalsIgnoreCase(center.getType()))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(), collected -> {
                    Collections.shuffle(collected);

                    return collected.stream();
                }))
                .collect(Collectors.toList());

        centerResponseModels.forEach(c -> centerRequestModels.add(new CenterRequestModel(
                c.getCenterId(),
                c.getName(),
                c.getType()
        )));

        return centerRequestModels;
    }

    @Override
    public CenterRequestModel getAdoptionCenter(List<CenterRequestModel> centers) {
        return centers.stream()
                .findAny()
                .get();
    }

    @Override
    public CenterRequestModel findNoneCenterType() {
        return centerRepository.findAll().stream()
                .filter(center -> CenterType.NONE.toString()
                        .equalsIgnoreCase(center.getType()))
                .findAny()
                .get();

    }
}
