package com.hafez.ecommerce.category;


import com.hafez.ecommerce.product.Product;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product> products;

}
