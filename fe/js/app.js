(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!

	loadList();
	getCountAll();
	
	
	/*할일 리스트*/
	function loadList(){
		$.ajax({
			type:'GET',
			url:'/api/todos/list',
			contentType :'application/json',
			success : function(data){
				var str ="";
				$(data).each(
						function(){
							if(this.status =='completed'){
								str += "<li class= 'completed'>" +
								"<div class='view' id='" +this.id+
								"'>" +
								"<input class='toggle' type='checkbox' checked>" +
								"<label>"+this.toDo+"</label>" +
								"<button class='destroy'></button>" +
								"</div>" +
								"<input class='edit' vlaue='Create a TodoMVC template'>" +
								"</li>"
							}
							else{
								str += "<li>" +
								"<div class='view' id='" +this.id+
								"'>" +
								"<input class='toggle' type='checkbox'>" +
								"<label>"+this.toDo+"</label>" +
								"<button class='destroy'></button>" +
								"</div>" +
								"<input class='edit' vlaue='Rule the web'>" +
								"</li>"
							}
				});
				$(".todo-list").html(str);
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	/*할일 갯수*/
	function getCountAll(){
		$.ajax({
			type:'GET',
			url:'/api/todos/count',
			contentType :'application/json',
			success : function(data){
				var str ="";
				$(".todo-count").html(
						"<strong>"+data+"<strong>");
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	
	/*할일 등록*/
	$(".new-todo").keypress(function(e){
		var value = $(".new-todo").val();
		if (e.which == 13) {/* 13 == enter key@ascii */
			//alert("text : "+value);
			if(value.length >0){
				$.ajax({
					type:'POST',
					url:'/api/todos',
					contentType :'application/json',
					dataType:'text',
					data:JSON.stringify({toDo:value}),
					success : function(){
						//alert("성공");
						loadList();
						getCountAll();
						$(".new-todo").val("");
					},
					error : function(){
						alert("잠시후에 다시 시도해 주세요");
					}
				});
			}else{
				alert("내용을 입력해 주세요");
			}
		}
	});


	/*할일 완료*/
	$(".todo-list").on("click",".toggle",function(){
		if($(this).prop("checked")){
			var id = $(this).parent().attr("id");
			updateStatus(id, "completed");
			$(this).parent().parent().addClass("completed");
		}else{
			var id = $(this).parent().attr("id");
			updateStatus(id, "active");
			$(this).parent().parent().removeClass("completed");
		}
	});
	
	/*할일 완료*/
	function updateStatus(id, status){
		$.ajax({
			type:'PUT',
			url:'/api/todos/'+id+"/"+status,
			contentType :'application/json',
			success : function(data){
				//console.log("staus성공");
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	/*할일 단일 삭제*/
	$(".todo-list").on("click",".destroy",function(){
		var id = $(this).parent().attr("id");
		deleteById(id);
	});
	
	/*할일 단일 삭제 함수*/
	function deleteById(id){
		$.ajax({
			type:'DELETE',
			url:'/api/todos/'+id,
			contentType :'application/json',
			success : function(){
				loadList();
				getCountAll();
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	/*전체 리스트*/
	$("#all-status").on("click",function(){
		loadList();
		getCountAll();
		$(".selected").removeClass("selected");
		$(this).addClass("selected");
	});
	
	/*active 리스트*/
	$("#active-status").on("click",function(){
		//console.log($(this));
		$(".selected").removeClass("selected");
		$(this).addClass("selected");
		loadStatusList("active");
		getCountStatus("active");
	});
	
	/*completed 리스트*/
	$("#completed-status").on("click",function(){
		//console.log($(this));
		$(".selected").removeClass("selected");
		$(this).addClass("selected");
		loadStatusList("completed");
		getCountStatus("completed");
	});
	
	/*상태 별 리스트*/
	function loadStatusList(status){
		
		$.ajax({
			type:'GET',
			url:'/api/todos/list/'+status,
			contentType :'application/json',
			success : function(data){
				var str ="";
				
				$(data).each(
						function(){
							if(this.status =='completed'){
								str += "<li class= 'completed'>" +
								"<div class='view' id='" +this.id+
								"'>" +
								"<input class='toggle' type='checkbox' checked>" +
								"<label>"+this.toDo+"</label>" +
								"<button class='destroy'></button>" +
								"</div>" +
								"<input class='edit' vlaue='Create a TodoMVC template'>" +
								"</li>"
							}
							else{
								str += "<li>" +
								"<div class='view' id='" +this.id+
								"'>" +
								"<input class='toggle' type='checkbox'>" +
								"<label>"+this.toDo+"</label>" +
								"<button class='destroy'></button>" +
								"</div>" +
								"<input class='edit' vlaue='Rule the web'>" +
								"</li>"
							}
				});
				$(".todo-list").html(str);
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	/*상태별 리스트 갯수*/
	function getCountStatus(status){
		$.ajax({
			type:'GET',
			url:'/api/todos/count/'+status,
			contentType :'application/json',
			success : function(data){
				var str ="";
				
				$(".todo-count").html(
						"<strong>"+data+"<strong>");
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
	/*완료 값 삭제*/
	$(".clear-completed").on("click",function(){
		deleteCompleted();
	});
	
	/*완료 삭제 함수*/
	function deleteCompleted(){
		$.ajax({
			type:'DELETE',
			url:'/api/todos/',
			contentType :'application/json',
			success : function(){
				loadList();
				getCountAll();
			},
			error : function(){
				alert("잠시후에 다시 시도해 주세요");
			}
		});
		
	}
	
})(window);
