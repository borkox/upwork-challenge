package com.urlshort.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;

import com.urlshort.TestConfig;
import com.urlshort.dto.UrlShortCreateRequestDto;
import com.urlshort.dto.UrlShortResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
@ActiveProfiles("test")
@Slf4j
public class UrlShortIntegrationTest {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testCreateShort() {
        // Create  link
        ResponseEntity<UrlShortResponseDto> response = testRestTemplate.postForEntity(
                "http://localhost:" + randomServerPort + "/api/url",
                new UrlShortCreateRequestDto("https://google.com"), UrlShortResponseDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        String shortUrl = response.getBody().getShortUrl();
        log.info("Created link is: {}", shortUrl);

        shortUrl = shortUrl.replace("example.com", "localhost:" + randomServerPort);
        ResponseEntity<Void> followLink = testRestTemplate.getForEntity(shortUrl, Void.class);
        assertThat(followLink.getStatusCode()).isEqualTo(HttpStatus.MOVED_PERMANENTLY);

    }

    @Test
    public void testShortLinkNotFound() {
        String shortUrl = "http://localhost:" + randomServerPort + "/sfsdfssdfs";
        ResponseEntity<Void> followLink = testRestTemplate.getForEntity(shortUrl, Void.class);
        assertThat(followLink.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}
