package kr.or.connect.todo.persistence;

public class TodoSqls {
	
	static final String COUNT_TODO_ALL = 
			"SELECT COUNT(*) FROM TODO_LIST_TB";
	static final String COUNT_STATUS = 
			"SELECT COUNT(*) FROM TODO_LIST_TB WHERE STATUS =:status";
	static final String LIST_ALL =
			"SELECT * FROM TODO_LIST_TB ORDER BY ID DESC";
	static final String LIST_STATUS =
			"SELECT * FROM TODO_LIST_TB WHERE STATUS =:status ORDER BY ID DESC";
	static final String UPDATE_STATUS =
			"UPDATE TODO_LIST_TB SET STATUS =:status WHERE ID =:id";
	static final String DELETE_ONE =
			"DELETE FROM TODO_LIST_TB WHERE ID=:id";
	static final String DELETE_COMPLETE=
			"DELETE FROM TODO_LIST_TB WHERE STATUS=\'completed\'";
}
