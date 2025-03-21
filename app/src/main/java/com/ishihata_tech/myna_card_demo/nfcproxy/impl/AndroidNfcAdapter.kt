package com.ishihata_tech.myna_card_demo.nfcproxy.impl

import android.app.Activity
import android.nfc.NfcAdapter
import android.content.Context
import android.nfc.Tag
import android.nfc.tech.IsoDep
import com.ishihata_tech.myna_card_demo.nfcproxy.IsoDepProxy
import com.ishihata_tech.myna_card_demo.nfcproxy.NfcAdapterProxy

class AndroidNfcAdapter(context: Context, private val activity: Activity) : NfcAdapterProxy {
    private val nfcAdapter = NfcAdapter.getDefaultAdapter(context)

    override fun enableReaderMode(callback: (IsoDepProxy) -> Unit) =
        nfcAdapter.enableReaderMode(
            activity,
            { tag -> callback(AndroidIsoDep(tag)) },
            NfcAdapter.FLAG_READER_NFC_B or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )

    override fun disableReaderMode() =
        nfcAdapter.disableReaderMode(activity)


    private class AndroidIsoDep(tag: Tag?) : IsoDepProxy {
        private val isoDep = IsoDep.get(tag)

        override fun transceive(byteArray: ByteArray): ByteArray = isoDep.transceive(byteArray)

        override fun connect() = isoDep.connect()

        override fun close() = isoDep.close()

        override var timeout: Int
            get() = isoDep.timeout
            set(value) {
                isoDep.timeout = value
            }
    }
}