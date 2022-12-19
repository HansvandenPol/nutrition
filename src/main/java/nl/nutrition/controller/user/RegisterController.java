//package nl.nutrition.controller.user;
//
//import javax.validation.Valid;
//import nl.nutrition.model.RegisterResponseModel;
//import nl.nutrition.model.RegisterViewModel;
//import nl.nutrition.model.dao.User;
//import nl.nutrition.service.user.UserEntityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///** Entrypoint for the registration page */
//@Controller
//@RequestMapping("register")
//@Validated
//public class RegisterController {
//  @Autowired private UserEntityService userEntityService;
//
//  /**
//   * Shows the registration page
//   *
//   * @return
//   */
//  @GetMapping
//  public String registerPage() {
//    return "register";
//  }
//
//  /**
//   * Tries to create a new user and add it to the database
//   *
//   * @param accountData the data from the client
//   * @return Response entity containing the user id of the new user
//   */
//  @PostMapping
//  public ResponseEntity<RegisterResponseModel> create(
//      @Valid @RequestBody RegisterViewModel accountData) {
//    final User createdUser = userEntityService.createUser(accountData);
//    final RegisterResponseModel model =
//        RegisterResponseModel.withUserId(createdUser.getId()).build();
//    return ResponseEntity.ok(model);
//  }
//}
