package com.adasoraninda.cifproject.controller;

import com.adasoraninda.cifproject.endpoint.MCIFAddressEndPoint;
import com.adasoraninda.cifproject.model.request.MCIFAddressRequest;
import com.adasoraninda.cifproject.model.response.MCIFAddressResponse;
import com.adasoraninda.cifproject.service.MCIFAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = MCIFAddressEndPoint.pathBase)
public class MCIFAddressController {

    private final MCIFAddressService service;

    @GetMapping
    public List<MCIFAddressResponse> getAddresses() {
        return service.getAddresses();
    }

    @GetMapping(path = MCIFAddressEndPoint.pathId)
    public MCIFAddressResponse getAddressById(
            @PathVariable(value = MCIFAddressEndPoint.variableId) Long addressId
    ) {
        return service.getAddressById(addressId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MCIFAddressResponse createAddress(
            @Valid @RequestBody MCIFAddressRequest addressRequest
    ) {
        return service.createAddress(addressRequest);
    }

    @PutMapping(path = MCIFAddressEndPoint.pathId)
    public MCIFAddressResponse updateAddress(
            @PathVariable(value = MCIFAddressEndPoint.variableId) Long addressId,
            @Valid @RequestBody MCIFAddressRequest addressRequest
    ) {
        return service.updateAddress(addressId, addressRequest);
    }

    @DeleteMapping(path = MCIFAddressEndPoint.pathId)
    public void deleteAddressById(
            @PathVariable(value = MCIFAddressEndPoint.variableId) Long addressId
    ) {
        service.deleteAddressById(addressId);
    }

    @DeleteMapping
    public void deleteAddresses() {
        service.deleteAddresses();
    }

}
