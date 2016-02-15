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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

        HttpURLConnection urlConnection = null;
        BufferedReader readr = null;

        //  Будет храниться JSON ответ как строка
        String forecastJsonStr = null;

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&units=metric&cnt=7&APPID=fc98f6f48ac0446ca20c391650c87479");
       //Создаем запрос к OpenWeatherMap, и открываем соединение
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Читаем входной потов в String
            InputStream inpuStream = urlConnection.getInputStream();
            StringBuffer buff = new StringBuffer();

            if (inpuStream == null){
                //Ничего не делаем
                forecastJsonStr = null;
            }
            readr = new BufferedReader(new InputStreamReader(inpuStream));

            String linee;

            while ((linee = readr.readLine())!=null){
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buff.append(linee + "\n");
            }
            if (buff.length()==0){
                //Поток был пустой.Нет точек для парсинга
                forecastJsonStr = null;
            }
            forecastJsonStr = buff.toString();

        }catch (IOException ee){
            Log.e("PlaceHolderFragment","Error",ee);
            //Если когд не получит успешно данные о погоде, здесь не указываются попытки распарсить это
            forecastJsonStr = null;
        } finally {
            if (urlConnection !=null){
                urlConnection.disconnect();
            }
            if (readr !=null){
                try {
                    readr.close();
                }catch (final IOException ee){
                    Log.e("PlaceHolderFragment","Error closing stream", ee);
                }
            }
        }

        return rootView;
    }
}
