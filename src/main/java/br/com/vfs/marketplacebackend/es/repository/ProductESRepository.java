package br.com.vfs.marketplacebackend.es.repository;

import br.com.vfs.marketplacebackend.es.entity.ProductES;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductESRepository extends ElasticsearchRepository<ProductES, String> {

    List<ProductES> findTop5ByNameIsLike(String name, Pageable pageable);

    Page<ProductES> findByNameIsLike(String name, Pageable pageable);

    Optional<ProductES> findByIdProductProviderAndProvider (String idProductProvider, String provider);
}
