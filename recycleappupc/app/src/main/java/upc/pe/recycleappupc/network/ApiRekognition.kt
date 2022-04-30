package upc.pe.recycleappupc.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRekognition {
    companion object{
        fun getInstance(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api-aws-rekognition.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit;
        }
    }
}