package com.website.eUniversity.repository;

import com.website.eUniversity.model.entity.JournalItem;
import com.website.eUniversity.model.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IJournalRepository extends JpaRepository<JournalItem, Integer> {

    List<JournalItem> getAllByStudent_IdOrderByDate(Integer studentId);

    JournalItem deleteJournalById(Integer id);

    @Query(value = "UPDATE Journals SET  attendance = :attendance, assess = :assess, feedback = :feedback" +
            " OUTPUT inserted.id, inserted.date, inserted.assess, inserted.attendance, inserted.feedback, inserted.schedule_id, inserted.student_id" +
            " WHERE j.id = :itemId", nativeQuery = true)
    JournalItem updateJournal(@Param("itemId") Integer itemId, @Param("attendance") Boolean attendance, @Param("assess") Integer assess, @Param("feedback") String feedback);


    @Query(value = "SELECT * FROM journal_items " +
            "INNER JOIN students ON students.id = journal_items.student_id" +
            " WHERE students.id = :studentId" +
            " ORDER BY journal_items.date DESC" +
            " OFFSET (:pageIndex * :pageSize)" +
            " ROWS FETCH NEXT :pageSize ROWS ONLY ", nativeQuery = true)
    List<JournalItem> getPaginatedJournalItemsByStudentId(@Param("studentId") Integer studentId,
                                                          @Param("pageSize") Integer pageSize,
                                                          @Param("pageIndex") Integer pageIndex);
}
