/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.admanager.AdManagerAdView

class FlowerAdapter(val flowerList: Array<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TYPE_FLOWER: Int = 0
    val TYPE_AD: Int = 1

    // Describes an item view and its place within the RecyclerView
    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val flowerTextView: TextView = itemView.findViewById(R.id.flower_text)

        fun bind(word: String) {
            flowerTextView.text = word
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_FLOWER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.flower_item, parent, false)

            return FlowerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ad_item, parent, false)

            return AdViewHolder(view)
        }
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return flowerList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position % 5 == 0) {
            (holder as AdViewHolder).bind(flowerList[position])
        } else {
            (holder as FlowerViewHolder).bind(flowerList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 5 == 0) {
            return TYPE_AD
        } else {
            return TYPE_FLOWER
        }

    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder is AdViewHolder) {
            // Clear ads allocations
            val adView: AdManagerAdView? = holder.getAdView()
            val parent = adView?.parent
            if (parent is ViewGroup) {
                parent.removeView(adView)
            }
            adView?.removeAllViews()
            adView?.destroy()
        }
    }
}