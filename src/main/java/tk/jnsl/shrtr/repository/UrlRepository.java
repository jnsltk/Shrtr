package tk.jnsl.shrtr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.jnsl.shrtr.entity.UrlEntity;

import java.util.Date;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByAlias(String alias);

    Boolean existsByAlias(String alias);

    void deleteByExpiryDateIsNotNullAndExpiryDateLessThan(Date now);

}
