package br.com.vfs.marketplacebackend.es.service;

import br.com.vfs.marketplacebackend.es.entity.ProductES;
import br.com.vfs.marketplacebackend.es.repository.ProductESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductESServiceImpl {

    private final ProductESRepository productsESRepository;

    @Autowired
    public ProductESServiceImpl(ProductESRepository productsESRepository) {
        this.productsESRepository = productsESRepository;
    }

    public List<String> findTop5ByNameIsLike(String name) {
        return productsESRepository.findTop5ByNameIsLike(name, PageRequest.of(0, 5))
                .stream()
                .map(ProductES::getName)
                .collect(Collectors.toList());
    }

    public Page<ProductES> findProducts(int page, int size) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        return productsESRepository.findAll(pageRequest);
    }

    public Page<ProductES> findProducts(int page, int size, String name) {
        final PageRequest pageRequest = PageRequest.of(page, size);
        return productsESRepository.findByNameIsLike(name, pageRequest);
    }

    public ProductES add(ProductES build) {
        return productsESRepository.save(build);
    }

    public ProductES findProductDetail(String uuid) {
        return productsESRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }
}
