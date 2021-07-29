package com.adasoraninda.cifproject.model.response;

import lombok.Data;

@Data
public class MCIFWorkResponse {
    private Long id;
    private String name;
    private String type;
    private Long penghasilan;
    private MCIFResponse cif;
}
