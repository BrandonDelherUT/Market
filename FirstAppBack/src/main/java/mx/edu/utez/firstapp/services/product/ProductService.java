package mx.edu.utez.firstapp.services.product;

import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.models.product.Product;
import mx.edu.utez.firstapp.models.product.ProductImages;
import mx.edu.utez.firstapp.models.product.ProductImagesRepository;
import mx.edu.utez.firstapp.models.product.ProductRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Value("${spring.os}")
    private String rootPath;

    private String separator = FileSystems.getDefault().getSeparator();
    private String BASEURL = "http://localhost:8080/api-market/product/loadfile/";


    public CustomResponse<Object> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Lista de productos"
        );
    }

    public CustomResponse<Object> getOne(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (product.isPresent())
            return new CustomResponse<>(
                    product.get(),
                    false,
                    200,
                    "Producto encontrado"
            );
        return new CustomResponse<>(
                null,
                true,
                400,
                "Producto no encontrado"
        );
    }

    public CustomResponse<Object> delete(Long id) {
        Optional<Product> product = this.repository.findById(id);
        if (product.isPresent()) {
            this.repository.delete(product.get());
            return new CustomResponse<>(
                    null,
                    false,
                    200,
                    "Producto eliminado"
            );
        }
        return new CustomResponse<>(
                null,
                true,
                400,
                "Producto no encontrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class, IOException.class})
    public CustomResponse<Object> update(Product product) {
        Optional<Product> exists = this.repository.findById(product.getId());
        if (exists.isPresent()) {
            this.repository.save(product);
            return new CustomResponse<>(
                    null,
                    false,
                    200,
                    "Producto actualizado"
            );
        }
        return new CustomResponse<>(
                null,
                true,
                400,
                "Producto no encontrado"
        );
    }

    //change status
    @Transactional(rollbackFor = {SQLException.class})
    @Modifying
    public CustomResponse<Boolean> changeStatus(Product product) {
        if (!this.repository.existsById(product.getId()))
            return new CustomResponse<>(
                    null, true, 400,
                    "La categoría no existe"
            );
        return new CustomResponse<>(
                this.repository.updateStatusById(
                        product.getStatus(), product.getId()) == 1,
                false, 200,
                "Categoría registrada correctamente"
        );
    }














    ///////////////////////////////////IMAGENES//////////////////////////////////////////////////////
    public ResponseEntity<Resource> getImage(String uid) throws IOException {
        Path path = Paths.get(rootPath + separator + uid);
        ByteArrayResource resource = new ByteArrayResource(
                Files.readAllBytes(path)
        );
        //consulta ProductImages -> MimeType
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

    @Transactional(rollbackFor = {SQLException.class, IOException.class})
    public CustomResponse<Object> insert(Product product) {
        List<ProductImages> imagesList = new ArrayList<>();
        product.getImages().forEach(image -> {
            byte[] bytes = Base64.decodeBase64(image.getFilebase64());
            String uid = UUID.randomUUID().toString();
            try (OutputStream stream = new FileOutputStream(
                    rootPath + separator + uid + image.getMimeType())
            ) {
                stream.write(bytes);
                image.setUrl(BASEURL + uid + image.getMimeType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (this.repository.existsByName(product.getName()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El producto ya existe"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(product),
                false,
                200,
                "Producto registrado correctamente"
        );
    }
}
