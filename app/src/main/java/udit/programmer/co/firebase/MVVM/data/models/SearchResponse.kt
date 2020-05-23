package udit.programmer.co.firebase.MVVM.data.models

import udit.programmer.co.firebase.MVVM.data.models.User

data class SearchResponse(
    val totalCount: Int? = null,
    val incompleteResults: Boolean? = null,
    val items: List<User>? = null
)