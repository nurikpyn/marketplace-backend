package br.com.vfs.marketplacebackend.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductResource {

    @GetMapping("/public/{name}/search")
    public ResponseEntity<?> searchNameProducts(@PathVariable(name = "name") String name){
        return ResponseEntity.ok(Arrays.asList("monitor 1","monitor 2","monitor 3"));
    }

//    public ResponseEntity<?> getProduct(@PathVariable(name = "name") String name){
//        return ResponseEntity.ok(Arrays.asList("monitor 1","monitor 2","monitor 3"));
//    }
}
