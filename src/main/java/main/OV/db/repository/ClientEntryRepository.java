package main.OV.db.repository;

import main.OV.db.entity.ClientEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface ClientEntryRepository extends JpaRepository<ClientEntryEntity, Long> {

    List<ClientEntryEntity> findByEntryTimeBetween(LocalDateTime startOfHour, LocalDateTime endOfHour);

    Collection<? extends ClientEntryEntity> findByExitTimeIsNullOrExitTimeAfter(LocalDateTime startOfHour);

    @Query("SELECT e FROM ClientEntryEntity e WHERE e.entryTime <= :startOfHour AND (e.exitTime >= :startOfHour OR e.exitTime IS NULL) AND e.exitTime <= :endOfHour")
    List<ClientEntryEntity> findByEntryTimeBeforeAndExitTimeAfter(
            @Param("startOfHour") LocalDateTime startOfHour,
            @Param("endOfHour") LocalDateTime endOfHour);




}