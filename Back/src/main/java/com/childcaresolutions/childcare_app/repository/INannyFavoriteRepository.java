
package com.childcaresolutions.childcare_app.repository;

import com.childcaresolutions.childcare_app.model.NannyFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INannyFavoriteRepository extends JpaRepository<NannyFavorite , Long>{
    
}
