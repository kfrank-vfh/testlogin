package testlogin.mad.vfh.kfrank.de.testlogin.model;

import java.util.List;

/**
 * Created by kfrank on 03.06.2017.
 */

public interface ITodoItemCrudOperations {

    public TodoItem createTodoItem(TodoItem item);

    public List<TodoItem> readAllTodoItems();

    public TodoItem readTodoItem(long todoItemId);

    public TodoItem updateTodoItem(TodoItem item);

    public boolean deleteTodoItem(long TodoItemId);
}
