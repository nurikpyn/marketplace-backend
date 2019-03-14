package br.com.vfs.marketplacebackend.repository;

import br.com.vfs.marketplacebackend.entity.ProviderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Long> {

    Optional<ProviderEntity> findByName(String name);
}
