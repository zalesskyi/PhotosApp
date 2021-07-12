package com.zalesskyi.photosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.zalesskyi.photosapp.base.BaseActivity
import com.zalesskyi.photosapp.databinding.ActivityMainBinding
import com.zalesskyi.photosapp.navigation.AppNavProvider
import com.zalesskyi.photosapp.tools.view_binding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main), AppNavProvider {

    override fun getNavController(): NavController = findNavController(R.id.nav_host_fragment)
}