package com.example.vocab.data

import com.example.vocab.data.entities.GeneralWordDbModel
import com.example.vocab.data.entities.UserWordDbModel
import com.example.vocab.domain.entities.Word

class DictionaryMapper {

    fun mapUserWordEntityToDbModel(word: Word) = UserWordDbModel(
        id = word.id,
        value = word.value,
        translate = word.translate,
        thematics = word.thematics
    )

    fun mapDbModelToUserWordEntity(userWordDbModel: UserWordDbModel) = Word(
        id = userWordDbModel.id,
        value = userWordDbModel.value,
        translate = userWordDbModel.translate,
        thematics = userWordDbModel.thematics
    )

    fun mapDbModelToGeneralWordEntity(generalWordDbModel: GeneralWordDbModel) = Word(
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