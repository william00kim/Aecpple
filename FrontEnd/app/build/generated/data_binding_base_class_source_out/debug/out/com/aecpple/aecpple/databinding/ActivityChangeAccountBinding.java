// Generated by view binder compiler. Do not edit!
package com.aecpple.aecpple.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.aecpple.aecpple.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityChangeAccountBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout LLChange;

  @NonNull
  public final Button btnChange;

  @NonNull
  public final RadioButton btnDisable2;

  @NonNull
  public final RadioButton btnNotdisable2;

  @NonNull
  public final EditText etChangebirth;

  @NonNull
  public final EditText etChangeid;

  @NonNull
  public final EditText etChangename;

  @NonNull
  public final ConstraintLayout main;

  private ActivityChangeAccountBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout LLChange, @NonNull Button btnChange, @NonNull RadioButton btnDisable2,
      @NonNull RadioButton btnNotdisable2, @NonNull EditText etChangebirth,
      @NonNull EditText etChangeid, @NonNull EditText etChangename,
      @NonNull ConstraintLayout main) {
    this.rootView = rootView;
    this.LLChange = LLChange;
    this.btnChange = btnChange;
    this.btnDisable2 = btnDisable2;
    this.btnNotdisable2 = btnNotdisable2;
    this.etChangebirth = etChangebirth;
    this.etChangeid = etChangeid;
    this.etChangename = etChangename;
    this.main = main;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityChangeAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityChangeAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_change_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityChangeAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LL_change;
      LinearLayout LLChange = ViewBindings.findChildViewById(rootView, id);
      if (LLChange == null) {
        break missingId;
      }

      id = R.id.btn_change;
      Button btnChange = ViewBindings.findChildViewById(rootView, id);
      if (btnChange == null) {
        break missingId;
      }

      id = R.id.btn_disable2;
      RadioButton btnDisable2 = ViewBindings.findChildViewById(rootView, id);
      if (btnDisable2 == null) {
        break missingId;
      }

      id = R.id.btn_notdisable2;
      RadioButton btnNotdisable2 = ViewBindings.findChildViewById(rootView, id);
      if (btnNotdisable2 == null) {
        break missingId;
      }

      id = R.id.et_changebirth;
      EditText etChangebirth = ViewBindings.findChildViewById(rootView, id);
      if (etChangebirth == null) {
        break missingId;
      }

      id = R.id.et_changeid;
      EditText etChangeid = ViewBindings.findChildViewById(rootView, id);
      if (etChangeid == null) {
        break missingId;
      }

      id = R.id.et_changename;
      EditText etChangename = ViewBindings.findChildViewById(rootView, id);
      if (etChangename == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      return new ActivityChangeAccountBinding((ConstraintLayout) rootView, LLChange, btnChange,
          btnDisable2, btnNotdisable2, etChangebirth, etChangeid, etChangename, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
