package br.com.vfs.marketplacebackend.es.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(indexName = "marketplace-sales", type = "sales")
public class SaleES {

    @Id
    private String idReceipt;

    private Long idDB;

    private ProductES product;

    private String userName;

    private Integer quantity;

    private BigDecimal totalValue;

    private LocalDateTime dateCreate;
}
