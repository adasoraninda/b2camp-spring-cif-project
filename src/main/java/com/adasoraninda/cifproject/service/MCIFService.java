package com.adasoraninda.cifproject.service;

import com.adasoraninda.cifproject.model.request.MCIFRequest;
import com.adasoraninda.cifproject.model.response.MCIFResponse;

import java.util.List;

public interface MCIFService {

    List<MCIFResponse> getListCIF();

    MCIFResponse getCIFById(Long cifId);

    MCIFResponse createCIF(MCIFRequest cifRequest);

    MCIFResponse updateCIF(Long cifId, MCIFRequest cifRequest);

    void deleteCIFById(Long cifId);

    void deleteAllCIF();

}
