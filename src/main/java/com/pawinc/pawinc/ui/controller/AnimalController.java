package com.pawinc.pawinc.ui.controller;

import com.pawinc.pawinc.services.AnimalService;
import com.pawinc.pawinc.ui.model.request.AnimalRequestModel;
import com.pawinc.pawinc.ui.model.request.AnimalUpdateModel;
import com.pawinc.pawinc.ui.model.response.AnimalResponseModel;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/animals")
@CrossOrigin(origins = "http://localhost:3001")// http://localhost:8080/animals
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AnimalResponseModel>> getAllAnimals() {
        return new ResponseEntity<>(animalService.getAllAnimals(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnimalResponseModel> getAnimalById(@PathVariable("id") long animalId) {
        return new ResponseEntity<>(animalService.getAnimalById(animalId), HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnimalResponseModel> registerAnimal(@Valid @NotNull @RequestBody AnimalRequestModel animalRequestModel) {
        return new ResponseEntity<>(animalService.registerAnimal(animalRequestModel), HttpStatus.OK);
    }

    @PutMapping(path = "/cleanse",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AnimalResponseModel>> cleanseAnimals(@RequestParam List<Long> animalIds) {
        return new ResponseEntity<>(animalService.cleanseAnimals(animalIds), HttpStatus.OK);
    }

    @PutMapping(path = "/adopt",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<AnimalResponseModel>> adoptAnimals(@RequestParam List<Long> animalIds) {
        return new ResponseEntity<>(animalService.adoptAnimals(animalIds), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AnimalResponseModel> deleteAnimal(@PathVariable("id") long animalId) {
        return new ResponseEntity<>(animalService.deleteAnimal(animalId), HttpStatus.OK);
    }

}
