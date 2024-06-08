
package com.childcaresolutions.childcare_app.service;

import com.childcaresolutions.childcare_app.enums.Day;
import com.childcaresolutions.childcare_app.enums.TimeSlot;
import com.childcaresolutions.childcare_app.exeptions.EmailAlreadyExistsException;
import com.childcaresolutions.childcare_app.exeptions.EntityNotFoundException;
import com.childcaresolutions.childcare_app.model.Nanny;
import com.childcaresolutions.childcare_app.model.Skill;
import com.childcaresolutions.childcare_app.model.dto.mapper.NannyMapper;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateNanny;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditNanny;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseNanny;
import com.childcaresolutions.childcare_app.repository.INannyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NannyService implements INannyService  {

    private final INannyRepository nannyRepository;
    private final NannyMapper nannyMapper;


    @Override
    public ResponseNanny createNanny(RequestCreateNanny requestCreateNanny) {

        // Validar la existencia de una niñera con el mismo email (si es aplicable)
        nannyRepository.findByEmail(requestCreateNanny.email()).ifPresent(nanny -> {
            throw new EmailAlreadyExistsException("Email " + requestCreateNanny.email() + " already exists.");
        });

        // Mapear RequestCreateNanny a la entidad Nanny
        Nanny nanny = nannyMapper.requestCreateToNanny(requestCreateNanny);

        // Guardar la entidad Nanny en la base de datos
        Nanny savedNanny = nannyRepository.save(nanny);

        // Mapear la entidad Nanny guardada a ResponseNanny
        return nannyMapper.nannyToResponse(savedNanny);
    }

    @Override
    public ResponseNanny readNanny(Long id) {
        Nanny nanny = nannyRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("The Nanny with id: " + id + " not exist"));
        return nannyMapper.nannyToResponse(nanny);


    }

    @Transactional
    @Override
    public ResponseNanny updateNanny(RequestEditNanny requestEditNanny) {
        Nanny nanny = nannyRepository.findById(requestEditNanny.id()).orElseThrow(() ->
                new EntityNotFoundException("The Nanny with id: " + requestEditNanny.id() + " was not found"));

        if (requestEditNanny.name() != null) {
            nanny.setName(requestEditNanny.name());
        }

        if (requestEditNanny.email() != null) {
            nanny.setEmail(requestEditNanny.email());
        }

        if (requestEditNanny.password() != null) {
            nanny.setPassword(requestEditNanny.password());
        }

        if (requestEditNanny.photo() != null) {
            nanny.setPhoto(requestEditNanny.photo());
        }

        if (requestEditNanny.location() != null) {
            nanny.setLocation(requestEditNanny.location());
        }

        if (requestEditNanny.experiences() != null) {
            nanny.setExperiences(requestEditNanny.experiences());
        }

        if (requestEditNanny.nannyRate() >= 0) {
            nanny.setNannyRate(requestEditNanny.nannyRate());
        }
        // Actualizar las habilidades (skills) si no son nulas
      //  if (requestEditParent.skills() != null) {
      //      Set<Skill> skills = requestEditParent.skills();
      //      parent.setSkills(skills);
      //  }
//
      //  // Actualizar los días disponibles (availableDays) si no son nulos
      //  if (requestEditParent.availableDays() != null) {
      //      Set<Day> availableDays = requestEditParent.availableDays();
      //      parent.setAvailableDays(availableDays);
      //  }
//
      //  // Actualizar el horario (timeSlot) si no es nulo
      //  if (requestEditParent.timeSlot() != null) {
      //      TimeSlot timeSlot = requestEditParent.timeSlot();
      //      parent.setTimeSlot(timeSlot);
      //  }

        // Actualizar las habilidades (skills) si no son nulas
        if (requestEditNanny.skills() != null) {
            nanny.setSkills(requestEditNanny.skills());
        }

        // Actualizar los días disponibles (availableDays) si no son nulos
        if (requestEditNanny.availableDaysN() != null) {
            nanny.setAvailableDaysN(requestEditNanny.availableDaysN());
        }

        // Actualizar el horario (timeSlot) si no es nulo
        if (requestEditNanny.timeSlotN() != null) {
            nanny.setTimeSlotN(requestEditNanny.timeSlotN());
        }

        Nanny updatedNanny = nannyRepository.save(nanny);
        return nannyMapper.nannyToResponse(updatedNanny);
    }

    @Override
    public Boolean toogleDeleteNanny(Long id) {
        nannyRepository.deleteById(id);
        return true;
    }
}
