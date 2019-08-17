package app.android.sa.neverhaveiever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.android.sa.neverhaveiever.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    AdView mAdview;
    private Button button;
    private String[] names;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //ads and shit like that u know
        MobileAds.initialize(this, "ca-app-pub-8256988727898497~9122979342");
        mAdview = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);

        //ADS
        MobileAds.initialize(this, "ca-app-pub-8256988727898497~9122979342");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-8256988727898497/4116450272");
        AdRequest request = new AdRequest.Builder().build();
        interstitialAd.loadAd(request);
        interstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });

        //show next "i have never" after button click.
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Get next index
                names = getResources().getStringArray(R.array.questions);
                int randomIndex = new Random().nextInt(names.length);
                String randomList = names[randomIndex];
                TextView textView = (TextView) findViewById(R.id.textView2);
                textView.setText(randomList);
            }
        };

        nextButton = (Button) findViewById(R.id.button1);
        nextButton.setOnClickListener(listener);


        //End game
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    private void openMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}

