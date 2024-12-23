package com.margarita_pekutovskaya.k_beautylab.data

import com.margarita_pekutovskaya.k_beautylab.data.model.CosmeticItem

class SampleDataSource : DataSource {

    private val cosmeticItemsList = listOf(
        CosmeticItem(
            name = "Toner",
            imageLink = "https://media.douglas.de/medias/pJN9iQ1103976-0-global.png?context=bWFzdGVyfGltYWdlc3wyODE5Mzl8aW1hZ2UvcG5nfGFHRmxMMmd5TkM4MU1EUTFPVGs1TmpVMU16STBOaTl3U2s0NWFWRXhNVEF6T1RjMlh6QmZaMnh2WW1Gc0xuQnVad3wyOWRjODM0NDlmMzRlMjg3YjY4NTgyZmE4ZGQ4OTE0YWY1OGE4ZTk4YzY5MzRhYzg4OGU0NDEwNGZmMWFlMDdk&grid=true&transparent=true&imPolicy=grayScaledtransparent&imdensity=1&imwidth=775",
            description = "Toner with centella for problem skin."
        ),
        CosmeticItem(
            name = "Serum",
            imageLink = "https://www.lovemycosmetic.de/media/image/a6/a4/07/Beauty_of_Joseon_Glow_Serum_30ml.jpg",
            description = "Beauty of Joseon Glow Serum Propolis + Niacinamide",
        ),
        CosmeticItem(
            name = "Sun Cream SPF50",
            imageLink = "https://sv4-cdn.stylevana.com/media/catalog/product/cache/88b7e9dfad912a7ad8c936475df40241/r/o/round-lab-birch-juice-moisturizing-sun-cream-50ml-453.jpg",
            description = "Birch Juice Moisturizing Sun Cream SPF50+ PA++++ - 50ml",
        ),
    )

    override fun getCosmeticItems(): List<CosmeticItem> {
        return cosmeticItemsList
    }
}