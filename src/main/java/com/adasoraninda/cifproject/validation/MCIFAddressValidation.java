package com.adasoraninda.cifproject.validation;

import com.adasoraninda.cifproject.model.entity.MCIFAddress;
import org.springframework.stereotype.Component;

@Component
public class MCIFAddressValidation {

    public void validateAndUpdate(MCIFAddress oldAddress, MCIFAddress newAddress){
        if(!nameValidation(oldAddress.getName(),newAddress.getName())){
            oldAddress.setName(newAddress.getName());
        }

        if(!addressValidation(oldAddress.getAddress(), newAddress.getAddress())){
            oldAddress.setAddress(newAddress.getAddress());
        }

        if(!cifValidation(oldAddress.getCif().getId(), newAddress.getCif().getId())){
            oldAddress.setCif(newAddress.getCif());
        }
    }

    private boolean nameValidation(String oldName, String newName){
        return oldName.equals(newName);
    }

    private boolean addressValidation(String oldAddress, String newAddress){
        return oldAddress.equals(newAddress);
    }

    private boolean cifValidation(Long oldCifId, Long newCifId){
        return oldCifId.equals(newCifId);
    }

}
