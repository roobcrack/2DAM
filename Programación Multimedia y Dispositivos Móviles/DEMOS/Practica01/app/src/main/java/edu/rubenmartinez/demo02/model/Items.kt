package edu.rubenmartinez.demo02.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Items(
    val id: Int, val title: String, val year: Int, val duration: Int, val genre: String, val director: String, val cover: String
) : Parcelable {
    companion object {
        val items: MutableList<Items> = mutableListOf()

        init {
            for (i in 900..2000
            ) {
                items.add(
                    Items(
                        i,
                        "Titulo Item",

                        2024,
                        200,
                        "Genero Item",
                        "Director Item",
                        "https://picsum.photos/200/200?image=$i"
                    )
                )
            }
        }
    }
}