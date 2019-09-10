package todo.experiment.models;

public class TodoItem {
    public String todoName;
    public int id;
    public boolean completed;

    public void setTodoName(String setTodo)
    {
        todoName = setTodo;
    }

    public String getTodoName()
    {
        return todoName;
    }

    public void setId(int setId)
    {
        id = setId;
    }

    public int getId()
    {
        return id;
    }

    public void setCompleted()
    {
        completed = !completed;
    }

    public boolean getCompleted()
    {
        return completed;
    }
}
