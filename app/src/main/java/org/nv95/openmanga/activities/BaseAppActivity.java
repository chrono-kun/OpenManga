package org.nv95.openmanga.activities;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

/**
 * Created by nv95 on 19.02.16.
 */
public abstract class BaseAppActivity extends AppCompatActivity {
    private boolean mActionBarVisible = false;
    private boolean mHomeAsUpEnabled = false;

    public void enableHomeAsUp() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !mHomeAsUpEnabled) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mHomeAsUpEnabled = true;
        }
    }

    public void disableTitle() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        mActionBarVisible = true;
    }

    public void setSupportActionBar(@IdRes int toolbarId) {
        setSupportActionBar((Toolbar) findViewById(toolbarId));
    }

    public void hideActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && mActionBarVisible) {
            mActionBarVisible = false;
            actionBar.hide();
        }
    }

    public void showActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && !mActionBarVisible) {
            mActionBarVisible = true;
            actionBar.show();
        }
    }

    public void toggleActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (!mActionBarVisible) {
                mActionBarVisible = true;
                actionBar.show();
            } else {
                mActionBarVisible = false;
                actionBar.hide();
            }
        }
    }

    public boolean isActionBarVisible() {
        return mActionBarVisible;
    }

    public void setSubtitle(@Nullable CharSequence subtitle) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

    public void setSubtitle(@StringRes int subtitle) {
        setSubtitle(getString(subtitle));
    }

    public void enableTransparentStatusBar(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            final Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            if (color != 0) {
                window.setStatusBarColor(ContextCompat.getColor(this, color));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && mHomeAsUpEnabled) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
