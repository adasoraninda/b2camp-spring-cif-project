package com.adasoraninda.cifproject.service.impl;

import com.adasoraninda.cifproject.exception.BusinessException;
import com.adasoraninda.cifproject.exception.message.MCIFErrorMessage;
import com.adasoraninda.cifproject.model.entity.MCIF;
import com.adasoraninda.cifproject.model.request.MCIFRequest;
import com.adasoraninda.cifproject.model.response.MCIFResponse;
import com.adasoraninda.cifproject.repository.MCIFRepository;
import com.adasoraninda.cifproject.service.MCIFService;
import com.adasoraninda.cifproject.validation.MCIFValidation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.*;

@Service
@AllArgsConstructor
public class MCIFServiceImpl implements MCIFService {

    private final ModelMapper modelMapper;
    private final MCIFValidation validation;

    private final MCIFRepository cifRepository;

    @Override
    public List<MCIFResponse> getListCIF() {
        var listCIF = cifRepository.findAll();

        if (listCIF.isEmpty()) {
            throw new BusinessException(
                    new MCIFErrorMessage(
                            Map.of(CIF_IS_EMPTY, new Object()),
                            CIF_IS_EMPTY));
        }

        return listCIF.stream()
                .map(f -> modelMapper.map(f, MCIFResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public MCIFResponse getCIFById(Long cifId) {
        return cifRepository.findById(cifId)
                .map(f -> modelMapper.map(f, MCIFResponse.class))
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, cifId),
                                CIF_NOT_FOUND)));
    }

    @Override
    @Transactional
    public MCIFResponse createCIF(MCIFRequest cifRequest) {
        var cifEntity = modelMapper.map(cifRequest, MCIF.class);

        if (validation.checkNpwp(cifEntity)) {
            throw new BusinessException(
                    new MCIFErrorMessage(
                            Map.of(CIF_NPWP_MUST_FILLED, new Object()),
                            CIF_NPWP_MUST_FILLED));
        }

        return modelMapper.map(
                cifRepository.save(cifEntity),
                MCIFResponse.class);
    }

    @Override
    @Transactional
    public MCIFResponse updateCIF(Long cifId, MCIFRequest cifRequest) {
        var cifEntity = cifRepository.findById(cifId).orElseThrow(() -> new BusinessException(
                new MCIFErrorMessage(
                        Map.of(CIF_NOT_FOUND, cifId),
                        CIF_NOT_FOUND)));

        var newCifEntity = modelMapper.map(cifRequest, MCIF.class);

        validation.validateAndUpdate(cifEntity, newCifEntity);

        return modelMapper.map(
                cifRepository.save(cifEntity),
                MCIFResponse.class);
    }

    @Override
    @Transactional
    public void deleteCIFById(Long cifId) {
        var cifExists = cifRepository.existsById(cifId);

        if (!cifExists) {
            throw new BusinessException(
                    new MCIFErrorMessage(
                            Map.of(CIF_NOT_FOUND, cifId),
                            CIF_NOT_FOUND));
        }

        cifRepository.delete(cifRepository.getById(cifId));
    }

    @Override
    @Transactional
    public void deleteAllCIF() {
        var listCIF = cifRepository.findAll();

        if (listCIF.isEmpty()) {
            throw new BusinessException(
                    new MCIFErrorMessage(
                            Map.of(CIF_IS_EMPTY, new Object()),
                            CIF_IS_EMPTY));
        }

        cifRepository.deleteAll();
    }
}
