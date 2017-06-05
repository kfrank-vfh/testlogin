package testlogin.mad.vfh.kfrank.de.testlogin.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kfrank on 03.06.2017.
 */

public class TodoItemCrudOperationsImpl implements ITodoItemCrudOperations {

    List<TodoItem> items;

    public TodoItemCrudOperationsImpl() {
        items = new LinkedList<>();
        items.add(new TodoItem(1, "Todo 1", "Todo 1 Beschreibung", false, false, System.currentTimeMillis()));
        items.add(new TodoItem(1, "Todo 2", "Todo 2 Beschreibung", false, true, System.currentTimeMillis()));
        items.add(new TodoItem(1, "Todo 3", "Todo 3 Beschreibung", true, false, System.currentTimeMillis()));
    }

    @Override
    public TodoItem createTodoItem(TodoItem item) {
        // do nothing
        return null;
    }

    @Override
    public List<TodoItem> readAllTodoItems() {
        return items;
    }

    @Override
    public TodoItem readTodoItem(long todoItemId) {
        return items.get((int) todoItemId);
    }

    @Override
    public TodoItem updateTodoItem(TodoItem item) {
        // do nothing
        return null;
    }

    @Override
    public boolean deleteTodoItem(long TodoItemId) {
        // do nothing
        return false;
    }
}
