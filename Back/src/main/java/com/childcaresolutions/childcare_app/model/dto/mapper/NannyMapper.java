package com.childcaresolutions.childcare_app.model.dto.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.childcaresolutions.childcare_app.model.Nanny;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateNanny;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditNanny;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseNanny;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NannyMapper {

    Nanny requestCreateToNanny(RequestCreateNanny requestCreateNanny);

    Nanny requestEditToNanny(RequestEditNanny requestEditNanny);

    ResponseNanny nannyToResponse (Nanny nanny);

}
