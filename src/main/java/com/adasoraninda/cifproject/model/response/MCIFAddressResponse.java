package com.adasoraninda.cifproject.model.response;

import lombok.Data;

@Data
public class MCIFAddressResponse {
    private Long id;
    private String name;
    private String address;
    private MCIFResponse cif;
}
