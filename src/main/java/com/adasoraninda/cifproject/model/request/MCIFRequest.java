package com.adasoraninda.cifproject.model.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class MCIFRequest {

    @NotNull(message = "KTP harus di isi")
    @NotEmpty(message = "KTP tidak boleh kosong")
    @NotBlank(message = "KTP tidak boleh hanya karakter spasi")
    @Size(min = 16, max = 16, message = "KTP harus berukuran 16 karakter")
    private String idKtp;

    @NotNull(message = "Nama harus di isi")
    @NotEmpty(message = "Nama tidak boleh kosong")
    @NotBlank(message = "Nama tidak boleh hanya karakter spasi")
    private String name;

    private String npwp;

    @NotNull(message = "Nomor telepon harus di isi")
    @NotEmpty(message = "Nomor telepon tidak boleh kosong")
    @NotBlank(message = "Nomor telepon tidak boleh hanya karakter spasi")
    @Size(max = 15, message = "Nomor telepon hanta berukuran sampai 15 karakter")
    private String noTelepon;

    @Email
    private String email;

    @NotNull(message = "Tipe harus di isi")
    @NotEmpty(message = "Tipe tidak boleh kosong")
    @NotBlank(message = "Tipe tidak boleh hanya karakter spasi")
    private String type;

}
