package br.com.vfs.marketplacebackend.resource;

import br.com.vfs.marketplacebackend.es.service.SaleESServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sales")
public class SaleResource {

    private final SaleESServiceImpl saleESService;
    @Autowired
    public SaleResource(SaleESServiceImpl saleESService) {
        this.saleESService = saleESService;
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getSale(@PathVariable(name = "code") String code){
        try {
            return ResponseEntity.ok(saleESService.findByID(code));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getAllSalesByUserName(@RequestParam(name = "username") String username){
        try {
            return ResponseEntity.ok(saleESService.findByUserName(username));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
