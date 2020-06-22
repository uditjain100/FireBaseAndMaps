package udit.programmer.co.firebase.FireBase

interface FirebaseLoadDone {
    fun onFirebaseLoadSuccess(EDMTQuizList: List<EDMTQuiz>)
    fun onFirebaseLoadFailed(message : String?)
}