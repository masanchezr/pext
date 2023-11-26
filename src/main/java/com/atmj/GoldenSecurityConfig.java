package com.atmj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.atmj.gsboot.services.users.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class GoldenSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain employeeFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/employee/**")
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(AntPathRequestMatcher.antMatcher("/employee/admin"),
								AntPathRequestMatcher.antMatcher("/employee/myincidents"),
								AntPathRequestMatcher.antMatcher("/employee/in"),
								AntPathRequestMatcher.antMatcher("/employee/daily"),
								AntPathRequestMatcher.antMatcher("/employee/out"),
								AntPathRequestMatcher.antMatcher("/employee/newoperation"),
								AntPathRequestMatcher.antMatcher("/employee/newconcept"),
								AntPathRequestMatcher.antMatcher("/employee/newincome"),
								AntPathRequestMatcher.antMatcher("/employee/newgratification"),
								AntPathRequestMatcher.antMatcher("/employee/registernumber"),
								AntPathRequestMatcher.antMatcher("/employee/lastgratifications"),
								AntPathRequestMatcher.antMatcher("/employee/newpaymentchangemachine"),
								AntPathRequestMatcher.antMatcher("/employee/newmoneyadvance"),
								AntPathRequestMatcher.antMatcher("/employee/newopenmachine"),
								AntPathRequestMatcher.antMatcher("/employee/extrahours"))
						.hasRole("USER").anyRequest().authenticated())
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
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/admin"),
								AntPathRequestMatcher.antMatcher("/admin/newcalendar"),
								AntPathRequestMatcher.antMatcher("/admin/daily"),
								AntPathRequestMatcher.antMatcher("/admin/newentrysafe"),
								AntPathRequestMatcher.antMatcher("/admin/newentrymachine"),
								AntPathRequestMatcher.antMatcher("/admin/againday*"),
								AntPathRequestMatcher.antMatcher("/admin/beforeday*"),
								AntPathRequestMatcher.antMatcher("/admin/updateoperation"),
								AntPathRequestMatcher.antMatcher("/admin/pendingissues"),
								AntPathRequestMatcher.antMatcher("/admin/totalsafe"),
								AntPathRequestMatcher.antMatcher("/admin/reset"),
								AntPathRequestMatcher.antMatcher("/admin/searchmonthreset"),
								AntPathRequestMatcher.antMatcher("/admin/newmessage"),
								AntPathRequestMatcher.antMatcher("/admin/messages"),
								AntPathRequestMatcher.antMatcher("/admin/searchticketsByDay"),
								AntPathRequestMatcher.antMatcher("/admin/changemachinetotal"),
								AntPathRequestMatcher.antMatcher("/admin/newentryvisible"),
								AntPathRequestMatcher.antMatcher("/admin/searchregister"))
						.hasRole("ADMIN").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/admin/login").permitAll()
						.defaultSuccessUrl("/admin/admin", true).failureForwardUrl("/403admin"))
				.logout(logout -> logout.logoutUrl("/admin/j_spring_security_logout").logoutSuccessUrl("/admin/login")
						.invalidateHttpSession(true))
				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403admin"));
		return http.build();
	}

	@Bean
	public SecurityFilterChain bossFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/admin"),
				AntPathRequestMatcher.antMatcher("/newuser"), AntPathRequestMatcher.antMatcher("/newentrymoney"),
				AntPathRequestMatcher.antMatcher("/summaryincome"),
				AntPathRequestMatcher.antMatcher("/summaryexpenses"),
				AntPathRequestMatcher.antMatcher("/searchregister"),
				AntPathRequestMatcher.antMatcher("/searchrecharges"), AntPathRequestMatcher.antMatcher("/daily"),
				AntPathRequestMatcher.antMatcher("/searchtpv"),
				AntPathRequestMatcher.antMatcher("/searchentrysortsafe"),
				AntPathRequestMatcher.antMatcher("/enabledisableuser"),
				AntPathRequestMatcher.antMatcher("/searchopenmachines"), AntPathRequestMatcher.antMatcher("/newtpv"),
				AntPathRequestMatcher.antMatcher("/extrahours"), AntPathRequestMatcher.antMatcher("/manualpayments"))
				.hasRole("BOSS").anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").permitAll().defaultSuccessUrl("/admin", true)
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
		return web -> web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher("/styles/**"),
				AntPathRequestMatcher.antMatcher("/img/**"), AntPathRequestMatcher.antMatcher("/js/**"));
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService);
	}
}
