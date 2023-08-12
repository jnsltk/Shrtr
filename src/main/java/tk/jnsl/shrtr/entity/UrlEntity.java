package tk.jnsl.shrtr.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Timestamp;

@Entity
@Table(name = "Url_records")
public class UrlEntity {
    // Time it takes for records to expire in milliseconds
    private final static long RECORD_EXPIRATION = 86400000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;
    @NaturalId
    @Column(name = "alias", nullable = false, unique = true)
    private String alias;
    @Column(name = "url", nullable = false)
    private String url;
    @Column(name = "expiry")
    private Timestamp expiryDate;

    public UrlEntity() {

    }
    public UrlEntity(String alias, String url, long expiresAfter) {
        this.alias = alias;
        this.url = url;
        this.expiryDate = (expiresAfter != 0) ? new Timestamp(System.currentTimeMillis() + expiresAfter) : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "UrlEntity{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
