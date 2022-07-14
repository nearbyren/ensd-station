package ejiayou.station.module

import androidx.lifecycle.MutableLiveData
import ejiayou.common.module.base.BaseAppViewModel
import ejiayou.common.module.dto.BannerBean

/**
 * @author: lr
 * @created on: 2022/7/10 4:01 下午
 * @description:
 */
class StationOilViewModel : BaseAppViewModel() {
    val articleList = MutableLiveData<BannerBean>()
    private val repo by lazy { MessageRepoImpl() }

    /**
     * 获取文章列表
     */
    fun getArticleList() {
        launchOnUI {
            showLoadingView(true)
            repo.getArticleList(1, 20)
                .onCompletion { showLoadingView(false) }
                .onSuccess {
                    showToast("获取文章列表11")
                }
                .onFailure { code, msg ->
                    showToast(msg ?: "获取文章列表失败")
                }
                .onCatch { error ->
                    showToast(error.errorMsg)
                }
        }
    }
}