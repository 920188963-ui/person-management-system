package org.example.companyusersystem.service.impl;

import org.example.companyusersystem.entity.Employee;
import org.example.companyusersystem.mapper.EmployeeMapper;
import org.example.companyusersystem.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.selectAll();
    }

    @Override
    public Employee getById(long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public boolean add(Employee employee) {
        return employeeMapper.insert(employee) > 0;
    }

    @Override
    public boolean update(Employee employee) {
        return employeeMapper.update(employee) > 0;
    }

    @Override
    public boolean updateAvatar(long id, String avatar) {
        return employeeMapper.updateAvatar(id, avatar) > 0;
    }

    @Override
    public boolean delete(long id) {
        return employeeMapper.deleteById(id) > 0;
    }

    @Override
    public List<Employee> search(String name) {
        return employeeMapper.selectByName(name);
    }

    @Override
    public List<Employee> findByGender(String gender) {
        return employeeMapper.selectByGender(gender);
    }

    @Override
    public List<Employee> findByDepartment(String department) {
        return employeeMapper.selectByDepartment(department);
    }

    @Override
    public List<Employee> findByStatus(boolean status) {
        return employeeMapper.selectByStatus(status);
    }

    @Override
    public List<Employee> page(int page, int size) {
        int offset = (page - 1) * size;
        return employeeMapper.selectPage(offset, size);
    }

    @Override
    public int count() {
        return employeeMapper.count();
    }

    @Override
    public boolean batchDelete(List<Long> ids) {
        return employeeMapper.batchDelete(ids) > 0;
    }
}
