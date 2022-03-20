package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.PaginatedListDTO;
import com.website.eUniversity.model.dto.PaginationDTO;
import com.website.eUniversity.model.dto.admin_panel.entity.JournalItemDTO;
import com.website.eUniversity.model.entity.JournalItem;
import com.website.eUniversity.repository.IJournalRepository;
import com.website.eUniversity.service.IJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalService implements IJournalService {

    @Autowired
    private IJournalRepository journalRepository;

    @Override
    public List<JournalItemDTO> getJournalItemsByStudent(Integer studentId) {
        return journalRepository.getAllByStudent_IdOrderByDate(studentId).stream()
                .map(JournalItem::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JournalItemDTO editItemOfJournal(JournalItemDTO journalItemDTO) {
        return JournalItem.fromEntityToDto(journalRepository.updateJournal(journalItemDTO.getItemId(), journalItemDTO.getPresent(),
                journalItemDTO.getAssess(), journalItemDTO.getFeedback()));
    }

    @Override
    public JournalItemDTO removeItemOfJournal(Integer journalItemId) throws NotFoundException {
        return JournalItem.fromEntityToDto(journalRepository.deleteJournalById(journalItemId));
    }

    @Override
    public PaginatedListDTO<JournalItemDTO> getPaginatedJournalItems(Integer studentId, PaginationDTO dto) {

        return new PaginatedListDTO<JournalItemDTO>().setItems(journalRepository
                .getPaginatedJournalItemsByStudentId(studentId, dto.getPageIndex(), dto.getPageSize())
                .stream()
                .map(JournalItem::fromEntityToDto)
                .collect(Collectors.toList()));
    }
}
