package com.androiddemo.model

import java.io.Serializable

data class Movies(val title: String, val year: String, val director: String, val description: String, val images: String) : Serializable