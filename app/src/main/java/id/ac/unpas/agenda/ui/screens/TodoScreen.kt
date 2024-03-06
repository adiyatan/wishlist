package id.ac.unpas.agenda.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.room.Room
import id.ac.unpas.agenda.persistences.AppDatabase

@Composable
fun TodoScreen() {
    val context = LocalContext.current

    val db = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    val todoDao = db.todoDao()

    Column(modifier = Modifier.padding(10.dp).fillMaxWidth()) {
        FormTodoScreen(todoDao)

        ListTodoScreen(todoDao)
    }
}