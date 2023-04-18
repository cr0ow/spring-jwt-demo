package com.quicktodo.http.enpoint;

import com.quicktodo.handling.ToDoRequestHandler;
import com.quicktodo.http.HttpConstants;
import com.quicktodo.http.request.ToDoRequest;
import com.quicktodo.persistence.entity.ToDo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = HttpConstants.USERS_API_BASE_PATH)
public class ToDoHttpEndpoint {

    private final ToDoRequestHandler toDoRequestHandler;

    @GetMapping("/{userId}/todos")
    public List<ToDo> getActiveTodosForUser(@PathVariable(name = "userId") final long userId) {
        return toDoRequestHandler.getActiveTodosForUser(userId);
    }

    @PostMapping("/{userId}/todos")
    public ToDo addToDO(@PathVariable(name = "userId") final long userId,
                        @RequestBody final ToDoRequest request) {
        return toDoRequestHandler.addToDo(userId, request);
    }

    @PutMapping("/{userId}/todos/{todoId}")
    public ToDo editToDo(@PathVariable(name = "userId") final long userId,
                         @PathVariable(name = "todoId") final long todoId,
                         @RequestBody final ToDoRequest request) {
        return toDoRequestHandler.editToDo(userId, todoId, request);
    }

    @PutMapping("/{userId}/todos/{todoId}/done")
    public void markAsDone(@PathVariable(name = "userId") final Long userId,
                           @PathVariable(name = "todoId") final Long todoId,
                           @RequestParam(name = "done") final Boolean done) {
        toDoRequestHandler.markAsDone(userId, todoId, done);
    }

    @PutMapping("/{userID}/todos/{todoId}/star")
    public void markAsStarred(@PathVariable(name = "userId") final long userId,
                              @PathVariable(name = "todoId") final long todoId,
                              @RequestParam(name = "starred") final Boolean starred) {
        toDoRequestHandler.markAsStarred(userId, todoId, starred);
    }

    @DeleteMapping("/{userId}/todos/{todoId}")
    public void deleteToDo(@PathVariable(name = "userId") final long userId,
                           @PathVariable(name = "todoId") final long todoId) {
        toDoRequestHandler.deleteToDo(userId, todoId);
    }

}
