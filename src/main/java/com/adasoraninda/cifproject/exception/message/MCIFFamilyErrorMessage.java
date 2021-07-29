package com.adasoraninda.cifproject.exception.message;

import com.adasoraninda.cifproject.exception.code.MCIFFamilyErrorCode;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.adasoraninda.cifproject.exception.code.MCIFFamilyErrorCode.CIF_FAMILY_ALREADY_EXISTS;
import static com.adasoraninda.cifproject.exception.code.MCIFFamilyErrorCode.CIF_FAMILY_NOT_FOUND;

@AllArgsConstructor
public class MCIFFamilyErrorMessage implements ErrorMessage {

    private final Map<MCIFFamilyErrorCode, Object> data;
    private final MCIFFamilyErrorCode code;

    @Override
    public String getCode() {
        return code.name();
    }

    @Override
    public String getMessage() {
        switch (code) {
            case CIF_FAMILY_NOT_FOUND:
                return errorNotFoundWithId();
            case CIF_FAMILY_ALREADY_EXISTS:
                return errorAlreadyExistsWithId();
            case CIF_FAMILY_IS_EMPTY:
                return errorIsEmpty();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String errorNotFoundWithId() {
        return "Data keluarga dengan id " + data.get(CIF_FAMILY_NOT_FOUND) + " tidak ditemukan";
    }

    private String errorAlreadyExistsWithId() {
        return "Data keluarga " + data.get(CIF_FAMILY_ALREADY_EXISTS) + " sudah ada";
    }

    private String errorIsEmpty() {
        return "Data keluarga tidak ada";
    }

}