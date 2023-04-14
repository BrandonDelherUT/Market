package mx.edu.utez.firstapp.controllers.subcategory;

import mx.edu.utez.firstapp.controllers.subcategory.dtos.SubcategoryDto;
import mx.edu.utez.firstapp.models.subcategory.Subcategory;
import mx.edu.utez.firstapp.services.subcategory.SubcategoryService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-market/subcategory")
@CrossOrigin(origins = {"*"})
public class SubcategoryController {
    @Autowired
    private SubcategoryService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Subcategory>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Subcategory>> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Subcategory>> insert(
            @RequestBody SubcategoryDto subcategoryDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.insert(subcategoryDto.getSubCategory()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Subcategory>> update(
            @RequestBody SubcategoryDto subcategoryDto, @Valid BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.service.update(subcategoryDto.getSubCategory()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(
            @RequestBody SubcategoryDto subcategoryDto) {
        return new ResponseEntity<>(
                this.service.changeStatus(subcategoryDto.getSubCategory()),
                HttpStatus.OK
        );
    }
}
