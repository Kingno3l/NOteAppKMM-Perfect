package android.example.noteappkmm.domain.data.note

import android.example.noteappkmm.domain.domain.time.DateTimeUtil
import android.example.noteappkmm.domain.domain.note.Note
import android.example.noteappkmm.domain.domain.note.NoteDataSource
import com.plcoding.noteappkmm.database.NoteDatabase

class SqlDelightNoteDataSource(db: NoteDatabase): NoteDataSource{

    private val queries = db.noteQueries

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            colorHex = note.colorHex,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
    }

    override suspend fun getNoteById(id: Long): Note? {
        return  queries
                .getNotesById(id)
                .executeAsOneOrNull()
                ?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList().map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}