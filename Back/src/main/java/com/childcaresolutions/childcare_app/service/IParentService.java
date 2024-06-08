
package com.childcaresolutions.childcare_app.service;


import com.childcaresolutions.childcare_app.model.dto.request.RequestCreateParent;
import com.childcaresolutions.childcare_app.model.dto.request.RequestEditParent;
import com.childcaresolutions.childcare_app.model.dto.respose.ResponseParent;

public interface IParentService {

    ResponseParent createParent(RequestCreateParent requestCreateParent);

    ResponseParent readParent(Long id);

    ResponseParent updateParent(RequestEditParent requestEditParent);

    Boolean toogleDeleteParent(Long id);

}
