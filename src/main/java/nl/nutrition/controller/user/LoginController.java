package nl.nutrition.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Entrypoint controller for the login page
 *
 * <p>The post mapping is handled by Spring Security
 */
@Controller
@RequestMapping("login")
public class LoginController {
  @GetMapping
  public String loginPage() {
    return "login";
  }
}
