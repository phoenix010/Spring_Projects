package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CredentialsMapper {

    @Insert("INSERT INTO CREDENTIALS (url, key, password, userId ) VALUES (#{url}, #{key},#{password} #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credentials creds);


}
