package org.example.companyusersystem.service;

import org.example.companyusersystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> list();

    Employee getById(long id);

    boolean add(Employee employee);

    boolean update(Employee employee);

    boolean updateAvatar(long id, String avatar);

    boolean delete(long id);

    List<Employee> search(String name);

    List<Employee> findByGender(String gender);

    List<Employee> findByDepartment(String department);

    List<Employee> findByStatus(boolean status);

    List<Employee> page(int page, int size);

    int count();

    boolean batchDelete(List<Long> ids);
}
