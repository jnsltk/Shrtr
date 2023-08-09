package tk.jnsl.shrtr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.jnsl.shrtr.entity.UrlEntity;
import tk.jnsl.shrtr.exception.BadRequestException;
import tk.jnsl.shrtr.exception.NotFoundException;
import tk.jnsl.shrtr.repository.UrlRepository;
import tk.jnsl.shrtr.request.ShortenUrlRequest;
import tk.jnsl.shrtr.util.UrlEncoder;

import java.util.Optional;

@Service
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<UrlEntity> shorten(ShortenUrlRequest shortenUrlRequest) {
        if (shortenUrlRequest.getAlias() == null) {
            shortenUrlRequest.setAlias(UrlEncoder.encodeUrl(shortenUrlRequest.getUrl()));
        }
        if (urlRepository.existsByAlias(shortenUrlRequest.getAlias())) {
            throw new BadRequestException("Alias already exists.");
        }
        System.out.println("Shorten Url Request: " + shortenUrlRequest);
        UrlEntity urlEntity = new UrlEntity(shortenUrlRequest.getAlias(), shortenUrlRequest.getUrl());

        UrlEntity postSaveUrlEntity = urlRepository.save(urlEntity);
        System.out.println("Shortened Url: " + postSaveUrlEntity);

        return Optional.of(postSaveUrlEntity);
    }

    public UrlEntity getUrl(String alias) {
        return urlRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("This alias doesn't exist, try making it!"));
    }
}
