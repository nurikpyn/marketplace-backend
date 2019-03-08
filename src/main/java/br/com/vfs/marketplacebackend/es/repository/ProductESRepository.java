package br.com.vfs.marketplacebackend.es.repository;

import br.com.vfs.marketplacebackend.es.entity.ProductES;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductESRepository extends ElasticsearchRepository<ProductES, Long> {

    List<ProductES> findTop5ByNameIsLike(String name, Pageable pageable);
}
