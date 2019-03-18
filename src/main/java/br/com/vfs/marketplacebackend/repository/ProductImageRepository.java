package br.com.vfs.marketplacebackend.repository;

import br.com.vfs.marketplacebackend.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {

}
