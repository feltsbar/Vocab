package com.example.vocab.data

import com.example.vocab.data.entities.GeneralWordDbModel
import com.example.vocab.data.entities.UserWordDbModel
import com.example.vocab.domain.entities.GeneralWord
import com.example.vocab.domain.entities.UserWord

class DictionaryMapper {

    fun mapUserWordEntityToDbModel(userWord: UserWord) = UserWordDbModel(
        id = userWord.id,
        value = userWord.value,
        translate = userWord.translate,
        thematics = userWord.thematics
    )

    fun mapDbModelToUserWordEntity(userWordDbModel: UserWordDbModel) = UserWord(
        id = userWordDbModel.id,
        value = userWordDbModel.value,
        translate = userWordDbModel.translate,
        thematics = userWordDbModel.thematics
    )

    fun mapDbModelToGeneralWordEntity(generalWordDbModel: GeneralWordDbModel) = GeneralWord(
        id = generalWordDbModel.id,
        value = generalWordDbModel.value,
        translate = generalWordDbModel.translate,
        thematics = generalWordDbModel.thematics
    )

    fun mapUserListDbModelToListEntity(list: List<UserWordDbModel>) = list.map {
        mapDbModelToUserWordEntity(it)
    }

    fun mapGeneralListDbModelToListEntity(list: List<GeneralWordDbModel>) = list.map {
        mapDbModelToGeneralWordEntity(it)
    }

}