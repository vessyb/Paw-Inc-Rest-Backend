package com.pawinc.pawinc.ui.controller;

import com.pawinc.pawinc.services.CenterService;
import com.pawinc.pawinc.exceptions.CenterServiceException;
import com.pawinc.pawinc.ui.model.request.CenterRequestModel;
import com.pawinc.pawinc.ui.model.request.CenterUpdateModel;
import com.pawinc.pawinc.ui.model.response.CenterResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/centers") //http://localhost:8080/centers
public class CenterController {

    @Autowired
    CenterService centerService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CenterResponseModel>> getAllCenters() {
        return new ResponseEntity<>(centerService.getAllCenters(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CenterResponseModel> getCenterById(@PathVariable("id") long centerId) {
        return new ResponseEntity<>(new CenterResponseModel(centerService.getCenterById(centerId)), HttpStatus.OK);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CenterResponseModel> registerCenter(@Valid @NotNull @RequestBody CenterRequestModel centerDetails) {
        return new ResponseEntity<>(centerService.registerCenter(centerDetails), HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CenterResponseModel> updateCenter(@PathVariable("id") long centerId,
                                                            @Valid @NotNull @RequestBody CenterUpdateModel centerUpdateModel) {
        return new ResponseEntity<>(centerService.updateCenter(centerId, centerUpdateModel), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CenterResponseModel> deleteCenter(@PathVariable("id") long centerId) {
        return new ResponseEntity<>(centerService.deleteCenter(centerId), HttpStatus.OK);
    }
}
