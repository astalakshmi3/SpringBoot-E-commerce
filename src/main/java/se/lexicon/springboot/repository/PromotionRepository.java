package se.lexicon.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.springboot.entity.Promotion;

import java.lang.classfile.Opcode;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    // active on date
    @Query ("SELECT p FROM Promotion p WHERE p.startDate <= :date AND (p.endDate IS NULL OR p.endDate>=:date)")
    List<Promotion> findByDate (@Param("date") Instant date);

    Optional<Promotion> findByCode(@Param("code") String code);
    List<Promotion> findByStartDateAfter (LocalDate date);
    List<Promotion> findByEndDateBefore (LocalDate date);
    List<Promotion> findByEndDateIsNull ();

}
