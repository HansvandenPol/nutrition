package nl.nutrition.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configuration for Spring Security. In this configuration you can determine which endpoints need
 * authentication, if csrf should be enabled, which users/roles can access which pages and much
 * more.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /**
   * Configures what should happen before the request enters the controller, and the response is
   * received by the client.
   *
   * <p>In this configuration we tell Spring to enable 'csrf' only for the '/dev/dashboard'
   * endpoint. Also we configure that all endpoints are permitted by all users, since we do the
   * authentication ourselves.
   *
   * @param http the HttpSecurity object
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests(a ->
      a.antMatchers("/html/index.html")
          .permitAll()
          .antMatchers("/css/**")
          .permitAll()
          .antMatchers("/js/**")
          .permitAll()
          .antMatchers("/api/public/**")
          .permitAll()
          .antMatchers("/api/meal/**")
          .hasRole("USER")
          .antMatchers("/**")
          .denyAll()
    ).formLogin();
  }
}
