/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.misyn.codequality.service.impl;

import com.misyn.codequality.dto.DepartmentDto;
import com.misyn.codequality.entity.Department;
import com.misyn.codequality.repository.DepartmentRepository;
import com.misyn.codequality.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department department = departmentRepository.save(departmentDto.toEntity());
        return toDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return toDtoList(departments);
    }

    @Override
    public DepartmentDto getDepartment(Long departmentId) {
        Department department = getDepartmentEntity(departmentId);
        return toDto(department);
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        Department department = getDepartmentEntity(departmentDto.getId());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return toDto(department);
    }

    private List<DepartmentDto> toDtoList(List<Department> departments) {
        List<DepartmentDto> departmentDtos = new ArrayList<>(departments.size());
        for (Department department : departments) {
            departmentDtos.add(toDto(department));
        }
        return departmentDtos;
    }

    private DepartmentDto toDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        return departmentDto;
    }

    private Department getDepartmentEntity(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new RuntimeException("Department Not Found");
        }
    }
}
