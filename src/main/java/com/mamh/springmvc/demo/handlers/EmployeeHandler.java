package com.mamh.springmvc.demo.handlers;

import com.mamh.springmvc.demo.dao.DepartmentDao;
import com.mamh.springmvc.demo.dao.EmployeeDao;
import com.mamh.springmvc.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;


    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        //这个ID应该是post表单中传过来的。 这个方法作用到update（）方法之前，这个时候能获得ID的，然后从数据库中获得这个id对应的employee对象。
        //然后把它放到map中，键名是类名第一个字母小写。然后在update（Employee employee）方法中就能得到入参
        System.out.println("ModelAttribute getEmployee " + id);
        if (id != null) {
            map.put("employee", employeeDao.get(id));
        }

    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public String update(Employee employee) {
        System.out.println("update.........." + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        System.out.println("input..修改......" + id);
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        System.out.println("input.新建.........");
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());

        return "input";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Integer id) {
        System.out.println("delete.........." + id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String save(Employee employee) {
        System.out.println("save.........." + employee);

        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {

        map.put("employees", employeeDao.getAll());

        return "list";
    }

}
