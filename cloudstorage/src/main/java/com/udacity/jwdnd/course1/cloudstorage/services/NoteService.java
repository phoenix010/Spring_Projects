package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private  UserService userService;
    private  NoteMapper noteMapper;
//    private Note note;

    public NoteService(UserService userService, NoteMapper noteMapper) {
        this.userService = userService;
        this.noteMapper = noteMapper;
    }

    public int inserOrUpdatetNote(Note note,long userId){
        note.setUserId(userId);
        long noteId = note.getNoteId();
        if(noteMapper.getNotesByNoteId(noteId).isEmpty()) {
            return noteMapper.update(note);
        }

        return noteMapper.insertNote(note);
    }
    public int deleteNote(Note note, long userId) {
        note.setUserId(userId);
        return noteMapper.delete(note);
    }
    public List<Note> noteUpload(Long userId){ return noteMapper.getNotesByUserId(userId);  }
}
