package ru.miel.data.network.dto.quotes

data class StatisticQuotesResponse (
   val month : String,
   val issued : Int,
   val invited : Int,
   val accepted : Int,
   val rejected : Int,
   val subtracted : Int,
)
