package planner.fitness.com.fitnessplanner.data

import org.json.JSONArray
import org.json.JSONObject

class FPOrganisation(var state: String = "",
                     val cities: ArrayList<String>,
                     var name: String = "",
                     var serverUrl: String?) {

    companion object {

        @JvmStatic
        fun createOrganisation(json: JSONObject): FPOrganisation {
            val o = FPOrganisation("", arrayListOf(), "", null)

            o.state = json.getString("state")

            val cities = json.getString("city")
            if (cities != null && cities.matches(Regex.fromLiteral("^\\[.*\\]$"))) {
                val city = JSONArray(cities)
                val length = city.length()
                for (index in 0..(length - 1)) {
                    o.cities.add(city.getString(index))
                }
            } else if (cities != null) {
                o.cities.add(cities)
            }

            o.name = json.getString("organisation")
            o.serverUrl = json.getString("server")

            return o
        }

    }

}