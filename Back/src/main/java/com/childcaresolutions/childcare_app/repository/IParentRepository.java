
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IParentRepository extends JpaRepository<Parent , Long>{

    Optional<Parent> findByEmail(String email);
}
