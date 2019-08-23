package nick.android.roomapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;
    public NoteViewModel(@NonNull Application application){
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note){}

    public void update(Note note){}

    public void delete(Note note){}

    public void deleteAll(){}

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


}

