package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteMapper {

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId ) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteNotebyNoteId(long noteId);

    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    File getNotebyNoteId(long noteId);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getNotesByUserId(long userId);

    @Select("Select* FROM NOTES where noteId = #{noteId}")
    List<Note> getNotesByNoteId(long noteId);

    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    int update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId} AND userId = #{userId}")
    int delete(long noteId, long userId);

    @Select("Select NOTETITLE FROM NOTES WHERE noteid = #{noteId}")
    String getNotetile(long noteId);
}
