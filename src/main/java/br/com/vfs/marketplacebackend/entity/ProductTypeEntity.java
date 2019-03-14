package br.com.vfs.marketplacebackend.entity;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "product_types")
public class ProductTypeEntity {
    @Id
    private Integer id;

    @NotBlank
    @Size(min=3, max = 20)
    private String description;

    @OneToMany(mappedBy = "productType")
    private List<ProductEntity> products;

    public enum ProductType {
        NOTEBOOK(1),
        DESKTOP(2),
        MONITOR(3),
        COMPONENTE(4),
        PERIFERICO(5);

        private Integer code;

        ProductType(final Integer code) {
            this.code = code;
        }

        public static ProductType getProductType(Integer code) {
            return Arrays.stream(ProductType.values())
                    .filter(t -> t.code == code)
                    .findFirst()
                    .orElseThrow(() -> new EnumConstantNotPresentException(ProductType.class, code.toString()));
        }

        public Integer getCode() {
            return code;
        }
    }

    public ProductType getProductType(){
        return ProductType.getProductType(id);
    }
}
