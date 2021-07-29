package com.adasoraninda.cifproject.validation;

import com.adasoraninda.cifproject.model.entity.MCIFFamily;
import org.springframework.stereotype.Component;

@Component
public class MCIFFamilyValidation {
    public void validateAndUpdate(MCIFFamily oldFamily, MCIFFamily newFamily) {
        if(!nameValidation(oldFamily.getName(),newFamily.getName())){
            oldFamily.setName(newFamily.getName());
        }

        if(!typeValidation(oldFamily.getType(), newFamily.getType())){
            oldFamily.setType(newFamily.getType());
        }

        if(!cifValidation(oldFamily.getCif().getId(),newFamily.getCif().getId())){
            oldFamily.setCif(newFamily.getCif());
        }
    }

    private boolean nameValidation(String oldName, String newName){
        return oldName.equals(newName);
    }

    private boolean typeValidation(String oldType, String newType){
        return oldType.equals(newType);
    }

    private boolean cifValidation(Long oldCifId, Long newCifId){
        return oldCifId.equals(newCifId);
    }

}
