package kr.or.connect.todo.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService service;
	private Logger log = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	public TodoController(TodoService service){
		this.service =service;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void  create(@RequestBody Todo todo){
		log.info("**************controller : create"+todo);
		service.create(todo);
	}
	
	@GetMapping("/list")
	public Collection<Todo> selectAll(){
		log.info("**************controller : list");
		return service.findAll();
	}
	
	@GetMapping("/list/{status}")
	public Collection<Todo> selectStatus(@PathVariable String status){
		log.info("**************controller : list status");
		return service.findStatus(status);
	}
	
	@GetMapping("/count")
	public int countAll(){
		log.info("**************controller : count All");
		return service.countAll();
	}
	@GetMapping("/count/{status}")
	public int countStatus(@PathVariable String status){
		log.info("**************controller : count status");
		return service.countStatus(status);
	}
	
	@PutMapping("/{id}/{status}")
	public void update(@PathVariable String status, @PathVariable int id){
		Todo todo = new Todo();
		todo.setId(id);
		todo.setStatus(status);
		log.info("**************controller : update status : "+todo);
		service.updateState(todo);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOne(@PathVariable int id){
		log.info("**************controller : delete id : "+id);
		service.deleteOne(id);
	}
	
	@DeleteMapping
	public void deleteComplete(){
		log.info("**************controller : delete all complete ");
		service.deleteComplete();
	}
	
}
