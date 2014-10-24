package labs.course.modernartui;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
/**
 * SEE http://stackoverflow.com/questions/19841477/java-smooth-color-transition
 * @author amarasco
 *
 */
public class MainActivity extends Activity {

	public static List<Integer> elencoId = Arrays.asList(R.id.elem1,R.id.elem2,R.id.elem3,R.id.elem4);//,R.id.elem5,R.id.elem6,R.id.elem7,R.id.elem8);
	
	public static Map<Integer,InfoColor> transitionColor = new HashMap<Integer,InfoColor>();
	
	private SeekBar seekbar;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekbar = (SeekBar)findViewById(R.id.elemSeekBar);
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				TextView elem = null;
				for(Integer id : elencoId){
					elem = (TextView)findViewById(id);
					int actualColor = ((ColorDrawable)elem.getBackground()).getColor();
					elem.setBackgroundColor(actualColor+progress);
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
