package com.example.sportapp.support2026.features.soccer.data.dto.mappers

import com.example.sportapp.support2026.features.soccer.data.dto.GoalDto
import com.example.sportapp.support2026.features.soccer.data.dto.GoalGetterDto
import com.example.sportapp.support2026.features.soccer.domain.entities.Goal
import com.example.sportapp.support2026.features.soccer.domain.entities.GoalGetter

fun GoalGetterDto.toDomain(): GoalGetter {
    return GoalGetter(
        goalGetterId = this.goalGetterId ?: 0,
        goalGetterName = this.goalGetterName.orEmpty(),
        goalCount = this.goalCount ?: 0
    )
}

fun Goal.toDto(): GoalDto {
    return GoalDto(
        goalID = this.goalID,
        scoreTeam1 = this.scoreTeam1,
        scoreTeam2 = this.scoreTeam2,
        matchMinute = this.matchMinute,
        goalGetterID = this.goalGetterID,
        goalGetterName = this.goalGetterName,
        scoringTeamId = this.scoringTeamId,
        isPenalty = this.isPenalty,
        isOwnGoal = this.isOwnGoal,
        isOvertime = this.isOvertime,
        comment = this.comment
    )
}