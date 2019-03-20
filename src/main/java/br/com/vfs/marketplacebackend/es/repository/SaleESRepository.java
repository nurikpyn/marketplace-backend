package br.com.vfs.marketplacebackend.es.repository;

import br.com.vfs.marketplacebackend.es.entity.SaleES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SaleESRepository extends ElasticsearchRepository<SaleES, String> {
}
