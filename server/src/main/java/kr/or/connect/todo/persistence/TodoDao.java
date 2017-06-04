package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.Todo;

@Repository
public class TodoDao {
	
	private TodoSqls sql;
	private Logger log = LoggerFactory.getLogger(TodoDao.class);
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private static RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo_list_tb")
				.usingGeneratedKeyColumns("id");
	}
	
	public int countAllTodo(){
		Map<String, Object> params = Collections.emptyMap();
		log.info(">>>>>>>>>>>>>>>>>>>DAO : count all !!!");
		return jdbc.queryForObject(sql.COUNT_TODO_ALL, params, Integer.class);
	}
	
	public int countStatus(String status){
		Map<String, Object> params = Collections.singletonMap("status", status);
		log.info(">>>>>>>>>>>>>>>>>>>DAO : count STATUS !!!: " +status);
		return jdbc.queryForObject(sql.COUNT_STATUS, params, Integer.class);
	}
	
	public Integer insert(Todo todo) {
		log.info(">>>>>>>>>>>>>>>>>DAO : insert !!!");
		
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	public int updateStatus(Todo todo){
		log.info(">>>>>>>>>>>>>>>>>DAO : updateStatus !!!");
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(sql.UPDATE_STATUS, params);
	}
	
	public List<Todo> selectAll(){
		log.info(">>>>>>>>>>>>>>>>>DAO : selectAll !!!");
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(sql.LIST_ALL, params, rowMapper);
	}
	
	public List<Todo> selectStatus(String status){
		log.info(">>>>>>>>>>>>>>>>>DAO : selectStatus !!! : "+status);
		Map<String, Object> params = Collections.singletonMap("status", status);
		return jdbc.query(sql.LIST_STATUS, params, rowMapper);
	}
	

	public int deleteOne(Integer id){
		log.info(">>>>>>>>>>>>>>>>>DAO : deleteONe !!!");
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(sql.DELETE_ONE, params);
	}

	public int deleteComplte(){
		log.info(">>>>>>>>>>>>>>>>>DAO : delete COMPLETE !!!");
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.update(sql.DELETE_COMPLETE, params);
	}
	
}

