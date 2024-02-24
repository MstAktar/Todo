package com.example.todo.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

data class Success(val todos:List<Todo>)

data class Todo(
    var userId: Int,
    var id: Int,
    var title: String,
    var completed: Boolean
)

const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface TodosApi{
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object{
        var todosServices: TodosApi? = null

        fun getInstance(): TodosApi{
            if(todosServices === null){
                todosServices = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(TodosApi::class.java)
            }
            println("From model $todosServices")
            return todosServices!!
        }
    }

}
