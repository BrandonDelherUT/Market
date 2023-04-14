package mx.edu.utez.firstapp.controllers.Contact;

import mx.edu.utez.firstapp.controllers.Contact.Dtos.EmailDto;
import mx.edu.utez.firstapp.utils.CustomResponse;
import mx.edu.utez.firstapp.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-market/contact")
@CrossOrigin(origins = {"*"})
public class ContactController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Object>> sendEmailContact(@Valid @RequestBody EmailDto email){
        if(this.emailService.sendMail(email))
            return new ResponseEntity<>(
                    new CustomResponse<>(null, false, 200, "OK"), HttpStatus.OK
            );
        return new ResponseEntity<>(
                new CustomResponse<>(null, true, 400, "BAD_REQUEST"), HttpStatus.BAD_REQUEST
        );
    }
}
