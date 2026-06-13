package org.example.companyusersystem.controller;

import jakarta.servlet.http.HttpSession;
import org.example.companyusersystem.entity.Employee;
import org.example.companyusersystem.entity.SysUser;
import org.example.companyusersystem.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    //定义业务层对象
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //查询全部
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> list(){
        return employeeService.list();
    }

    //根据id查询
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public Employee get(@RequestParam long id){
        return employeeService.getById(id);
    }

    //添加
    @PostMapping("/add")
    public Map<String,Object> add(@RequestBody Employee employee, HttpSession session){
        checkAdmin(session);
        Map<String,Object> res = new HashMap<>();
        res.put("success",employeeService.add(employee));
        return res;
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody Employee employee, HttpSession session){
        checkAdmin(session);
        Map<String,Object> res = new HashMap<>();
        res.put("success",employeeService.update(employee));
        return res;
    }

    @PostMapping("/delete")
    public Map<String,Object> deleteById(@RequestParam long id, HttpSession session){
        checkAdmin(session);
        Map<String,Object> res = new HashMap<>();
        res.put("success",employeeService.delete(id));
        return res;
    }

    //姓名查询
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> search(String name){
        return employeeService.search(name);
    }

    //性别查询
    @RequestMapping(value = "/gender", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> findByGender(String gender){
        return employeeService.findByGender(gender);
    }

    //部门查询
    @RequestMapping(value = "/department", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> findByDepartment(String department){
        return employeeService.findByDepartment(department);
    }

    //状态查询
    @RequestMapping(value = "/status", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> findByStatus(boolean status){
        return employeeService.findByStatus(status);
    }

    //分页查询
    @RequestMapping(value = "/page", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Employee> page(int page, int size){
        return employeeService.page(page, size);
    }

    //统计
    @RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    public long count(){
        return employeeService.count();
    }

    //批量删除
    @PostMapping("/batchDelete")
    public Map<String,Object> batchDelete(@RequestBody List<Long> ids, HttpSession session){
        checkAdmin(session);
        Map<String,Object> res = new HashMap<>();
        res.put("success",employeeService.batchDelete(ids));
        return res;
    }

    //文件上传
    @PostMapping("/upload")
    public Map<String,Object> upload(@RequestParam("id") long id,
                                     @RequestParam("file") MultipartFile file,
                                     HttpSession session)throws Exception{
        checkAdmin(session);
        if (employeeService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (file.isEmpty() || file.getContentType() == null || !file.getContentType().startsWith("image/")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        //获取项目运行目录
        String path = System.getProperty("user.dir") + File.separator + "uploads";
        //创建目录对象
        File dir = new File(path);
        //若目录不存在，创建目录
        if(!dir.exists()){
            dir.mkdirs();
        }
        //生成文件名，按时间随机生成名称防止重名
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        //保存文件
        file.transferTo(new File(dir, fileName));
        //返回数据
        Map<String,Object> res = new HashMap<>();
        //返回访问路径
        String url = "/uploads/" + fileName;
        res.put("success", employeeService.updateAvatar(id, url));
        res.put("url", url);
        return res;
    }

    private void checkAdmin(HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("loginUser");
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        boolean isAdmin = "admin".equalsIgnoreCase(user.getRole()) || "admin".equals(user.getUsername());
        if (!isAdmin) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
