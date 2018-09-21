package ilgulee.com.flickrokhttpjsonglide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CardRecyclerViewAdapter mCardRecyclerViewAdapter;
    private RecyclerView mRecyclerView;
    private List<Photo> mPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String baseUrl = "https://api.flickr.com/services/feeds/photos_public.gne";
        String searchCriteria = "twice,mina";
        boolean matchAll = true;
        String language = "en-us";

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
        String url = urlBuilder.addQueryParameter("tags", searchCriteria)
                .addQueryParameter("tagmode", matchAll ? "ALL" : "ANY")
                .addQueryParameter("lang", language)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .build().toString();

        final Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                final String responseData = response.body().string();
                Log.d(TAG, "onResponse: responseData " + responseData);

                mPhotos = new ArrayList<>();
                try {
                    JSONObject jsonData = new JSONObject(responseData);
                    JSONArray itemsArray = jsonData.getJSONArray("items");

                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                        String title = jsonPhoto.getString("title");
                        String author = jsonPhoto.getString("author");
                        String authorId = jsonPhoto.getString("author_id");
                        String link = jsonPhoto.getString("link");
                        String tags = jsonPhoto.getString("tags");
                        JSONObject media = jsonPhoto.getJSONObject("media");
                        String smallImageURL = media.getString("m");
                        String largeImageURL = smallImageURL.replaceFirst("_m", "_b");

                        Photo photo = new Photo(title, author, authorId, link, smallImageURL, tags, largeImageURL);
                        mPhotos.add(photo);
                        Log.d(TAG, "onResponse: " + photo.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onResponse: " + e.getMessage());
                }

                MainActivity.this.runOnUiThread(() -> {
                    mCardRecyclerViewAdapter = new CardRecyclerViewAdapter(MainActivity.this, mPhotos);
                    mRecyclerView = findViewById(R.id.recyclerView);
                    mRecyclerView.setAdapter(mCardRecyclerViewAdapter);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
                    mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                });
            }
        });
    }
}
