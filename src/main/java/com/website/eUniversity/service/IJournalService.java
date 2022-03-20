package com.website.eUniversity.service;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.GroupDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.JournalItemDTO;

import java.util.List;

public interface IJournalService {

    List<JournalItemDTO> getJournalItemsByStudent(Integer studentId);

    JournalItemDTO editItemOfJournal(JournalItemDTO journalItemDTO);

    JournalItemDTO removeItemOfJournal(Integer journalItemId) throws NotFoundException;

    PaginatedListDTO<JournalItemDTO> getPaginatedJournalItems(Integer studentId, PaginationDTO dto);

}
