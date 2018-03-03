package br.com.hugo.victor.architectureaacretrofit.repositories

import android.arch.lifecycle.LiveData
import br.com.hugo.victor.architectureaacretrofit.entities.EnderecoResponse

interface EnderecoRepository {

    fun buscarEndereco(cep: String): LiveData<EnderecoResponse>

}