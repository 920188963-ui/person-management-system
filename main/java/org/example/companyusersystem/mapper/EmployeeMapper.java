package org.example.companyusersystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.companyusersystem.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();

    Employee selectById(long id);

    List<Employee> selectByName(String name);

    List<Employee> selectByGender(String gender);

    List<Employee> selectByDepartment(String department);

    List<Employee> selectByStatus(boolean status);

    int insert(Employee employee);

    int update(Employee employee);

    int updateAvatar(@Param("id") long id, @Param("avatar") String avatar);

    int deleteById(Long id);

    List<Employee> selectPage(@Param("offset") int offset, @Param("size") int size);

    int count();

    int batchDelete(List<Long> ids);
}
