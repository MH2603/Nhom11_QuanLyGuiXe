package com.example.quanlyguixe.di;

import android.app.Activity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.quanlyguixe.R;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class AppModule {

    @Provides
    public NavController provideNavController(Activity activity) {
        return Navigation.findNavController(activity, R.id.nav_host_fragment_content_main);
    }
}
