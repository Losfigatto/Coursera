package labs.course.dailyselfie;

import java.io.File;

import android.net.Uri;

public class SelfieModel {
	
	//AbsolutePath of File Image
	private String pathFile;
	
	//Complete FileName
	private String nameFile;

	private Uri uriFile;
	
	public SelfieModel(File img) {
		pathFile = img.getAbsolutePath();
		uriFile = Uri.fromFile(img);
		nameFile = img.getName().substring(MainActivity.JPEG_PREFIX.length(), MainActivity.JPEG_PREFIX.length()+15);
	}
	
	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public Uri getUriFile() {
		return uriFile;
	}

	public void setUriFile(Uri uriFile) {
		this.uriFile = uriFile;
	}
	
	
}
