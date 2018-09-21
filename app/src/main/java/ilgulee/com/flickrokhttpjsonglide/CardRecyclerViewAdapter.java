package ilgulee.com.flickrokhttpjsonglide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = CardRecyclerViewAdapter.class.getSimpleName();

    private Context mContext;
    private List<Photo> mPhotos;

    public CardRecyclerViewAdapter(Context context, List<Photo> photos) {
        mContext = context;
        mPhotos = photos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: starts");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grid, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .asBitmap()
                .load(mPhotos.get(position).getSmallImageURL())
                .apply(requestOptions)
                .into(viewHolder.mImageView);
        viewHolder.mTextView.setText(mPhotos.get(position).getTitle());
        viewHolder.mRelativeLayout.setOnClickListener(view -> {
            Log.d(TAG, "onBindViewHolder: onClick" + mPhotos.get(position).getTitle());
            Toast.makeText(mContext, mPhotos.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//            Intent intent =new Intent(mContext,PhotoDetailActivity.class);
////            intent.putExtra("image_url",mPhotos.get(position).getLargeImageURL());
////            intent.putExtra("image_author",mPhotos.get(position).getAuthor());
////            intent.putExtra("image_title",mPhotos.get(position).getTitle());
////            intent.putExtra("image_tag",mPhotos.get(position).getTags());
////            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ((mPhotos!=null)&&(mPhotos.size()!=0))?mPhotos.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ViewHolder";
        final ImageView mImageView;
        final TextView mTextView;
        final RelativeLayout mRelativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.imageViewCard);
            this.mTextView = itemView.findViewById(R.id.textViewCard);
            this.mRelativeLayout = itemView.findViewById(R.id.cardListView_parent);
        }
    }
}
