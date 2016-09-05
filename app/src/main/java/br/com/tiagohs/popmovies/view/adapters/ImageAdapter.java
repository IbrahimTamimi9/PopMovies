package br.com.tiagohs.popmovies.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import br.com.tiagohs.popmovies.R;
import br.com.tiagohs.popmovies.model.dto.ImageDTO;
import br.com.tiagohs.popmovies.util.ImageUtils;
import br.com.tiagohs.popmovies.util.enumerations.ImageSize;
import br.com.tiagohs.popmovies.view.callbacks.ImagesCallbacks;

/**
 * Created by Tiago Henrique on 28/08/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<ImageDTO> mImages;
    private Context mContext;
    private ImagesCallbacks mCallbacks;

    public ImageAdapter(Context context, List<ImageDTO> images, ImagesCallbacks callbacks) {
        this.mContext = context;
        this.mImages = images;
        this.mCallbacks = callbacks;
    }

    public void setImages(List<ImageDTO> images) {
        mImages = images;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_wallpapers_movies, parent, false);

        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bindImage(mImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageArtwork;
        private ProgressWheel mProgress;

        private ImageDTO mImage;

        public ImageViewHolder(View itemView) {
            super(itemView);

            mImageArtwork = (ImageView) itemView.findViewById(R.id.image_movie_poster_movie);
            mProgress = (ProgressWheel) itemView.findViewById(R.id.image_item_movie_progress);

            itemView.setOnClickListener(this);
        }

        public void bindImage(ImageDTO image) {
            this.mImage = image;

            ImageUtils.load(mContext, mImage.getImagePath(), mImageArtwork, R.drawable.placeholder_images_default, R.drawable.placeholder_images_default, ImageSize.BACKDROP_300, mProgress);
        }

        @Override
        public void onClick(View view) {
            mCallbacks.onClickImage(mImage);
        }
    }


}