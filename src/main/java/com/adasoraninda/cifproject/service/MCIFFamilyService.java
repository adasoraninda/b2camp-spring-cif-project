package com.adasoraninda.cifproject.service;

import com.adasoraninda.cifproject.model.request.MCIFFamilyRequest;
import com.adasoraninda.cifproject.model.response.MCIFFamilyResponse;

import java.util.List;

public interface MCIFFamilyService {

    List<MCIFFamilyResponse> getFamilies();

    MCIFFamilyResponse getFamilyById(Long familyId);

    MCIFFamilyResponse createFamily(MCIFFamilyRequest familyRequest);

    MCIFFamilyResponse updateFamily(Long familyId,MCIFFamilyRequest familyRequest);

    void deleteFamilyById(Long familyId);

    void deleteFamilies();

}
