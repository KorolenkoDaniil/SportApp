package com.example.sportapp.support2026.features.soccer.domain.useCases

import com.example.sportapp.support2026.features.soccer.domain.repository.SoccerDomainRepository
import javax.inject.Inject

class GetMatchByIdUseCase @Inject constructor(
    private val repository: SoccerDomainRepository
) {

}