
package com.childcaresolutions.childcare_app.service;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.RoleEnum;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import com.childcaresolutions.childcare_app.exeptions.EmailAlreadyExistsException;
import com.childcaresolutions.childcare_app.exeptions.EntityNotFoundException;
import com.childcaresolutions.childcare_app.model.Parent;
import com.childcaresolutions.childcare_app.model.Skill;
import com.childcaresolutions.childcare_app.model.dto.mapper.ParentMapper;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateParent;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditParent;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseParent;
import com.childcaresolutions.childcare_app.repository.IParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class ParentService implements IParentService {

    private final IParentRepository parentRepository;
    private final ParentMapper parentMapper;


    @Override
    public ResponseParent createParent(RequestCreateParent requestCreateParent) {

        // Validar la existencia de un padre con el mismo email
        parentRepository.findByEmail(requestCreateParent.email()).ifPresent(parent -> {
            throw new EmailAlreadyExistsException("Email " + requestCreateParent.email() + " already exists.");
        });

        // Mapear RequestCreateParent a la entidad Parent
        Parent parent = parentMapper.requestCreateToParent(requestCreateParent);

        // Aplicar lógica de negocio adicional

       // parent.setRole(RoleEnum.PARENT);

        // Guardar la entidad Parent en la base de datos
        Parent savedParent = parentRepository.save(parent);

        // Registrar el evento
       // logParentCreation(savedParent);

        // Mapear la entidad Parent guardada a ResponseParent
        return parentMapper.parentToRespose(savedParent);


    }

    @Override
    public ResponseParent readParent(Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow (()->
                new EntityNotFoundException("The Parent with id: " + id + " not exist"));
        return parentMapper.parentToRespose(parent);
    }
    @Transactional
    @Override
    public ResponseParent updateParent(RequestEditParent requestEditParent) {
      Parent parent = parentRepository.findById(requestEditParent.id()).orElseThrow(() ->
              new com.childcaresolutions.childcare_app.exeptions.EntityNotFoundException("The Parent with id: " + requestEditParent.id() + " was not found"));

        if (requestEditParent.name() != null) {
            parent.setName(requestEditParent.name());
        }

        if (requestEditParent.email() != null) {
            parent.setEmail(requestEditParent.email());
        }
        //dilate updatePassword ?
        if (requestEditParent.password() != null) {
            parent.setPassword(requestEditParent.password());
        }
        if (requestEditParent.photo() != null) {
            parent.setPhoto(requestEditParent.photo());
        }
        if (requestEditParent.location() != null) {
            parent.setLocation(requestEditParent.location());
        }
        if (requestEditParent.phoneNumber() != null) {
            parent.setPhoneNumber(requestEditParent.phoneNumber());
        }
        if (requestEditParent.numberOfChildren() >= 0) {
            parent.setNumberOfChildren(requestEditParent.numberOfChildren());
        }
        if (requestEditParent.infoFamily() != null) {
            parent.setInfoFamily(requestEditParent.infoFamily());
        }


        // Actualizar las habilidades (skills) si no son nulas
        if (requestEditParent.skills() != null) {
            Set<Skill> skills = requestEditParent.skills();
            parent.setSkills(skills);
        }

        // Actualizar los días disponibles (availableDays) si no son nulos
        if (requestEditParent.availableDays() != null) {
            Set<Day> availableDays = requestEditParent.availableDays();
            parent.setAvailableDays(availableDays);
        }

        // Actualizar el horario (timeSlot) si no es nulo
        if (requestEditParent.timeSlot() != null) {
            TimeSlot timeSlot = requestEditParent.timeSlot();
            parent.setTimeSlot(timeSlot);
        }
        Parent updatedParent = parentRepository.save(parent);
        return parentMapper.parentToRespose(updatedParent);

    }

    @Override
    public Boolean toogleDeleteParent(Long id) {
        parentRepository.deleteById(id);
        return true;
    }
}
