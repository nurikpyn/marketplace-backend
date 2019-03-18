package br.com.vfs.marketplacebackend.soap.endpoint;

import br.com.vfs.marketplacebackend.dto.Image;
import br.com.vfs.marketplacebackend.service.ProductServiceImpl;
import br.com.vfs.marketplacebackend.soap.dto.ImageWSRequest;
import br.com.vfs.marketplacebackend.soap.dto.ImageWSResponse;
import br.com.vfs.marketplacebackend.soap.dto.ProductWSRequest;
import br.com.vfs.marketplacebackend.soap.dto.ProductWSResponse;
import br.com.vfs.marketplacebackend.soap.dto.StatusWS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductEndpoint.class);

    private final ProductServiceImpl productService;

    @Autowired
    public ProductEndpoint(final ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PayloadRoot(
            namespace = "dto.soap.marketplacebackend.vfs.com.br",
            localPart = "ProductWSRequest")
    @ResponsePayload
    public ProductWSResponse addProduct(@RequestPayload ProductWSRequest request) {
        LOGGER.info("Iniciar o processamento de {} produtos ", request.getProductsWS().size());
        request.getProductsWS().parallelStream().forEach(productWS -> productService.upsertProduct(productWS));
        LOGGER.info("Finalizado o processamento de {} produtos ", request.getProductsWS().size());
        ProductWSResponse response = new ProductWSResponse();
        response.setStatus(StatusWS.SUCCESS);
        return response;
    }

    @PayloadRoot(
            namespace = "dto.soap.marketplacebackend.vfs.com.br",
            localPart = "ImageWSRequest")
    @ResponsePayload
    public ImageWSResponse addImage(@RequestPayload ImageWSRequest request) {
        LOGGER.info("Iniciar o processamento de imagem do produto {}, fornecedor {} ", request.getId(), request.getProvider());
        final Image image = Image.builder()
                .idProvider(request.getId())
                .provider(request.getProvider())
                .primary(request.isPrimary())
                .type(request.getType())
                .bytes(request.getImage())
                .build();
        final ImageWSResponse response = new ImageWSResponse();
        try {
            productService.saveImageProduct(image);
            LOGGER.info("Finalizando o processamento de imagem do produto {}, fornecedor {} ", request.getId(), request.getProvider());
            response.setStatus(StatusWS.SUCCESS);
        } catch (Exception e){
           e.printStackTrace();
            response.setStatus(StatusWS.ERROR);
        }
        return response;
    }
}
