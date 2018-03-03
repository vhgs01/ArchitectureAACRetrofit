package br.com.hugo.victor.architectureaacretrofit.ui.mainscreen

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import br.com.hugo.victor.architectureaacretrofit.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        btPesquisar.setOnClickListener {
            mainViewModel.pesquisarEndereco(etCEP.text.toString())
        }

        mainViewModel.apiResponse.observe(this, Observer { apiResponse ->
            if (apiResponse?.erro == null) {
                tvResultado.text =
                        "${apiResponse?.endereco?.logradouro} - " +
                                "${apiResponse?.endereco?.bairro} -  " +
                                "${apiResponse?.endereco?.localidade}/" +
                                "${apiResponse?.endereco?.uf}"
            } else {
                Log.i("TAG", "ERRO: ${apiResponse.erro}")
            }
        })

        mainViewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading!!) {
                llLoading.visibility = View.VISIBLE
            } else {
                llLoading.visibility = View.GONE
                Thread.sleep(1000)
            }
        })

    }

}
