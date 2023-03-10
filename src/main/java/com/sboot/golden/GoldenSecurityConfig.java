package com.sboot.golden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import com.sboot.golden.services.users.UserDetailServiceImpl;

public class GoldenSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	private static String[] resources = new String[] { "/styles/**", "/img/**", "/js/**" };

	@Configuration
	@Order(1)
	public static class EmployeeSecurityConfig {
		private static final String ROLEUSER = "USER";
		private static final String LOGINURL = "/employee/login";
		private static final String ADMINURL = "/employee/admin";

		@Bean
		public SecurityFilterChain employeeFilterChain(HttpSecurity http) throws Exception {
			http.antMatcher("/employee/**").authorizeRequests()
					.antMatchers(ADMINURL, "/employee/myincidents", "/employee/in", "/employee/daily", "/employee/out",
							"/employee/newoperation", "/employee/newconcept", "/employee/newincome",
							"/employee/newgratification", "/employee/registernumber", "/employee/lastgratifications",
							"/employee/newpaymentchangemachine", "/employee/newmoneyadvance",
							"/employee/newopenmachine", "/employee/extrahours")
					.hasRole(ROLEUSER).anyRequest().authenticated().and().formLogin().loginPage(LOGINURL).permitAll()
					.defaultSuccessUrl(ADMINURL, true).failureForwardUrl("/403employee").and().exceptionHandling()
					.accessDeniedPage("/403employee").and().logout().logoutUrl("/employee/j_spring_security_logout")
					.logoutSuccessUrl(LOGINURL).invalidateHttpSession(true);
			return http.build();
		}
	}

	@Configuration
	@Order(2)
	public static class AdminSecurityConfig {
		private static final String ROLEADMIN = "ADMIN";
		private static final String LOGINURL = "/admin/login";
		private static final String ADMINURL = "/admin/admin";

		@Bean
		public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/**").authorizeRequests()
					.antMatchers(ADMINURL, "/admin/newcalendar", "/admin/daily", "/admin/newentrysafe",
							"/admin/newentrymachine", "/admin/againday*", "/admin/beforeday*", "/admin/updateoperation",
							"/admin/pendingissues", "/admin/totalsafe", "/admin/reset", "/admin/searchmonthreset",
							"/admin/newmessage", "/admin/messages", "/admin/searchticketsByDay",
							"/admin/changemachinetotal", "/admin/newentryvisible", "/admin/searchregister")
					.hasRole(ROLEADMIN).anyRequest().authenticated().and().formLogin().loginPage(LOGINURL).permitAll()
					.defaultSuccessUrl(ADMINURL, true).failureForwardUrl("/403admin").and().exceptionHandling()
					.accessDeniedPage("/403admin").and().logout().logoutUrl("/admin/j_spring_security_logout")
					.logoutSuccessUrl(LOGINURL).invalidateHttpSession(true);
			return http.build();
		}
	}

	@Configuration
	@Order(3)
	public static class BossSecurityConfig {
		private static final String ROLEBOSS = "BOSS";
		private static final String LOGINURL = "/login";
		private static final String ADMINURL = "/adminboss";

		@Bean
		public SecurityFilterChain bossFilterChain(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers(ADMINURL, "/newuser", "/newentrymoney", "/summaryincome", "/summaryexpenses",
							"/searchregister", "/searchrecharges", "/daily", "/searchtpv", "/searchentrysortsafe",
							"/enabledisableuser", "/searchopenmachines", "/newtpv", "/extrahours", "/manualpayments")
					.hasRole(ROLEBOSS).anyRequest().authenticated().and().formLogin().loginPage(LOGINURL).permitAll()
					.defaultSuccessUrl(ADMINURL, true).failureForwardUrl("/403").and().exceptionHandling()
					.accessDeniedPage("/403").and().logout().logoutUrl("/j_spring_security_logout")
					.logoutSuccessUrl(LOGINURL).invalidateHttpSession(true);
			return http.build();
		}
	}

	@Configuration
	@Order(4)
	public static class AllSecurityConfig {
		@Bean
		public WebSecurityCustomizer webSecurityCustomiz() throws Exception {
			return (web) -> web.ignoring().antMatchers(HttpMethod.GET, resources);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService);
	}
}
