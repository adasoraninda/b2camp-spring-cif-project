package com.adasoraninda.cifproject.controller;

import com.adasoraninda.cifproject.endpoint.MCIFWorkEndPoint;
import com.adasoraninda.cifproject.model.request.MCIFWorkRequest;
import com.adasoraninda.cifproject.model.response.MCIFWorkResponse;
import com.adasoraninda.cifproject.service.MCIFWorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = MCIFWorkEndPoint.pathBase)
public class MCIFWorkController {

    private final MCIFWorkService service;

    @GetMapping
    public List<MCIFWorkResponse> getWorks() {
        return service.getWorks();
    }

    @GetMapping(path = MCIFWorkEndPoint.pathId)
    public MCIFWorkResponse getWorkById(
            @PathVariable(value = MCIFWorkEndPoint.variableId) Long workId
    ) {
        return service.getWorkById(workId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MCIFWorkResponse createWork(
            @Valid @RequestBody MCIFWorkRequest workRequest
    ) {
        return service.createWork(workRequest);
    }

    @PutMapping(path = MCIFWorkEndPoint.pathId)
    public MCIFWorkResponse updateWork(
            @PathVariable(value = MCIFWorkEndPoint.variableId) Long workId,
            @Valid @RequestBody MCIFWorkRequest workRequest
    ) {
        return service.updateWork(workId, workRequest);
    }

    @DeleteMapping(path = MCIFWorkEndPoint.pathId)
    public void deleteWorkById(
            @PathVariable(value = MCIFWorkEndPoint.variableId) Long workId
    ) {
        service.deleteWorkById(workId);
    }

    @DeleteMapping
    public void deleteWorks() {
        service.deleteWorks();
    }

}
