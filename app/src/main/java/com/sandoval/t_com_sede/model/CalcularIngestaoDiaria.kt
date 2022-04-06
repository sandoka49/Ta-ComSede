package com.sandoval.t_com_sede.model

class CalcularIngestaoDiaria{

    private val ML_JOVEM = 40.0
    private val ML_ADULTO = 35.0
    private val ML_IDOSO = 30.0
    private val ML_MAISDE66 = 25.0

    private var resultadoML = 0.0
    private var totalML = 0.0

    fun CalcularTotalML(peso: Double , idade:Int){
        if(idade<=17){
            resultadoML=peso * ML_JOVEM
            totalML =resultadoML
        }else if(idade<=55){
            resultadoML=peso * ML_ADULTO
            totalML =resultadoML

        }else if(idade<=65){
            resultadoML=peso * ML_IDOSO
            totalML =resultadoML

        }else{
            resultadoML=peso * ML_MAISDE66
            totalML =resultadoML
        }

    }
    fun ResultadoTotalML(): Double{
        return totalML
    }

}