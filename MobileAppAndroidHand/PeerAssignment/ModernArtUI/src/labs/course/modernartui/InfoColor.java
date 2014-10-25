package labs.course.modernartui;

import android.graphics.Color;

public class InfoColor {
	
	private int startGreen;
	private int startBlue;
	private int startRed;
	
	private int endGreen;
	private int endBlue;
	private int endRed;
	
	private int ratioGreen;
	private int ratioBlue;
	private int ratioRed;
	
	public InfoColor(int startColor,int endColor){
		startGreen = Color.green(startColor);
		startBlue = Color.blue(startColor);
		startRed = Color.red(startColor);
		
		endGreen = Color.green(endColor);
		endBlue = Color.blue(endColor);
		endRed = Color.red(endColor);
		ratioGreen = 0;
		ratioBlue = 0;
		ratioRed = 0 ;
	}
	
	public void setMaxTransition(int maxTransition){
		ratioRed = (int)Math.abs((endRed - startRed)/maxTransition);
		ratioGreen = (int)Math.abs((endGreen - startGreen)/maxTransition);
		ratioBlue = (int)Math.abs((endBlue - startBlue)/maxTransition);
	}
	
	public int getNextColorFromRatio(int percent){
		int red = startRed + ratioRed * percent;
		int green = startGreen + ratioGreen * percent;
		int blue = startBlue + ratioBlue * percent;
		return Color.rgb(red, green, blue);
	}
	
	public int getStartColor(){
		return Color.rgb(startRed, startGreen, startBlue);
	}
}
