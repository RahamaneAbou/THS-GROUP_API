package com.THS_GROUP.Services;

import com.THS_GROUP.Entyties.Note;
import com.THS_GROUP.DTO.NoteDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class NoteServiceDTO{

    public NoteDTO mapToDto(Note note) {
        if (note == null) return null;

        NoteDTO dto = new NoteDTO();
        dto.setId(note.getId());
        dto.setEtudiantId(note.getEtudiant().getId());
        dto.setCoursId(note.getCours().getId());
        dto.setValeur(note.getValeur());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dto.setDateAttribution(dateFormat.format(note.getDateAttribution()));

        return dto;
    }
}