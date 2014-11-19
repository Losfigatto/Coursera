package labs.course.dailyselfie;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class MainActivity extends ListActivity{

	private static final String TAG = MainActivity.class.getName();
	
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final String EXTRA_RES_ID = "IMGPATH";
	static final String JPEG_PREFIX = "IMG_";
	static final String JPEG_EXT = ".jpg";
	
	private SelfieListAdapter mSelfieAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getApplicationContext(),
					"External Storage is not available.", Toast.LENGTH_LONG)
					.show();
			finish();
		}
        
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Toast.makeText(getApplicationContext(),
					"Camera is not available.", Toast.LENGTH_LONG)
					.show();
			finish();
		}
        
        mSelfieAdapter = new SelfieListAdapter(getApplicationContext());
        getListView().setAdapter(mSelfieAdapter);
        
        getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				//Create an Intent to start the ImageViewActivity
				Intent intent = new Intent(MainActivity.this,
						ImageViewActivity.class);
				
				SelfieModel model = (SelfieModel)mSelfieAdapter.getItem(position);
				// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra(EXTRA_RES_ID, model.getPathFile());
				
				// Start the ImageViewActivity
				startActivity(intent);
				
			}
		});
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
    
    

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        	File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            
            Toast.makeText(getApplicationContext(), "BitMap is null? "+(imageBitmap==null), Toast.LENGTH_SHORT).show();
        }
    }
    
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_PREFIX + timeStamp + "_";
        File storageDir = SelfieListAdapter.getDirecotryStorage();
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            JPEG_EXT,         /* suffix */
            storageDir      /* directory */
        );

        return image;
    }
}
