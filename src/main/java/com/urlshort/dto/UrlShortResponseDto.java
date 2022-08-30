package com.urlshort.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortResponseDto {

    @NotNull
    private String shortUrl;
    @NotNull
    private String longUrl;
}
