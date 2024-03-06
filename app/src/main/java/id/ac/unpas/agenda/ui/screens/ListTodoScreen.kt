package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import id.ac.unpas.agenda.models.Todo
import id.ac.unpas.agenda.persistences.TodoDao

@Composable
fun ListTodoScreen(todoDao: TodoDao) {

    val _list: LiveData<List<Todo>> = todoDao.loadAll()
    val list: List<Todo> by _list.observeAsState(listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(list.size) { index ->
                val item = list[index]
                TodoItem(item = item)
            }
        }
    }
}