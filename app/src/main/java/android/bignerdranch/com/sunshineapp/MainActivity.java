package android.bignerdranch.com.sunshineapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragmenPlaceHold = new PlaceHolderFragment();
        FragmentTransaction fragmTrans = getFragmentManager().beginTransaction()
                .add(R.id.container_main_activity, fragmenPlaceHold);
        fragmTrans.commit();

    }
}

