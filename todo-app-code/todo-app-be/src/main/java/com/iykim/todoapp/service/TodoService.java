package com.iykim.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iykim.todoapp.domain.TodoItem;
import com.iykim.todoapp.repository.TodoRepository;

// Service is the place where the business logic happens, unique
// All Controllers and Repository have pretty similar across all apps
// Service is the part where it makes the app unique

@Service
public class TodoService {

	@Autowired // inject repo to service
	private TodoRepository todoRepo;

	public List<TodoItem> fetchAllTodoItems() {
		return todoRepo.fetchAllTodoItems();
	}

	public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
		Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
											.stream()
											.filter(item -> item.getId()
											.equals(id))
											.findAny();
		if (todoOpt.isPresent()) {
			TodoItem item = todoOpt.get();
			item.setIsDone(todoItem.getIsDone());
			item.setTask(todoItem.getTask());
			
			return item;
		}
		
		return null;

	}

	public TodoItem createTodoItem() {
		TodoItem todoItem = new TodoItem();
		
		todoItem.setIsDone(false);
		
		todoItem = todoRepo.save(todoItem);
		todoItem.setTask("Task #" + todoItem.getId());
		
		return todoItem;
	}

	public void deleteTodoItem(Integer id) {
		todoRepo.delete(id);
		
	}

}
