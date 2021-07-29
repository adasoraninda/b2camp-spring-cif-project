package com.adasoraninda.cifproject.service.impl;

import com.adasoraninda.cifproject.exception.BusinessException;
import com.adasoraninda.cifproject.exception.message.MCIFErrorMessage;
import com.adasoraninda.cifproject.exception.message.MCIFFamilyErrorMessage;
import com.adasoraninda.cifproject.model.entity.MCIFFamily;
import com.adasoraninda.cifproject.model.request.MCIFFamilyRequest;
import com.adasoraninda.cifproject.model.response.MCIFFamilyResponse;
import com.adasoraninda.cifproject.repository.MCIFFamilyRepository;
import com.adasoraninda.cifproject.repository.MCIFRepository;
import com.adasoraninda.cifproject.service.MCIFFamilyService;
import com.adasoraninda.cifproject.validation.MCIFFamilyValidation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.CIF_NOT_FOUND;
import static com.adasoraninda.cifproject.exception.code.MCIFFamilyErrorCode.CIF_FAMILY_IS_EMPTY;
import static com.adasoraninda.cifproject.exception.code.MCIFFamilyErrorCode.CIF_FAMILY_NOT_FOUND;

@Service
@AllArgsConstructor
public class MCIFFamilyServiceImpl implements MCIFFamilyService {

    private final ModelMapper modelMapper;
    private final MCIFFamilyValidation validation;

    private final MCIFRepository cifRepository;
    private final MCIFFamilyRepository familyRepository;

    @Override
    public List<MCIFFamilyResponse> getFamilies() {
        var families = familyRepository.findAll();

        if (families.isEmpty()) {
            throw new BusinessException(
                    new MCIFFamilyErrorMessage(
                            Map.of(CIF_FAMILY_IS_EMPTY, new Object()),
                            CIF_FAMILY_IS_EMPTY));
        }

        return families.stream()
                .map(f -> modelMapper.map(f, MCIFFamilyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public MCIFFamilyResponse getFamilyById(Long familyId) {
        return familyRepository.findById(familyId)
                .map(f -> modelMapper.map(f, MCIFFamilyResponse.class))
                .orElseThrow(() -> new BusinessException(
                        new MCIFFamilyErrorMessage(
                                Map.of(CIF_FAMILY_NOT_FOUND, familyId),
                                CIF_FAMILY_NOT_FOUND)));
    }

    @Override
    @Transactional
    public MCIFFamilyResponse createFamily(MCIFFamilyRequest familyRequest) {
        var cifEntity = cifRepository.findById(familyRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, familyRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var familyEntity = modelMapper.map(familyRequest, MCIFFamily.class);
        familyEntity.setCif(cifEntity);

        return modelMapper.map(
                familyRepository.save(familyEntity),
                MCIFFamilyResponse.class);
    }

    @Override
    @Transactional
    public MCIFFamilyResponse updateFamily(Long familyId, MCIFFamilyRequest familyRequest) {
        var cifEntity = cifRepository.findById(familyRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, familyRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var familyEntity = modelMapper.map(getFamilyById(familyId), MCIFFamily.class);
        var newFamilyEntity = modelMapper.map(familyRequest, MCIFFamily.class);

        newFamilyEntity.setCif(cifEntity);

        validation.validateAndUpdate(familyEntity, newFamilyEntity);

        return modelMapper.map(
                familyRepository.save(familyEntity),
                MCIFFamilyResponse.class);
    }

    @Override
    @Transactional
    public void deleteFamilyById(Long familyId) {
        var familyIsExists = familyRepository.existsById(familyId);

        if (!familyIsExists) {
            throw new BusinessException(
                    new MCIFFamilyErrorMessage(
                            Map.of(CIF_FAMILY_NOT_FOUND, familyId),
                            CIF_FAMILY_NOT_FOUND));
        }

        familyRepository.delete(familyRepository.getById(familyId));
    }

    @Override
    @Transactional
    public void deleteFamilies() {
        var families = familyRepository.findAll();

        if (families.isEmpty()) {
            throw new BusinessException(
                    new MCIFFamilyErrorMessage(
                            Map.of(CIF_FAMILY_IS_EMPTY, new Object()),
                            CIF_FAMILY_IS_EMPTY));
        }

        familyRepository.deleteAll();
    }
}
