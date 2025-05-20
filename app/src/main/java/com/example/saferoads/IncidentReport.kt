package com.example.saferoads

import android.os.Parcel
import android.os.Parcelable

data class IncidentReport(
    val date: String,
    val time: String,
    val place: String,
    val injuries: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(place)
        parcel.writeString(injuries)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<IncidentReport> {
            override fun createFromParcel(parcel: Parcel): IncidentReport {
                return IncidentReport(parcel)
            }

            override fun newArray(size: Int): Array<IncidentReport?> {
                return arrayOfNulls(size)
            }
        }
    }
}
