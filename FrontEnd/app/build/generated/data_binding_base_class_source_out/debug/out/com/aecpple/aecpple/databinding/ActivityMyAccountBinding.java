// Generated by view binder compiler. Do not edit!
package com.aecpple.aecpple.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityMyAccountBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final LinearLayout LLAccount;

  @NonNull
  public final Button btnGoback;

  @NonNull
  public final ConstraintLayout main;

  @NonNull
  public final TextView tvHandicap;

  @NonNull
  public final TextView tvUserbirth;

  @NonNull
  public final TextView tvUserid;

  @NonNull
  public final TextView tvUsername;

  private ActivityMyAccountBinding(@NonNull ConstraintLayout rootView,
      @NonNull LinearLayout LLAccount, @NonNull Button btnGoback, @NonNull ConstraintLayout main,
      @NonNull TextView tvHandicap, @NonNull TextView tvUserbirth, @NonNull TextView tvUserid,
      @NonNull TextView tvUsername) {
    this.rootView = rootView;
    this.LLAccount = LLAccount;
    this.btnGoback = btnGoback;
    this.main = main;
    this.tvHandicap = tvHandicap;
    this.tvUserbirth = tvUserbirth;
    this.tvUserid = tvUserid;
    this.tvUsername = tvUsername;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMyAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMyAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_my_account, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMyAccountBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.LL_account;
      LinearLayout LLAccount = ViewBindings.findChildViewById(rootView, id);
      if (LLAccount == null) {
        break missingId;
      }

      id = R.id.btn_goback;
      Button btnGoback = ViewBindings.findChildViewById(rootView, id);
      if (btnGoback == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      id = R.id.tv_handicap;
      TextView tvHandicap = ViewBindings.findChildViewById(rootView, id);
      if (tvHandicap == null) {
        break missingId;
      }

      id = R.id.tv_userbirth;
      TextView tvUserbirth = ViewBindings.findChildViewById(rootView, id);
      if (tvUserbirth == null) {
        break missingId;
      }

      id = R.id.tv_userid;
      TextView tvUserid = ViewBindings.findChildViewById(rootView, id);
      if (tvUserid == null) {
        break missingId;
      }

      id = R.id.tv_username;
      TextView tvUsername = ViewBindings.findChildViewById(rootView, id);
      if (tvUsername == null) {
        break missingId;
      }

      return new ActivityMyAccountBinding((ConstraintLayout) rootView, LLAccount, btnGoback, main,
          tvHandicap, tvUserbirth, tvUserid, tvUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
