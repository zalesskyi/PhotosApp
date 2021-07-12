package com.zalesskyi.photosapp.navigation

import androidx.navigation.NavController

interface AppNavProvider {

    fun getNavController(): NavController
}