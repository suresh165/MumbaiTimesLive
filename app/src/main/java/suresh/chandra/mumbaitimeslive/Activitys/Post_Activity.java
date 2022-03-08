package suresh.chandra.mumbaitimeslive.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Date;

import suresh.chandra.mumbaitimeslive.R;

public class Post_Activity extends AppCompatActivity {
    WebView webView;
    Uri uri;
    private TextView content_text_1,date_text,comment_text;
    private ImageView imagepost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_);
        webView = findViewById(R.id.content_View);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //get data to Main activity by adapter
        Intent getdata = getIntent();
        String str_title = getdata.getStringExtra("title");
        String str_content = getdata.getStringExtra("content");
        String str_date = getdata.getStringExtra("date");
        String str_comment = getdata.getStringExtra("comment");
        String str_image = getdata.getStringExtra("image");

        //show title in header
        getSupportActionBar().setTitle(str_title);

        //web view
        final String mimType = "text/html";
        final String encoding = "UTF-8";
        webView.loadDataWithBaseURL("", str_content, mimType, encoding, "");
        //set data in field
        content_text_1 = findViewById(R.id.content_text);
        date_text = findViewById(R.id.text_date);
        comment_text = findViewById(R.id.comment_text);
        imagepost = findViewById(R.id.image);
        //set date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String data = simpleDateFormat.format(new Date());
        date_text.setText(data);
        content_text_1.setText(str_title);
        comment_text.setText(str_comment);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
