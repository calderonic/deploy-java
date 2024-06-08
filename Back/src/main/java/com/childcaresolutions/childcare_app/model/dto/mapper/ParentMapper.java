package com.childcaresolutions.childcare_app.model.dto.mapper;

import com.childcaresolutions.childcare_app.model.Parent;
import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateParent;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditParent;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseParent;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ParentMapper {

    Parent requestCreateToParent (RequestCreateParent requestCreateParent);
    Parent requestEditToDoctor(RequestEditParent requestEditParent);

    ResponseParent parentToRespose (Parent parent);

}
