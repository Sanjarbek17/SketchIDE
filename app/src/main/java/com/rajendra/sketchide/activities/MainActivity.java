package com.rajendra.sketchide.activities;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rajendra.sketchide.R;
import com.rajendra.sketchide.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CoordinatorLayout coordinator;
    private FragmentManager fragmentManager = null;

    Toolbar toolbar;
    FloatingActionButton createNewProjectFloatingBtn;

    MenuItem lastCheckedItem; // Keep track of the last checked item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = binding.toolbar; // Initialize the toolbar

        setSupportActionBar(toolbar);

        // Testing inProgress
        createNewProjectFloatingBtn = binding.createNewProject;
        binding.createNewProject.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.myproject_dialog);
            EditText edittextAppName = dialog.findViewById(R.id.edittext_app_name);
            TextView dialogCancel = dialog.findViewById(R.id.dialog_cancel);
            TextView dialogOkay = dialog.findViewById(R.id.dialog_okay);
            dialogCancel.setOnClickListener(v1 -> {
                // Your code to handle button click goes here
                Toast.makeText(v1.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

            dialogOkay.setOnClickListener(v12 -> {
                // Your code to handle button click goes here
                Toast.makeText(v12.getContext(),"Okay",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

            // Dialog Size Match_Parent
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);


            dialog.show();
        });




        // Set click listener for the navigation icon
        binding.toolbar.setNavigationOnClickListener(v -> binding.drawerLayout.open());


        // Set item selected listener for navigation view
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            // Handle menu item selected
            if (lastCheckedItem != null) {
                lastCheckedItem.setChecked(true);
            }

            // Check the current item
            menuItem.setChecked(true);
            lastCheckedItem = menuItem;

            //Drawer Item Click Action

            int ItemId = menuItem.getItemId();

            if (ItemId == R.id.drawer_about) {
                // Define the URL of the external link
                String url = "https://github.com/androidbulb/SketchIDE/tree/Design"; // Replace this with your desired URL
                // Create an intent with ACTION_VIEW action and the URL data
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                // Start the activity
                startActivity(intent);
            }
            if (ItemId == R.id.drawer_settings) {
                Toast.makeText(MainActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            }
            if (ItemId == R.id.drawer_information) {
                Toast.makeText(MainActivity.this, "Information Clicked", Toast.LENGTH_SHORT).show();
            }
            if (ItemId == R.id.drawer_tools) {
                Toast.makeText(MainActivity.this, "Tools Clicked", Toast.LENGTH_SHORT).show();
            }
            if (ItemId == R.id.drawer_sign) {
                Toast.makeText(MainActivity.this, "Sign Clicked", Toast.LENGTH_SHORT).show();
            }

            binding.drawerLayout.close();
            return true;
        });
        //MyProjectFragment Call
        Fragment fragment = getSupportFragmentManager().findFragmentById(androidx.fragment.R.id.fragment_container_view_tag);
        coordinator = findViewById(R.id.layout_coordinator);

    }
// Home OptionMenu
    public boolean onCreateOptionsMenu(Menu arg0) {
        super.onCreateOptionsMenu(arg0);
        getMenuInflater().inflate(R.menu.menu_items, arg0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_bar) {
            // Handle search bar click
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            // Add your desired search functionality here
            return true;
        } else if (id == R.id.menu) {
            // Handle menu click
            Toast.makeText(this, "Sort clicked", Toast.LENGTH_SHORT).show();
            // Add your desired sorting functionality here
            return true;
        } else if (id == R.id.create_project) {
            // Handle create project click
            Toast.makeText(this, "Create project clicked", Toast.LENGTH_SHORT).show();
            // Add your desired project creation functionality here
            return true;
        } else if (id == R.id.contribute) {
            String url = "https://github.com/androidbulb/SketchIDE/tree/Design";
            Intent intentContribute = new Intent(Intent.ACTION_VIEW);
            intentContribute.setData(Uri.parse(url));
            startActivity(intentContribute);
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}