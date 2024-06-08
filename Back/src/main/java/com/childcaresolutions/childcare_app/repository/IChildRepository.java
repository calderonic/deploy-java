
package com.childcaresolutions.childcare_app.repository;


import com.childcaresolutions.childcare_app.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChildRepository extends JpaRepository<Child , Long>{
    
}
