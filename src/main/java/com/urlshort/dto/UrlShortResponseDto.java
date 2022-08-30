package com.urlshort.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortResponseDto {

    @NotNull
    private String shortUrl;
    @NotNull
    private String longUrl;
}
