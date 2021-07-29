package com.adasoraninda.cifproject.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MCIFAddressRequest {

    @NotNull(message = "Nama harus di isi")
    @NotEmpty(message = "Nama tidak boleh kosong")
    @NotBlank(message = "Nama tidak boleh hanya karakter spasi")
    private String name;

    @NotNull(message = "Alamat harus di isi")
    @NotEmpty(message = "Alamat tidak boleh kosong")
    @NotBlank(message = "Alamat tidak boleh hanya karakter spasi")
    private String address;

    @NotNull(message = "CIF id harus di isi")
    private Long cifId;
}
