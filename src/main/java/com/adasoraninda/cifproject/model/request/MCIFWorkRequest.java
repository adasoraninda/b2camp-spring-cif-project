package com.adasoraninda.cifproject.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MCIFWorkRequest {
    @NotNull(message = "Nama harus di isi")
    @NotEmpty(message = "Nama tidak boleh kosong")
    @NotBlank(message = "Nama tidak boleh hanya karakter spasi")
    private String name;

    @NotNull(message = "Tipe harus di isi")
    @NotEmpty(message = "Tipe tidak boleh kosong")
    @NotBlank(message = "Tipe tidak boleh hanya karakter spasi")
    private String type;

    @NotNull(message = "Penghasilan harus di isi")
    @NotEmpty(message = "Penghasilan tidak boleh kosong")
    @NotBlank(message = "Penghasilan tidak boleh hanya karakter spasi")
    private Long penghasilan;

    @NotNull(message = "CIF id harus di isi")
    private Long cifId;
}
