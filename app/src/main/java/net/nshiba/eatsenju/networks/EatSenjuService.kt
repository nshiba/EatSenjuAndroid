package net.nshiba.eatsenju.networks

import android.text.method.SingleLineTransformationMethod

interface EatSenjuService {
    @GET("macros/echo?user_content_key=o_cTUl6CMCaW0Sg2DeMpfjBvWDI6o67Nut8E0V_zKbcg8P8GkYNkpGoTJdP7Mz57Gf8Bgj9AANDal0yq0aW97sQMbkfg5V-Jm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnNqPwCBRGAH-PcBEmKBDalnk0NgqNnBCCMaefBU1k4FbNaVrruTrUcrw-jBN-VFVfNveWPraiRQB&lib=MPuixP1oMDpjlDIdT_bqVlAwJX0hRdcSS")
    fun stores() : Single<Storesk>
}
