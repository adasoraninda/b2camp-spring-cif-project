package com.adasoraninda.cifproject.exception.message;

import com.adasoraninda.cifproject.exception.code.MCIFAddressErrorCode;
import lombok.AllArgsConstructor;

import java.util.Map;

import static com.adasoraninda.cifproject.exception.code.MCIFAddressErrorCode.CIF_ADDRESS_ALREADY_EXISTS;
import static com.adasoraninda.cifproject.exception.code.MCIFAddressErrorCode.CIF_ADDRESS_NOT_FOUND;

@AllArgsConstructor
public class MCIFAddressErrorMessage implements ErrorMessage {

    private final Map<MCIFAddressErrorCode, Object> data;
    private final MCIFAddressErrorCode code;

    @Override
    public String getCode() {
        return code.name();
    }

    @Override
    public String getMessage() {
        switch (code) {
            case CIF_ADDRESS_NOT_FOUND:
                return errorNotFoundWithId();
            case CIF_ADDRESS_ALREADY_EXISTS:
                return errorAlreadyExistsWithId();
            case CIF_ADDRESS_IS_EMPTY:
                return errorIsEmpty();
            default:
                throw new IllegalArgumentException();
        }
    }

    private String errorNotFoundWithId() {
        return "Data alamat dengan id " + data.get(CIF_ADDRESS_NOT_FOUND) + " tidak ditemukan";
    }

    private String errorAlreadyExistsWithId() {
        return "Data alamat denga id " + data.get(CIF_ADDRESS_ALREADY_EXISTS) + " sudah ada";
    }

    private String errorIsEmpty() {
        return "Data alamat tidak ada";
    }

}
