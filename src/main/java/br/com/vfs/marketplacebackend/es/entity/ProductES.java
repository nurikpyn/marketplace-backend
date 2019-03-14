package br.com.vfs.marketplacebackend.es.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(indexName = "marketplace", type = "products")
public class ProductES {
    @Id
    private String idES;

    private Long idDB;

    private String name;

    private String description;

    private String idProductProvider;

    private String provider;

    private BigDecimal value;

    private String productType;

}
