package tk.jnsl.shrtr.dto;

import jakarta.validation.constraints.NotNull;
import tk.jnsl.shrtr.util.UrlEncoder;

public class ShortenUrlInputDto {
    @NotNull
    private String url;
    private String alias;

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
