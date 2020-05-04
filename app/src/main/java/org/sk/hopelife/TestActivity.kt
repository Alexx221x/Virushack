package org.sk.hopelife

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.sk.hopelife.conditions.EmergancyRedActivity
import org.sk.hopelife.conditions.EmergancyYellowActivity
import org.sk.hopelife.conditions.GoodActivity
import org.sk.hopelife.conditions.RecomendationActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TestActivity : AppCompatActivity() {

    val API_HOST = "http://hopelife.rugrisser.ru/"
    val test = mutableMapOf(
        "sid" to "covid19",
        "name" to "COVID-19",
        "questions" to arrayListOf(
            mutableMapOf(
                "text" to "Измерьте температуру в подмышечной впадине и выберите вариант ответа. Температура:",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "больше 37.6",
                        "score" to 100
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "37.5-37.3",
                        "score" to 70
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "37.2-37.0",
                        "score" to 20
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Меньше 36.9",
                        "score" to 0
                    )
                )
            ),
            mutableMapOf(
                "text" to "Выберите вариант ответа:",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Отсутствие дыхания, потеря сознания, посинение кожных покровов, невозможность улыбнуться, назвать свое имя или произнести простое предложение",
                        "score" to 100
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Ничего из вышеперечисленного",
                        "score" to 0
                    )
                )
            ),
            mutableMapOf(
                "text" to "Есть ли у Вас кашель, который недавно появился или же усилился?",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Частый кашель (возможно небольшое количество крови) или ощущение удушья",
                        "score" to 70
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Частый кашель (сухой или с небольшим количеством мокроты) ",
                        "score" to 40
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Кашель (сухой или с небольшим количеством мокроты)",
                        "score" to 30
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Ничего из вышеперечисленного",
                        "score" to 0
                    )
                )
            ),
            mutableMapOf(
                "text" to "Чувствуйте ли Вы ощущение удушья, которое недавно появилось? Если есть пульсоксиметр, то выберите значение, ссылаясь также на самочувствие: ",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Да, появление одышки, ощущение удушья (или меньше 89) ",
                        "score" to 60
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Появление одышки (или 89-92)  ",
                        "score" to 50
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Трудности с дыханием, ощущение заложенности в грудной клетке (или 92-94) ",
                        "score" to 70
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Ничего из вышеперечисленного (или выше 95) ",
                        "score" to 0
                    )
                )
            ),
            mutableMapOf(
                "text" to "Выберите вариант ответа:",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Сильная слабость с проблемами поднятия любой части тела/Проблемы с открытием век./Заторможенная реакция на вопросы и ответы от собеседника/Полное нарушение краткосрочной памяти и концентрации, невменяемость ",
                        "score" to 100
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Быстрая утомляемость и заторможенная реакция на вопросы и ответы от собеседника, нарастающее недомогание",
                        "score" to 50
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Присутствует усталость на фоне с головной болью, общее недомогание",
                        "score" to 10
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Присутствует слабая усталость без симптомов или со слабой головой  болью; нет усталости",
                        "score" to 0
                    )
                )
            ),
            mutableMapOf(
                "text" to "Оцените свое обоняние за последнее время:",
                "answers" to arrayListOf(
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Не чувствую никаких запахов с отсутствием заложенности носа ",
                        "score" to 100
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Не чувствую никаких запахов на фоне слабой заложенности носа ",
                        "score" to 30
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Понижение чувствительности обоняния на фоне заложенности носа ",
                        "score" to 5
                    ),
                    mutableMapOf(
                        "questionID" to 1,
                        "text" to "Нет изменений",
                        "score" to 0
                    )
                )
            )
        )
    )

    var score = 0
    var num = 0
    var all = 0
    lateinit var question_num: TextView
    lateinit var question: TextView
    lateinit var progress: ProgressBar
    lateinit var adapter: AnswersAdapter
    var scores = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val dis = intent.extras?.getString("dis")
        question_num = findViewById(R.id.q)
        question = findViewById(R.id.question)
        progress = findViewById(R.id.progress)

        val retrofit = Retrofit.Builder()
            .baseUrl(API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SurveyService::class.java)
        val call = service.get(dis)

        call.enqueue(object : Callback<SurveyResponse> {
            override fun onFailure(call: Call<SurveyResponse>, t: Throwable) {
                Log.d("Status", "ERROR")
                goToMenuActivity()
            }

            override fun onResponse(
                call: Call<SurveyResponse>,
                response: Response<SurveyResponse>
            ) {
                if (response.code() == 200) {
                    // получаем объект
                    Log.d("Status", "OK 200")
                } else {
                    Log.d("Status", "NOT FOUND 404")
                    goToMenuActivity()
                }
            }

        })
        
        val questions = test["questions"] as ArrayList<MutableMap<String, Any>>
        all = questions.size
        val text = questions[num].get("text").toString()
        val answers = prepareAnswers(questions[num].get("answers") as ArrayList<MutableMap<String, Any>>)
        adapter = AnswersAdapter(answers, this)
        setQuestion(text, answers)
        findViewById<ListView>(R.id.list).adapter = adapter

        findViewById<Button>(R.id.next).setOnClickListener {
            if (num + 1 < all){

                val choosed = adapter.choosedPosition
                if (choosed != -1){
                    score+= scores[choosed]
                    if (score >= 99){
                        val intent = Intent(this, EmergancyRedActivity::class.java)
                        val sp = application.applicationContext.getSharedPreferences("quiz", 0)
                        sp.edit().putBoolean("timer", true).apply()
                        startService()
                        this.finish()
                        startActivity(intent)
                    }

                    num++
                    val text_tmp = questions[num].get("text").toString()
                    val answers_tmp = questions[num].get("answers") as ArrayList<MutableMap<String, Any>>
                    setQuestion(text_tmp, prepareAnswers(answers_tmp))
                }
            }else{
                var intent: Intent
                val sp = application.applicationContext.getSharedPreferences("quiz", 0)
                if (score >= 70 && score <= 98){
                    intent = Intent(this, EmergancyYellowActivity::class.java)
                    sp.edit().putBoolean("timer", true).apply()
                }
                else if (score >= 20 && score <  70){
                    sp.edit().putBoolean("timer", true).apply()
                    intent = Intent(this, RecomendationActivity::class.java)
                }else{
                    sp.edit().putBoolean("timer", false).apply()
                    intent = Intent(this, GoodActivity::class.java)
                }
                startService()
                this.finish()
                startActivity(intent)

            }
        }
        findViewById<Button>(R.id.prev).setOnClickListener {
            if (num > 0){
                num--
                val text_tmp = questions[num].get("text").toString()
                val answers_tmp = questions[num].get("answers") as ArrayList<MutableMap<String, Any>>
                setQuestion(text_tmp, prepareAnswers(answers_tmp))
            }
        }
    }

    fun prepareAnswers(items: ArrayList<MutableMap<String, Any>>): ArrayList<String>{
        val list = ArrayList<String>()
        scores.clear()
        items.forEach {
            list.add(it["text"].toString())
            scores.add(it["score"].toString().toInt())
        }
        return list
    }

    fun setQuestion(questionStr: String, answers: ArrayList<String>){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progress.setProgress(100*(num+1)/all, true)
        }else{
            progress.progress = 100*(num+1)/all
        }
        question_num.text = "Вопрос ${num + 1} из $all"
        question.text = questionStr
        adapter.items = answers
        adapter.notifyDataSetChanged()
    }

    fun startService(){
        val jobService = ComponentName(this, DoTestService::class.java)
        val exerciseJobBuilder = JobInfo.Builder(0, jobService)
        exerciseJobBuilder.setMinimumLatency(TimeUnit.SECONDS.toMillis(1))
        exerciseJobBuilder.setOverrideDeadline(TimeUnit.SECONDS.toMillis(5))
        exerciseJobBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        exerciseJobBuilder.setRequiresDeviceIdle(false)
        exerciseJobBuilder.setRequiresCharging(false)
        exerciseJobBuilder.setBackoffCriteria(
            TimeUnit.SECONDS.toMillis(10),
            JobInfo.BACKOFF_POLICY_LINEAR
        )
        val jobScheduler = this.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(exerciseJobBuilder.build())
    }

    fun goToMenuActivity() {
        val intent = Intent(this@TestActivity, MenuActivity::class.java)
        // Может надо сюда ещё что-то дописать
    }

}
