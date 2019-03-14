package br.com.vfs.marketplacebackend.repository;

import br.com.vfs.marketplacebackend.entity.ProductEntity;
import br.com.vfs.marketplacebackend.entity.ProviderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    Optional<ProductEntity> findByIdProductProviderAndProvider(String idProductProvider, ProviderEntity provider);
}
