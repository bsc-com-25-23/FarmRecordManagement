package com.example.farmrecordapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.farmrecordapp.ui.navigation.FarmRecordApp
import com.example.farmrecordapp.ui.theme.FARMRECORDAPPTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FARMRECORDAPPTheme {
                FarmRecordApp()
            }
        }
    }
}
