package com.pawinc.pawinc.services;

import com.pawinc.pawinc.ui.model.request.AnimalRequestModel;
import com.pawinc.pawinc.ui.model.request.AnimalUpdateModel;
import com.pawinc.pawinc.ui.model.response.AnimalResponseModel;

import java.util.List;

public interface AnimalService {

    List<AnimalResponseModel> getAllAnimals();
    AnimalResponseModel getAnimalById(long animalId);

    AnimalResponseModel registerAnimal(AnimalRequestModel animalRequestModel);

   /* AnimalResponseModel updateAnimal(long animalId, AnimalUpdateModel animalUpdateModel);*/

    AnimalResponseModel deleteAnimal(long animalId);

    List<AnimalResponseModel> cleanseAnimals(List<Long> animalIds);

    List<AnimalResponseModel> adoptAnimals(List<Long> animalIds);

}
