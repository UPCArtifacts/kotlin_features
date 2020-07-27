package com.benoitquenaudon.tvfoot.red.app.data.entity.search

import com.benoitquenaudon.tvfoot.red.util.TeamCode
import com.squareup.moshi.adapters.formatIso8601
import java.util.Date

data class Where( //#data_class,func_with_default_value
    private val startAt: Long,
    private val onlyBroadcasted: Boolean = false,
    private val teams: List<TeamCondition> = emptyList()
) {
  override fun toString(): String {
    val buffer = mutableListOf<String>()

    buffer.add(""""start-at":{"gte":"${Date(startAt).formatIso8601()}"}""") //#string_template
    buffer.add(""""deleted":{"neq":1}""")
    if (onlyBroadcasted) buffer.add(""""broadcasters":{"gt":[]}""")
    if (teams.isNotEmpty()) buffer.add(""""or":[${teams.joinToString(",")}]""") //#string_template

    return buffer.joinToString(prefix = "{", separator = ",", postfix = "}") //#func_call_with_named_arg
  }

  data class TeamCondition( //#data_class
      private val code: TeamCode
  ) {
    override fun toString() =
        """{"home-team.code":"$code"},{"away-team.code":"$code"}""" //#string_template
  }
}
