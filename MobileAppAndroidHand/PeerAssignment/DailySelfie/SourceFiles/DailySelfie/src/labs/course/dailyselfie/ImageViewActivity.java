package labs.course.dailyselfie;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageViewActivity extends Activity {
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	// Get the Intent used to start this Activity
			Intent intent = getIntent();
			
			// Make a new ImageView
			ImageView imageView = new ImageView(getApplicationContext());
			
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageURI(Uri.parse(intent.getStringExtra(MainActivity.EXTRA_RES_ID)));
			
			setContentView(imageView);
	};
	
}
