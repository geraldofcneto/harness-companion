package br.com.objective.harnesscompanion;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ExecTurnList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_turn_list);
        
        fetchExecTurnNamesList();
    }

	private void fetchExecTurnNamesList() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ExecTurnList.this, android.R.layout.simple_dropdown_item_1line, execTurnNames());
				AutoCompleteTextView view = (AutoCompleteTextView) findViewById(R.id.execTurnNameAutoCompleteView);
				view.setAdapter(arrayAdapter);
				return null;
			}
		}.execute();
	}

    private List<String> execTurnNames() {
		return Arrays.asList("Bogus");
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.exec_turn_list, menu);
        return true;
    }
    
}
