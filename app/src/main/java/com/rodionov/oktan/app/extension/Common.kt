package com.rodionov.oktan.app.extension

import android.content.ContentUris
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodionov.oktan.app.FuelStationApp
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File
import java.security.MessageDigest

import java.util.*

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dpToPx: Float
    get() = (this * Resources.getSystem().displayMetrics.density)


//fun Long.toDateFormatGmt(context: Context, pattern: String = "dd MMM yyyy"): String =
//    SimpleDateFormat(pattern, Locale(LocaleHelper.getLanguage(context).toString()))
//        .apply { timeZone = TimeZone.getTimeZone("GMT") }
//        .format(this.checkUnixTimeStamp())
//        .let {
//            return@let if (LocaleHelper.getLanguage(context).toString() == "ru") it.plus(" г.") else it
//        }

fun Long.checkUnixTimeStamp() = if (this.toString().length <= 10) this * 1000L else this

fun Long.toUnixTimeStamp() = this / 1000

fun Double.formatWithDot()  = String.format(Locale.US, "%.5f", this)

fun Double.equalsValues(value: Double?) = "%.7f".format(this) == "%.7f".format(value)

fun <T> AbsDelegationAdapter<T>.setData(data: T) {
    items = data
    notifyDataSetChanged()
}

fun <T> AbsDelegationAdapter<T>.setSingleItem(item: T, index: Int) {
    var record = items.takeIf {
        it == item
    }
    record = item
    notifyDataSetChanged()
    notifyItemChanged(index)
}

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
    }
}

val Uri.realName: String get() {
    try {
        val projection = arrayOf(OpenableColumns.DISPLAY_NAME)
        FuelStationApp.context.contentResolver
            .query(this, projection, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                    return cursor.getString(index)
                }
            }
    } catch (ignore: java.lang.Exception) {}
    return lastPathSegment ?: "undefined"
}

val Uri.realPath: String? get() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(FuelStationApp.context, this)) {
        val docId = DocumentsContract.getDocumentId(this)
        when {
            isExternalStorageDocument -> {
                val split = docId.split(":")
                val type = split[0]
                val path = split[1]
                if ("primary".equals(type, ignoreCase = true)) {
                    return "${Environment.getExternalStorageDirectory()}/$path"
                } //else "/storage/$type/$path"
            }
            isDownloadsDocument -> {
                val contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), docId.toLong())
                return contentUri.getDataColumn(null, null)
            }
            isMediaDocument -> {
                val split = docId.split(":")
                val contentUri = when(split[0]) {
                    "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                    else -> return null
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return contentUri.getDataColumn(selection, selectionArgs)
            }
        }
    } else if ("content".equals(scheme, ignoreCase = true)) {
        return getDataColumn(null, null)
    } else if ("file".equals(scheme, ignoreCase = true)) {
        return path
    }
    return null
}

val Uri.isExternalStorageDocument: Boolean get() = "com.android.externalstorage.documents" == authority
val Uri.isDownloadsDocument: Boolean get() = "com.android.providers.downloads.documents" == authority
val Uri.isMediaDocument: Boolean get() = "com.android.providers.media.documents" == authority

fun Uri.getDataColumn(selection: String? = null, selectionArgs: Array<String>? = null): String? {
    val column = "_data"
    val projection = arrayOf(column)
    try {
        FuelStationApp.context.contentResolver.query(this, projection, selection, selectionArgs, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                val value = cursor.getString(index)
                if (value.startsWith("content://") || !value.startsWith("/") && !value.startsWith("file://")) {
                    return null
                }
                return value
            }
        }
    } catch (ignore: Exception) {}
    return null
}

val Uri.realSize: Long get() {
    val projection = arrayOf(OpenableColumns.SIZE)
    FuelStationApp.context
        .contentResolver.query(this, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(OpenableColumns.SIZE)
                return cursor.getLong(index)
            }
        }
    return File(this.path).length()
}

val Uri.mimeType: String get() {
    val extension = MimeTypeMap.getFileExtensionFromUrl(path)
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension) ?: "file/*"
}

val Uri.mediaType: MediaType? get() {
    return mimeType.toMediaTypeOrNull()
}

val Uri.sha256: String get() {
    FuelStationApp.context.contentResolver.openInputStream(this)?.use {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(it.readBytes().toString(charset("ISO-8859-1")).toByteArray(charset("UTF-8")))
        val hexString = StringBuffer()
        for (byt in bytes) {
            val hex = Integer.toHexString(0xff and byt.toInt())
            if (hex.length == 1) hexString.append('0')
            hexString.append(hex)
        }
        return hexString.toString()
    }
    throw Exception("Не удалось вычислить Sha256")
}

fun Resources.getVectorDrawable(resource: Int, theme: Resources.Theme?) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getDrawable(resource, theme)
    } else {
        VectorDrawableCompat.create(this, resource, theme)
    }

inline fun <reified T> Gson.fromJson(json: String) =
        this.fromJson<T>(json, object : TypeToken<T>() {}.type)