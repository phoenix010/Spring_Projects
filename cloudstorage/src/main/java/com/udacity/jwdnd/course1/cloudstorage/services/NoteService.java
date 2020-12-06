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

    public int insertNote(Note note,long userId){
        note.setUserId(userId);
        return noteMapper.insertNote(note);
    }
    public List<Note> NoteUpload(Long userId){ return noteMapper.getNotesByUserId(userId);  }






}
