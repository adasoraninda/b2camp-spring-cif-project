package com.adasoraninda.cifproject.controller;

import com.adasoraninda.cifproject.endpoint.MCIFEndPoint;
import com.adasoraninda.cifproject.model.request.MCIFRequest;
import com.adasoraninda.cifproject.model.response.MCIFResponse;
import com.adasoraninda.cifproject.service.MCIFService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = MCIFEndPoint.pathBase)
public class MCIFController {

    private final MCIFService service;

    @GetMapping
    public List<MCIFResponse> getListCIF() {
        return service.getListCIF();
    }

    @GetMapping(path = MCIFEndPoint.pathId)
    public MCIFResponse getCIFById(
            @PathVariable(value = MCIFEndPoint.variableId) Long cifId
    ) {
        return service.getCIFById(cifId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MCIFResponse createCIF(
            @Valid @RequestBody MCIFRequest cifRequest
    ) {
        return service.createCIF(cifRequest);
    }

    @PutMapping(path = MCIFEndPoint.pathId)
    public MCIFResponse updateCIF(
            @PathVariable(value = MCIFEndPoint.variableId) Long cifId,
            @Valid @RequestBody MCIFRequest cifRequest) {
        return service.updateCIF(cifId, cifRequest);
    }

    @DeleteMapping(path = MCIFEndPoint.pathId)
    public void deleteCIFById(
            @PathVariable(value = MCIFEndPoint.variableId) Long cifId
    ) {
        service.deleteCIFById(cifId);
    }

    @DeleteMapping
    public void deleteAllCIF() {
        service.deleteAllCIF();
    }

}
