/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.misyn.codequality.dto;

import com.misyn.codequality.entity.Department;

public class DepartmentDto extends BaseDto<Department> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public Department toEntity() {
        Department department = new Department();
        department.setId(id);
        department.setName(name);

        return department;
    }
}
