package com.urlshort.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UrlShortCreateRequestDto {

    @NotNull
    @NotEmpty
    private String url;

}
