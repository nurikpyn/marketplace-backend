package br.com.vfs.marketplacebackend.resource;

import br.com.vfs.marketplacebackend.es.entity.ProductES;
import br.com.vfs.marketplacebackend.es.service.ProductESServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductESServiceImpl productESService;

    @Autowired
    public ProductResource(final ProductESServiceImpl productESService) {
        this.productESService = productESService;
    }

    @GetMapping("/public/search")
    public ResponseEntity<?> searchNameProducts(@RequestParam(name = "name") String name){
        try {
            String nameEncode = URLEncoder.encode(name, "UTF-8");
            return ResponseEntity.ok(productESService.findTop5ByNameIsLike(nameEncode));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/list")
    public ResponseEntity<?> getProducts(
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "name") String name){
        try {
            String nameEncode = URLEncoder.encode(name, "UTF-8");
            return ResponseEntity.ok(productESService.findProducts(page, size, nameEncode));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/{uuid}")
    public ResponseEntity<?> getProductDetails(@PathVariable(name = "uuid") String uuid){
        try {
            return ResponseEntity.ok(productESService.findProductDetail(uuid));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("public/add")
    public ResponseEntity add(@RequestBody String name){
        final ProductES productES = productESService.add(ProductES.builder().id(UUID.randomUUID().toString()).name(name).build());
        return ResponseEntity.created(URI.create(String.format("/products/%s",productES.getId()))).body(productES);
    }
}
