package tk.jnsl.shrtr.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.jnsl.shrtr.entity.UrlEntity;
import tk.jnsl.shrtr.dto.ShortenUrlInputDto;
import tk.jnsl.shrtr.service.UrlShortenerService;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShrtrAppController {
    private final UrlShortenerService urlShortenerService;
    @Autowired
    public ShrtrAppController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/{alias}")
    public ResponseEntity<?> returnRedirect(@PathVariable String alias) throws URISyntaxException {
        UrlEntity urlEntity = urlShortenerService.getUrl(alias);
        URI uri = new URI(urlEntity.getUrl());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/shorten/")
    public ResponseEntity<?> createRedirect(@Valid @RequestBody ShortenUrlInputDto shortenUrlInputDto) {
        return ResponseEntity.ok(urlShortenerService.shorten(shortenUrlInputDto));
    }
}
