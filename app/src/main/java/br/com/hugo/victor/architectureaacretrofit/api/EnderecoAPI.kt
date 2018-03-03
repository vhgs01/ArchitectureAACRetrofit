package br.com.hugo.victor.architectureaacretrofit.api

import br.com.hugo.victor.architectureaacretrofit.entities.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {

    @GET("/ws/{cep}/json/")
    fun pesquisar(@Path("cep") cep: String): Call<Endereco>

}