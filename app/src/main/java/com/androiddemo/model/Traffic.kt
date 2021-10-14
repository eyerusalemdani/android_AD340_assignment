package com.androiddemo.model

import java.io.Serializable


data class Traffic (
    val Features: ArrayList<Feature>
) : Serializable {
    data class Feature(
        val PointCoordinate: ArrayList<String>,
        val Cameras: ArrayList<Camera>
    ) : Serializable {
        data class Camera(
            val Id: String,
            val Description: String,
            val ImageUrl: String,
            val Type: String,
        ): Serializable
    }
}