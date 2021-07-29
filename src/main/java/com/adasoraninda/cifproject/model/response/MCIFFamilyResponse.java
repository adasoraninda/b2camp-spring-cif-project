package com.adasoraninda.cifproject.model.response;

import lombok.Data;

@Data
public class MCIFFamilyResponse {
    private Long id;
    private String name;
    private String type;
    private MCIFResponse cif;
}
