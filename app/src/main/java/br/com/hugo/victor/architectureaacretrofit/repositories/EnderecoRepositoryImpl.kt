package br.com.hugo.victor.architectureaacretrofit.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import br.com.hugo.victor.architectureaacretrofit.api.EnderecoAPI
import br.com.hugo.victor.architectureaacretrofit.entities.Endereco
import br.com.hugo.victor.architectureaacretrofit.entities.EnderecoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnderecoRepositoryImpl : EnderecoRepository {

    private val enderecoAPI: EnderecoAPI

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://viacep.com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        enderecoAPI = retrofit.create(EnderecoAPI::class.java)
    }

    override fun buscarEndereco(cep: String): LiveData<EnderecoResponse> {

        val liveData = MutableLiveData<EnderecoResponse>()

        enderecoAPI.pesquisar(cep).enqueue(object : Callback<Endereco> {
            override fun onResponse(call: Call<Endereco>?, response: Response<Endereco>?) {
                liveData.value = EnderecoResponse(response?.body())
            }

            override fun onFailure(call: Call<Endereco>?, t: Throwable?) {
                liveData.value = EnderecoResponse(t!!)
            }

        })

        return liveData

    }

}