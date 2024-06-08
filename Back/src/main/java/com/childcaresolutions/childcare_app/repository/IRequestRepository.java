
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRequestRepository extends JpaRepository<Request , Long>{
    
}
