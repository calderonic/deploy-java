
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdministratorRepository extends JpaRepository<Administrator , Long>{
    
}
