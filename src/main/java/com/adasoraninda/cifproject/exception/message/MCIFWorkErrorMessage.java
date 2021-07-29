package com.adasoraninda.cifproject.exception.message;

import com.adasoraninda.cifproject.exception.code.MCIFWorkErrorCode;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.adasoraninda.cifproject.exception.code.MCIFWorkErrorCode.CIF_WORK_ALREADY_EXISTS;
import static com.adasoraninda.cifproject.exception.code.MCIFWorkErrorCode.CIF_WORK_NOT_FOUND;

@AllArgsConstructor
public final class MCIFWorkErrorMessage implements ErrorMessage {

    private final Map<MCIFWorkErrorCode, Object> data;
    private final MCIFWorkErrorCode code;

    @Override
    public String getCode() {
        return code.name();
    }

    @Override
    public String getMessage() {
        switch (code) {
            case CIF_WORK_NOT_FOUND:
                return errorNotFoundWithId();
            case CIF_WORK_ALREADY_EXISTS:
                return errorAlreadyExistsWithId();
            case CIF_WORK_IS_EMPTY:
                return errorIsEmpty();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String errorNotFoundWithId() {
        return "Data pekerjaan dengan id " + data.get(CIF_WORK_NOT_FOUND) + " tidak ditemukan";
    }

    private String errorAlreadyExistsWithId() {
        return "Data pekerjaan dengan id " + data.get(CIF_WORK_ALREADY_EXISTS) + " sudah ada";
    }

    private String errorIsEmpty() {
        return "Data pekerjaan tidak ada";
    }

}
