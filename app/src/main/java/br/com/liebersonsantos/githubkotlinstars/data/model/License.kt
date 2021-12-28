package br.com.liebersonsantos.githubkotlinstars.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
data class License (
    @SerializedName("key")
    val key : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("spdx_id")
    val spdxId : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("node_id")
    val nodeId : String
): Serializable