package com.pawinc.pawinc.services;

import com.pawinc.pawinc.ui.model.request.CenterRequestModel;
import com.pawinc.pawinc.ui.model.request.CenterUpdateModel;
import com.pawinc.pawinc.ui.model.response.CenterResponseModel;

import java.util.List;

public interface CenterService {

    List<CenterResponseModel> getAllCenters();
    CenterRequestModel getCenterById(long centerId);

    CenterResponseModel registerCenter(CenterRequestModel centerRequestModel);

    CenterResponseModel updateCenter(long centerId, CenterUpdateModel centerDetails);

    CenterResponseModel deleteCenter(long centerId);

    /*CenterResponseModel getCenterByName(String name);*/

    long getCenterIdByName(String name);

    CenterRequestModel getAdoptionCenter(List<CenterRequestModel> centers);

List<CenterRequestModel> shuffleAdoptionCenters();

CenterRequestModel findNoneCenterType();
}
