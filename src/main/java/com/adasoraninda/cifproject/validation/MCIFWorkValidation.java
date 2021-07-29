package com.adasoraninda.cifproject.validation;

import com.adasoraninda.cifproject.model.entity.MCIFWork;
import org.springframework.stereotype.Component;

@Component
public class MCIFWorkValidation {

    public void validateAndUpdate(MCIFWork oldWork, MCIFWork newWork) {
        if (!nameValidation(oldWork.getName(), newWork.getName())) {
            oldWork.setName(newWork.getName());
        }

        if (!typeValidation(oldWork.getType(), newWork.getType())) {
            oldWork.setType(newWork.getType());
        }

        if (!incomeValidation(oldWork.getPenghasilan(), newWork.getPenghasilan())) {
            oldWork.setPenghasilan(newWork.getPenghasilan());
        }

        if (!cifValidation(oldWork.getCif().getId(), newWork.getCif().getId())) {
            oldWork.setCif(newWork.getCif());
        }
    }

    private boolean nameValidation(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean typeValidation(String oldType, String newType) {
        return oldType.equals(newType);
    }

    private boolean incomeValidation(Long oldIncome, Long newIncome) {
        return oldIncome.equals(newIncome);
    }

    private boolean cifValidation(Long oldCifId, Long newCifId) {
        return oldCifId.equals(newCifId);
    }


}
