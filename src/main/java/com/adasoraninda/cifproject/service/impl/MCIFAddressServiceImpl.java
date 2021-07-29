package com.adasoraninda.cifproject.service.impl;

import com.adasoraninda.cifproject.exception.BusinessException;
import com.adasoraninda.cifproject.exception.message.MCIFAddressErrorMessage;
import com.adasoraninda.cifproject.exception.message.MCIFErrorMessage;
import com.adasoraninda.cifproject.model.entity.MCIFAddress;
import com.adasoraninda.cifproject.model.request.MCIFAddressRequest;
import com.adasoraninda.cifproject.model.response.MCIFAddressResponse;
import com.adasoraninda.cifproject.repository.MCIFAddressRepository;
import com.adasoraninda.cifproject.repository.MCIFRepository;
import com.adasoraninda.cifproject.service.MCIFAddressService;
import com.adasoraninda.cifproject.validation.MCIFAddressValidation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adasoraninda.cifproject.exception.code.MCIFAddressErrorCode.CIF_ADDRESS_IS_EMPTY;
import static com.adasoraninda.cifproject.exception.code.MCIFAddressErrorCode.CIF_ADDRESS_NOT_FOUND;
import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.CIF_NOT_FOUND;

@Service
@AllArgsConstructor
public class MCIFAddressServiceImpl implements MCIFAddressService {

    private final ModelMapper modelMapper;
    private final MCIFAddressValidation validation;

    private final MCIFRepository cifRepository;
    private final MCIFAddressRepository addressRepository;

    @Override
    public List<MCIFAddressResponse> getAddresses() {
        var addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new BusinessException(
                    new MCIFAddressErrorMessage(
                            Map.of(CIF_ADDRESS_IS_EMPTY, new Object()),
                            CIF_ADDRESS_IS_EMPTY));
        }

        return addresses.stream()
                .map(a -> modelMapper.map(a, MCIFAddressResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public MCIFAddressResponse getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .map(a -> modelMapper.map(a, MCIFAddressResponse.class))
                .orElseThrow(() -> new BusinessException(
                        new MCIFAddressErrorMessage(
                                Map.of(CIF_ADDRESS_NOT_FOUND, addressId),
                                CIF_ADDRESS_NOT_FOUND)));
    }

    @Override
    @Transactional
    public MCIFAddressResponse createAddress(MCIFAddressRequest addressRequest) {
        var cifEntity = cifRepository.findById(addressRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, addressRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var addressEntity = modelMapper.map(addressRequest, MCIFAddress.class);
        addressEntity.setCif(cifEntity);

        return modelMapper.map(
                addressRepository.save(addressEntity),
                MCIFAddressResponse.class);
    }

    @Override
    @Transactional
    public MCIFAddressResponse updateAddress(Long addressId, MCIFAddressRequest addressRequest) {
        var cifEntity = cifRepository.findById(addressRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, addressRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var addressEntity = modelMapper.map(getAddressById(addressId), MCIFAddress.class);
        var newAddressEntity = modelMapper.map(addressRequest, MCIFAddress.class);

        newAddressEntity.setCif(cifEntity);

        validation.validateAndUpdate(addressEntity, newAddressEntity);

        return modelMapper.map(
                addressRepository.save(addressEntity),
                MCIFAddressResponse.class);
    }

    @Override
    @Transactional
    public void deleteAddressById(Long addressId) {
        var addressIsExists = addressRepository.existsById(addressId);

        if (!addressIsExists) {
            throw new BusinessException(
                    new MCIFAddressErrorMessage(
                            Map.of(CIF_ADDRESS_NOT_FOUND, addressId),
                            CIF_ADDRESS_NOT_FOUND));
        }

        addressRepository.delete(addressRepository.getById(addressId));
    }

    @Override
    @Transactional
    public void deleteAddresses() {
        var addresses = addressRepository.findAll();

        if (addresses.isEmpty()) {
            throw new BusinessException(
                    new MCIFAddressErrorMessage(
                            Map.of(CIF_ADDRESS_IS_EMPTY, new Object()),
                            CIF_ADDRESS_IS_EMPTY));
        }

        addressRepository.deleteAll();
    }

}
