package ru.miel.domain.models

data class InfoUser (
   val role : String? = null,
   val full_name : String,
   val email : String,
   val phone : String? = null,
   val photo : String? = null,
   val office_name : String,
   val office_location : String,
   val department : String,
   val office_quota : String? = null,
   val office_used_quota : String? = null,
)