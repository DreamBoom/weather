package yfkj.weatherstation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * @author zsl
 * @date 2019/11/22.
 */
public class CustomViewPager extends ViewPager {
   public CustomViewPager(@NonNull Context context) {
      super(context);
   }

   public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
      int height = 0;
      for (int i = 0,len = getChildCount(); i < len; i++) {
         View child = getChildAt(i);
         ViewGroup.LayoutParams lp = child.getLayoutParams();
         int childWidth = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
         int childHeight = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
         child.measure(childWidth,childHeight);
         int measuredHeight = child.getMeasuredHeight();
         height = Math.max(measuredHeight,height);
      }
      heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);
      
   }
}