package com.example.quanlyguixe.screen.main;

import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.databinding.ActivityMainBinding;
import com.example.quanlyguixe.util.base.BaseActivity;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private AppBarConfiguration mAppBarConfiguration;

    //private TicketViewModel ticketViewModel;

    @Override
    public ActivityMainBinding inflateViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    //Khởi tạo màn hình, dữ liệu
    @Override
    protected void initScreenData() {
        //Khởi tạo các ActivityViewModel
        //ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);

        //Khởi tạo thanh tiêu đề
        setSupportActionBar(viewBinding.appBarMain.toolbar);
        initNavigationDrawer();
        //updateTitleToolBar("Quản lý nhà xe");
    }

    // Thêm các sự kiện
    @Override
    protected void addEvent() {

    }

    // Gán các biến ViewModel tiện cho việc cập nhật dữ liệu
    @Override
    protected void bindToViewModel() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    private void initNavigationDrawer() {
        DrawerLayout drawer = viewBinding.drawerLayout;
        NavigationView navigationView = viewBinding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration =
                new AppBarConfiguration.Builder(
                        R.id.nav_home,
                        R.id.nav_list_employee,
                        R.id.nav_check_in_out_vehicle_title,
                        R.id.nav_list_tickets,
                        R.id.nav_list_parking_lots,
                        R.id.nav_reports_detail
                )
                        .setOpenableLayout(drawer)
                        .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        viewBinding.navView.setNavigationItemSelectedListener(item -> {
            navController.popBackStack(R.id.nav_home, false);

            int id = item.getItemId();

            if (id == R.id.nav_check_in_out_vehicle_title) {
                navController.navigate(R.id.action_nav_home_to_checkInOutVehicleFragment);
            } else if (id == R.id.nav_reports_detail) {
                navController.navigate(R.id.action_nav_home_to_revenueReportFragment);
            } else if (id == R.id.nav_list_tickets) {
                navController.navigate(R.id.action_nav_home_to_nav_list_tickets);
            } else if (id == R.id.nav_list_employee){
                navController.navigate(R.id.action_nav_home_to_nav_list_employee);
            } else if (id == R.id.nav_list_parking_lots){
                navController.navigate(R.id.action_nav_home_to_parkingLotsFragment);
            }

 //           else if(R.id.nav_list_shift_manager == id1){
//                navController.navigate(R.id.action_nav_home_to_nav_list_shift_manager);
//            }else if(R.id.nav_checkin_checkout_vehicle == id1){
//                navController.navigate(R.id.action_nav_home_to_nav_checkin_checkout_vehicle);
//            }
            viewBinding.drawerLayout.closeDrawers();
            return true;
        });
    }

    public void updateTitleToolBar(String title) {
        viewBinding.appBarMain.toolbar.setTitle(title);
    }
}