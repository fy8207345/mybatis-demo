package com.fy.mybatis.mapper;

import com.fy.mybatis.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> listStudent();
}
