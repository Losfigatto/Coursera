package labs.course.modernartui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * 
 * @author amarasco
 * 
 */
@SuppressLint("UseSparseArrays")
public class MainActivity extends Activity {

	public static List<Integer> elencoId = Arrays.asList(R.id.elem1,
			R.id.elem2, R.id.elem3, R.id.elem4, R.id.elem5, R.id.elem6,
			R.id.elem7, R.id.elem8);
	
	static private final String URL = "http://www.moma.com";
	
	@SuppressWarnings("serial")
	public Map<Integer, InfoColor> transitionColor = new HashMap<Integer, InfoColor>() {
		{
			put(R.id.elem1,
					new InfoColor(Color.rgb(255, 204, 0), Color.rgb(255, 204,
							204)));
			put(R.id.elem2,
					new InfoColor(Color.rgb(255, 0, 0), Color.rgb(255, 0, 255)));
			put(R.id.elem3,
					new InfoColor(Color.rgb(0, 153, 51), Color.rgb(0, 153, 255)));
			put(R.id.elem4,
					new InfoColor(Color.rgb(0, 51, 204), Color
							.rgb(204, 51, 204)));
			put(R.id.elem5,
					new InfoColor(Color.rgb(255, 0, 0), Color.rgb(255, 0, 255)));
			put(R.id.elem6,
					new InfoColor(Color.rgb(0, 153, 51), Color.rgb(0, 153, 255)));
			put(R.id.elem7,
					new InfoColor(Color.rgb(0, 51, 204), Color
							.rgb(204, 51, 204)));
			put(R.id.elem8,
					new InfoColor(Color.rgb(255, 204, 0), Color.rgb(255, 204,
							204)));

		}
	};

	private SeekBar seekbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		seekbar = (SeekBar) findViewById(R.id.elemSeekBar);
		TextView elem = null;

		for (Integer element : elencoId) {

			transitionColor.get(element).setMaxTransition(seekbar.getMax());
			elem = (TextView) findViewById(element);
			elem.setBackgroundColor(transitionColor.get(element)
					.getStartColor());
		}
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView elem = null;
				for (Integer id : elencoId) {
					elem = (TextView) findViewById(id);
					elem.setBackgroundColor(transitionColor.get(id)
							.getNextColorFromRatio(progress));
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_about) {
			
			DialogOpenSite.newInstance().show(getFragmentManager(), "Alert");
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static class DialogOpenSite extends DialogFragment {
		
		public static DialogOpenSite newInstance() {
			return new DialogOpenSite();
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new AlertDialog.Builder(getActivity())
			.setMessage("Inspired by the works of modern artist. Learn More?")
			.setCancelable(false)
			.setNegativeButton("Not Now",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,
							int id) {
						dismiss();
					}
				})
			.setPositiveButton("Visit MOMA",
				  new DialogInterface.OnClickListener() {
					public void onClick(
							final DialogInterface dialog, int id) {
						Uri webpage = Uri.parse(URL);
						
				        Intent baseIntent = new Intent(Intent.ACTION_VIEW, webpage);
						
						startActivity(baseIntent);
					}
				}).create();
		}
	}
}
