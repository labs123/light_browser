package labs_corporation.lightbrowser;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   WebView browse;
   EditText Urledit;
   Button go, back , forward, reload , clear;
   ProgressBar progessBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progessBar = (ProgressBar)findViewById(R.id.pro_bar);
        browse=(WebView)findViewById(R.id.brow_wv);
        Urledit= (EditText)findViewById(R.id.t_b) ;
        go = (Button)findViewById(R.id.go_b);
        forward= (Button)findViewById(R.id.fwd_b);
        back = (Button)findViewById(R.id.bck_b);
        reload = (Button)findViewById(R.id.rl_b);
        clear = (Button)findViewById(R.id.rl_b);
                // default url;
                    browse.loadUrl("http://www.riamonline.com");
       browse.setWebViewClient(new Viewcl());
   browse.setWebChromeClient(new WebChromeClient(){

       @Override
       public void onProgressChanged(WebView view, int newProgress) {
         progessBar.setProgress(newProgress);


         if (newProgress==100){
             progessBar.setVisibility(View.GONE);
         }else{
             
             progessBar.setVisibility(View.VISIBLE);
         }
       }
   });


        WebSettings webSettings = browse.getSettings();
        webSettings.setJavaScriptEnabled(true);



        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_v = Urledit.getText().toString();
                if (!text_v.startsWith("http://"))
                    text_v = "http://"  + text_v;

                String url = text_v;
                browse.loadUrl(url);

                InputMethodManager IMM = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                IMM.hideSoftInputFromWindow(Urledit.getWindowToken() ,0);
            }
        });

     forward.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if(browse.canGoForward())
                                            browse.goForward();
                                    }
                                }
     );
        back.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           if(browse.canGoBack())
                                               browse.goBack();
                                       }
                                   }
        );

        reload.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           browse.reload();
                                       }
                                   }
        );
        clear.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                         browse.clearHistory();
                                       }
                                   }
        );



    }
}
