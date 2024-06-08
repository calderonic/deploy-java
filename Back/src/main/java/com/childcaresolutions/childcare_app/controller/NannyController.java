
package com.childcaresolutions.childcare_app.controller;

import com.childcaresolutions.childcare_app.exeptions.EntityNotFoundException;
import com.childcaresolutions.childcare_app.exeptions.NannyNotFoundException;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateNanny;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditNanny;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseNanny;
import com.childcaresolutions.childcare_app.service.NannyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Validated
@RequestMapping(value ="/api/nanny")
@RestController
public class NannyController {

    private NannyService nannyService;

    @PostMapping("/register")
    public ResponseEntity<ResponseNanny> createNanny(@Valid @RequestBody RequestCreateNanny requestCreateNanny) {
        try {
            ResponseNanny response = nannyService.createNanny(requestCreateNanny);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Aquí lanzamos una nueva excepción con un mensaje personalizado
            throw new RuntimeException("Error registering nanny: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseNanny> getNanny(@PathVariable Long id) {
        try {
            ResponseNanny response = nannyService.readNanny(id);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseNanny> updateNanny(@Valid @RequestBody RequestEditNanny requestEditNanny) {
        try {
            ResponseNanny response = nannyService.updateNanny(requestEditNanny);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNanny(@PathVariable Long id) {
        try {
            boolean deleted = nannyService.toogleDeleteNanny(id);
            if (deleted) {
                return ResponseEntity.ok("Nanny with ID: " + id + " has been deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nanny could not be deleted");
            }
        } catch (NannyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nanny not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred: " + e.getMessage());
        }
    }
  //  @DeleteMapping("/delete/{id}")
  //  public ResponseEntity<String> deleteNanny(@PathVariable Long id) {
  //      try {
  //          boolean deleted = nannyService.toogleDeleteNanny(id);
  //          if (deleted) {
  //              return ResponseEntity.ok("Nanny deleted successfully");
  //          } else {
  //              return ResponseEntity.badRequest().body("Nanny could not be deleted");
  //          }
  //      } catch (FoundException e) {
  //          return ResponseEntity.notFound().build();
  //      }
  //  }


   // @PostMapping("/register")
   // public ResponseNanny createNanny(@Validated @RequestBody RequestCreateNanny requestCreateNanny) {
   //     return nannyService.createNanny(requestCreateNanny);
   // }
//
   //@GetMapping("/{id}")
   //public ResponseNanny getNanny(@PathVariable Long id) {
   //    return nannyService.readNanny(id);
   //}
//
//
   // @PutMapping("/update")
   // public ResponseNanny updateNanny(@Validated @RequestBody RequestEditNanny requestEditNanny) {
   //     return nannyService.updateNanny(requestEditNanny);
   // }
//
   // @DeleteMapping("/delete/{id}")
   // public Boolean deleteNanny(@PathVariable Long id) {
   //     return nannyService.toogleDeleteNanny(id);
   // }
//

}
