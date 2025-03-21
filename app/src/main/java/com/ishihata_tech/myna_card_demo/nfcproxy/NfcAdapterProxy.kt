package com.ishihata_tech.myna_card_demo.nfcproxy

interface NfcAdapterProxy {
    fun enableReaderMode(
        callback : (isoDepProxy: IsoDepProxy) -> Unit,
    )

    fun disableReaderMode()
}