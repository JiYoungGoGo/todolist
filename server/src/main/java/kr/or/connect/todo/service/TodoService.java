package kr.or.connect.todo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private Logger log = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	private TodoDao dao;
	
	public TodoService(TodoDao dao){
		this.dao = dao;
	}
	
	private ConcurrentMap<Integer, Todo> repo = new ConcurrentHashMap<>();
	private AtomicInteger maxId = new AtomicInteger(0);
	
	public Todo findById(Integer id){
		return repo.get(id);
	}
	
	public Collection<Todo> findAll(){
		return dao.selectAll();
	}
	
	public Todo create(Todo todo){
		todo.setStatus("active");
		Integer id = dao.insert(todo);
		todo.setId(id);
		return todo;
	}
	
	public Collection<Todo> selectAll(){
		return Arrays.asList();
	}

	public Collection<Todo> findStatus(String status) {
		
		return dao.selectStatus(status);
	}

	public int countAll() {
	
		return dao.countAllTodo();
	}

	public int countStatus(String status) {
	
		return dao.countStatus(status);
	}

	public void updateState(Todo todo) {
		dao.updateStatus(todo);
		
	}

	public void deleteOne(int id) {
		dao.deleteOne(id);
	}

	public void deleteComplete() {
		dao.deleteComplte();
	}
}
