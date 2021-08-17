package com.example.projectvegan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class Yolo extends AppCompatActivity {
    private ImageView img_server;
    private Button btn_server;
    private Handler mHandler;
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private String ip = "121.147.52.90";
    private String img_path;
    private int port = 9005;
    private Button btn_search;
    private static HashMap<String, Bitmap> bitmapHashMap = new HashMap<String, Bitmap>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yolo);
        img_server = findViewById(R.id.img_server);
        btn_search = findViewById(R.id.btn_search);



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });

    }
    void connect() {
        String urlStr ="http://121.147.52.90:8081/AndroidImg/MyImg";
        ImageLoadTaskYolo imageLoadTaskYolo = new ImageLoadTaskYolo(urlStr,img_server);
        imageLoadTaskYolo.execute();

        mHandler = new Handler();


        Log.w("connect", "연결 하는중");
        Thread checkUpdate = new Thread() {
            public void run() {
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

                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] bytes = byteArray.toByteArray();

                // 서버 접속
                try {
                    socket = new Socket(ip, port);
                    Log.w("서버:", "서버 접속됨");
                } catch (IOException e1) {
                    Log.w("서버:", "서버접속못함");
                    e1.printStackTrace();
                }

                Log.w(": ", "안드로이드에서 서버로 연결요청");

                try {
                    dos = new DataOutputStream(socket.getOutputStream());
                    dis = new DataInputStream(socket.getInputStream());




                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("버퍼:", "버퍼생성 잘못됨");
                }
                Log.w("버퍼:", "버퍼생성 잘됨");

                try {
                    dos.writeUTF(Integer.toString(bytes.length));
                    dos.flush();


                    dos.write(bytes);
                    dos.flush();
                    String line = readUTF8(dis);
                    int result_person=(int)dis.read();


                    Log.v("탐지된 사람의 수 ",result_person+"");
                    Log.v("음식탐지 여부 ",line);



                    socket.close();

                } catch (Exception e) {
                    Log.w("error", "error occur");
                }
            }
        };
        checkUpdate.start();

        try {
            checkUpdate.join();
        } catch (InterruptedException e) {

        }

    }
    public String readUTF8 (DataInputStream in) throws IOException {
        int length = in.readInt();
        byte[] encoded = new byte[length];
        in.readFully(encoded, 0, length);
        return new String(encoded,"UTF-8");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1234) {
            if (resultCode == RESULT_OK) {
                //이미지 선택을 취소했을때


                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                    img_server.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        }


    }
