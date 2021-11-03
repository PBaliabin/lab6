package project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled=true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

//    @Autowired
//    public void initialize(AuthenticationManagerBuilder builder) throws Exception {
//        builder.inMemoryAuthentication().withUser("dave")
//                .password("secret").roles("USER");
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select login, password, enabled from \"app_user\" where login=?")
//                .authoritiesByUsernameQuery(
//                        "select login, role from \"roles\" where login=?")
//                .withUser("admin").password(passwordEncoder.encode("password")).roles("ADMIN");
//    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .antMatchers("/add/**", "/update/**", "/delete/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/perform_login").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
