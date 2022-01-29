package nl.nutrition.controller.meal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meal")
public class MealController {

  @GetMapping("test")
  public String test() {
    return "doet t !";
  }


}
