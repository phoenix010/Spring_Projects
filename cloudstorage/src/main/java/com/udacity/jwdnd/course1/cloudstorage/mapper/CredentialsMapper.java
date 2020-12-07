package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialsMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId ) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credentials creds);

//    @Insert("INSERT INTO FILES (filename, contentType, filesize, userid, filedata) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
//    @Options(useGeneratedKeys = true, keyProperty = "fileId")
//    int insertFile(File file);
    @Select("SELECT username FROM CREDENTIALS WHERE userId = #{userId}")
    String getUsernamebyuserId(long userId);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credentials> getCredsByUserId(long userId);

    @Select("SELECT credentialId FROM CREDENTIALS WHERE userId = #{userId}")
    long getCredId(long userId);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId} AND userId = #{userId}")
    int delete(long credentialId, long userId);



}
