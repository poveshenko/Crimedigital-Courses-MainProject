package com.example.matchresults.model

import android.os.Parcel
import android.os.Parcelable

data class MatchModel(
    val AwayTeam: String,
    val AwayTeamScore: Int,
    val DateUtc: String,
    val Group: Any,
    val HomeTeam: String,
    val HomeTeamScore: Int,
    val Location: String,
    val MatchNumber: Int,
    val RoundNumber: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readValue(Any::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(AwayTeam)
        parcel.writeInt(AwayTeamScore)
        parcel.writeString(DateUtc)
        parcel.writeValue(Group)
        parcel.writeString(HomeTeam)
        parcel.writeInt(HomeTeamScore)
        parcel.writeString(Location)
        parcel.writeInt(MatchNumber)
        parcel.writeInt(RoundNumber)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchModel> {
        override fun createFromParcel(parcel: Parcel): MatchModel {
            return MatchModel(parcel)
        }

        override fun newArray(size: Int): Array<MatchModel?> {
            return arrayOfNulls(size)
        }
    }
}
