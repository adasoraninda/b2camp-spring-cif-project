package com.adasoraninda.cifproject.service.impl;

import com.adasoraninda.cifproject.exception.BusinessException;
import com.adasoraninda.cifproject.exception.message.MCIFErrorMessage;
import com.adasoraninda.cifproject.exception.message.MCIFWorkErrorMessage;
import com.adasoraninda.cifproject.model.entity.MCIFWork;
import com.adasoraninda.cifproject.model.request.MCIFWorkRequest;
import com.adasoraninda.cifproject.model.response.MCIFWorkResponse;
import com.adasoraninda.cifproject.repository.MCIFRepository;
import com.adasoraninda.cifproject.repository.MCIFWorkRepository;
import com.adasoraninda.cifproject.service.MCIFWorkService;
import com.adasoraninda.cifproject.validation.MCIFWorkValidation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.CIF_NOT_FOUND;
import static com.adasoraninda.cifproject.exception.code.MCIFWorkErrorCode.CIF_WORK_IS_EMPTY;
import static com.adasoraninda.cifproject.exception.code.MCIFWorkErrorCode.CIF_WORK_NOT_FOUND;

@Service
@AllArgsConstructor
public class MCIFWorkServiceImpl implements MCIFWorkService {

    private final ModelMapper modelMapper;
    private final MCIFWorkValidation validation;

    private final MCIFRepository cifRepository;
    private final MCIFWorkRepository workRepository;

    @Override
    public List<MCIFWorkResponse> getWorks() {
        var works = workRepository.findAll();

        if (works.isEmpty()) {
            throw new BusinessException(
                    new MCIFWorkErrorMessage(
                            Map.of(CIF_WORK_IS_EMPTY, new Object()),
                            CIF_WORK_IS_EMPTY));
        }

        return works.stream()
                .map(f -> modelMapper.map(f, MCIFWorkResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public MCIFWorkResponse getWorkById(Long workId) {
        return workRepository.findById(workId)
                .map(f -> modelMapper.map(f, MCIFWorkResponse.class))
                .orElseThrow(() -> new BusinessException(
                        new MCIFWorkErrorMessage(
                                Map.of(CIF_WORK_NOT_FOUND, workId),
                                CIF_WORK_NOT_FOUND)));
    }

    @Override
    @Transactional
    public MCIFWorkResponse createWork(MCIFWorkRequest workRequest) {
        var cifEntity = cifRepository.findById(workRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, workRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var workEntity = modelMapper.map(workRequest, MCIFWork.class);
        workEntity.setCif(cifEntity);

        return modelMapper.map(
                workRepository.save(workEntity),
                MCIFWorkResponse.class);
    }

    @Override
    @Transactional
    public MCIFWorkResponse updateWork(Long workId, MCIFWorkRequest workRequest) {
        var cifEntity = cifRepository.findById(workRequest.getCifId())
                .orElseThrow(() -> new BusinessException(
                        new MCIFErrorMessage(
                                Map.of(CIF_NOT_FOUND, workRequest.getCifId()),
                                CIF_NOT_FOUND)));

        var workEntity = modelMapper.map(getWorkById(workId), MCIFWork.class);
        var newWorkEntity = modelMapper.map(workId, MCIFWork.class);

        newWorkEntity.setCif(cifEntity);

        validation.validateAndUpdate(workEntity, newWorkEntity);

        return modelMapper.map(
                workRepository.save(workEntity),
                MCIFWorkResponse.class);
    }

    @Override
    @Transactional
    public void deleteWorkById(Long workId) {
        var workIsExists = workRepository.existsById(workId);

        if (!workIsExists) {
            throw new BusinessException(
                    new MCIFWorkErrorMessage(
                            Map.of(CIF_WORK_NOT_FOUND, workId),
                            CIF_WORK_NOT_FOUND));
        }

        workRepository.delete(workRepository.getById(workId));
    }

    @Override
    @Transactional
    public void deleteWorks() {
        var works = workRepository.findAll();

        if (works.isEmpty()) {
            throw new BusinessException(
                    new MCIFWorkErrorMessage(
                            Map.of(CIF_WORK_IS_EMPTY, new Object()),
                            CIF_WORK_IS_EMPTY));
        }

        workRepository.deleteAll();
    }
}
