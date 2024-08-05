package com.afoxplus.module.demo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIKitTheme {
                UIKitText(text = "Hello World!")
            }
        }
    }
}