package gh.gov.moh.admissionsportal.config;

import gh.gov.moh.admissionsportal.service.UserService;
import gh.gov.moh.admissionsportal.web.controller.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpSession;


/**
 * Created by molayodecker on 26/01/2017.
 */
@Configuration
@EnableWebSecurity
@EnableJpaRepositories
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().hasRole("USER")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(loginSuccessHandler())
                    .failureHandler(loginFailureHandler())
                    .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login").deleteCookies("JSESSIONID").logoutSuccessUrl("/");
    }

    public AuthenticationSuccessHandler loginSuccessHandler() {
        //return (request, response, authentication) -> response.sendRedirect("/");
        return (request, response, authentication)-> {
            HttpSession session = request.getSession(false);
            if(session != null){
                request.setAttribute("program","program");
                if(request.getAttribute("program") == "Certificate-program") {
                    response.sendRedirect("/cert-prog");
                }
            }
            response.sendRedirect("/");
        };
    }

    public AuthenticationFailureHandler loginFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
            //request.removeAttribute("username");
            response.sendRedirect("/login");
        };
    }

    @Bean
    public EvaluationContextExtension securityExtension(){
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "security";
            }

            @Override
            public Object getRootObject() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication) {};
            }
        };
    }
}
