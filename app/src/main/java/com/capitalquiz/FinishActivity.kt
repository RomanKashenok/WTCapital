package com.capitalquiz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import android.os.Bundle
import android.view.View
import com.capitalquiz.quiz.QuizDataHolder
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import kotlinx.android.synthetic.main.activity_result.*


class FinishActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        MobileAds.initialize(this, "ca-app-pub-1867687838298230~9902359578")

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        resultText.text = "You answered correctly ${QuizDataHolder.correctAnswers} of  ${QuizDataHolder.gamesLimit}"
        replay.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
      val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        QuizDataHolder.correctAnswers = 0
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