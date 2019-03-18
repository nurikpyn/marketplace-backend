package br.com.vfs.marketplacebackend.entity;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "products")
public class ProductEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=3, max = 50)
    @Column(name = "id_product_provider")
    private String idProductProvider;

    @ManyToOne
    @JoinColumn(name = "id_provider")
    private ProviderEntity provider;

    @NotBlank
    @Size(min=3, max = 50)
    private String name;

    @NotBlank
    @Size(min=3, max = 2000)
    private String description;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "id_product_type")
    private ProductTypeEntity productType;

    @Column(name = "primary_image_url")
    private String primaryImageUrl;

    @OneToMany(mappedBy = "product")
    private List<ProductImageEntity> images;
}
