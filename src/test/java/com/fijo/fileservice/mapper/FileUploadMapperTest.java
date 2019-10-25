package com.fijo.fileservice.mapper;

import com.fijo.fileservice.model.FileUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadMapperTest {
@Autowired
private  FileUploadMapper mapper;
    @Test
    public void getAll() {
        FileUpload o=new FileUpload();
        mapper.getAll(o);
    }
}