package labs.course.dailyselfie;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlarmManager;
import android.app.ListActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class MainActivity extends ListActivity{

	private static final String TAG = MainActivity.class.getSimpleName();
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final String EXTRA_RES_ID = "IMGPATH";
	static final String JPEG_PREFIX = "IMG_";
	static final String JPEG_EXT = ".jpg";
	static final String PATTERN_DATE = "yyyyMMdd_HHmmss";
	
	private SelfieListAdapter mSelfieAdapter;
	
	//Alarm
	private AlarmManager mAlarmManager;
	private Intent mNotificationReceiverIntent;
	private PendingIntent mNotificationReceiverPendingIntent;
	
	private static final long TIME_ALARM_REPEAT = 2 * 60 * 1000L;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getApplicationContext(),
					"External Storage is not available.", Toast.LENGTH_LONG)
					.show();
			Log.i(TAG, "External Storage is not available.");
			finish();
		}
        
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Toast.makeText(getApplicationContext(),
					"Camera is not available.", Toast.LENGTH_LONG)
					.show();
			Log.i(TAG, "Camera is not available.");
			finish();
		}
        
    	// Get the AlarmManager Service
		mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        
		// Create an Intent to broadcast to the AlarmNotificationReceiver
		mNotificationReceiverIntent = new Intent(MainActivity.this,
				AlarmNotificationReceiver.class);

		// Create an PendingIntent that holds the NotificationReceiverIntent
		mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
				MainActivity.this, 0, mNotificationReceiverIntent, 0);
		
        setContentView(R.layout.activity_main);
        
        //Create and set Adapter
        mSelfieAdapter = new SelfieListAdapter(getApplicationContext());
        getListView().setAdapter(mSelfieAdapter);
        
        //set listener onClick on every Selfie picture 
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Create an Intent to start the ImageViewActivity
				Intent intent = new Intent(MainActivity.this,
						ImageViewActivity.class);
				
				SelfieModel model = (SelfieModel)mSelfieAdapter.getItem(position);
				Log.d(TAG, "Get image in position "+position+" with path: "+model.getUriFile().getPath());
				
				// Add the path of the thumbnail to display as an Intent Extra
				intent.putExtra(EXTRA_RES_ID, model.getUriFile().getPath());
				
				// Start the ImageViewActivity
				startActivity(intent);
				
			}
		});
        
        mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
				SystemClock.elapsedRealtime() + TIME_ALARM_REPEAT,
				TIME_ALARM_REPEAT,
				mNotificationReceiverPendingIntent);
        
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	if(mNotificationReceiverPendingIntent!=null){
    		mAlarmManager.cancel(mNotificationReceiverPendingIntent);
    	}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

    	int id = item.getItemId();
        if (id == R.id.action_camera) {
        	Log.d(TAG, "Open Camera App");
        	dispatchTakePictureIntent();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    private File mTempPathImage = null;

    private void dispatchTakePictureIntent() {
        
    	Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        
    	if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        	
            try {
            	
            	mTempPathImage = createTempImageFile();
            	Log.d(TAG, "Create TempImageFile");
            	
            } catch (IOException ex) {
                Log.e(TAG,"Error on create temp file", ex);
            }

            // Continue only if the File was successfully created
            if (mTempPathImage != null) {
                
            	takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTempPathImage));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                
            }else{
            	
            	Toast.makeText(getApplicationContext(), "Cannot create a Temporary Image File!", Toast.LENGTH_LONG).show();
            }
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	Log.d(TAG,"Return from Activity resultCode ["+resultCode +"], requestCode ["+requestCode+"]");
    	
    	if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
        	
        	mSelfieAdapter.add(new SelfieModel(mTempPathImage));
            
        }else{
        	Log.d(TAG, "Delete temp File");
        	mTempPathImage.delete();
        }

        mTempPathImage = null;
    }
    
    private File createTempImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat(PATTERN_DATE).format(new Date());
        String imageFileName = JPEG_PREFIX + timeStamp + "_";
        File storageDir = SelfieListAdapter.getDirectoryToSaveImage();
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            JPEG_EXT,         /* suffix */
            storageDir      /* directory */
        );

        return image;
    }
}
