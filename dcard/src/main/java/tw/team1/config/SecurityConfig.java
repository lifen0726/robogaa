package tw.team1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
//				.requestMatchers("/page/*").permitAll()
//				.requestMatchers("/css/*").permitAll()
//				.requestMatchers("/js/*").permitAll()
//				.requestMatchers("/partials/*").permitAll()
//				.requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/**").permitAll()
//				.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER", "ADMIN")
//				.requestMatchers(HttpMethod.POST, "/api/**").hasAnyRole("USER", "ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated())
				.rememberMe().tokenValiditySeconds(86400)
				.key("rememberMe-key")
				.and()
				.csrf().disable().cors().disable()
				.formLogin()
	            .loginPage("/login")
	            .permitAll()
				.defaultSuccessUrl("/welcome")
				.permitAll();

		return http.build();
	}

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors().disable()
//            .csrf().disable()
//            .authorizeHttpRequests()
//                .requestMatchers("/index", "/login").permitAll()
//                .requestMatchers("/**").permitAll()
////                .requestMatchers("/**").hasAnyRole("USER","ADMIN")
//                .anyRequest().authenticated()
//            .and()
//            .httpBasic();
//
//        return http.build();
//    }
}