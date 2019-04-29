package com.example.pokedex

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(
    val Imagen:String = "N/A",
    val Nombre:String = "N/A",
    val Tipo:String = "N/A"
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Imagen)
        parcel.writeString(Nombre)
        parcel.writeString(Tipo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}