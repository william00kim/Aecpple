// Generated by view binder compiler. Do not edit!
package com.aecpple.aecpple.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.aecpple.aecpple.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFitnessinfolistBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FrameLayout Label;

  @NonNull
  public final BottomNavigationView bottomNav;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final FrameLayout frameLayout;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final RecyclerView name;

  @NonNull
  public final TextView textView;

  private ActivityFitnessinfolistBinding(@NonNull ConstraintLayout rootView,
      @NonNull FrameLayout Label, @NonNull BottomNavigationView bottomNav,
      @NonNull ConstraintLayout constraintLayout, @NonNull FrameLayout frameLayout,
      @NonNull LinearLayout linearLayout, @NonNull ConstraintLayout main,
      @NonNull RecyclerView name, @NonNull TextView textView) {
    this.rootView = rootView;
    this.Label = Label;
    this.bottomNav = bottomNav;
    this.constraintLayout = constraintLayout;
    this.frameLayout = frameLayout;
    this.linearLayout = linearLayout;
    this.main = main;
    this.name = name;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFitnessinfolistBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFitnessinfolistBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_fitnessinfolist, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFitnessinfolistBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Label;
      FrameLayout Label = ViewBindings.findChildViewById(rootView, id);
      if (Label == null) {
        break missingId;
      }

      id = R.id.bottomNav;
      BottomNavigationView bottomNav = ViewBindings.findChildViewById(rootView, id);
      if (bottomNav == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.frameLayout;
      FrameLayout frameLayout = ViewBindings.findChildViewById(rootView, id);
      if (frameLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.name;
      RecyclerView name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivityFitnessinfolistBinding((ConstraintLayout) rootView, Label, bottomNav,
          constraintLayout, frameLayout, linearLayout, main, name, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
