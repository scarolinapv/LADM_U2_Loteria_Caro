package com.example.ladm_u2_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ladm_u2_loteria.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val act =this
    var bonche = ArrayList<Int>()
    var scope= CoroutineScope(Job() + Dispatchers.Main)
    var anda=true
    var ind=1
    var ppa =1
    var cart = 54 ///////////////// BORRAR NO SIRVE :'(
    var voz: MediaPlayer?=null
    var para=false
    lateinit var binding : ActivityMainBinding

    val cartaAudio = arrayOf(
        R.raw.ca1,
        R.raw.ca2,
        R.raw.ca3,
        R.raw.ca4,
        R.raw.ca5,
        R.raw.ca6,
        R.raw.ca7,
        R.raw.ca8,
        R.raw.ca9,
        R.raw.ca10,
        R.raw.ca11,
        R.raw.ca12,
        R.raw.ca13,
        R.raw.ca14,
        R.raw.ca15,
        R.raw.ca16,
        R.raw.ca17,
        R.raw.ca18,
        R.raw.ca19,
        R.raw.ca20,
        R.raw.ca21,
        R.raw.ca22,
        R.raw.ca23,
        R.raw.ca24,
        R.raw.ca25,
        R.raw.ca26,
        R.raw.ca27,
        R.raw.ca28,
        R.raw.ca29,
        R.raw.ca30,
        R.raw.ca31,
        R.raw.ca32,
        R.raw.ca33,
        R.raw.ca34,
        R.raw.ca35,
        R.raw.ca36,
        R.raw.ca37,
        R.raw.ca38,
        R.raw.ca39,
        R.raw.ca40,
        R.raw.ca41,
        R.raw.ca42,
        R.raw.ca43,
        R.raw.ca44,
        R.raw.ca45,
        R.raw.ca46,
        R.raw.ca47,
        R.raw.ca48,
        R.raw.ca49,
        R.raw.ca50,
        R.raw.ca51,
        R.raw.ca52,
        R.raw.ca53,
        R.raw.ca54)

    val cartas = arrayOf(
        R.drawable.c1,
        R.drawable.c2,
        R.drawable.c3,
        R.drawable.c4,
        R.drawable.c5,
        R.drawable.c6,
        R.drawable.c7,
        R.drawable.c8,
        R.drawable.c9,
        R.drawable.c10,
        R.drawable.c11,
        R.drawable.c12,
        R.drawable.c13,
        R.drawable.c14,
        R.drawable.c15,
        R.drawable.c16,
        R.drawable.c17,
        R.drawable.c18,
        R.drawable.c19,
        R.drawable.c20,
        R.drawable.c21,
        R.drawable.c22,
        R.drawable.c23,
        R.drawable.c24,
        R.drawable.c25,
        R.drawable.c26,
        R.drawable.c27,
        R.drawable.c28,
        R.drawable.c29,
        R.drawable.c30,
        R.drawable.c31,
        R.drawable.c32,
        R.drawable.c33,
        R.drawable.c34,
        R.drawable.c35,
        R.drawable.c36,
        R.drawable.c37,
        R.drawable.c38,
        R.drawable.c39,
        R.drawable.c40,
        R.drawable.c41,
        R.drawable.c42,
        R.drawable.c43,
        R.drawable.c44,
        R.drawable.c45,
        R.drawable.c46,
        R.drawable.c47,
        R.drawable.c48,
        R.drawable.c49,
        R.drawable.c50,
        R.drawable.c51,
        R.drawable.c52,
        R.drawable.c53,
        R.drawable.c54)


    override fun onCreate(savedInstanceState: Bundle?) {

        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.iniciar.setOnClickListener{
            anda=true
            ppa=1

            val jobCorrutina = scope.launch(EmptyCoroutineContext, CoroutineStart.LAZY) {
                while (anda) {
                    (0..cartaAudio.size).forEach() {// 54 cartas
                        if (para) { //pause
                            delay(3000)
                        } else {
                            try {
                                voz = MediaPlayer.create(act, cartaAudio[bonche[it]])
                            } catch (e: Exception) {
                                //Ya caminé :'(
                            }
                            try {
                                runOnUiThread {
                                    act.binding.carta.setImageResource(cartas[bonche[it]])
                                    voz?.start()
                                }
                                delay(3000)

                            } catch (e: Exception) {
                                //Ya caminé :'( x2
                            }
                            voz?.release()
                        }
                    }
                    anda = false
                    ind++
                    delay(3000)
                }
            }
            if (anda == true) {
                mezclar()
                jobCorrutina.start()

                binding.iniciar.isEnabled = false
                binding.buenas.isEnabled = true
                binding.terminar.isEnabled = true
                return@setOnClickListener
            }
        }

        binding.buenas.setOnClickListener{
            if (!para) {
                para = true
            }
            //------------------------------------------------------
                if(para){
                    binding.terminar.setOnClickListener{
                        para=false
                        binding.iniciar.isEnabled = true
                        return@setOnClickListener
                    }
                }
            //------------------------------------------------------
        }
    }


/*
    fun mezclar(){
        bonche = ArrayList<Int>()
        //val distinct = bonche.distinct().toList()
        for (m in cartaAudio){
            bonche.add(Random.nextInt(cart))
            cart--
        }
    }
*/
//--------------------------------------------------------------------------REVISAR


    fun mezclar(){
        bonche = ArrayList<Int>()
        for (m in cartaAudio){
            bonche.add(azar(bonche))
        }
    }

    fun azar(z:ArrayList<Int>):Int{
        var dig = Random.nextInt(54)
        while (!z.contains(dig)){ return dig }
        return  azar(z)
    }

}