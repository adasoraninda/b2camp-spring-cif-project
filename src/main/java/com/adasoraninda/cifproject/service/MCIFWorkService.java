package com.adasoraninda.cifproject.service;

import com.adasoraninda.cifproject.model.request.MCIFWorkRequest;
import com.adasoraninda.cifproject.model.response.MCIFWorkResponse;

import java.util.List;

public interface MCIFWorkService {

    List<MCIFWorkResponse> getWorks();

    MCIFWorkResponse getWorkById(Long workId);

    MCIFWorkResponse createWork(MCIFWorkRequest workRequest);

    MCIFWorkResponse updateWork(Long workId, MCIFWorkRequest workRequest);

    void deleteWorkById(Long workId);

    void deleteWorks();

}
