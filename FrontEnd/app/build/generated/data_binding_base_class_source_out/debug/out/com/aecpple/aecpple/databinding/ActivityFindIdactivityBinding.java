// Generated by view binder compiler. Do not edit!
package com.aecpple.aecpple.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.aecpple.aecpple.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityFindIdactivityBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout LLFindid;

  @NonNull
  public final EditText etPutbirth;

  @NonNull
  public final EditText etPutname;

  @NonNull
  public final Button finishIdBtn;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView textView4;

  private ActivityFindIdactivityBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout LLFindid, @NonNull EditText etPutbirth, @NonNull EditText etPutname,
      @NonNull Button finishIdBtn, @NonNull ConstraintLayout main, @NonNull TextView textView4) {
    this.rootView = rootView;
    this.LLFindid = LLFindid;
    this.etPutbirth = etPutbirth;
    this.etPutname = etPutname;
    this.finishIdBtn = finishIdBtn;
    this.main = main;
    this.textView4 = textView4;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityFindIdactivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityFindIdactivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_find_idactivity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityFindIdactivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LL_findid;
      LinearLayout LLFindid = ViewBindings.findChildViewById(rootView, id);
      if (LLFindid == null) {
        break missingId;
      }

      id = R.id.et_putbirth;
      EditText etPutbirth = ViewBindings.findChildViewById(rootView, id);
      if (etPutbirth == null) {
        break missingId;
      }

      id = R.id.et_putname;
      EditText etPutname = ViewBindings.findChildViewById(rootView, id);
      if (etPutname == null) {
        break missingId;
      }

      id = R.id.finish_id_btn;
      Button finishIdBtn = ViewBindings.findChildViewById(rootView, id);
      if (finishIdBtn == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      return new ActivityFindIdactivityBinding((ConstraintLayout) rootView, LLFindid, etPutbirth,
          etPutname, finishIdBtn, main, textView4);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}