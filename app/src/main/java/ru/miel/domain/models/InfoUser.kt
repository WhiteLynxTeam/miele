package ru.miel.domain.models

data class InfoUser (
   val role : String,
   val full_name : String,
   val email : String,
   val phone : String?,
   val photo : String?,
   val office_name : String,
   val office_location : String,
   val department : String,
   val office_quota : String,
   val office_used_quota : String,
)