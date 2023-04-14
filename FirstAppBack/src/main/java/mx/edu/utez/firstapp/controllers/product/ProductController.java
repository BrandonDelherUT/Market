package mx.edu.utez.firstapp.controllers.product;

import mx.edu.utez.firstapp.controllers.product.Dtos.ProductDto;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.edu.utez.firstapp.services.product.ProductService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api-market/product")
@CrossOrigin(origins = {"*"})
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<Object>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Object>> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Object>> update(@Valid @RequestBody ProductDto product){
        return new ResponseEntity<>(
                this.service.update(product.getProduct()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(@RequestBody ProductDto product){
        return new ResponseEntity<>(
                this.service.changeStatus(product.getProduct()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Object>> delete(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.delete(id),
                HttpStatus.OK
        );
    }



    ///////////////////////////////////IMAGENES//////////////////////////////////////////////////////

    @GetMapping("/loadfile/{uid}")
    public ResponseEntity<Resource> loadfile(@PathVariable("uid") String uid) throws IOException {
        return this.service.getImage(uid);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Object>> insert(@Valid @RequestBody ProductDto product){
        return new ResponseEntity<>(
                this.service.insert(product.getProduct()),
                HttpStatus.CREATED
        );
    }



}
