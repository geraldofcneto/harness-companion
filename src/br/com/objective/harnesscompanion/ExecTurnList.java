package br.com.objective.harnesscompanion;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ExecTurnList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exec_turn_list);

		setupExecTurnNamesList();
	}

	private void setupExecTurnNamesList() {
		AutoCompleteTextView execTurnName = (AutoCompleteTextView) findViewById(R.id.execTurnNameAutoCompleteView);
		execTurnName.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				fetchTurnResult(arg0.getItemAtPosition(arg2).toString());
			}
		});
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				ExecTurnList.this, android.R.layout.simple_dropdown_item_1line,
				execTurnNames());
		execTurnName.setAdapter(arrayAdapter);
	}

	private void fetchTurnResult(String turnName) {
		TextView executados = (TextView) findViewById(R.id.executadosTextView);
		executados.setVisibility(View.VISIBLE);
		executados.setText(String.format("%s %d%%",
				getString(R.string.executados), 100 * getExecuted()
						/ getScenariosQuantity()));

		ProgressBar progress = (ProgressBar) findViewById(R.id.execTurnProgressBar);
		progress.setVisibility(View.VISIBLE);
		progress.setMax(getScenariosQuantity());
		progress.setProgress(getExecuted());

		TextView vermelhos = (TextView) findViewById(R.id.vermelhosVerdesTextView);
		vermelhos.setVisibility(View.VISIBLE);
		vermelhos.setText(String.format("%s %d%%",
				getString(R.string.vermelhos_verdes), 100 * getFailed()
						/ getExecuted()));

		ProgressBar redGreen = (ProgressBar) findViewById(R.id.redGreenProgressBar);
		redGreen.setVisibility(View.VISIBLE);
		redGreen.setMax(getExecuted());
		redGreen.setProgress(getFailed());
	}

	private int getFailed() {
		return 25;
	}

	private int getExecuted() {
		return 100;
	}

	private int getScenariosQuantity() {
		return 250;
	}

	private String[] execTurnNames() {
		return new String[] { "Belgium", "France", "Italy", "Germany", "Spain" };
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exec_turn_list, menu);
		return true;
	}

}
