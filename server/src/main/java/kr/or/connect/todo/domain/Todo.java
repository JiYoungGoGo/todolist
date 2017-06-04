package kr.or.connect.todo.domain;

public class Todo {

	private int id;
	private String toDo;
	private String status;
	
	public Todo(){
		
	}
	
	public Todo(String toDo){
		this.toDo = toDo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToDo() {
		return toDo;
	}
	public void setToDo(String toDo) {
		this.toDo = toDo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Todo [id=" + id + ", toDo=" + toDo + ", status=" + status + "]";
	}
	
	
	
}
