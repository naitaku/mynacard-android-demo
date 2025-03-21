package com.ishihata_tech.myna_card_demo.nfcproxy

interface IsoDepProxy : AutoCloseable {
    fun transceive(byteArray: ByteArray) : ByteArray
    fun connect()
    override fun close()
    var timeout: Int
}