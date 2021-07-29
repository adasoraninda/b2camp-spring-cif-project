package com.adasoraninda.cifproject.service;

import com.adasoraninda.cifproject.model.request.MCIFAddressRequest;
import com.adasoraninda.cifproject.model.response.MCIFAddressResponse;

import java.util.List;

public interface MCIFAddressService {

    List<MCIFAddressResponse> getAddresses();

    MCIFAddressResponse getAddressById(Long addressId);

    MCIFAddressResponse createAddress(MCIFAddressRequest addressRequest);

    MCIFAddressResponse updateAddress(Long addressId, MCIFAddressRequest addressRequest);

    void deleteAddressById(Long addressId);

    void deleteAddresses();

}
