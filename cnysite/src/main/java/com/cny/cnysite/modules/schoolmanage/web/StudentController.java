/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.cny.cnysite.modules.schoolmanage.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cny.cnysite.common.web.ApiBaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cny.cnysite.common.config.Global;
import com.cny.cnysite.common.persistence.Page;
import com.cny.cnysite.common.web.BaseController;
import com.cny.cnysite.common.utils.StringUtils;
import com.cny.cnysite.modules.schoolmanage.entity.Student;
import com.cny.cnysite.modules.schoolmanage.service.StudentService;

/**
 * 学生管理Controller
 * @author 陈南阳
 * @version 2018-05-25
 */
@Controller
@RequestMapping(value = "${adminPath}/schoolmanage/student")
public class StudentController extends ApiBaseController {

	@Autowired
	private StudentService studentService;
	
	@ModelAttribute
	public Student get(@RequestParam(required=false) String id) {
		Student entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = studentService.get(id);
		}
		if (entity == null){
			entity = new Student();
		}
		return entity;
	}

	@RequiresPermissions("schoolmanage:student:view")
	@RequestMapping("index")
	public String index(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/schoolmanage/studentIndex";
	}

	@RequiresPermissions("schoolmanage:student:view")
	@RequestMapping(value = {"list", ""})
	public String list(Student student, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Student> page = studentService.findPage(new Page<Student>(request, response), student); 
		model.addAttribute("page", page);
		return "modules/schoolmanage/studentList";
	}

	@RequiresPermissions("schoolmanage:student:view")
	@RequestMapping(value = "form")
	public String form(Student student, Model model) {
		model.addAttribute("student", student);
		return "modules/schoolmanage/studentForm";
	}

	@RequiresPermissions("schoolmanage:student:edit")
	@RequestMapping(value = "save")
	public String save(Student student, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, student)){
			return form(student, model);
		}
		studentService.save(student);
		addMessage(redirectAttributes, "保存学生成功");
		return "redirect:"+Global.getAdminPath()+"/schoolmanage/student/?repage";
	}
	
	@RequiresPermissions("schoolmanage:student:edit")
	@RequestMapping(value = "delete")
	public String delete(Student student, RedirectAttributes redirectAttributes) {
		studentService.delete(student);
		addMessage(redirectAttributes, "删除学生成功");
		return "redirect:"+Global.getAdminPath()+"/schoolmanage/student/?repage";
	}

}