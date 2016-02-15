package android.bignerdranch.com.sunshineapp;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PlaceHolderFragment extends Fragment {

    public final String TAG="PlaceHolderFragment";
    public PlaceHolderFragment(){

    }
    public String[] sunList={
            "Today-Sunny-80/65",
            "Tomorrow-Foggy-70/45",
            "Weds-Cloudly-72/66",
            "Thrus-Rainy-64/52",
            "Fri-Foggy-60/44",
            "Sat-Sunny-77/65"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =inflater.inflate(R.layout.fragment_main,container,false);

        TextView textvv = (TextView)rootView.findViewById(R.id.list_item_forecast_textview);

        ImageView imgview =(ImageView)rootView.findViewById(R.id.imageView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity()
                ,R.layout.list_item_forecast
                ,R.id.list_item_forecast_textview
                ,sunList);

        //   textvv.setTextColor(getResources().getColor(R.color.colorGray_2));

        ListView list =(ListView)rootView.findViewById(R.id.list_view_for_cast);
       
        list.setAdapter(adapter);

        Log.d(TAG,"PlaceHolderFragment работа фрагмеента");

        
        return rootView;
    }
}
