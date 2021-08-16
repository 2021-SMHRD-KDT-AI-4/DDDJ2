package com.example.projectvegan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ImageLoadTaskYolo extends AsyncTask<Void, Void, Bitmap> {
    private  String urlStr;
    private ImageView imageView;
    private static HashMap<String, Bitmap> bitmapHashMap = new HashMap<String, Bitmap>();

    public ImageLoadTaskYolo(String urlStr, ImageView imageView) {
        this.urlStr = urlStr;
        this.imageView = imageView;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
        imageView.invalidate();
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bitmap =null;
        if (bitmapHashMap.containsKey(urlStr)){
            Bitmap oldbitmap = bitmapHashMap.remove(urlStr);
            if (oldbitmap != null){
                oldbitmap.recycle();;
                oldbitmap = null;
            }
        }

        try {
            URL url = new URL(urlStr);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            bitmapHashMap.put(urlStr,bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
