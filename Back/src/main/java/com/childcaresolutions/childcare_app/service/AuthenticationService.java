package com.childcaresolutions.childcare_app.service;

import com.childcaresolutions.childcare_app.exeptions.InvalidCredentialsException;
import com.childcaresolutions.childcare_app.model.Nanny;
import com.childcaresolutions.childcare_app.model.Parent;
import com.childcaresolutions.childcare_app.model.User;
import com.childcaresolutions.childcare_app.model.dto.mapper.NannyMapper;
import com.childcaresolutions.childcare_app.model.dto.mapper.ParentMapper;
import com.childcaresolutions.childcare_app.model.dto.request.RequestLogin;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseLogin;
import com.childcaresolutions.childcare_app.repository.INannyRepository;
import com.childcaresolutions.childcare_app.repository.IParentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AuthenticationService implements IAuthenticationService {

    private final IParentRepository parentRepository;
    private final INannyRepository nannyRepository;
    private final NannyMapper nannyMapper;
    private final ParentMapper parentMapper;
    @Override
    public Object login(RequestLogin requestLogin) {
        // Buscar el padre por su email en la base de datos
        Optional<Parent> optionalParent = parentRepository.findByEmail(requestLogin.email());

        if (optionalParent.isPresent()) {
            Parent parent = optionalParent.get();
            // Verificar la contraseña
            if (requestLogin.password().equals(parent.getPassword())) {
                // Devolver la respuesta de login con solo el ID

                return parentMapper.parentToRespose(parent);
            }
        } else {
            // Buscar la nanny por su email en la base de datos
            Optional<Nanny> optionalNanny = nannyRepository.findByEmail(requestLogin.email());
            if (optionalNanny.isPresent()) {
                Nanny nanny = optionalNanny.get();
                // Verificar la contraseña
                if (requestLogin.password().equals(nanny.getPassword())) {
                    // Devolver la respuesta de login con solo el ID
                    return nannyMapper.nannyToResponse(nanny);
                }
            }
        }

        // Lanzar una excepción personalizada si no se encuentra el padre o la nanny, o si la contraseña es incorrecta
        throw new InvalidCredentialsException("No se pudo encontrar un usuario con las credenciales proporcionadas.");
    }

  //  @Override
  //  public  ResponseLogin login(RequestLogin requestLogin) {
  //      // Buscar el padre por su email en la base de datos
  //      Optional<Parent> optionalParent = parentRepository.findByEmail(requestLogin.email());
  //      if (optionalParent.isPresent()) {
  //          Parent parent = optionalParent.get();
  //          // Verificar la contraseña
  //          if (requestLogin.password().equals(parent.getPassword())) {
  //              // Devolver la respuesta de login con solo el ID
  //              ResponseLogin responseLogin = new ResponseLogin(parent.getId());
  //              return responseLogin;
  //          }
//
  //          if (!optionalParent.isPresent()) {
  //              var user2 = nannyRepository.findByEmail(requestLogin.email());
  //              if (!user2.isPresent()) { throw new EntityNotFoundException(requestLogin.email()); }//exepcion persoanlizada
  //             Nanny nanny = r;
  //              ResponseLogin responseLogin = new ResponseLogin((nanny.getId));
  //              return responseLogin;
  //          }
  //      }
  //      // Retornar una respuesta de error si no se encuentra el padre o la contraseña es incorrecta
  //      return null;
//
  //  }

    @Override
    public String forgotPassword(String email) {
        return null;
    }

    @Override
    public String setPassword(String email, String newPassword) {
        return null;
    }
}