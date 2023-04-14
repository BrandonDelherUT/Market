package mx.edu.utez.firstapp.controllers.subcategory.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.subcategory.Subcategory;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubcategoryDto {
    private Long id;
    private String name;
    private Boolean status;
    private Category category;
    private List<Product> product;
    public Subcategory getSubCategory(){
        return new Subcategory(
                getId(),
                getName(),
                getStatus(),
                getCategory(),
                getProduct()
        );
    }
}
