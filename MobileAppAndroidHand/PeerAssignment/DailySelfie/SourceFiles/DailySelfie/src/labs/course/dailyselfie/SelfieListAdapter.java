package labs.course.dailyselfie;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelfieListAdapter extends BaseAdapter {

	private ArrayList<SelfieModel> list = new ArrayList<SelfieModel>();
	private static LayoutInflater inflater = null;
	private Context mContext;
	
	public SelfieListAdapter(Context context) {
		mContext = context;
		inflater = LayoutInflater.from(mContext);
		setList();
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			convertView = inflater.inflate(R.layout.single_selfie_view, null);
		}
		
		
		SelfieModel model = list.get(position);
		
		((TextView)convertView.findViewById(R.id.nameFile)).setText(model.getNameFile());
		
		setPic(model.getPathFile(),(ImageView) convertView.findViewById(R.id.imageSelfie));
		
		return convertView;
	}

	private void setPic(String mCurrentPhotoPath,ImageView mImageView) {
	    // Get the dimensions of the View
	    int targetW = mContext.getResources().getDimensionPixelSize(R.dimen.image_dim_Width);
	    int targetH = mContext.getResources().getDimensionPixelSize(R.dimen.image_dim_Height);

	    // Get the dimensions of the bitmap
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    // Determine how much to scale down the image
	    int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

	    // Decode the image file into a Bitmap sized to fill the View
	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
	    mImageView.setImageBitmap(bitmap);
	}
	
	private void setList(){

//		File[] elencoImmagini = null;
//			if(elencoImmagini!=null && elencoImmagini.length>0){
//				for( File img : getDirecotryStorage().listFiles()){
//					
//					SelfieModel model = new SelfieModel(img);
//					list.add(model);
//				}
//			}else{
				Toast.makeText(mContext, "No Selfie present!", Toast.LENGTH_SHORT).show();
//			}
		
	}
	
	public void add(SelfieModel modello){
		list.add(modello);
		notifyDataSetChanged();
	}
}
