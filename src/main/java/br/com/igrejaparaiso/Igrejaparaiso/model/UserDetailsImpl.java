package br.com.igrejaparaiso.Igrejaparaiso.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.igrejaparaiso.Igrejaparaiso.enums.Perfil;

public class UserDetailsImpl implements UserDetails {

    private Membro membro;

    public UserDetailsImpl(Membro membro){
        this.membro = membro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Perfil perfil = membro.isAdm() ? Perfil.ADMIN : Perfil.USER;

        return AuthorityUtils.createAuthorityList(perfil.toString());
    }

    @Override
    public String getPassword() {
        return membro.getSenha();
    }

    @Override
    public String getUsername() {
        return membro.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
