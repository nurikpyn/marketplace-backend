package br.com.vfs.marketplacebackend.service;

import static br.com.vfs.marketplacebackend.entity.ProductTypeEntity.ProductType.createEntity;

import br.com.vfs.marketplacebackend.entity.ProductEntity;
import br.com.vfs.marketplacebackend.entity.ProviderEntity;
import br.com.vfs.marketplacebackend.es.entity.ProductES;
import br.com.vfs.marketplacebackend.es.repository.ProductESRepository;
import br.com.vfs.marketplacebackend.repository.ProductRepository;
import br.com.vfs.marketplacebackend.repository.ProviderRepository;
import br.com.vfs.marketplacebackend.soap.dto.ProductWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;
    private final ProductESRepository productESRepository;
    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository,
            final ProviderRepository providerRepository,
            final ProductESRepository productESRepository) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.productESRepository = productESRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsertProduct(ProductWS productWS) {
        final ProviderEntity providerEntity = providerRepository.findByName(productWS.getAuthProvider())
                .orElseThrow(RuntimeException::new);
        ProductEntity productEntity = productRepository
                .findByIdProductProviderAndProvider(productWS.getId(), providerEntity).orElse(null);
        if(productEntity == null) {
            productEntity = ProductEntity.builder()
                    .idProductProvider(productWS.getId())
                    .name(productWS.getName())
                    .description(productWS.getDescription())
                    .productType(createEntity(productWS.getType().name()))
                    .value(productWS.getValue())
                    .provider(providerEntity)
                    .build();
        } else  {
            productEntity.setName(productWS.getName());
            productEntity.setDescription(productWS.getDescription());
            productEntity.setProductType(createEntity(productWS.getType().name()));
            productEntity.setValue(productWS.getValue());
        }
        productEntity = productRepository.save(productEntity);

        //gravar no elastic search
        final String idES = String.format("%s-%s", productEntity.getIdProductProvider(), productEntity.getProvider().getName());
        final ProductES productES = ProductES.builder()
                .idES(idES)
                .idDB(productEntity.getId())
                .idProductProvider(productEntity.getIdProductProvider())
                .provider(productEntity.getProvider().getName())
                .productType(productEntity.getProductType().getDescription())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .build();
        productESRepository.save(productES);

    }

}
