package com.nonamer777.madlevel7task2.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nonamer777.madlevel7task2.R

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        else -> super.onOptionsItemSelected(item)
    }
}
