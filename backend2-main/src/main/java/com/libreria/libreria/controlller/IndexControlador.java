package com.libreria.libreria.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador extends ApiBaseController{
   public IndexControlador() {
   }

   @GetMapping({"/"})
   public String home() {
      return "index";
   }
}
