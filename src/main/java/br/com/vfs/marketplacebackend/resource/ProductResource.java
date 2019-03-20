package br.com.vfs.marketplacebackend.resource;

import br.com.vfs.marketplacebackend.dto.Sale;
import br.com.vfs.marketplacebackend.es.service.ProductESServiceImpl;
import br.com.vfs.marketplacebackend.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductESServiceImpl productESService;
    private final ProductServiceImpl productService;

    @Autowired
    public ProductResource(final ProductESServiceImpl productESService, ProductServiceImpl productService) {
        this.productESService = productESService;
        this.productService = productService;
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
            if(StringUtils.isEmpty(name) || name.equals("%")){
                return ResponseEntity.ok(productESService.findProducts(page, size));
            }
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


    @PostMapping("/sale")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity saleProduct(@RequestBody Sale sale){
        try {
            return ResponseEntity.ok(productService.saleProduct(sale));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
