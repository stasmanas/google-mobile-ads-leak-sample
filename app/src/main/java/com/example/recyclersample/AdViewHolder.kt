package com.example.recyclersample

import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView

class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val adContainer: FrameLayout = itemView.findViewById(R.id.ad_container)
    private val adViewTag: String = "AD_VIEW"

    fun bind(data: String) {
        val adRequest: AdManagerAdRequest = AdManagerAdRequest.Builder().build()
        val adView = AdManagerAdView(itemView.context)
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
            }

            override fun onAdFailedToLoad(errorCode: Int) {
            }
        }
        adView.tag = adViewTag
        adContainer.addView(adView, 0)
        adView.setAdSizes(AdSize.BANNER)
        adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
        adView.loadAd(adRequest)
    }

    fun getAdView(): AdManagerAdView? = itemView.findViewWithTag(adViewTag);
}