package com.example.appmusickotlin.ui.home

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.appmusickotlin.R
import com.example.appmusickotlin.ui.home.Fragment.HomeFragment
import com.example.appmusickotlin.ui.home.Fragment.LibraryFragment
import com.example.appmusickotlin.ui.home.Fragment.PlayListsFragment
import com.example.appmusickotlin.broadcastReceivers.BluetoothReceiver
import com.example.appmusickotlin.databinding.ActivityHomeScreenBinding
import java.util.Locale

class HomeScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeScreenBinding
    private lateinit var bluetoothReceiver: BluetoothReceiver


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("language_key", Locale.getDefault().language)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val defaultFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, defaultFragment).commit()


        bluetoothReceiver()


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        fragmentCheckManager()


    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bluetoothReceiver)
    }

    private fun fragmentCheckManager() {

        // màn mặc đinh
        val fragmentManager = supportFragmentManager
        val fragmentCount = fragmentManager.backStackEntryCount


        if (fragmentCount == 0) {
            val fragment = HomeFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack("HomeFragment")
            transaction.commit()
        }


        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.btnHome -> {
                    navigateToFragment(HomeFragment())
                    true
                }

                R.id.btnLibrary -> {
                    navigateToFragment(LibraryFragment())
                    // Xử lý khi nút Library được chọn
                    true
                }

                R.id.btnPlaylist -> {
                    navigateToFragment(PlayListsFragment())
                    // Xử lý khi nút Playlist được chọn
                    true
                }

                else -> false
            }
        }


    }
    // Phương thức để thay đổi Fragment trong BottomNavigationView
     public fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun bluetoothReceiver() {

        bluetoothReceiver = BluetoothReceiver()

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        }
        registerReceiver(bluetoothReceiver, intentFilter)
    }



}