package com.uuu;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;


public class MyWallPaper extends WallpaperService {

	public class MyEngine extends Engine {
		//開啟觸控
		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			// 新增
			setTouchEventsEnabled(true);
		}

		void drawFrame(){
			final SurfaceHolder holder = getSurfaceHolder();
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if(canvas != null){
					setBackground(canvas);
				}
			}finally {
				if(canvas != null){
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}

		private void setBackground(Canvas canvas) {
			canvas.drawColor(Color.YELLOW);
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			paint.setStrokeWidth(4.0f);
			paint.setStyle(Paint.Style.STROKE);
			for(int i = 0; i < 10 ; i++){
				canvas.drawCircle(20 * i, 20 * i, 10, paint);
			}
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			if(visible){
				drawFrame();
			}

		}

		@Override
		public void onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				String message = String.format("touch at %f, %f", event.getX(), event.getY());
				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
				
			}
			super.onTouchEvent(event);
		}
		

	}
	
	@Override
	public Engine onCreateEngine() {
		return new MyEngine();
	}

}
