package com.atmj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.atmj.gsboot.services.users.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class GoldenSecurityConfig {

	private UserDetailServiceImpl userDetailsService;
	private static final String ADMIN = "ADMIN";

	public GoldenSecurityConfig(UserDetailServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain employeeFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/employee/**")
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/employee/admin").hasRole("USER")
						.requestMatchers("/employee/myincidents").hasRole("USER").requestMatchers("/employee/in")
						.hasRole("USER").requestMatchers("/employee/daily").hasRole("USER")
						.requestMatchers("/employee/out").hasRole("USER").requestMatchers("/employee/newoperation")
						.hasRole("USER").requestMatchers("/employee/newconcept").hasRole("USER")
						.requestMatchers("/employee/newincome").hasRole("USER")
						.requestMatchers("/employee/newgratification").hasRole("USER")
						.requestMatchers("/employee/registernumber").hasRole("USER")
						.requestMatchers("/employee/lastgratifications").hasRole("USER")
						.requestMatchers("/employee/newpaymentchangemachine").hasRole("USER")
						.requestMatchers("/employee/newmoneyadvance").hasRole("USER")
						.requestMatchers("/employee/newopenmachine").hasRole("USER")
						.requestMatchers("/employee/extrahours").hasRole("USER").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/employee/login").permitAll()
						.defaultSuccessUrl("/employee/admin", true).failureForwardUrl("/403employee"))
				.logout(logout -> logout.logoutUrl("/employee/j_spring_security_logout")
						.logoutSuccessUrl("/employee/login").invalidateHttpSession(true))
				.exceptionHandling(eH -> eH.accessDeniedPage("/403employee"));
		return http.build();
	}

	@Bean
	public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/admin/**")
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/admin/admin").hasRole(ADMIN)
						.requestMatchers("/admin/newcalendar").hasRole(ADMIN).requestMatchers("/admin/daily")
						.hasRole(ADMIN).requestMatchers("/admin/newentrysafe").hasRole(ADMIN)
						.requestMatchers("/admin/newentrymachine").hasRole(ADMIN).requestMatchers("/admin/againday*")
						.hasRole(ADMIN).requestMatchers("/admin/beforeday*").hasRole(ADMIN)
						.requestMatchers("/admin/updateoperation").hasRole(ADMIN)
						.requestMatchers("/admin/pendingissues").hasRole(ADMIN).requestMatchers("/admin/totalsafe")
						.hasRole(ADMIN).requestMatchers("/admin/searchmonthreset").hasRole(ADMIN)
						.requestMatchers("/admin/newmessage").hasRole(ADMIN).requestMatchers("/admin/messages")
						.hasRole(ADMIN).requestMatchers("/admin/searchticketsByDay").hasRole(ADMIN)
						.requestMatchers("/admin/changemachinetotal").hasRole(ADMIN)
						.requestMatchers("/admin/newentryvisible").hasRole(ADMIN)
						.requestMatchers("/admin/searchregister").hasRole(ADMIN)
						.requestMatchers("/admin/resultentrysortsafe").hasRole(ADMIN).anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/admin/login").permitAll()
						.defaultSuccessUrl("/admin/admin", true).failureForwardUrl("/403admin"))
				.logout(logout -> logout.logoutUrl("/admin/j_spring_security_logout").logoutSuccessUrl("/admin/login")
						.invalidateHttpSession(true))
				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403admin"));
		return http.build();
	}

	@Bean
	public SecurityFilterChain bossFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/adminboss").hasRole("BOSS")
				.requestMatchers("/newuser").hasRole("BOSS").requestMatchers("/newentrymoney").hasRole("BOSS")
				.requestMatchers("/summaryincome").hasRole("BOSS").requestMatchers("/summaryexpenses").hasRole("BOSS")
				.requestMatchers("/searchregister").hasRole("BOSS").requestMatchers("/searchrecharges").hasRole("BOSS")
				.requestMatchers("/daily").hasRole("BOSS").requestMatchers("/searchtpv").hasRole("BOSS")
				.requestMatchers("/changemachinetotal").hasRole("BOSS").requestMatchers("/searchentrysortsafe")
				.hasRole("BOSS").requestMatchers("/enabledisableuser").hasRole("BOSS").requestMatchers("/reset")
				.hasRole("BOSS").requestMatchers("/searchopenmachines").hasRole("BOSS").requestMatchers("/newtpv")
				.hasRole("BOSS").requestMatchers("/extrahours").hasRole("BOSS").requestMatchers("/manualpayments")
				.hasRole("BOSS").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").permitAll().defaultSuccessUrl("/adminboss", true)
						.failureForwardUrl("/403"))
				.logout(logout -> logout.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login")
						.invalidateHttpSession(true))
				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403"));

		return http.build();
	}

	/**
	 * Así es como funcionan los resources, hay que poner configuration arriba en la
	 * clase sino no funciona
	 * 
	 * @return
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCostumizer() {
		return web -> web.ignoring().requestMatchers("/styles/**", "/img/**", "/js/**");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService);
	}
}
