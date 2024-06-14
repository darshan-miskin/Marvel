package com.marveluniverse.www.screens.home.domain.response.charactermodels

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.marveluniverse.www.screens.common.TAG_API
import com.marveluniverse.www.screens.common.domain.response.BaseResult
import java.io.Serializable

@Entity
data class CharacterModel(
    @PrimaryKey
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("modified") var modified: String? = null,
    @SerializedName("thumbnail") var thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI") var resourceURI: String? = null,
    @SerializedName("comics") var comics: Comics? = Comics(),
//    @SerializedName("series") var series: Series? = Series(),
//    @SerializedName("stories") var stories: Stories? = Stories(),
//    @SerializedName("events") var events: Events? = Events(),
//    @SerializedName("urls") var urls: ArrayList<Urls> = arrayListOf()

) : BaseResult(), Serializable {
    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String = ""
        get() = "${thumbnail?.path}.${thumbnail?.extension}"

    @ColumnInfo(name = "isComplete")
    var isComplete: Boolean = false
        get() = thumbnail?.path?.lowercase()
        ?.contains("image_not_available") == false && description?.trim()?.isEmpty() == false

    companion object {
        fun List<CharacterModel>.print(stringName: String = "data") {
            var str = ""
            for (character in this) {
                str += character.name + ","
            }
            Log.d(TAG_API, "$stringName: $str")
        }
    }
}

class CharacterModelDiffUtil : DiffUtil.ItemCallback<CharacterModel>(){
  override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
    return oldItem == newItem
  }
}