package com.adasoraninda.cifproject.validation;

import com.adasoraninda.cifproject.model.entity.MCIF;
import org.springframework.stereotype.Component;

@Component
public class MCIFValidation {

    public void validateAndUpdate(MCIF oldCif, MCIF newCif) {
        if (!ktpValidation(oldCif.getIdKtp(), newCif.getIdKtp())) {
            oldCif.setIdKtp(newCif.getIdKtp());
        }

        if (!nameValidation(oldCif.getName(), newCif.getName())) {
            oldCif.setName(newCif.getName());
        }

        if (!npwpValidation(oldCif.getNpwp(), newCif.getNpwp())) {
            oldCif.setNpwp(newCif.getNpwp());
        }

        if (!phoneNumberValidation(oldCif.getNoTelepon(), newCif.getNoTelepon())) {
            oldCif.setNoTelepon(newCif.getNoTelepon());
        }

        if (!emailValidation(oldCif.getEmail(), newCif.getEmail())) {
            oldCif.setEmail(newCif.getEmail());
        }

        if (!typeValidation(oldCif.getType(), newCif.getType())) {
            oldCif.setType(newCif.getType());
        }

    }

    public boolean checkNpwp(MCIF cif) {
        String TYPE_COMPANY = "COMPANY";

        if (cif.getType().equalsIgnoreCase(TYPE_COMPANY)) {
            return cif.getNpwp() == null;
        }

        return false;
    }

    private boolean ktpValidation(String oldKtp, String newKtp) {
        return oldKtp.equals(newKtp);
    }

    private boolean nameValidation(String oldName, String newName) {
        return oldName.equals(newName);
    }

    private boolean phoneNumberValidation(String oldNumber, String newNumber) {
        return oldNumber.equals(newNumber);
    }

    private boolean typeValidation(String oldType, String newType) {
        return oldType.equals(newType);
    }

    private boolean npwpValidation(String oldNpwp, String newNpwp) {
        if (oldNpwp != null && newNpwp != null) {
            return oldNpwp.equals(newNpwp);
        }
        return false;
    }

    private boolean emailValidation(String oldEmail, String newEmail) {
        if (oldEmail != null && newEmail != null) {
            return oldEmail.equals(newEmail);
        }
        return false;
    }

}
