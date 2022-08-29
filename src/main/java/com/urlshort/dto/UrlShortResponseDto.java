package com.urlshort.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlShortResponseDto {

    @NotNull
    private String shortUrl;
    @NotNull
    private String longUrl;
}
