package com.example.projectvegan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImgLoadTask extends AsyncTask<Void, Void, Bitmap> {

    // 웹 주소
    private String urlStr;
    private ImageView imageView;
    private static HashMap<String, Bitmap> bitmapHashMap = new HashMap<String, Bitmap>();

    // doInBackGround return값 받아서 imageView 업데이트
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }

    public ImgLoadTask(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {

        Bitmap bitmap = null;
        if (bitmapHashMap.containsKey(urlStr)) {
            Bitmap oldbitmap = bitmapHashMap.remove(urlStr);
            if (oldbitmap != null) {
                oldbitmap.recycle();
                oldbitmap = null;
            }
        }
        try {
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            bitmapHashMap.put(urlStr, bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}


//import java.io.IOException;
//        import java.io.InputStream;
//        import java.net.HttpURLConnection;
//        import java.net.MalformedURLException;
//        import java.net.URL;
//        import java.util.HashMap;
//        import java.util.Map;
//        import java.util.Random;
//
//        import android.app.Activity;
//        import android.graphics.Bitmap;
//        import android.graphics.BitmapFactory;
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.ImageView;
//
//public class HTTPTest extends Activity {
//    ImageView imView;
//    String imageUrl="http://11.0.6.23/";
//    Random r;
//
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.main);
//        r= new Random();
//
//        Button bt3= (Button)findViewById(R.id.get_imagebt);
//        bt3.setOnClickListener(getImgListener);
//        imView = (ImageView)findViewById(R.id.imview);
//    }
//
//    View.OnClickListener getImgListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View view) {
//            int i =r.nextInt()%4;
//            downloadFile(imageUrl+"png"+i+".png");
//            Log.i("im url",imageUrl+"png"+i+".png");
//        }
//    };
//
//    Bitmap bmImg;
//    void downloadFile(String fileUrl){
//        URL myFileUrl =null;
//        try {
//            myFileUrl= new URL(fileUrl);
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        try {
//            HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            int length = conn.getContentLength();
//            InputStream is = conn.getInputStream();
//
//            bmImg = BitmapFactory.decodeStream(is);
//            imView.setImageBitmap(bmImg);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//}
