package com.example.practice.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.practice.services.CustUserDetailsService;

@Configuration         
@EnableWebSecurity           // hum na application.yml banai ha wo bilkul same ha .proprties wali sa bus formate docket wala style
							// sa likhata hai is lia saqib na ya use kia hai ya bhi default spring read karlaata hai
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustUserDetailsService userdetailsservice;   // hum na apni service banai hai us sa hum la raeh ha data
	

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		     // in memory matlab khud ka username or password
//		auth.inMemoryAuthentication().withUser("ali").password(getPasswordEncoder().encode("123456")).roles("ADMIN");
//		
//	}

	            // jab ya project run hoga ya method chala ga wo phir CustUserDetailsService ka andar jo method hai 
	@Override	// usko call karaga 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		     // in memory matlab khud ka username or password
		auth.userDetailsService(userdetailsservice);
		
	}
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {                   //httpBasic ya deafault pop up login ka data hai laking mana isko desable kardia hai
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//	}
	
															//HttpMethod.OPTIONS is lia likha ha taka tamam request admin get kar saka warna HttpMethod.post karta to admin sirf post ki i request ko access karsat=kta tha
	@Override				// .antMatchers("/home","Login","contact).permitAll() is sa har koi request access kar laga //db ma role ki value ROLE_ADMIN rakha ga ya role ko khud remove kardaga
	protected void configure(HttpSecurity http) throws Exception {                   // ya deafault pop up login ka data hai laking mana isko desable kardia hai
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/user/**").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();
	}															// ya role based Authentication
	
//	auth.inMemoryAuthentication().withUser("ali").password(getPasswordEncoder().encode("123456")).roles("ADMIN");
//	auth.inMemoryAuthentication().withUser("salman").password(getPasswordEncoder().encode("123456")).roles("NORMAL");
	
	
//	.antMatchers("/public/**").hasRole("NORMAL")  normal role wala is ka tamam request ko dakha sakta ha magar wo home wala url ko access ni karsatkta hai ya role based authentication hai
//	.antMatchers("/home/**").hasRole("ADMIN")	admin ya tamam ko access kar sakta hai 
//	.antMatchers("/public/**","/home/**").hasRole("ADMIN") agar ma cha raha ho admin home ka tamam access kara or public ka sara access kara to ma asa kar sakta ho
	
	
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();           // ya latest ha dcript wala hack ho chika hai ya wala acha hai phala yapassword ko laga or usko bcript karaga or database ma bhi decrippt form ma honachaeaya
////		return NoOpPasswordEncoder.getInstance();     /// iska matlaba ha ka password encode na ho normal ma hi rahe 
//	}

	
}
