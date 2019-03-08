package br.com.vfs.marketplacebackend.resource;

import br.com.vfs.marketplacebackend.es.service.ProductESServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

//    public ResponseEntity<?> getProduct(@PathVariable(name = "name") String name){
//        return ResponseEntity.ok(Arrays.asList("monitor 1","monitor 2","monitor 3"));
//    }
}
