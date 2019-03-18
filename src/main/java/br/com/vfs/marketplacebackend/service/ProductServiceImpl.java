package br.com.vfs.marketplacebackend.service;

import static br.com.vfs.marketplacebackend.entity.ProductTypeEntity.ProductType.createEntity;

import br.com.vfs.marketplacebackend.dto.Image;
import br.com.vfs.marketplacebackend.entity.ProductEntity;
import br.com.vfs.marketplacebackend.entity.ProductImageEntity;
import br.com.vfs.marketplacebackend.entity.ProviderEntity;
import br.com.vfs.marketplacebackend.es.entity.ProductES;
import br.com.vfs.marketplacebackend.es.repository.ProductESRepository;
import br.com.vfs.marketplacebackend.repository.ProductImageRepository;
import br.com.vfs.marketplacebackend.repository.ProductRepository;
import br.com.vfs.marketplacebackend.repository.ProviderRepository;
import br.com.vfs.marketplacebackend.soap.dto.ProductWS;
import br.com.vfs.marketplacebackend.soap.endpoint.ProductEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductEndpoint.class);

    private final ProductRepository productRepository;

    private final ProviderRepository providerRepository;

    private final ProductImageRepository productImageRepository;

    private final ProductESRepository productESRepository;

    private final ImageServiceImpl imageService;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository,
            final ProviderRepository providerRepository,
            final ProductImageRepository productImageRepository,
            final ProductESRepository productESRepository,
            final ImageServiceImpl imageService) {
        this.productRepository = productRepository;
        this.providerRepository = providerRepository;
        this.productImageRepository = productImageRepository;
        this.productESRepository = productESRepository;
        this.imageService = imageService;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsertProduct(ProductWS productWS) {
        try {
            final ProviderEntity providerEntity = providerRepository.findByName(productWS.getProvider())
                    .orElseThrow(RuntimeException::new);
            ProductEntity productEntity = productRepository
                    .findByIdProductProviderAndProvider(productWS.getId(), providerEntity).orElse(null);
            if (productEntity == null) {
                productEntity = ProductEntity.builder()
                        .idProductProvider(productWS.getId())
                        .name(productWS.getName())
                        .description(productWS.getDescription())
                        .productType(createEntity(productWS.getType().name()))
                        .value(productWS.getValue())
                        .provider(providerEntity)
                        .build();
            } else {
                productEntity.setName(productWS.getName());
                productEntity.setDescription(productWS.getDescription());
                productEntity.setProductType(createEntity(productWS.getType().name()));
                productEntity.setValue(productWS.getValue());
            }
            productEntity = productRepository.save(productEntity);

            //gravar no elastic search
            final String idES = String
                    .format("%s-%s", productEntity.getIdProductProvider(), productEntity.getProvider().getName());
            final ProductES productES = ProductES.builder()
                    .idES(idES)
                    .idDB(productEntity.getId())
                    .idProductProvider(productEntity.getIdProductProvider())
                    .provider(productEntity.getProvider().getName())
                    .productType(productEntity.getProductType().getDescription())
                    .name(productEntity.getName())
                    .description(productEntity.getDescription())
                    .value(productEntity.getValue())
                    .build();
            productESRepository.save(productES);
        } catch (Exception e) {
            LOGGER.error("Erro ao criar/atualizar um produto! productWS.id = {}, productWS.provider = {}",
                    productWS.getId(), productWS.getProvider(), e);
            throw e;
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveImageProduct(Image image) {
        final ProductES productES = productESRepository
                .findByIdProductProviderAndProvider(image.getIdProvider(), image.getProvider())
                .orElseThrow(RuntimeException::new);
        final ProductEntity product = productRepository.findById(productES.getIdDB())
                .orElseThrow(RuntimeException::new);
        final String url = imageService.saveBlobFile(image);
        if (image.isPrimary()) {
            productES.setUrlPrimaryImage(url);
            product.setPrimaryImageUrl(url);
            productRepository.save(product);
        } else {
            productES.getUrlImages().add(url);
            productImageRepository.save(ProductImageEntity.builder()
                    .product(product)
                    .url(url)
                    .build());
        }
        productESRepository.save(productES);
    }

}
