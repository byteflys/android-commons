package x.android.commons.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

object ANY {

    @OptIn(ExperimentalContracts::class)
    inline fun <T : Any> T?.isNull(): Boolean {
        contract {
            returns(true) implies (this@isNull == null)
            returns(false) implies (this@isNull != null)
        }
        return this == null
    }

    @OptIn(ExperimentalContracts::class)
    inline fun <T : Any> T?.isNotNull(): Boolean {
        contract {
            returns(true) implies (this@isNotNull != null)
            returns(false) implies (this@isNotNull == null)
        }
        return this != null
    }
}