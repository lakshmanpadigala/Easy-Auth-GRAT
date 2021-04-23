package com.example.graphicalpattern;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Random;

public class ImageAdapter extends BaseAdapter {

    public Context mContext;
    public static String []img ={"img1","img2","img3","img4","img5","img6","img7","img8","img9"};
    static int []arr;
            //={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6,R.drawable.img7,R.drawable.img8,R.drawable.img9};

    Random rand = new Random();
    int l=9;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
        //for(int i=0;i<arr.length;i++){
          //  arr[i] = arr1[i];
        //}
        //arr ->to ImageAdapter.arr******
        /*for(int i = 0; i < l; i++) {
            int randomIndexToSwap = rand.nextInt(ImageAdapter.arr.length);
            int temp = ImageAdapter.arr[randomIndexToSwap];
            ImageAdapter.arr[randomIndexToSwap] = ImageAdapter.arr[i];
            ImageAdapter.arr[i] = temp;
            String temp1= img[randomIndexToSwap];
            img[randomIndexToSwap]=img[i];
            img[i]=temp1;
        }*/
    }

    public void swap(){
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
    public static int[] arret(){
        return arr;
    }

    public void getType(int abc[]){
        arr=abc;
    }
}