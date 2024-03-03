package com.springapps.pricegenerator.repository;

import com.springapps.pricegenerator.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QutationRepository extends JpaRepository<Quotation,Long> {

    List<Quotation> findAllByUserAndExpireDateAfter (LocalDateTime date);

    void deleteAllByExpireDateBefore(LocalDateTime date);
}
