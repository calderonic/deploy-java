
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.Penalty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPenaltyRepository extends JpaRepository<Penalty , Long>{
    
}
