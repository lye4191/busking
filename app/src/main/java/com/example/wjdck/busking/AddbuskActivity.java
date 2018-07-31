package com.example.wjdck.busking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddbuskActivity extends AppCompatActivity {
    //데이터 베이스
    private FirebaseDatabase database;
    private DatabaseReference ref;

    private Uri mImageCaptureUri;
    private ImageView iv_UserPhoto;
    private int id_view;
    private String absolutePath;
    static boolean storagePermission = false;
    //정보 담을 객체
    List<Busk> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbusk);

        iv_UserPhoto = (ImageView)this.findViewById(R.id.user_image);
        Button btn_cancle = (Button)this.findViewById(R.id.cancle_btn);
        Button btn_regist = (Button)this.findViewById(R.id.regist_btn);
        Button btn_attach = (Button)this.findViewById(R.id.attach_btn);
        final EditText edit_name = (EditText) this.findViewById(R.id.name_edit);
        final EditText edit_locate = (EditText) this.findViewById(R.id.locate_edit);
        final EditText edit_time = (EditText) this.findViewById(R.id.time_edit);
        final EditText edit_description = (EditText) this.findViewById(R.id.description_edit);
        final EditText edit_genre = (EditText) this.findViewById(R.id.genre_edit);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("busks");

        DatabaseReference myRef = ref.getRoot();

        btn_cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                String locate = edit_locate.getText().toString();
                String time = edit_time.getText().toString();
                String description = edit_description.getText().toString();
                String genre = edit_genre.getText().toString();
                Busk busk = null;
                if(TextUtils.isEmpty(absolutePath)) {
                    busk = new Busk(name, locate, time, description, genre);
                } else {
                    Bitmap bm = BitmapFactory.decodeFile(absolutePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //quality 조절
                    bm.compress(Bitmap.CompressFormat.JPEG, 45, baos);
                    byte[] b = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                    busk = new Busk(name, locate, time, description, genre, encodedImage);
                }

                ref.push().setValue(busk);
                onBackPressed();
            }
        });



        btn_attach.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!isStoragePermissionGranted()) {
                    checkStoragePermission();
                } else {
                    doTakeAlbumAction();
                }
            }
        });

    }
    private boolean isStoragePermissionGranted() {
        return storagePermission;
    }

    private void checkStoragePermission() {
        TedPermission.with(this)
                .setPermissionListener(storagePermissionListener)
                .setRationaleConfirmText("저장장소 접근 권한이 필요합니다")
                .setDeniedMessage("[설정] > [권한]에서 권한을 허용해주세요")
                .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
    PermissionListener storagePermissionListener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            storagePermission = true;
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            storagePermission = false;
        }
    };
    public void doTakeAlbumAction() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 1)
            return;

        mImageCaptureUri = data.getData();
        absolutePath = getPath(this, mImageCaptureUri);
        Log.d("SmartWheel", absolutePath);

        Glide.with(this).load(new File(absolutePath)).into(iv_UserPhoto);

    }
    public static String getPath(Context context, Uri uri ) {
        String result = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query( uri, proj, null, null, null );
        if(cursor != null){
            if ( cursor.moveToFirst( ) ) {
                int column_index = cursor.getColumnIndexOrThrow( proj[0] );
                result = cursor.getString( column_index );
            }
            cursor.close( );
        }
        if(result == null) {
            result = "Not found";
        }
        return result;
    }

}
