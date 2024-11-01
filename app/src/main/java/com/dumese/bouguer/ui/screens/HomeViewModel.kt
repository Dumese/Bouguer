package com.dumese.bouguer.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumese.bouguer.BuildConfig.QWeather_key
import com.dumese.bouguer.models.Location
import com.dumese.bouguer.models.Weather
import com.dumese.bouguer.repository.LocationRepo
import com.dumese.bouguer.repository.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo,
    private val locationRepo: LocationRepo
) : ViewModel() {
    private val _location = MutableStateFlow(Location())
    val location = _location.asStateFlow()

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {
        locationRepo.getLocation { location ->
            _location.value = location
            viewModelScope.launch {
                val now = async {
                    weatherRepo.getNow(
                        key = QWeather_key,
                        location = "${_location.value.longitude},${_location.value.latitude}"
                    )
                }
                val daily = async {
                    weatherRepo.getDaily(
                        key = QWeather_key,
                        location = "${_location.value.longitude},${_location.value.latitude}"
                    )
                }
                val hourly = async {
                    weatherRepo.getHourly(
                        key = QWeather_key,
                        location = "${_location.value.longitude},${_location.value.latitude}"
                    )
                }

                val nowData = now.await()
                val dailyData = daily.await()
                val hourlyData = hourly.await()

                if (dailyData != null && nowData != null && hourlyData != null) {
                    _weather.value = Weather(
                        daily = dailyData.daily,
                        now = nowData.now,
                        hourly = hourlyData.hourly
                    )
                }
            }
        }
    }
}