package com.rest.RESTAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.rest.dto.Doubts;
import com.rest.dto.Faculty;
import com.rest.dto.Review;
import com.rest.dto.Student;
import com.rest.dto.Submit;
import com.rest.dto.Tasks;
import com.ts.dao.DoubtsDAO;
import com.ts.dao.FacultyDAO;
import com.ts.dao.ReviewDAO;
import com.ts.dao.StudentDAO;
import com.ts.dao.SubmitDAO;
import com.ts.dao.TasksDAO;


@Path("myresource1")
public class MyResource1 {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    @Path("regTasks")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String daoTest() {
    /*	Faculty dept = new Faculty();
        dept.setFacultyId(5);
      
    	dept.setFacultyName("Indira");
    	dept.setGender("F");
    	dept.setSubject("C++");
    	dept.setEmailId("indira123@gmail.com");
    	dept.setMobileNumber("9934575345");
    	dept.setAddress("hyderabad");
    */	
    	
//    	Employee emp=new Employee();
//    	emp.setEmpId(100);
//    	emp.setEmpName("PASHA");
//    	emp.setEmail("dummy@gmail.com");
//    	emp.setJoinDate(new java.util.Date());
//    	emp.setDepartment(dept);
//    	
//    	EmployeeDAO empDao = new EmployeeDAO();
//    	empDao.register(emp);
    	
    	/*Student student = new Student();
    	
    	student.setAddress("hyderabad");
    	student.setBranch("IT");
    	student.setDateOfBirth("05-02-2000");
    	student.setEmailId("akhila@gmail.com");
    	student.setStudentName("akhila");
    	student.setMobileNumber("9876543245");
    	student.setGender("F");
    	
    	StudentDAO studentDao = new StudentDAO();
    	studentDao.register(student);
    	
    	Tasks task = new Tasks();
    	
    	task.setDescription("complete 1 program of scheduling");
    	task.setFaculty(dept);
    	task.setSubject("C");
    	task.setSubmitDate("2020-07-20");
    	task.setUploadDate("2020-07-10");
    	
    	TasksDAO taskDao = new TasksDAO();
    	taskDao.register(task);

    	Doubts doubt = new Doubts();
    
    	doubt.setStudent(student);
    	doubt.setTask(task);
    	doubt.setQuestion("can you give any reference book?");
    	
    	DoubtsDAO doubtDao = new DoubtsDAO();
    	doubtDao.register(doubt);
    	
    	Review rev = new Review();
   
    	rev.setStudent(student);
    	rev.setTasks(task);
    	rev.setReview("good");
    	
    	ReviewDAO revDao = new ReviewDAO();
    	revDao.register(rev); */
    	
    	//Faculty fac = new Faculty();
    	//fac.setFacultyId(1);
    	//FacultyDAO facDao = new FacultyDAO();
    	//facDao.update(dept);
    	
    	//FacultyDAO facuDao = new FacultyDAO();
    	//facuDao.login("venkat@gmail.com","venkat");
    	
    	TasksDAO taskDao = new TasksDAO();
    	taskDao.getTasks(5);
    	return "Success";
    }
}
