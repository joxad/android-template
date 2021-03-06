package com.joxad.androidtemplate.core.databindingutils;

import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.joxad.androidtemplate.core.view.list.FlingNestedScrollView;


/**
 * Created by josh on 26/03/16.
 */
public class Bindings {


    @BindingAdapter(value = {"imageUrl", "imageError"}, requireAll = false)
    public static void loadImage(ImageView view, String imageUrl, @Nullable Drawable imageError) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
    @BindingAdapter(value = "srcCompat")
    public static void setImage(AppCompatImageView view, Drawable imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    @BindingAdapter("android:typeface")
    public static void setTypeface(TextView v, String style) {
        switch (style) {
            case "normal":
                v.setTypeface(null, Typeface.NORMAL);
                break;
            case "bold":
                v.setTypeface(null, Typeface.BOLD);
                break;
            default:
                v.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }

    /**
     * ViewGroup.FOCUS_BLOCK_DESCENDANTS
     *
     * @param nestedScrollView
     * @param descendant
     */
    @BindingAdapter("descendant")
    public static void setDescendant(FlingNestedScrollView nestedScrollView, int descendant) {
        nestedScrollView.setDescendantFocusability(descendant);

    }

    @BindingAdapter({"android:enabled"})
    public static void loadImage(ToggleButton view, boolean enable) {
        view.setEnabled(enable);
    }


    @BindingAdapter("backgroundTint")
    public static void setBackgroundTint(final FloatingActionButton fab, final @ColorInt int bg_color_res) {
        fab.setBackgroundTintList(bg_color_res != 0 ? ColorStateList.valueOf(bg_color_res) : null);
    }

    @BindingAdapter("backgroundColor")
    public static void setBackgroundColor(final FloatingActionButton fab, final @ColorInt int bg_color) {
        fab.setBackgroundTintList(bg_color != 0 ? ColorStateList.valueOf(bg_color) : null);
    }

    @BindingAdapter({"animateTranslation", "translationY"})
    public static void translationY(final View view, final boolean show, final float translationY) {
        if (show & view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            view.setY(translationY);
            view.animate().translationY(0).setDuration(400).start();
        } else if (!show && view.getVisibility() == View.VISIBLE) {
            view.animate().translationY(translationY).setDuration(400).withEndAction(new Runnable() {
                @Override
                public void run() {
                    view.setVisibility(View.GONE);
                }
            }).start();
        }


    }


    @BindingAdapter({"animateHideScale"})
    public static void animateHideScale(View view, boolean hide) {
        view.animate().scaleX(hide ? 0 : 1).scaleY(hide ? 0 : 1).setDuration(200).start();
    }

    @BindingAdapter({"strike"})
    public static void addStrike(TextView view, boolean b) {
        if (b)
            view.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @BindingAdapter({"nestedScrollingEnabled"})
    public static void nestedScroll(RecyclerView rv, boolean b) {
        rv.setNestedScrollingEnabled(b);
    }


    @BindingAdapter({"android:drawableTop"})
    public static void setDrawableTop(TextView view, int image) {
        view.setCompoundDrawablesWithIntrinsicBounds(0, image, 0, 0);
        
        /*
         Drawable img = ContextCompat.getDrawable(view.getContext(), image);
         view.setCompoundDrawables(null, img, null, null);*/
    }

    @BindingAdapter("android:layout_marginTop")
    public static void setTopMargin(View view, int topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginEnd")
    public static void setEndMargin(View view, float endMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                (int) endMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:paddingTop")
    public static void setTopPadding(View view, int topMargin) {
        view.setPaddingRelative(view.getPaddingStart(), topMargin, view.getPaddingEnd(), view.getPaddingBottom());
    }

    @BindingAdapter("backgroundTint")
    public static void setBackgroundTint(Button button, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackgroundTintList(ColorStateList.valueOf(color));
        }
    }

}
