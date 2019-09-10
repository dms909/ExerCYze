package todo.experiment.Controller;

import todo.experiment.models.TodoItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TodoController.TODO_BASE_URI)


public class TodoController {
    public static final String TODO_BASE_URI = "svc/v1/todo";

    @RequestMapping(value = "{todoNumber}")
    public TodoItem getContract(@PathVariable final int todoNumber)
    {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(todoNumber);
        return todoItem;
    }

}
