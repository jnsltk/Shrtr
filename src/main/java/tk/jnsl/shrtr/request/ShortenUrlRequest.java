package tk.jnsl.shrtr.request;

import jakarta.validation.constraints.NotNull;

public class ShortenUrlRequest {
    @NotNull
    private String url;
    private String alias;

    public ShortenUrlRequest(String url) {
        this.url = url;
    }

    public ShortenUrlRequest(String url, String alias) {
        this.url = url;
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "url='" + url + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
