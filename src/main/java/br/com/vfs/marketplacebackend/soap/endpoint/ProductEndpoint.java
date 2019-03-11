package br.com.vfs.marketplacebackend.soap.endpoint;

import br.com.vfs.marketplacebackend.soap.dto.ProductRequest;
import br.com.vfs.marketplacebackend.soap.dto.ProductResponse;
import br.com.vfs.marketplacebackend.soap.dto.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ProductEndpoint.class);

    @PayloadRoot(
            namespace = "dto.soap.marketplacebackend.vfs.com.br",
            localPart = "ProductRequest")
    @ResponsePayload
    public ProductResponse addProduct(@RequestPayload ProductRequest request) {

        LOGGER.info("chegou o produto"+ request.getProduct().getNome());
        ProductResponse response = new ProductResponse();
        response.setStatus(Status.SUCESSO);
        return response;
    }
}
