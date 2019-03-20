package br.com.vfs.marketplacebackend.dto;

import br.com.vfs.marketplacebackend.es.entity.ProductES;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Sale {

    private ProductES product;
    private Integer quantity;
    private BigDecimal totalValue;
    private String username;
}
