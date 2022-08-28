package nl.nutrition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Entrypoint for the index page. Accessible by '/' or '/index' */
@Controller
public class IndexController {
  @GetMapping({"/", "index"})
  public String home() {
    return "index";
  }
}
