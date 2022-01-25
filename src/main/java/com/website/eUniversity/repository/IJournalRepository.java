package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJournalRepository extends JpaRepository<Journal, Integer> {

    List<Journal> getAllByStudent_IdOrderByDate(Integer studentId);

    Journal deleteJournalById(Integer id);

    @Query(value = "UPDATE Journals SET  attendance = :attendance, assess = :assess, feedback = :feedback" +
            " OUTPUT inserted.id, inserted.date, inserted.assess, inserted.attendance, inserted.feedback, inserted.schedule_id, inserted.student_id" +
            " WHERE j.id = :itemId", nativeQuery = true)
    Journal updateJournal(@Param("itemId") Integer itemId, @Param("attendance") Boolean attendance, @Param("assess") Integer assess, @Param("feedback") String feedback);
}
