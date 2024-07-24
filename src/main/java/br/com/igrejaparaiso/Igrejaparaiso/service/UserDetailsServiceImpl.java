package br.com.igrejaparaiso.Igrejaparaiso.service;

import java.util.concurrent.ExecutionException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.model.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    MembroService service;

    public UserDetailsServiceImpl(MembroService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Membro membro = null;
        try {
            membro = service.getMembroByEmail(username);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (membro.getId() == null || membro == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new UserDetailsImpl(membro);
    }

}
