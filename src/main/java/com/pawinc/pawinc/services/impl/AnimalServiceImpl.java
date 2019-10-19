package com.pawinc.pawinc.services.impl;

import com.pawinc.pawinc.exceptions.AnimalServiceException;
import com.pawinc.pawinc.exceptions.CenterServiceException;
import com.pawinc.pawinc.repository.AnimalRepository;
import com.pawinc.pawinc.repository.CenterRepository;
import com.pawinc.pawinc.services.AnimalService;
import com.pawinc.pawinc.services.CenterService;
import com.pawinc.pawinc.shared.AnimalStatus;
import com.pawinc.pawinc.ui.model.request.AnimalRequestModel;
import com.pawinc.pawinc.ui.model.request.AnimalUpdateModel;
import com.pawinc.pawinc.ui.model.request.CenterRequestModel;
import com.pawinc.pawinc.ui.model.response.AnimalResponseModel;
import com.pawinc.pawinc.ui.model.response.CenterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    CenterRepository centerRepository;
    @Autowired
    CenterService centerService;

    public AnimalServiceImpl() {
    }

    @Override
    public List<AnimalResponseModel> getAllAnimals() {
        if (!animalRepository.findAll().isEmpty()) {
            List<AnimalResponseModel> animals = new ArrayList<>();
            animalRepository.findAll()
                    .forEach(animal -> animals.add(getAnimalResponseElements(animal)));
            return animals;
        } else {
            throw new AnimalServiceException("There are no animals in the database");
        }
    }

    @Override
    public AnimalResponseModel getAnimalById(long animalId) {
        if (animalRepository.existsById(animalId)) {
            AnimalRequestModel animal = getAnimalRequestModel(animalId);
            return new AnimalResponseModel(animal);
        } else {
            throw new AnimalServiceException("There is no animal with " + animalId + " in the database");
        }
    }



    @Override
    public AnimalResponseModel registerAnimal(AnimalRequestModel animalRequestModel) {
        animalRequestModel.setCenterId(centerService.getCenterIdByName(animalRequestModel.getCenterName()));
        AnimalRequestModel animal = animalRepository.save(animalRequestModel);
        animal.setCenterId(centerService.getCenterIdByName(animal.getCenterName()));
        animal.setCenter(centerService.getCenterById(animal.getCenterId()));
        return new AnimalResponseModel(animal);
    }

    @Override
    public AnimalResponseModel deleteAnimal(long animalId) {
        if (animalRepository.existsById(animalId)) {
            AnimalRequestModel animal = getAnimalRequestModel(animalId);
            animalRepository.deleteById(animalId);
            return new AnimalResponseModel(animal);
        } else {
            throw new AnimalServiceException("There is no animal with " + animalId + " in the database");
        }
    }

    @Override
    public List<AnimalResponseModel> cleanseAnimals(List<Long> animalIds) {
        List<AnimalResponseModel> animals = new ArrayList<>();
        animalIds.forEach(animalId -> {
            if (animalRepository.existsById(animalId)) {
                AnimalRequestModel animal = getAnimalRequestModel(animalId);
                if (animal.getStatus().equalsIgnoreCase(AnimalStatus.REGISTERED.toString())) {
                    animal.setStatus(AnimalStatus.CLEANSED.toString());
                    if (!isCenterRepositoryEmpty()) {
                        List<CenterRequestModel> shuffledAdotpionCenters = centerService.shuffleAdoptionCenters();
                        CenterRequestModel adoptionCenter = centerService.getAdoptionCenter(shuffledAdotpionCenters);
                        setCenterForAnimal(animal, adoptionCenter);
                    } else {
                        throw new CenterServiceException("There are no centers in the database");
                    }
                    animals.add(new AnimalResponseModel(animal));
                    animalRepository.save(animal);
                } else{
                    throw new AnimalServiceException("Animal couldn't be cleansed, current animal status should be registered.");
                }
            } else {
                throw new AnimalServiceException("Animal with id " + animalId + " couldn't be cleansed.");
            }
        });
        return animals;
    }




    @Override
    public List<AnimalResponseModel> adoptAnimals(List<Long> animalIds) {
        List<AnimalResponseModel> animals = new ArrayList<>();
        animalIds.forEach(animalId -> {
            if (animalRepository.existsById(animalId)) {
                AnimalRequestModel animal = getAnimalRequestModel(animalId);
                if (animal.getStatus().equalsIgnoreCase(AnimalStatus.CLEANSED.toString())) {
                    animal.setStatus(AnimalStatus.ADOPTED.toString());
                    if (!isCenterRepositoryEmpty()) {
                        CenterRequestModel noneCenterType = centerService.findNoneCenterType();
                        setCenterForAnimal(animal, noneCenterType);
                    } else {
                        throw new CenterServiceException("There are no centers in the database");
                    }
                    animals.add(new AnimalResponseModel(animal));
                    animalRepository.save(animal);
                }
                else {
                    throw new AnimalServiceException("Animal couldn't be adopted, current animal status should be cleansed.");
                }
            } else {
                throw new AnimalServiceException("Animals with id " + animalId + " couldn't be adopted");
            }
        });
        return animals;
    }

    private boolean isCenterRepositoryEmpty() {
        return centerRepository.findAll().isEmpty();
    }

    private AnimalResponseModel getAnimalResponseElements(AnimalRequestModel animal) {
        return new AnimalResponseModel(
                animal.getAnimalId(),
                animal.getName(),
                animal.getType(),
                animal.getStatus(),
                animal.getCenter()
        );
    }

    private AnimalRequestModel getAnimalRequestModel(long animalId) {
        return animalRepository.findById(animalId).get();
    }

    private void setCenterForAnimal(AnimalRequestModel animal, CenterRequestModel adoptionCenter) {
        animal.setCenter(adoptionCenter);
        animal.setCenterId(adoptionCenter.getCenterId());
    }
}
