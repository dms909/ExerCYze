package todo.experiment.models;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    List<TodoItem> todoList = new ArrayList<TodoItem>();

    public TodoItem getTodoItem(int id)
    {
        if(todoList.get(id) != null && id != 0)
        {
            return todoList.get(id);
        }
        else
        {
            TodoItem defaultItem = new TodoItem();
            defaultItem.setCompleted();
            defaultItem.setId(0);
            defaultItem.setTodoName("0 is not a valid ID");
            return defaultItem;
        }
    }

}
