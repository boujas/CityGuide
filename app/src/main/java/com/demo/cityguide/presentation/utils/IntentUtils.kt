package com.demo.cityguide.presentation.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

fun openInstagram(context: Context, username: String) {
    val appIntent = Intent(
        Intent.ACTION_VIEW,
        "instagram://user?username=$username".toUri()
    )
    try {
        context.startActivity(appIntent)
    } catch (e: ActivityNotFoundException) {
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            "https://instagram.com/$username".toUri()
        )
        context.startActivity(webIntent)
    }
}

fun openGoogleMaps(context: Context, address: String) {
    val encoded = Uri.encode(address)
    val intent = Intent(
        Intent.ACTION_VIEW,
        "geo:0,0?q=$encoded".toUri()
    )
    intent.setPackage("com.google.android.apps.maps")
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        val webIntent =
            Intent(
                Intent.ACTION_VIEW,
                "https://maps.google.com/?q=$encoded".toUri()
            )
        context.startActivity(webIntent)
    }
}