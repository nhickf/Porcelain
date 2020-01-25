package com.nhick.porcelain.repository

object DummyDatabase {

    val listOfArticles get() = dummyArticles()
    val listOfBanner get() = dummyBanners()


    private fun dummyArticles() : ArrayList<Articles>{

        val arrayList = ArrayList<Articles>()


        arrayList.add(Articles(
            "Lastest Galaxy S20 leak reveals potential pricing",
            "Another leak of Samsung's upcoming Galaxy S20 series details when you'll probably" +
                    "be able to buy the phone and price you'll end up",
            "21 JAN 2020",
            "https://www.extremetech.com/wp-content/uploads/2020/01/667601-samsung-galaxy-note-10.jpg"
        ))

        arrayList.add(Articles(
            "New PS5 rumor teases February 5 launch event and new features",
            "If this 4chan rumor reposted on Reddit is true, we may have all the " +
                    "details about Sony Playstation 5 by the next month.",
            "21 JAN 2020",
            "https://cdn.images.express.co.uk/img/dynamic/143/590x/PS5-1231016.jpg?r=1579549389223"
        ))

        arrayList.add(Articles(
            "These Huawei smartphones will get the Android 10 and EMUI 10 update",
            "Huawei has provided a timeline for the Android 10/EMUI 10 update " +
                    "rollout for its devices. The Huawei P30 and Mate 20 will get update",
            "21 JAN 2020",
            "https://mondrian.mashable.com/2019%252F04%252F05%252Fb3%252Fb6c057a7ead244379c374111c6ce8ea7.29ac3.jpg%252F1200x630.jpg?signature=gU3pO35poBWj0YmdPI9uOhlcNy8="
        ))

        arrayList.add(Articles(
            "PAL to revive Cebu-Los Angeles nonstop flights starting May",
            "The country's flag carrier will soon to be flying passengers from Queen's City of the " +
                    "south to US",
            "21 JAN 2020",
            "https://www.bworldonline.com/wp-content/uploads/2018/07/PAL-071818.jpg"
        ))

        return arrayList
    }


    private fun dummyBanners() : ArrayList<Banner>{
        val arrayList = ArrayList<Banner>()

        arrayList.add(
            Banner("Weather updates for today","Expect showers Saturday Afternoon",
            "Temperature and Relative Humidity " +
                    "EXTREMES FOR THE 24-HOUR PERIOD ENDING AT 8:00 PM YESTERDAY " +
                    "(Recorded at PAGASA Weather Station, Science Garden, Diliman, Quezon City)")
        )
        arrayList.add(Banner("Forecast Weather Conditions","Cloudy skies with isolated rainshowers",
            "Possible flash floods or landslides during moderate to at times heavy rains"))
        arrayList.add(Banner("Quezon City Weather Forecast"," Local time in Quezon City is 3:14:27 PM +08.",
            "Quezon City Weather Forecast. Providing a local 3 hourly Quezon City weather forecast of rain, sun, wind, humidity and temperature. The Long-range 10 day forecast also includes detail for Quezon City weather today. Live weather reports from Quezon City weather stations and weather warnings that include risk of thunder, high UV index and forecast gales. See the links below the 10-day Quezon City weather forecast table for other cities and towns nearby along with weather conditions for local outdoor activities."))



        return arrayList
    }

}