package ejiayou.station.module

import com.google.gson.reflect.TypeToken
import ejiayou.common.module.api.response.InfoResponse
import ejiayou.common.module.api.response.ResponseHolder
import ejiayou.common.module.dto.BannerBean
import ejiayou.common.module.dto.MyBannerBean
import ejiayou.common.module.http.CorHttp

/**
 * @author: lr
 * @created on: 2022/7/10 8:17 下午
 * @description:
 */
class MessageRepoImpl : MessageRepo {
    private val URL_ARTICLE_LIST = "app/index/seckillAdLink" // 获取文章列表
    override suspend fun getArticleList(
        page_no: Int,
        page_size: Int


    ): ResponseHolder<MyBannerBean<BannerBean>> {
        return CorHttp.getInstance().getClient()
                .postJson(
                    url = URL_ARTICLE_LIST,
                    params = mapOf<String, String>(
                        "logLongitude" to "113.922152",
                        "logCityName" to "深圳市",
                        "latitude" to "22.487379",
                        "telephone" to "15236183971",
                        "version" to "6.5.6",
                        "userId" to "2199123",
                        "car_type" to "5",
                        "versionId" to "6.5.6",
                        "logLatitude" to "22.487379",
                        "carType" to "5",
                        "user_id" to "2199123",
                        "os_type" to "2",
                        "osType" to "2",
                        "channelName" to "",
                        "versionBuild" to "205",
                        "longitude" to "113.922152",
                        "oilId" to "1"

                    ),
                    type = object : TypeToken<InfoResponse<MyBannerBean<BannerBean>>>() {}.type,
                )

    }
}