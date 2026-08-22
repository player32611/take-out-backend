package com.player32611.mapper;

import com.player32611.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee")
    List<Employee> selectAll();

    @Select("select * from employee where id = #{id}")
    Employee selectById(Long id);

    @Select("select * from employee where username = #{username}")
    Employee selectByUsername(String username);

    @Select("""
    select *
    from employee
    where username like concat('%', #{username}, '%')
    """)
    List<Employee> selectByUsernameLike(String username);

    @Select("select * from employee where username = #{username} and password = #{password}")
    Employee selectByUsernameAndPassword(String username, String password);

    @Insert("insert into employee(name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) values(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    Integer insert(Employee employee);

    Integer update(Employee employee);
}
