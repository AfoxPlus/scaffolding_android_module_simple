package com.afoxplus.module.demo.global

import android.content.Intent
import com.afoxplus.demo_config.delivery.flow.StartDemoFlow
import com.afoxplus.module.demo.ui.MainActivity
import com.afoxplus.uikit.activities.UIKitBaseActivity
import javax.inject.Inject

class SampleStartDemoFlow @Inject constructor() : StartDemoFlow {
    override fun start(activity: UIKitBaseActivity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
    }
}