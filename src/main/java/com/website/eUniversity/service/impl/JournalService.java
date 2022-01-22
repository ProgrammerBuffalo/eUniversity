package com.website.eUniversity.service.impl;

import com.website.eUniversity.exception.NotFoundException;
import com.website.eUniversity.model.dto.entity.JournalItemDTO;
import com.website.eUniversity.model.entity.Journal;
import com.website.eUniversity.model.entity.Schedule;
import com.website.eUniversity.repository.IJournalRepository;
import com.website.eUniversity.service.IJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JournalService implements IJournalService {

    @Autowired
    private IJournalRepository journalRepository;

    @Override
    public List<JournalItemDTO> getJournalItemsByStudent(Integer studentId) {
        return journalRepository.getAllByStudent_IdOrderByDate(studentId).stream()
                .map(Journal::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public JournalItemDTO editItemOfJournal(JournalItemDTO journalItemDTO) {
        return Journal.fromEntityToDto(journalRepository.updateJournal(journalItemDTO.getItemId(), journalItemDTO.getPresent(),
                journalItemDTO.getAssess(), journalItemDTO.getFeedback()));
    }

    @Override
    public JournalItemDTO removeItemOfJournal(Integer journalItemId) throws NotFoundException {
        return Journal.fromEntityToDto(journalRepository.deleteJournalById(journalItemId));
    }
}
