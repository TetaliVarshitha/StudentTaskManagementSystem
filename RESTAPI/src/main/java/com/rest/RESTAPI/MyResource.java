package com.rest.RESTAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.rest.dto.Doubts;
import com.rest.dto.Faculty;
import com.rest.dto.Login;
import com.rest.dto.Profile;
import com.rest.dto.Review;
import com.rest.dto.Student;
import com.rest.dto.StudentProfile;
import com.rest.dto.Submit;
import com.rest.dto.Tasks;
import com.rest.dto.Todo;
import com.rest.dto.TrippleDes;
import com.rest.dto.emailSending;
import com.ts.dao.DoubtsDAO;
import com.ts.dao.FacultyDAO;
import com.ts.dao.ProfileDAO;
import com.ts.dao.ReviewDAO;
import com.ts.dao.StudentDAO;
import com.ts.dao.StudentProfileDAO;
import com.ts.dao.SubmitDAO;
import com.ts.dao.TasksDAO;
import com.ts.dao.TodoDAO;

@Path("myresource")
public class MyResource {
	
	@Path("registerFaculty")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerFaculty(Faculty faculty) {
		System.out.println("Data Recieved in Register : " + faculty); 
		try {
			TrippleDes t = new TrippleDes();
			String password = t.encrypt(faculty.getPassword());
			faculty.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    FacultyDAO facultyDao = new FacultyDAO();
		facultyDao.register(faculty); 
	}

	@Path("registerTask")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerTask(Tasks task) {
		System.out.println("Data Recieved in Register : " + task); 
	    TasksDAO taskDao = new TasksDAO();
		taskDao.register(task);
		FacultyDAO facDao = new FacultyDAO();
		Faculty faculty = facDao.getFaculty(task.getFacultyId());
		emailSending studentDao = new emailSending();
		studentDao.MailSending(faculty,task);
	}
	@Path("registerReview")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerReview(Review task) {
		System.out.println("Data Recieved in Register : " + task); 
	    ReviewDAO taskDao = new ReviewDAO();
		taskDao.register(task); 
	}
	
	@Path("registerStudent")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerStudent(Student student) {
		System.out.println("Data Recieved in Register : " + student); 
		try {
			TrippleDes t = new TrippleDes();
			String password = t.encrypt(student.getPassword());
			student.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    StudentDAO studentDao = new StudentDAO();
		studentDao.register(student); 
	}
	
	@Path("registerDoubt")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerDoubt(Doubts doubt) {
		System.out.println("Data Recieved in Register : " + doubt); 
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate= formatter.format(date);
		doubt.setDate(strDate);
		System.out.println(strDate);
	    DoubtsDAO doubtDao = new DoubtsDAO();
		doubtDao.register(doubt); 
		StudentDAO studentDao = new StudentDAO();
		Student student = studentDao.getStudent(doubt.getStudentId());
   	    TasksDAO taskDao = new TasksDAO();
			Tasks task = taskDao.getTask(doubt.getTaskId());
			FacultyDAO facDao = new FacultyDAO();
			Faculty faculty = facDao.getFaculty(task.getFacultyId());
		emailSending studentDao1 = new emailSending();
	    String x=studentDao1.sendSms(student,faculty,doubt);
	    System.out.println(x);
	}
	
	@Path("registerTodo")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerTodo(Todo todo) {
		System.out.println("Data Recieved in Register : " + todo); 
	    TodoDAO doubtDao = new TodoDAO();
		doubtDao.register(todo); 
	}
	
	@Path("getUser/{details}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Faculty getUser(@PathParam("details") String details) {
		
		System.out.println(details);
		String loginId, password, words[];
    	words = details.split(" ");
    	System.out.println(words[0]);
    	System.out.println(words[1]);
    	loginId = words[0];
    	password = words[1];
    	try {
			TrippleDes t = new TrippleDes();
			String password1 = t.encrypt(password);
			password = password1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Faculty fac = new Faculty();
		FacultyDAO facultyDao = new FacultyDAO();
		//System.out.println(loginForm);
		fac = facultyDao.login(loginId, password);
		TrippleDes t1;
		try {
			t1 = new TrippleDes();
			String password2 = t1.decrypt(fac.getPassword());
			fac.setPassword(password2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fac); 
		
		return fac;
	}
	
	@Path("check/{details}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Submit check(@PathParam("details") String details) {
		
		System.out.println(details);
		int taskId,studentId; 
		String words[];
    	words = details.split(" ");
    	System.out.println(words[0]);
    	System.out.println(words[1]);
    	taskId = Integer.parseInt(words[0]);
        studentId = Integer.parseInt(words[1]);
        
        SubmitDAO submit = new SubmitDAO();
        Submit sum = submit.getSubmission(taskId,studentId);
        String str;
        if(sum==null) {
        	str = "Not Submitted";
        } else {
        	ReviewDAO rev = new ReviewDAO();
        	Review review = rev.getReview1(taskId,studentId);
        	if(review==null) {
        		str = "Submitted, Not Reviewed";
        	} else {
        		str = "Submitted, "+review.getReview();
        	}
        }
        //JSON.parse(str);
        return sum;
	}
	
	@Path("check1/{details}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Review check1(@PathParam("details") String details) {
		
		System.out.println(details);
		int taskId,studentId; 
		String words[];
    	words = details.split(" ");
    	System.out.println(words[0]);
    	System.out.println(words[1]);
    	taskId = Integer.parseInt(words[0]);
        studentId = Integer.parseInt(words[1]);
        	ReviewDAO rev = new ReviewDAO();
        	Review review = rev.getReview1(taskId,studentId);
        return review;
	}
	
	@Path("getAllFaculty")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Faculty> getAllFaculty() {
		
		FacultyDAO facDao = new FacultyDAO();
		List<Faculty> empList = facDao.getAllFaculty();
		return empList;
	}
	
	@Path("getAllSubmissions/{subject}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Submit> getAllSubmissions(@PathParam("subject") String subject) {
		
		SubmitDAO facDao = new SubmitDAO();
		System.out.println("called");
		List<Submit> empList = facDao.getAllSubmissions(subject);
		return empList;
	}
	
	@Path("getProfile/{facultyId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("facultyId") int facultyId) {
		
		ProfileDAO facDao = new ProfileDAO();
		System.out.println("called");
		Profile empList = facDao.getAllProfiles(facultyId);
		return empList;
	}
	
	@Path("getStudentProfile/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public StudentProfile getStudentProfile(@PathParam("studentId") int studentId) {
		
		StudentProfileDAO facDao = new StudentProfileDAO();
		System.out.println("called");
		StudentProfile empList = facDao.getAllProfiles(studentId);
		return empList;
	}
	
	@Path("getAllTasks/{facultyId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Tasks> getAllTasks(@PathParam("facultyId") int facultyId) {
		
		TasksDAO taskDao = new TasksDAO();
		System.out.println(facultyId);
		List<Tasks> taskList = taskDao.getTasks(facultyId);
		return taskList;
	}
	
	@Path("getAllTodos/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Todo> getAllTodos(@PathParam("studentId") int studentId) {
		
		TodoDAO taskDao = new TodoDAO();
		System.out.println(studentId);
		List<Todo> taskList = taskDao.getTodos(studentId);
		return taskList;
	}
	
	@Path("getReviews/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Review> geReviews(@PathParam("studentId") int studentId) {
		
		ReviewDAO taskDao = new ReviewDAO();
		System.out.println(studentId);
		List<Review> taskList = taskDao.getReviewsByStudentId(studentId);
		return taskList;
	}
	

	@Path("getMyDoubts/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Doubts> getMyDoubts(@PathParam("studentId") int studentId) {
		
		DoubtsDAO doubtDao = new DoubtsDAO();
		System.out.println(studentId);
		List<Doubts> doubtList = doubtDao.getDoubtsByStudentId(studentId);
		for(Doubts doubt:doubtList) {
			if(doubt.getAnswer()==null) {
			doubt.setAnswer("Not Yet Reviewed");
			}
		}
		return doubtList;
	}
	
	@Path("getFacultyById/{facultyId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Faculty getFacultyById(@PathParam("facultyId") int facultyId) {
		
		FacultyDAO facDao = new FacultyDAO();
		Faculty faculty = facDao.getFaculty(facultyId);
		System.out.println(faculty); 
		return faculty;
	}
	
	@Path("getFaculty/{emailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Faculty getFaculty(@PathParam("emailId") String emailId) {
		
		FacultyDAO facDao = new FacultyDAO();
		Faculty faculty = facDao.getFacultyByEmailId(emailId);
		System.out.println(faculty); 
		return faculty;
	}
	
	@Path("getStudent1/{emailId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent1(@PathParam("emailId") String emailId) {
		
		StudentDAO facDao = new StudentDAO();
		Student faculty = facDao.getStudentByEmailId(emailId);
		System.out.println(faculty); 
		if(faculty!=null){
			try {
				TrippleDes t1 = new TrippleDes();
				String password2 = t1.decrypt(faculty.getPassword());
				faculty.setPassword(password2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			emailSending studentDao = new emailSending();
			studentDao.MailSending1(faculty);
		}
		return faculty;
	}
	
	@Path("getReviewedDoubts/{date}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Doubts> getReviewedDoubts(@PathParam("date") String date) {
		
		DoubtsDAO facDao = new DoubtsDAO();
		List <Doubts> doubtList= facDao.getReviewedDoubts(date);
		
		System.out.println(date); 
		return doubtList;
	}
	
	@Path("getPendingDoubts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Doubts> getPendingDoubts() {
		
		DoubtsDAO facDao = new DoubtsDAO();
		List <Doubts> doubtList= facDao.getPendingDoubts();
		//System.out.println(faculty); 
		for(Doubts doubt:doubtList) {
			doubt.setAnswer("Not Yet Reviewed");
		}
		return doubtList;
	}
	
	@Path("getAllStudents")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Student> getAllStudents() {
		
		StudentDAO studentDao = new StudentDAO();
		List<Student> studentList = studentDao.getAllStudents();
		return studentList;
	}
	
	@Path("sendEmail")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void sendEmail(Faculty faculty) {
		
		emailSending studentDao = new emailSending();
		//studentDao.MailSending(faculty);
	}
	
	@Path("getAllDoubts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Doubts> getAllDoubts() {
		
		DoubtsDAO doubtDao = new DoubtsDAO();
		List<Doubts> doubtList = doubtDao.getAllDoubts();
		for(Doubts doubt:doubtList) {
			if(doubt.getAnswer()==null) {
			doubt.setAnswer("Not Yet Reviewed");
			}
		}
		return doubtList;
	}
	
	@Path("getAllTask")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Tasks> getAllTask() {
		
		TasksDAO taskDao = new TasksDAO();
		List<Tasks> taskList = taskDao.getAllTasks1();
		return taskList;
	}
	
	@Path("getAllTasks")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List <Tasks> getAllTasks() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();  
        cal.setTime(today);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        
		Date date;
		ArrayList<Tasks> arrlist = new ArrayList<Tasks>(); 
		TasksDAO taskDao = new TasksDAO();
		List<Tasks> taskList = taskDao.getAllTasks();
		 for(Tasks task : taskList) {
			 String deadline = task.getSubmitDate();
			 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 try {
				 String str = format.format(today);
				 today = format.parse(str);
			    date = format.parse(deadline);
			    int x = today.compareTo(date);
			    System.out.println(x);
			    System.out.println(today);
			    if(today.compareTo(date) < 0 || today.equals(date)) {
			    	arrlist.add(task);
			         System.out.println("Date 1 occurs after Date 2");
			         System.out.println(today);
			     }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		return arrlist;
	}
	
	@Path("getStudent/{details}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("details") String details) {
		
		System.out.println(details);
		String loginId, password, words[];
    	words = details.split(" ");
    	System.out.println(words[0]);
    	System.out.println(words[1]);
    	loginId = words[0];
    	password = words[1];
    	Student fac = new Student();
    	StudentDAO facultyDao = new StudentDAO();
    	try {
			TrippleDes t = new TrippleDes();
			String password1 = t.encrypt(password);
			password = password1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(loginForm);
		fac = facultyDao.login(loginId, password);
		TrippleDes t1;
		try {
			t1 = new TrippleDes();
			String password2 = t1.decrypt(fac.getPassword());
			fac.setPassword(password2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(fac); 
		return fac;
	}
	
	@Path("getStudentById/{studentId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentById(@PathParam("studentId") int studentId) {
		
		StudentDAO studentDao = new StudentDAO();
		Student student = studentDao.getStudent(studentId);
		System.out.println(student); 
		return student;
	}
	
	@Path("deleteFaculty/{facultyId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteFaculty(@PathParam("facultyId") int facultyId) {
		System.out.println("Data Received in Delete: " + facultyId);
		
		FacultyDAO facultyDao = new FacultyDAO();
		Faculty faculty = facultyDao.getFaculty(facultyId);
		facultyDao.delete(faculty.getFacultyId());
		
		System.out.println("Faculty Record Deleted Successfully!!!");
	}
	
	@Path("deleteTodo/{todoId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteTodo(@PathParam("todoId") int todoId) {
		System.out.println("Data Received in Delete: " + todoId);
		
		TodoDAO facultyDao = new TodoDAO();
		Todo faculty = facultyDao.getTodo(todoId);
		facultyDao.delete(faculty.getTodoId());
		
		System.out.println("Faculty Record Deleted Successfully!!!");
	}
	
	@Path("deleteTask/{taskId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteTask(@PathParam("taskId") int taskId) {
		System.out.println("Data Received in Delete: " + taskId);
		
		TasksDAO taskDao = new TasksDAO();
		Tasks faculty = taskDao.getTask(taskId);
		taskDao.delete(faculty.getTaskId());
		
		System.out.println("Task Record Deleted Successfully!!!");
	}
	@Path("updateDoubt")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateDoubt(Doubts doubt) {
		System.out.println("Received Data in update: " + doubt);
		
		DoubtsDAO doubtDao = new DoubtsDAO();
		doubtDao.update(doubt);
		StudentDAO studentDao = new StudentDAO();
		Student student = studentDao.getStudent(doubt.getStudentId());
   	    TasksDAO taskDao = new TasksDAO();
			Tasks task = taskDao.getTask(doubt.getTaskId());
			FacultyDAO facDao = new FacultyDAO();
			Faculty faculty = facDao.getFaculty(task.getFacultyId());
		emailSending studentDao1 = new emailSending();
		String x=studentDao1.sendSms1(student,faculty,doubt);
		System.out.println(x);
		
		System.out.println("Doubt Record Updated!!!");
	}
	
	@Path("updateFaculty")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateFaculty(Faculty faculty) {
		System.out.println("Received Data in update: " + faculty);
		try {
			TrippleDes t = new TrippleDes();
			String password1 = t.encrypt(faculty.getPassword());
			faculty.setPassword(password1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FacultyDAO facDao = new FacultyDAO();
		facDao.update(faculty);
		
		System.out.println("Faculty Record Updated!!!");
	}
	
	@Path("updateStudent")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateStudent(Student student) {
		System.out.println("Received Data in update: " + student);
		String password = student.getPassword();
		try {
			TrippleDes t = new TrippleDes();
			String password1 = t.encrypt(password);
			student.setPassword(password1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StudentDAO studentDao = new StudentDAO();
		studentDao.update(student);
		
		System.out.println("Student Record Updated!!!");
	}
	
	@Path("deleteStudent/{studentId}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("studentId") int studentId) {
		System.out.println("Data Received in Delete: " + studentId);
		
		StudentDAO studentDao = new StudentDAO();
		Student student = studentDao.getStudent(studentId);
		studentDao.delete(student.getStudentId());
		
		System.out.println("Student Record Deleted Successfully!!!");
	}
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Path("/uploadImage")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void uploadImage(@FormDataParam("Image") InputStream fileInputStream, @FormDataParam("Image") FormDataContentDisposition
    formDataContentDisposition, @FormDataParam("taskId") int taskId, @FormDataParam("studentId") int studentId, @FormDataParam("subject") String subject) {
        System.out.println("Hello");
    	int read=0;
        byte[] bytes = new byte[1024];
        
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String pathArr[] = path.split("/WEB-INF/classes/");
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(pathArr[0] + "/image/", formDataContentDisposition.getFileName()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while ((read = fileInputStream.read(bytes)) != -1) {
			try {
				out.write(bytes, 0, read);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Submit submit = new Submit();
        submit.setTaskId(taskId);
        submit.setStudentId(studentId);
        TasksDAO taskDao = new TasksDAO();
        Tasks task = taskDao.getTask(taskId);
        submit.setSubject(task.getSubject());
        submit.setFile1(formDataContentDisposition.getFileName());
        SubmitDAO submitDao = new SubmitDAO();
        submitDao.register(submit);
    }
    
    @POST
    @Path("/uploadProfile")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void uploadProfile(@FormDataParam("Image") InputStream fileInputStream, @FormDataParam("Image") FormDataContentDisposition
    formDataContentDisposition, @FormDataParam("facultyId") int facultyId) {
        System.out.println("Hello");
    	int read=0;
        byte[] bytes = new byte[1024];
        
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String pathArr[] = path.split("/WEB-INF/classes/");
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(pathArr[0] + "/image/", formDataContentDisposition.getFileName()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while ((read = fileInputStream.read(bytes)) != -1) {
			try {
				out.write(bytes, 0, read);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ProfileDAO profiledao = new  ProfileDAO();
        Profile obj = null;
        obj = profiledao.getAllProfiles(facultyId);
        System.out.println(obj);
        
        if(obj==null) {
        	System.out.println("In if");
        Profile profile = new Profile();
        profile.setFacultyId(facultyId);
        profile.setImageUrl(formDataContentDisposition.getFileName());
        ProfileDAO submitDao = new ProfileDAO();
        submitDao.register(profile);
        } else {
        	 //Profile profile = new Profile();
            obj.setFacultyId(facultyId);
            obj.setImageUrl(formDataContentDisposition.getFileName());
             ProfileDAO submitDao = new ProfileDAO();
             submitDao.update(obj);
        } 
    }
    
    @POST
    @Path("/uploadStudentProfile")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public void uploadStudentProfile(@FormDataParam("Image") InputStream fileInputStream, @FormDataParam("Image") FormDataContentDisposition
    formDataContentDisposition, @FormDataParam("studentId") int studentId) {
        System.out.println("Hello");
    	int read=0;
        byte[] bytes = new byte[1024];
        
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String pathArr[] = path.split("/WEB-INF/classes/");
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(pathArr[0] + "/image/", formDataContentDisposition.getFileName()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			while ((read = fileInputStream.read(bytes)) != -1) {
			try {
				out.write(bytes, 0, read);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        StudentProfileDAO profiledao = new  StudentProfileDAO();
        StudentProfile obj = null;
        obj = profiledao.getAllProfiles(studentId);
        System.out.println(obj);
        
        if(obj==null) {
        	System.out.println("In if");
        StudentProfile profile = new StudentProfile();
        profile.setStudentId(studentId);
        profile.setImageUrl(formDataContentDisposition.getFileName());
        StudentProfileDAO submitDao = new StudentProfileDAO();
        submitDao.register(profile);
        } else {
        	 //Profile profile = new Profile();
            obj.setStudentId(studentId);
            obj.setImageUrl(formDataContentDisposition.getFileName());
             StudentProfileDAO submitDao = new StudentProfileDAO();
             submitDao.update(obj);
        } 
    }
    
    
}

