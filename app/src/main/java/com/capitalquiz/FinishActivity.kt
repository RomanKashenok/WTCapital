package com.capitalquiz

import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView


class FinishActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        MobileAds.initialize(this, "ca-app-pub-1867687838298230~9902359578")

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    public override fun onPause() {
        mAdView.pause()
        super.onPause()
    }


    public override fun onResume() {
        super.onResume()
        mAdView.resume()
    }


    public override fun onDestroy() {
        mAdView.destroy()
        super.onDestroy()
    }
}