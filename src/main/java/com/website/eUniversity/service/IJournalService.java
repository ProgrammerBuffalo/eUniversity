package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.JournalItemDTO;

import java.util.List;

public interface IJournalService {

    List<JournalItemDTO> getJournalItemsByStudent(Integer studentId);

    JournalItemDTO editItemOfJournal(JournalItemDTO journalItemDTO);

    JournalItemDTO removeItemOfJournal(Integer journalItemId) throws NotFoundException;
}
