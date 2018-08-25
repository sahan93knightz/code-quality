/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.misyn.codequality.controller;

import com.misyn.codequality.dto.DepartmentDto;
import com.misyn.codequality.service.DepartmentService;
import com.misyn.codequality.utility.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {

    private static final String DEPARTMENT_ATTRIBUTE = "department";
    private static final String DEPARTMENT_LIST_ATTRIBUTE = "departmentList";
    private static final String DEPARTMENT_SAVE_SUCCESS_MESSAGE = "Department Saved Successfully";
    private static final String DEPARTMENT_SAVE_ERROR_MESSAGE = "Department Save Failed";
    private static final String DEPARTMENT_NOT_FOUND_MESSAGE = "Department Not Found";

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    public String departmentForm(Model model) {
        model.addAttribute(DEPARTMENT_ATTRIBUTE, new DepartmentDto());
        model.addAttribute(ApplicationConstants.METHOD_ATTRIBUTE, ApplicationConstants.POST_METHOD);
        return "department/form";
    }

    @PostMapping("")
    public String save(@ModelAttribute DepartmentDto departmentDto, Model model) {
        try {
            departmentService.save(departmentDto);
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_SAVE_ERROR_MESSAGE);
            model.addAttribute(ApplicationConstants.EXCEPTION, e);
        }
        return ApplicationConstants.RESULT_PAGE_PATH;
    }
// Test Comment
    @GetMapping("/list")
    public String departmentList(Model model) {
        try {
            List<DepartmentDto> departments = departmentService.getAllDepartments();
            model.addAttribute(DEPARTMENT_LIST_ATTRIBUTE, departments);
            return "department/list";
        } catch (Exception e) {
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_SAVE_ERROR_MESSAGE);
            model.addAttribute(ApplicationConstants.EXCEPTION, e);
            return ApplicationConstants.RESULT_PAGE_PATH;
        }
    }

    @GetMapping("/edit")
    public String departmentEditForm(@RequestParam("departmentId") Long departmentId, Model model) {
        try {
            DepartmentDto department = departmentService.getDepartment(departmentId);
            model.addAttribute(DEPARTMENT_ATTRIBUTE, department);
            model.addAttribute(ApplicationConstants.METHOD_ATTRIBUTE, ApplicationConstants.PUT_METHOD);
            return "department/form";
        } catch (Exception e) {
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_NOT_FOUND_MESSAGE);
            model.addAttribute(ApplicationConstants.EXCEPTION, e);
            return ApplicationConstants.RESULT_PAGE_PATH;
        }
    }

    @PutMapping("")
    public String update(@ModelAttribute DepartmentDto departmentDto, Model model) {
        try {
            departmentService.update(departmentDto);
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_SAVE_SUCCESS_MESSAGE);
        } catch (Exception e) {
            model.addAttribute(ApplicationConstants.RESULT_MESSAGE_ATTRIBUTE, DEPARTMENT_SAVE_ERROR_MESSAGE);
            model.addAttribute(ApplicationConstants.EXCEPTION, e);
        }
        return ApplicationConstants.RESULT_PAGE_PATH;
    }

}
