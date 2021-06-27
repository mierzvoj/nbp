package com.pjatk.nbpApp.repository;

import com.pjatk.nbpApp.model.NbpResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbiApiResponseRepository extends JpaRepository<NbpResponse, Long> {

}
