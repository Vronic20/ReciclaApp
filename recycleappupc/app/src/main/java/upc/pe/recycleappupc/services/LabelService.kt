package upc.pe.recycleappupc.services

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import upc.pe.recycleappupc.models.Label

interface LabelService {
    @Multipart
    @POST("rekognition/detect-labels")
    fun detectLabels(@Part file: MultipartBody.Part): Call<List<Label>>
}
