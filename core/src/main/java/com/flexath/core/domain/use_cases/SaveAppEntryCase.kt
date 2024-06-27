package com.flexath.core.domain.use_cases

import com.flexath.core.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntryCase @Inject constructor(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}