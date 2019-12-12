package com.wyl.wegame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.tencent.bugly.beta.Beta

class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Beta.checkUpgrade(false, false)

        setupActionBarWithNavController(navController)

//        navController.addOnDestinationChangedListener()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
