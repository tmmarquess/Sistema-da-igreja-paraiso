package br.com.igrejaparaiso.Igrejaparaiso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.igrejaparaiso.Igrejaparaiso.enums.Perfil;
import br.com.igrejaparaiso.Igrejaparaiso.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsServiceImpl detalhes;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/images/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/eu/**").authenticated()
        .antMatchers("/**/cadastrar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/agenda/**/editar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/contatos/**/editar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/eventos/**/editar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/link/**/editar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/membros/**/editar/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/eventos/**/excluir/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/link/**/excluir/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/agenda/**/excluir/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/contatos/**/excluir/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/membros/**/excluir/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/painel/membros/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/painel/comprovantes/").hasAuthority(Perfil.ADMIN.toString())
        .antMatchers("/membros/cadastro/").permitAll()
        .anyRequest().authenticated();

        http.formLogin().loginPage("/membros/login/")
        .defaultSuccessUrl("/painel/")
        .permitAll();

        http.logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/painel/logout/","GET"))
        .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalhes)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
}
