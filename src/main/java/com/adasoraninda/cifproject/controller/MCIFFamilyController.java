package com.adasoraninda.cifproject.controller;

import com.adasoraninda.cifproject.endpoint.MCIFFamilyEndPoint;
import com.adasoraninda.cifproject.model.request.MCIFFamilyRequest;
import com.adasoraninda.cifproject.model.response.MCIFFamilyResponse;
import com.adasoraninda.cifproject.service.MCIFFamilyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = MCIFFamilyEndPoint.pathBase)
public class MCIFFamilyController {

    private final MCIFFamilyService service;

    @GetMapping
    public List<MCIFFamilyResponse> getFamilies() {
        return service.getFamilies();
    }

    @GetMapping(path = MCIFFamilyEndPoint.pathId)
    public MCIFFamilyResponse getFamilyById(
            @PathVariable(value = MCIFFamilyEndPoint.variableId) Long familyId
    ) {
        return service.getFamilyById(familyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MCIFFamilyResponse createFamily(
            @Valid @RequestBody MCIFFamilyRequest familyRequest
    ) {
        return service.createFamily(familyRequest);
    }

    @PutMapping(path = MCIFFamilyEndPoint.pathId)
    public MCIFFamilyResponse updateFamily(
            @PathVariable(value = MCIFFamilyEndPoint.variableId) Long familyId,
            @Valid @RequestBody MCIFFamilyRequest familyRequest
    ) {
        return service.updateFamily(familyId, familyRequest);
    }

    @DeleteMapping(path = MCIFFamilyEndPoint.pathId)
    public void deleteFamilyById(
            @PathVariable(value = MCIFFamilyEndPoint.variableId) Long familyId
    ) {
        service.deleteFamilyById(familyId);
    }

    @DeleteMapping
    public void deleteFamilies() {
        service.deleteFamilies();
    }

}
