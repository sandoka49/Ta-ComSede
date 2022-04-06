package com.sandoval.t_com_sede

import android.app.TimePickerDialog
import android.content.Intent
import android.icu.number.NumberFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.sandoval.t_com_sede.databinding.ActivityMainBinding
import com.sandoval.t_com_sede.model.CalcularIngestaoDiaria
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private lateinit var edit_peso: EditText
    private lateinit var edit_idade:EditText
    private lateinit var btn_calcular:Button
    private lateinit var txt_resultml:TextView
    private lateinit var  ic_cleanUp: ImageView
    private lateinit var  bt_lembrete: Button
    private lateinit var  bt_alarme : Button
    private lateinit var  txt_hora  : TextView
    private lateinit var  txt_minutos  : TextView


    private lateinit var calcularIngestaoDiaria :CalcularIngestaoDiaria
    private var resultadoFinalML = 0.0

        private lateinit var timePickerDialog: TimePickerDialog
        private lateinit var calendario:Calendar
        var horaAtual = 0
        var minutosAtuais = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
       // IniciarComponentes()
        calcularIngestaoDiaria = CalcularIngestaoDiaria()


        val btn_calcular=binding.btnCalcular

        btn_calcular.setOnClickListener{
            if(binding.editPeso.text.toString().isEmpty()){

                Toast.makeText(this,R.string.toats_informe_peso, Toast.LENGTH_SHORT).show()
            }else if(binding.editIdade.text.toString().isEmpty()){

                    Toast.makeText(this,R.string.toats_informe_idade, Toast.LENGTH_SHORT).show()
                }else{
                    val peso = binding.editPeso.text.toString().toDouble()
                    val idade = binding.editIdade.text.toString().toInt()
                    calcularIngestaoDiaria.CalcularTotalML(peso,idade)
                    resultadoFinalML = calcularIngestaoDiaria.ResultadoTotalML()
                   val formatar = NumberFormat.getNumberInstance(Locale ("pt","BR"))
                   formatar.isGroupingUsed
                     binding.txtResultml.text = formatar.format(resultadoFinalML) +" " + "ml"

                }
        }
        binding.icCleanUp.setOnClickListener(){
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle(R.string.dialog_titulo)
                .setMessage(R.string.dialog_desc)
                .setPositiveButton("OK",{dialogInterface,i->
                    binding.editPeso.setText("")
                    binding.editIdade.setText("")
                    binding.txtResultml.text = ""
                })
                    alertDialog.setNegativeButton("Cancelar",{dialogInteface,i->

                    })
            val dialog = alertDialog.create()
            dialog.show()
        }

        binding.btnDefinirLembrete.setOnClickListener{

            calendario = Calendar.getInstance()
            horaAtual =  calendario.get(Calendar.HOUR_OF_DAY)
            minutosAtuais =  calendario.get(Calendar.MINUTE)
            timePickerDialog = TimePickerDialog(this,{timePicker:TimePicker,hourOfDay:Int,minutes: Int->
             binding.txtHora.text = String.format("%02d",hourOfDay)
             binding.txtMinutos.text = String.format("%02d",minutes)

            },horaAtual,minutosAtuais,true)
            timePickerDialog.show()

        }

        binding.btnDefinirAlarme.setOnClickListener{
            if (!binding.txtHora.toString().isEmpty() && !binding.txtMinutos.toString().isEmpty()){
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR,binding.txtHora.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MINUTES,binding.txtMinutos.text.toString().toInt())
                intent.putExtra(AlarmClock.EXTRA_MESSAGE,getString(R.string.alarme_messagem))
                startActivity(intent)
                if (intent.resolveActivity(packageManager) !=null){
                    startActivity(intent)
                }
            }
        }



        }
       /* private fun IniciarComponentes() {

        edit_peso = findViewById(R.id.edit_peso)
        edit_idade=findViewById(R.id.edit_idade)
        btn_calcular=findViewById(R.id.btn_calcular)
        txt_resultml = findViewById(R.id.txt_resultml)
        ic_cleanUp = findViewById(R.id.ic_cleanUp)
        bt_lembrete = findViewById(R.id.btn_definir_lembrete)
        bt_alarme = findViewById(R.id.btn_definir_Alarme)
        txt_hora = findViewById(R.id.txt_hora)
        txt_minutos = findViewById(R.id.txt_minutos)

    } */
    }

