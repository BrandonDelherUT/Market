package mx.edu.utez.firstapp.models.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {

    boolean existsByName(String name);
}
