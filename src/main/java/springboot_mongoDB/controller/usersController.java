package springboot_mongoDB.controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import springboot_mongoDB.Repository.usersRepository;
import springboot_mongoDB.model.users;


@RestController
//@RequestMapping("/user")          --> all request methods must have this url 
public class usersController {

	@Autowired
	private usersRepository usersRepo;
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody users user) {
		usersRepo.save(user);
		return "Added user with id: "+user.getId(); 
	}
	
	//getting all the users
	@GetMapping("/findAllUsers")
	public List<users> getAllUsers(){
		return usersRepo.findAll();
	}
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	//getting all users with an id
	@GetMapping("/findAllUser/{_id}")
	public Optional<users> getUserById(@PathVariable ObjectId _id) {
		return usersRepo.findBy_id(_id);
	}
	
	//get user details with name
	@GetMapping("/findUser/{name}")
	public List<users> findByName(@PathVariable String name){
		return usersRepo.findByName(name);
	}
	
	//find user with email
	@GetMapping("/findEmail/{email}")
	public List<users> findByEmail(@PathVariable String email) {
		return usersRepo.findByEmail(email);
	}
	
	
	@DeleteMapping("/deleteUser/{_id}")
	public String deleteUser(@PathVariable ObjectId _id) {
		usersRepo.deleteBy_id(_id);
		return "user with id: "+_id+" was deleted";
	}
	
	//updating a user based on id.
	@PutMapping("/updateUser/{_id}")
	public String updateUser(@RequestBody users u,@PathVariable ObjectId _id) {
		u.setId(_id);
		usersRepo.save(u);
		return "updated user with id: "+_id;
	}
	
	//taking input from user via a webpg
	@RequestMapping("/signup")
	@ResponseBody
	public String SignUpNewUser(@RequestParam ObjectId _id,@RequestParam String name,@RequestParam String email) {
		
		users u =new users(_id,name,email);
		//String x=new Gson().toJson(u);
		usersRepo.save(u);
		return "hi! "+name+" your details were stored!!";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String Login(@RequestParam String name,@RequestParam String email) {
		List<users> u = usersRepo.findByName(name);
		List<users> v = usersRepo.findByEmail(email);
		if(u.equals(v)) {			
			return "Succesfully logged In!";
		}
		else			
			return "Ivalid User details";
	}

//--------------------------------
	@GetMapping("/get/{name}")
	public List<users> getName(@PathVariable String name)
	{
		return usersRepo.findName(name);
	}
}
