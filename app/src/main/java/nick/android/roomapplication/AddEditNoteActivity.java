package nick.android.roomapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "nick.android.roomapplication.EXTRA_ID";
    public static final String EXTRA_TITLE = "nick.android.roomapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "nick.android.roomapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "nick.android.roomapplication.EXTRA_PRIORITY";

    private EditText edtTextTitle;
    private EditText edtTextDescription;
    private NumberPicker nmbrPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edtTextTitle = findViewById(R.id.edtText_title);
        edtTextDescription = findViewById(R.id.edtText_Description);
        nmbrPicker = findViewById(R.id.numbrPicker_Priority);

        nmbrPicker.setMinValue(1);
        nmbrPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("EDIT NOTE...");
            edtTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            edtTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            nmbrPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }
        else{
            setTitle("ADD NOTE...");
        }
    }

    private void saveNote(){
        String title = edtTextTitle.getText().toString();
        String description =edtTextDescription.getText().toString();
        int priority = nmbrPicker.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please insert both title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
