package com.flexath.findit.news.domain.usecases.app_entry

import com.flexath.findit.news.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntryUseCase @Inject constructor(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}