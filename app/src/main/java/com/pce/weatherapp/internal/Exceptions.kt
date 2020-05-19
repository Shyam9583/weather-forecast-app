package com.pce.weatherapp.internal

import java.io.IOException

class NoConnectionException : IOException()

class LocationPermissionNotGrantedException : Exception()