package com.iykim.todoapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iykim.todoapp.domain.TodoItem;
import com.iykim.todoapp.service.TodoService;



// RestController listens incoming requests --> sends response(objects) ex.json, xml
// @Controller --> returns a view --> but RestController is more universal and generic way to build app
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoController {
	
	//   frontend        java server
	// HttpRequest  -->   Controller  -->  Service  -->  Repository  
    //	 frontend	<--	  Controller  <--  Service	<--
	
	// Controller Responsibility
	// Controller invokes the service
	// if the service gives us data back
	// send the data back to the frontend
	
	// CORS Policy
	// CORS is somthing built in a lot of browsers that will stop you 
	// 
	
	@Autowired
	private TodoService todoService;
	
	// When the react app sends the 'get' request
	// == fetch all todo items (from database)
	@GetMapping("/api/todoItems")
	public ResponseEntity<?> fetchAllTodoItems() {
		List<TodoItem> todoItems = todoService.fetchAllTodoItems();
		
		// return ResponseEntity.status(HttpStatus.OK).body(todoItems)
		return ResponseEntity.ok(todoItems);
	}
	
	/**
	 * CRUD
	 * Create = POST = 'http://localhost:8080/api/domainObjectName'
	 * Read = GET = 'http://localhost:8080/api/domainObjectName' or 'http://localhost:8080/api/domainObjectName/{id}'
	 * Update = Put = 'http://localhost:8080/api/domainObjectName/{id}'
	 * Delete = DELETE = 'http://localhost:8080/api/domainObjectName/{id}'
	 * 
	 * */
	@PostMapping("/api/todoItems")
	public ResponseEntity<?> createNewTodoItem() {
		TodoItem todoItem = todoService.createTodoItem();
		
		return ResponseEntity.ok(todoItem);
	}
	
	@PutMapping("/api/todoItems/{id}")
	public ResponseEntity<?> updateTodoItems(@PathVariable Integer id, @RequestBody TodoItem todoItem) {
		// call the service
		TodoItem updatedTodoItem = todoService.updateTodoItem(id, todoItem);
		// get the data back from server
		// send it (back to frontend)
		
		return ResponseEntity.ok(updatedTodoItem);
	}	
	
	@DeleteMapping("/api/todoItems/{id}")
	public ResponseEntity<?> deleteTodoItem(@PathVariable Integer id) {
		todoService.deleteTodoItem(id);
		
		return ResponseEntity.ok("ok");
	}
	
}
