package com.adasoraninda.cifproject.exception.message;

import com.adasoraninda.cifproject.exception.code.MCIFErrorCode;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.CIF_ALREADY_EXISTS;
import static com.adasoraninda.cifproject.exception.code.MCIFErrorCode.CIF_NOT_FOUND;

@AllArgsConstructor
public class MCIFErrorMessage implements ErrorMessage {

    private final Map<MCIFErrorCode, Object> data;
    private final MCIFErrorCode code;

    @Override
    public String getCode() {
        return code.name();
    }

    @Override
    public String getMessage() {
        switch (code) {
            case CIF_NOT_FOUND:
                return errorNotFoundWithId();
            case CIF_ALREADY_EXISTS:
                return errorAlreadyExistsWithId();
            case CIF_IS_EMPTY:
                return errorIsEmpty();
            case CIF_NPWP_MUST_FILLED:
                return errorNpwpMustBeFilled();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String errorNotFoundWithId() {
        return "CIF dengan id " + data.get(CIF_NOT_FOUND) + " tidak ditemukan";
    }

    private String errorAlreadyExistsWithId() {
        return "CIF dengan id " + data.get(CIF_ALREADY_EXISTS) + " sudah ada";
    }

    private String errorIsEmpty() {
        return "CIF tidak ada";
    }

    private String errorNpwpMustBeFilled() {
        return "NPWP dengan tipe COMPANY harus di isi";
    }

}
