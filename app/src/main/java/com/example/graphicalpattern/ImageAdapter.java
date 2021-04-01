package com.example.graphicalpattern;

        import android.content.Context;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;

        import java.sql.Array;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Collections;
        import java.util.Random;
        import androidx.gridlayout.widget.GridLayout;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    int []arr={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9};

    Random rand = new Random();
    int l=arr.length;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;

        for(int i = 0; i < l; i++) {
            int randomIndexToSwap = rand.nextInt(arr.length);
            int temp = arr[randomIndexToSwap];
            arr[randomIndexToSwap] = arr[i];
            arr[i] = temp;
        }

    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return arr[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(arr[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(340,350));

        return imageView;
    }
}

