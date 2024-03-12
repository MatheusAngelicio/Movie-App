package br.com.movieapp.core.util

import br.com.movieapp.BuildConfig

fun String?.toImageUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"
