package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userId}")
    File getFileByName(String filename);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileByFileId(long fileId);

    @Insert("INSERT INTO FILES (filename, contentType, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getFilesByUserId(long userId);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    void deleteFileByFileId(long fileId);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    List<File> getByUsername(String filename, Long userid);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File findByFileId(long fileId);

}
