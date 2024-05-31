
package com.libreria.libreria.controlller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@ControllerAdvice
@OpenAPIDefinition(
    info = @Info(
        title = "Api Libreria",
        version = "1.0",
        description = "api de libros"
    ),
    tags = {
        @Tag(
            name = "Controlador Base",
            description = "Controlador base de la api, este contralador se extendera a todos los endpoints"
        )
    }
)
public class ApiBaseController {
    
}
