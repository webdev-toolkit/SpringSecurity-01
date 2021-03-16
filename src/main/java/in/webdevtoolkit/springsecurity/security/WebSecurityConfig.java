package in.webdevtoolkit.springsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("foo").password(encoder.encode("admin")).roles("ADMIN").and()
                .withUser("bar").password(encoder.encode("use")).roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().mvcMatchers("/place-order").hasRole("USER")
               .mvcMatchers("/admin").hasRole("ADMIN")
               .anyRequest().authenticated().and().httpBasic().and().formLogin().successHandler(
                       (request, response, auth) -> {System.out.println("Login Successful ..!!");} )
       .and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
               .logoutSuccessHandler((request, response, auth) -> {System.out.println("Log Out Successful ..!!");});

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/user/**");
    }

    @Bean
    public StrictHttpFirewall httpFirewall(){
        StrictHttpFirewall httpFirewall = new StrictHttpFirewall();
        httpFirewall.setAllowedHeaderNames((headerName) -> {
            return true;
        });

        httpFirewall.setAllowedHeaderValues((headerValue) -> {
            return headerValue.contains("\\r");
        });


        return httpFirewall;
    }
}
