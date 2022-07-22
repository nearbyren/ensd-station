package ejiayou.station.module

import androidx.annotation.IntRange
import ejiayou.common.module.api.response.ResponseHolder
import ejiayou.common.module.dto.TestBannerBean
import ejiayou.common.module.dto.TestMyBannerBean

/**
 * @author: lr
 * @created on: 2022/7/10 8:14 下午
 * @description:
 */
interface MessageRepo {
  /**
   * 获取文章列表
   */
  suspend fun getArticleList(
    @IntRange(from = 1) page_no: Int = 1,
    page_size: Int = 20
  ): ResponseHolder<TestMyBannerBean<TestBannerBean>>
}
