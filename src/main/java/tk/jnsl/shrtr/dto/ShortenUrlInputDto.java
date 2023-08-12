package tk.jnsl.shrtr.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class ShortenUrlInputDto {
    @NotNull
    @URL
    private final String url;
    private final String alias;

    public ShortenUrlInputDto(String url, String alias) {
        this.url = url;
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "url='" + url + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
