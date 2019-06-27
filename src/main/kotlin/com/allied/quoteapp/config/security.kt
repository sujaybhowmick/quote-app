package com.allied.quoteapp.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var dataSource: DataSource

    @Value("\${spring.queries.user-query}")
    lateinit var userQuery: String

    @Value("\${spring.queries.roles-query}")
    lateinit var rolesQuery: String

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth
                ?.jdbcAuthentication()
                ?.usersByUsernameQuery(userQuery)
                ?.authoritiesByUsernameQuery(rolesQuery)
                ?.dataSource(dataSource)
                ?.passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        http
                ?.authorizeRequests()
                ?.antMatchers("/")?.permitAll()
                ?.antMatchers("/login")?.permitAll()
                ?.antMatchers("/registration")?.permitAll()
                ?.antMatchers("/admin")?.hasAuthority("ADMIN")?.anyRequest()
                ?.authenticated()?.and()?.csrf()?.disable()
                ?.formLogin()?.loginPage("/login")?.failureUrl("/login?error=true")
                ?.defaultSuccessUrl("/quotes")
                ?.usernameParameter("email")
                ?.passwordParameter("password")
                ?.and()?.logout()
                ?.logoutRequestMatcher(AntPathRequestMatcher("/logout"))
                ?.logoutSuccessUrl("/login")?.and()?.exceptionHandling()
                ?.accessDeniedPage("/access-denied")
    }

    override fun configure(web: WebSecurity?) {
        web
                ?.ignoring()
                ?.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**")
    }

}

@Configuration
class WebMvcConfiguration : WebMvcConfigurer{

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}