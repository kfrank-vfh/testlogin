package testlogin.mad.vfh.kfrank.de.testlogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Comparator;

import testlogin.mad.vfh.kfrank.de.testlogin.model.ITodoItemCrudOperations;
import testlogin.mad.vfh.kfrank.de.testlogin.model.TodoItem;
import testlogin.mad.vfh.kfrank.de.testlogin.model.TodoItemCrudOperationsImpl;

public class ListViewActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        // Zugriff auf die Listenansicht
        ListView listview = (ListView)findViewById(R.id.list);
        
        // lies die Optionen aus
        ITodoItemCrudOperations crud = new TodoItemCrudOperationsImpl();
        ArrayAdapter<TodoItem> adapter = new ArrayAdapter<TodoItem>(this, R.layout.listitem, crud.readAllTodoItems()){

            private boolean sorting = false;

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.listitem, null);
                }
                TextView nameView = (TextView) convertView.findViewById(R.id.itemName);
                CheckBox doneBox = (CheckBox) convertView.findViewById(R.id.doneBox);
                CheckBox favouriteBox = (CheckBox) convertView.findViewById(R.id.favouriteBox);
                TodoItem item = getItem(position);
                nameView.setText(item.getName());
                doneBox.setChecked(item.isDone());
                favouriteBox.setChecked(item.isFavourite());
                return convertView;
            }

            @Override
            public void notifyDataSetChanged() {
                if (!sorting) {
                    sorting = true;
                    sort(getComparator());
                    sorting = false;
                }
                super.notifyDataSetChanged();
            }
        };

        // setze den Adapter auf die Listenansicht
        adapter.sort(getComparator());
        listview.setAdapter(adapter);
    }

    private Comparator<TodoItem> getComparator() {
        return new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem o1, TodoItem o2) {
                if (o1.isDone() == o2.isDone()) {
                    if(o1.isFavourite() == o2.isFavourite()) {
                        return (int) (o1.getDueDate() - o2.getDueDate());
                    }
                    return o1.isFavourite() ? -1 : 1;
                }
                return o1.isDone() ? 1 : -1;
            }
        };
    }
}