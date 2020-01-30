package com.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText editText1, editText2, editText3;
    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText1 = findViewById(R.id.editText_uri);
        editText2 = findViewById(R.id.editText_location);
        editText3 = findViewById(R.id.editText_share);
        button1 = findViewById(R.id.button_website);
        button2 = findViewById(R.id.button_location);
        button3 = findViewById(R.id.button_share);
    }

    public void openWebsite(View view) {
        String url = editText1.getText().toString().trim();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "openWebsite: ");
        }

    }

    public void openLocation(View view) {
        String location = editText2.getText().toString().trim();
        Uri addressUri = Uri.parse("geo:0,0?q="+location);
        Intent intent = new Intent(Intent.ACTION_VIEW,addressUri);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }else{
            Log.d(TAG, "openLocation: ");
        }
    }

    public void ShareText(View view) {
        String shareText = editText3.getText().toString().trim();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with :")
                .setText(shareText)
                .startChooser();
    }
}
