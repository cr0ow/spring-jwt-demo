package com.quicktodo.handling;

import com.quicktodo.http.request.ToDoRequest;
import com.quicktodo.persistence.entity.ToDo;
import com.quicktodo.persistence.repository.ToDoRepository;
import com.quicktodo.persistence.repository.UserRepository;
import com.quicktodo.utility.validation.ToDoRequestValidator;
import com.quicktodo.utility.exception.ImpossibleActionException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ToDoRequestHandler {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;
    private final ToDoRequestValidator inputDataValidator;

    public List<ToDo> getActiveTodosForUser(final long userId) {
        return toDoRepository.findByOwnerUserId(userId).stream()
                .filter(todo -> !todo.getDone())
                .toList();
    }

    public ToDo addToDo(final long userId, final ToDoRequest request) {
        var owner = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user with given id does not exist")
        );
        inputDataValidator.validate(request);
        var toDo = ToDo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .repeat(request.getRepeat())
                .daysToRepeatOn(request.getDaysToRepeatOn())
                .wholeDay(request.getWholeDay())
                .done(false)
                .starred(false)
                .owner(owner)
                .build();
        return toDoRepository.save(toDo);
    }

    public ToDo editToDo(long userId, long todoId, ToDoRequest request) {
        var todos = toDoRepository.findById(todoId).orElseThrow(
                () -> new EntityNotFoundException("todos with given id does not exist")
        );
        if(todos.getOwner().getUserId() != userId) {
            throw new IllegalArgumentException("todos with id=" + todoId + " does not belong to user with id=" + userId);
        }
        inputDataValidator.validate(request);
        todos.setName(request.getName());
        todos.setDescription(request.getDescription());
        todos.setStartDate(request.getStartDate());
        todos.setEndDate(request.getEndDate());
        todos.setRepeat(request.getRepeat());
        todos.setDaysToRepeatOn(request.getDaysToRepeatOn());
        todos.setWholeDay(request.getWholeDay());
        return toDoRepository.save(todos);
    }

    public void markAsDone(final Long userId, final Long todoId, final Boolean done) {
        var todos = toDoRepository.findById(todoId).orElseThrow(
                () -> new EntityNotFoundException("todos with given id does not exist")
        );
        if(!todos.getOwner().getUserId().equals(userId)) {
            throw new IllegalArgumentException("todos with id=" + todoId + " does not belong to user with id=" + userId);
        }
        if(todos.getDone() && done) {
            throw new ImpossibleActionException("todos already marked as done");
        }
        else if(done) {
            todos.setDone(true);
        }
        else if(!todos.getDone()) {
            throw new ImpossibleActionException("todos already marked as undone");
        }
        else {
            todos.setDone(false);
        }
        toDoRepository.save(todos);
    }

    public void markAsStarred(final Long userId, final Long todoId, final Boolean starred) {
        var todos = toDoRepository.findById(todoId).orElseThrow(
                () -> new EntityNotFoundException("todos with given id does not exist")
        );
        if(!todos.getOwner().getUserId().equals(userId)) {
            throw new IllegalArgumentException("todos with id=" + todoId + " does not belong to user with id=" + userId);
        }
        if(todos.getStarred()) {
            throw new ImpossibleActionException("todos already marked as starred");
        }
        else if(starred) {
            todos.setStarred(true);
        }
        else if(!todos.getStarred()) {
            throw new ImpossibleActionException("todos already marked as unstarred");
        }
        else {
            todos.setStarred(false);
        }
        toDoRepository.save(todos);
    }

    public void deleteToDo(long userId, long todoId) {
        var todos = toDoRepository.findById(todoId).orElseThrow(
                () -> new EntityNotFoundException("todos with given id does not exist")
        );
        if(todos.getOwner().getUserId() != userId) {
            throw new IllegalArgumentException("todos with id=" + todoId + " does not belong to user with id=" + userId);
        }
        toDoRepository.delete(todos);
    }

}
