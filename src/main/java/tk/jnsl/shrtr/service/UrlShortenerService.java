package tk.jnsl.shrtr.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.jnsl.shrtr.entity.UrlEntity;
import tk.jnsl.shrtr.exception.BadRequestException;
import tk.jnsl.shrtr.exception.NotFoundException;
import tk.jnsl.shrtr.repository.UrlRepository;
import tk.jnsl.shrtr.dto.ShortenUrlInputDto;
import tk.jnsl.shrtr.util.UrlEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class UrlShortenerService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlShortenerService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Optional<UrlEntity> shorten(ShortenUrlInputDto shortenUrlInputDto) {
        String url = shortenUrlInputDto.getUrl();
        String alias;
        UrlEntity urlEntity;
        if (shortenUrlInputDto.getAlias() == null || shortenUrlInputDto.getAlias().isBlank()) {
            alias = UrlEncoder.encodeUrl(url);
            urlEntity = new UrlEntity(alias, url);
            if (urlRepository.existsByAlias(alias)) {
                return Optional.of(urlEntity);
            }
        } else {
            alias = shortenUrlInputDto.getAlias();
            urlEntity = new UrlEntity(alias, url);
            if (urlRepository.existsByAlias(alias)) {
                throw new BadRequestException("This custom alias already exists");
            }
        }

        System.out.println(urlEntity);
        UrlEntity postSaveUrlEntity = urlRepository.save(urlEntity);
        return Optional.of(postSaveUrlEntity);
    }

    public UrlEntity getUrl(String alias) {
        return urlRepository.findByAlias(alias)
                .orElseThrow(() -> new NotFoundException("This alias doesn't exist, try making it!"));
    }

    @Transactional
    @Scheduled(cron = "1 * * * * ?")
    public void purgeExpired() {
        Date now = Date.from(Instant.now());
        urlRepository.deleteByExpiryDateLessThan(now);
    }
}
