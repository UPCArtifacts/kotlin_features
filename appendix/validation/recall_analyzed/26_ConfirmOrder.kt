package org.fossasia.openevent.general.order

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("order")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class ConfirmOrder( //#data_class,func_with_default_value
    @Id
    val id: String,
    val status: String? = null,
    val orderNotes: String? = null
)
