package tk.jnsl.shrtr.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortenUrlInputDto {
    @NotNull
    @URL
    private final String url;
    private final String alias;
    private final long expiryDate;

    public ShortenUrlInputDto(String url, String alias, long expiryDate) {
        this.url = url;
        this.alias = alias;
        this.expiryDate = expiryDate;
    }

    public String getUrl() {
        return url;
    }

    public String getAlias() {
        return alias;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "ShortenUrlInputDto{" +
                "url='" + url + '\'' +
                ", alias='" + alias + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
