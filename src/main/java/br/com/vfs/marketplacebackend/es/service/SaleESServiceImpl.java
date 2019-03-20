package br.com.vfs.marketplacebackend.es.service;

import br.com.vfs.marketplacebackend.entity.SaleEntity;
import br.com.vfs.marketplacebackend.es.entity.ProductES;
import br.com.vfs.marketplacebackend.es.entity.SaleES;
import br.com.vfs.marketplacebackend.es.repository.SaleESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleESServiceImpl {
    private final SaleESRepository saleESRepository;
    @Autowired
    public SaleESServiceImpl(SaleESRepository saleESRepository) {
        this.saleESRepository = saleESRepository;
    }

    public SaleES addSaleES(SaleEntity saleEntity, ProductES productES) {
        return saleESRepository.save(SaleES.builder()
                .idReceipt(saleEntity.getCodReceipt())
                .idDB(saleEntity.getId())
                .idProductES(productES.getIdES())
                .userName(saleEntity.getUser().getUsername())
                .quantity(saleEntity.getQuantity())
                .totalValue(saleEntity.getTotalValue())
                .build());
    }

    public SaleES findByID(String code) {
        return saleESRepository.findById(code).orElseThrow(RuntimeException::new);
    }

    public List<SaleES> findByUserName(String username) {
        return saleESRepository.findAllByUserName(username);
    }
}
