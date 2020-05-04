package org.sk.hopelife

import com.google.gson.annotations.SerializedName

class SurveyResponse {
    @SerializedName("sid")
    var sid: String = ""
    @SerializedName("name")
    var name: String = ""
    @SerializedName("questions")
    var questions = ArrayList<Question>()
}

class Question {
    @SerializedName("text")
    var text: String = ""
    @SerializedName("answers")
    var answers = ArrayList<Answer>()
}

class Answer {
    @SerializedName("questionID")
    var questionID: Long = 0
    @SerializedName("text")
    var text: String = ""
    @SerializedName("score")
    var score: Int = 0
}