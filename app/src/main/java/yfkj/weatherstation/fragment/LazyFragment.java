package yfkj.weatherstation.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import yfkj.weatherstation.utils.ActivityUtils;
import yfkj.weatherstation.utils.LogUtils;

/**
 * @author zsl
 * @date 2019/11/22.
 */
public abstract class LazyFragment extends Fragment {
   ActivityUtils utils;

   @Override
   public void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public void onAttach(@NonNull Activity context) {
      super.onAttach(context);
      utils = new ActivityUtils(context);
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View root = inflater.inflate(getLayoutResource(),container,false);
      initView(root);
      return root;
   }


   @Override
   public void onDestroy() {
      super.onDestroy();
   }


   /**
    * 初始化资源控件
    *
    * @param view root
    */
   protected abstract void initView(View view);

   /**
    * 获取布局资源
    *
    * @return layout resource id
    */
   protected abstract int getLayoutResource();

}